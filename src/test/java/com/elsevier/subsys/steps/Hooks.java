package com.elsevier.subsys.steps;

import com.elsevier.subsys.FrameworkApplicationContext;
import com.elsevier.subsys.framework.base.DriverContext;
import com.elsevier.subsys.framework.base.LocalDriverContext;
import com.elsevier.subsys.framework.base.WebDriverInitialisation;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.net.MalformedURLException;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = FrameworkApplicationContext.class)
public class Hooks {

    @Autowired
    private WebDriverInitialisation webDriverInitialisation;

    @Before (order = 0)
    public void InitializeWebDriver() throws MalformedURLException {
        log.debug("- Initialise WebDriver BEFORE FEATURE hook");
        webDriverInitialisation.initialiseWebDriverWithFrameworkSettings();
    }

    @Before (order = 1)
    public void MaximizeBrowserWindow () {
//        if ((config.testServerURL === 'http://hub-cloud.browserstack.com/wd/hub') && (this.platform === 'DESKTOP')) {
        log.debug("- Maximize browser window BEFORE FEATURE hook");
//        LocalDriverContext.getWebDriver().manage().window().setPosition(0, 0);
        LocalDriverContext.getWebDriver().manage().window().maximize();
//        }
    }

    @Before (order = 2)
    public void GetCBTPublicSession() {
//   TODO: Make it conditional for CBT runs
        DriverContext.DriverSleep(3000); //TODO: Do a trial to determine if this sleep is necessary
        log.debug("- CrossBrowserTesting public session URL BEFORE FEATURE hook");
        String seleniumTestId = ((RemoteWebDriver) LocalDriverContext.getWebDriver()).getSessionId().toString();
        HttpResponse<JsonNode> response = Unirest.get("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}")
                .basicAuth(System.getenv("CBT_USERNAME"), System.getenv("CBT_AUTH_KEY"))
                .routeParam("seleniumTestId", seleniumTestId)
                .asJson();
        log.debug("- Public CrossBrowserTesting session URL: " + response.getBody().getObject().getString("show_result_public_url"));
    }

    @After (order = 2)
    public void UpdateCBTTestDescription(Scenario scenario) {
//   TODO: Make it conditional for CBT runs
        log.debug("- CrossBrowserTesting update CBT test description URL AFTER FEATURE hook");
        String seleniumTestId = ((RemoteWebDriver) LocalDriverContext.getWebDriver()).getSessionId().toString();
        String scenarioDescription = scenario.getName().toString();
        HttpResponse<JsonNode> response = Unirest.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}")
                .basicAuth(System.getenv("CBT_USERNAME"), System.getenv("CBT_AUTH_KEY"))
                .routeParam("seleniumTestId", seleniumTestId)
                .field("action","set_description")
                .field("description", scenarioDescription)
                .asJson();
    }

    @After (order = 1)
    public void UpdateCBTResult(Scenario scenario) {
//   TODO: Make it conditional for CBT runs
        log.debug("- CrossBrowserTesting update CBT result URL AFTER FEATURE hook");
        String score = "unset";
        String seleniumTestId = ((RemoteWebDriver) LocalDriverContext.getWebDriver()).getSessionId().toString();
        if (scenario.getStatus().toString().equals("PASSED")) {
            score = "pass";
        } else if (scenario.getStatus().toString().equals("FAILED")) {
            score = "fail";
        }
        HttpResponse<JsonNode> response = Unirest.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}")
                .basicAuth(System.getenv("CBT_USERNAME"), System.getenv("CBT_AUTH_KEY"))
                .routeParam("seleniumTestId", seleniumTestId)
                .field("action","set_score")
                .field("score", score)
                .asJson();
    }

    @After (order = 0)
    public void QuitWebDriver() {
        log.debug("- WebDriver Quit AFTER FEATURE hook {}", LocalDriverContext.getWebDriver());
        DriverContext.quitDriver();
    }


}
