package com.elsevier.bts.regional.ecom.framework.hooks;

import com.browserstack.local.Local;
import com.elsevier.bts.regional.ecom.framework.FrameworkApplicationContext;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.base.LocalDriverContext;
import com.elsevier.bts.regional.ecom.framework.base.WebDriverInitialisation;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.lambdatest.tunnel.Tunnel;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.HashMap;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.zaproxy.clientapi.core.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

@Slf4j
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = FrameworkApplicationContext.class)
public class Hooks {

    @Autowired
    private WebDriverInitialisation webDriverInitialisation;

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    // ANSI escape codes for colors
    private String reset = "\u001B[0m";
    private String red = "\u001B[31m";
    private String green = "\u001B[32m";
    private String blue = "\u001B[34m";
    private ClientApi zapApi;
    private Tunnel tunnel;
    private Local bsLocal;
    @Getter
    private String tunnelName = Instant.now().toString();
    @Getter
    private String buildName;
    //    private String seleniumTestId;
    @Getter
    private String smartUIBuildName;
    @Getter
    private String smartUIProjectName;
    private String testStatus;

    @Before(order = 0)
    public void ScenarioNameBefore(Scenario scenario) {
        System.out.println(blue + "------------------------------------------------------------------------------------------");
        System.out.println("STARTED TEST - " + scenario.getName());
        System.out.println("------------------------------------------------------------------------------------------" + reset);
    }

    @Before(order = 0)
    public void InitializeWebDriver(Scenario scenario) throws Exception {
        log.debug("- Initialise WebDriver - BEFORE FEATURE hook");
        System.out.println("===============> Thread ID: " + Thread.currentThread().getId());
        if (BSModeEnabled()) {
            log.debug("- BrowserStack Tunnel - BEFORE FEATURE hook");
            buildName = System.getenv("BROWSERSTACK_BUILD_NAME") != null ? System.getenv("BUILD_TAG") : "Local BS Run: " + System.getenv("BROWSERSTACK_USERNAME");
//            buildName = System.getenv("BUILD_TAG") != null ? System.getenv("BUILD_TAG") : "Local BS Run: " + System.getenv("BROWSERSTACK_USERNAME");
            HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
            bsLocalArgs.put("key", System.getenv("BROWSERSTACK_ACCESS_KEY"));
            bsLocalArgs.put("localIdentifier", tunnelName);
            bsLocal = new Local();
            bsLocal.start(bsLocalArgs);
            System.out.println("bsLocal.isRunning(): " + bsLocal.isRunning());
        } else if (LTModeEnabled()) {
            log.debug("- LambdaTest Tunnel - BEFORE FEATURE hook");
            buildName = System.getenv("BUILD_TAG") != null ? System.getenv("BUILD_TAG") : "Local LT Run: " + System.getenv("LT_USERNAME");
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("user", System.getenv("LT_USERNAME"));
            options.put("key", System.getenv("LT_ACCESS_KEY"));
            options.put("tunnelName", tunnelName);
            tunnel = new Tunnel();
            tunnel.start(options);
        }
        smartUIBuildName = scenario.getName() + " - " + Instant.now().toString();
        smartUIProjectName = scenario.getSourceTagNames().iterator().next().replaceAll("\\@", "");

//        System.out.println("scenario id: " + scenario.getId());

        webDriverInitialisation.initialiseWebDriverWithFrameworkSettings(buildName, scenario.getName(), tunnelName, smartUIBuildName, smartUIProjectName);
        if (frameworkConfigurationService.getZAP_scan_enabled()) {
            zapApi = new ClientApi(frameworkConfigurationService.getZAP_ADDRESS(), frameworkConfigurationService.getZAP_PORT(), frameworkConfigurationService.getZAP_API_KEY());
        }
    }

    @Before(order = 1)
    public void MaximizeBrowserWindow() {
        if (!IsMobilePlatform()) {
            log.debug("- Maximize browser window - BEFORE FEATURE hook");
            LocalDriverContext.getWebDriver().manage().window().maximize();
        }
    }

    @Before(order = 4)
    public void GetPublicSessionLink() throws ParseException {
        if (BSModeEnabled()) {
            log.debug("- BrowserStack public session URL - BEFORE FEATURE hook");
            JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
            Object response = jsExecutor.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
//            System.out.println(response);
            JSONObject json = (JSONObject) new JSONParser().parse((String) response);
            log.info("BS public session URL: " + (String) json.get("public_url"));
        } else if (LTModeEnabled()) {
            DriverContext.driverSleep(1000); //TODO: Do a trial to determine if this sleep is necessary
            log.debug("- LambdaTest public session URL - BEFORE FEATURE hook");
            String seleniumTestId = ((RemoteWebDriver) LocalDriverContext.getWebDriver()).getSessionId().toString();
            HttpResponse<JsonNode> response = Unirest.get("https://api.lambdatest.com/automation/api/v1/sessions/{seleniumTestId}/video")
                    .basicAuth(System.getenv("LT_USERNAME"), System.getenv("LT_ACCESS_KEY"))
                    .routeParam("seleniumTestId", seleniumTestId)
                    .asJson();
            log.info("LT session public video URL: " + response.getBody().getObject().getString("view_video_url"));
        } else if (CBTModeEnabled()) {
            log.debug("- CrossBrowserTesting public session URL BEFORE FEATURE hook");
            String seleniumTestId = ((RemoteWebDriver) LocalDriverContext.getWebDriver()).getSessionId().toString();
            HttpResponse<JsonNode> response = Unirest.get("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}")
                    .basicAuth(System.getenv("CBT_USERNAME"), System.getenv("CBT_AUTH_KEY"))
                    .routeParam("seleniumTestId", seleniumTestId)
                    .asJson();
            log.debug("- Public CrossBrowserTesting session URL: " + response.getBody().getObject().getString("show_result_public_url"));
        }
    }

    @After(value = "@lighthouse")
    public void GetLightHouseReport() throws IOException {
        if (LTModeEnabled()) {
            DriverContext.driverSleep(3000); //TODO: Do a trial to determine if this sleep is necessary
            log.debug("- LambdaTest Get LightHouse Report AFTER FEATURE hook");
            String seleniumTestId = ((RemoteWebDriver) LocalDriverContext.getWebDriver()).getSessionId().toString();
            HttpResponse<JsonNode> response = Unirest.get("https://api.lambdatest.com/automation/api/v1/lighthouse/report/{seleniumTestId}")
                    .basicAuth(System.getenv("LT_USERNAME"), System.getenv("LT_ACCESS_KEY"))
                    .routeParam("seleniumTestId", seleniumTestId)
                    .asJson();
            System.out.println("response.getBody().getObject(): " + response.getBody().getObject());
            log.info("- LambdaTest Lighthouse JSON report download link: " + response.getBody().getObject().getJSONObject("data").getString("json_report"));
            log.info("- LambdaTest Lighthouse HTML report download link: " + response.getBody().getObject().getJSONObject("data").getString("html_report"));
            URL htmlReportUrl = new URL(response.getBody().getObject().getJSONObject("data").getString("html_report"));
            File htmlReportFile = new File("/target/Lighthouse-HTML-report.html");
            FileUtils.copyURLToFile(htmlReportUrl, htmlReportFile);
        }
    }

    @After(order = 4)
    public void UpdateTestResult(Scenario scenario) {
        testStatus = "unset";
        if (scenario.getStatus().toString().equals("PASSED")) {
            testStatus = "passed";
        } else if (scenario.getStatus().toString().equals("FAILED")) {
            testStatus = "failed";
        }
        if (BSModeEnabled()) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getWebDriver();
//            jsExecutor.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"passed\"}}");
            jsExecutor.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"" + testStatus + "\"}}");
        } else if (LTModeEnabled()) {
            String seleniumTestId = ((RemoteWebDriver) LocalDriverContext.getWebDriver()).getSessionId().toString();
            RestAssured.baseURI = "https://api.lambdatest.com/automation/api/v1/sessions/" + seleniumTestId;
            String jsonStringBody = "{" +
                    " \"status_ind\" : \"" + testStatus + "\"" +
                    " }";
            Response lt_result_update_response = given()
                    .auth().basic(System.getenv("LT_USERNAME"), System.getenv("LT_ACCESS_KEY"))
                    .body(jsonStringBody)
                    .when().patch();
        } else if (CBTModeEnabled()) {
            log.debug("- CrossBrowserTesting update CBT result URL AFTER FEATURE hook");
            String score = "unset";
            String seleniumTestId = ((RemoteWebDriver) LocalDriverContext.getWebDriver()).getSessionId().toString();
            if (testStatus == "passed") {
                score = "pass";
            } else if (testStatus == "failed") {
                score = "fail";
            }
            HttpResponse<JsonNode> response = Unirest.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}")
                    .basicAuth(System.getenv("CBT_USERNAME"), System.getenv("CBT_AUTH_KEY"))
                    .routeParam("seleniumTestId", seleniumTestId)
                    .field("action", "set_score")
                    .field("score", score)
                    .asJson();
        }
    }

    @After(value = "@zap_passive", order = 3)
    public void ZapPassiveSecurityScan() {
        if (frameworkConfigurationService.getZAP_scan_enabled()) {
            log.debug("- ZAP Passive Security Scan AFTER FEATURE hook");
            startZapPassiveSecurityScan();
            getZapScanReports();
        }
    }

    //    @After(value = "@zap_active", order = 3)
//    public void ZapActiveScan() {
//        if (frameworkConfigurationService.getZAP_scan_enabled()) {
//            String mdUrl = frameworkConfigurationService.getManuscriptDashboardBaseUrlDefault();
//            startZapActiveSecurityScan(mdUrl);
//        }
//    }

    @After(value = "@zap_active", order = 3)
    public void ZapActiveScan() {
        if (frameworkConfigurationService.getZAP_scan_enabled()) {
            // String mdUrl = frameworkConfigurationService.getManuscriptDashboardBaseUrlDefault();
            // startZapActiveSecurityScan(mdUrl);
        }
    }

    @After(value = "@zap_spider", order = 3)
    public void ZapSpiderScan() {
        if (frameworkConfigurationService.getZAP_scan_enabled()) {
            String rrUrl = frameworkConfigurationService.getReviewerRecommenderBaseUrl();
            log.debug("- ZAP Spider Security Scan - AFTER FEATURE hook");
            startZapSpiderScan(rrUrl);
        }
    }

    @After(order = 2)
    public void QuitTunnel() {
        if (BSModeEnabled()) {
            log.debug("- BrowserStack Tunnel Quit - AFTER FEATURE hook");
            try {
                bsLocal.stop();
            } catch (Exception e) {
                System.out.println("- Caught exception stacktrace (BS tunnel stop): ");
                e.printStackTrace();
            }
        } else if (LTModeEnabled()) {
            log.debug("- LambdaTest Tunnel Quit - AFTER FEATURE hook");
            try {
                tunnel.stop();
            } catch (Exception e) {
                System.out.println("- Caught exception stacktrace (LT tunnel stop): ");
                e.printStackTrace();
            }
        }
    }

    @After(order = 1)
    public void QuitWebDriver() {
        log.debug("- WebDriver Quit - AFTER FEATURE hook");
        DriverContext.quitDriver();
    }

    @After(order = 0)
    public void ScenarioInfo(Scenario scenario) {
        System.out.println(blue + "------------------------------------------------------------------------------------------");
        System.out.println("COMPLETED TEST - " + scenario.getName() + reset);
        if (testStatus.equals("failed")) {
            System.out.println(red + "RESULT - " + testStatus + reset);
        } else if (testStatus.equals("passed")) {
            System.out.println(green + " " +
                    "RESULT - " + testStatus + reset);
        }
        System.out.println(blue + "------------------------------------------------------------------------------------------" + reset);
    }

    private boolean BSModeEnabled() {
        return System.getenv("BrowserType").contains("BS") && System.getenv("BROWSERSTACK_USERNAME") != null && System.getenv("BROWSERSTACK_ACCESS_KEY") != null;
    }

    private boolean LTModeEnabled() {
        return System.getenv("BrowserType").contains("LT") && System.getenv("LT_USERNAME") != null && System.getenv("LT_ACCESS_KEY") != null;
    }

    private boolean CBTModeEnabled() {
        return System.getenv("BrowserType").contains("CBT") && System.getenv("CBT_USERNAME") != null && System.getenv("CBT_AUTH_KEY") != null;
    }

    private boolean VisualTestingEnabled() {
        return frameworkConfigurationService.getBrowserType().name().contains("Visual");
    }

    private boolean IsMobilePlatform() {
        return frameworkConfigurationService.getBrowserType().name().contains("iOS") || frameworkConfigurationService.getBrowserType().name().contains("Android");
    }

    private void startZapPassiveSecurityScan() {
        System.out.println("--- ZAP passive scan started --- ");
        try {
//            zapApi.pscan.enableAllScanners(); // enable all scanners.
            ApiResponse response = zapApi.pscan.recordsToScan(); // getting a response
            System.out.println("Number of records left for scanning : " + response);
            //Iterating until the number of records left to scan is "0".
            while (!response.toString().equals("0")) {
                response = zapApi.pscan.recordsToScan();
            }
            System.out.println("--- Passive scan completed! ---");
        } catch (ClientApiException e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void startZapActiveSecurityScan(String targetUrl) {
        System.out.println("--- ZAP active scan started --- ");
        try {
//            zapApi.pscan.enableAllScanners(); // enable all scanners.
            ApiResponse response = zapApi.ascan.scan(targetUrl, "True", "False", null, null, null);
            int progress;

            // The scan now returns a scan id to support concurrent scanning
            String scanid = ((ApiResponseElement) response).getValue();
            // Poll the status until it completes
            while (true) {
                Thread.sleep(5000);
                progress =
                        Integer.parseInt(
                                ((ApiResponseElement) zapApi.ascan.status(scanid)).getValue());
                System.out.println("Active Scan progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }
            System.out.println("--- Active scan completed! ---");
        } catch (ClientApiException | InterruptedException e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void getZapScanReports() {
        try {
            // calling core api to get html report
            String strHtmlReport = new String(zapApi.core.htmlreport(), StandardCharsets.UTF_8);
            String time = getCurrentTime();

            File zapHtmlReport = new File("target/ZAP_passive_scan_" + time + ".html");
            FileWriter fw = new FileWriter(zapHtmlReport);
            fw.write(strHtmlReport);
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy'('HH:mm:ss')'");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    private void startZapSpiderScan(String TARGET) {
        try {
            // Start spidering the target
            System.out.println("Spidering target : " + TARGET);
            ApiResponse resp = zapApi.spider.scan(TARGET, null, null, null, null);
            String scanID;
            int progress;

            // The scan returns a scan id to support concurrent scanning
            scanID = ((ApiResponseElement) resp).getValue();
            // Poll the status until it completes
            while (true) {
                Thread.sleep(1000);
                progress = Integer.parseInt(((ApiResponseElement) zapApi.spider.status(scanID)).getValue());
                System.out.println("Spider progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }
            System.out.println("Spider completed");
            // If required post process the spider results
            List<ApiResponse> spiderResults = ((ApiResponseList) zapApi.spider.results(scanID)).getItems();
            System.out.println(spiderResults);

            // TODO: Explore the Application more with Ajax Spider or Start scanning the application for vulnerabilities

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

}
