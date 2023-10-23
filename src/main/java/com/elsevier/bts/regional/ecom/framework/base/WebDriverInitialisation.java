package com.elsevier.bts.regional.ecom.framework.base;

import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebDriverInitialisation {

    private FrameworkConfigurationService frameworkConfigurationService;

    public WebDriverInitialisation(FrameworkConfigurationService frameworkConfigurationService) {
        this.frameworkConfigurationService = frameworkConfigurationService;
    }

    public void initialiseWebDriverWithFrameworkSettings(String buildName, String scenarioName, String tunnelName, String smartUIBuildName, String smartUIProjectName) throws Exception {
        initialiseWebDriver(frameworkConfigurationService, buildName, scenarioName, tunnelName, smartUIBuildName, smartUIProjectName);
    }

    private void initialiseWebDriver(FrameworkConfigurationService frameworkConfigurationService, String buildName, String scenarioName, String tunnelName, String smartUIBuildName, String smartUIProjectName) throws Exception {

        WebDriver driver;
        switch (frameworkConfigurationService.getBrowserType()) {
            case Chrome_Local -> {
                ChromeOptions capabilities = new ChromeOptions();
                capabilities.addArguments("--user-agent=regional_ecom_automated_tests_user_agent");

                driver = new ChromeDriver();
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Chrome_Local_Mobile -> {
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "iPhone X");
                ChromeOptions capabilities = new ChromeOptions();
                capabilities.setExperimentalOption("mobileEmulation", mobileEmulation);

                driver = new ChromeDriver();
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Chrome_Local_Headless -> {
                ChromeOptions capabilities = new ChromeOptions();
                capabilities.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200");

                driver = new ChromeDriver();
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Chrome_Local_ZAP -> {
                ChromeOptions capabilities = new ChromeOptions();
                capabilities.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                capabilities.addArguments("test-type", "start-maximized", "no-default-browser-check", "no-sandbox", "disable-infobars", "disable-extensions",
                        "ignore-certificate-errors", "allow-running-insecure-content", "window-position=0,0", "window-size=1280,768", "enable-features=NetworkService",
                        "whitelisted-ips");
//                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//                capabilities.setCapability(CapabilityType.VERSION, true);
                capabilities.merge(zapProxyCapabilities());

                driver = new ChromeDriver();
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Chrome_Zalenium -> {
                ChromeOptions capabilities = new ChromeOptions();
                capabilities.setCapability("recordVideo", true);
                capabilities.setCapability("build", "1.4.1");
                capabilities.setCapability("idleTimeout", 150);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getZaleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Win10_Chrome_CBT -> {
                ChromeOptions capabilities = new ChromeOptions();
                capabilities.setCapability("name", buildName);
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("platform", "Windows 10");
                capabilities.setCapability("browserName", "chrome");
//                capabilities.setCapability("version", System.getenv("BrowserVersion"));
                capabilities.setCapability("version", "85");
                capabilities.setCapability("screenResolution", "1920x1080");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Win10_Chrome_LT -> {
                ChromeOptions capabilities  = new ChromeOptions();
                capabilities.setPlatformName("Windows 11");
                capabilities.setBrowserVersion("latest-1");
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("resolution", "1920x1080");
                ltOptions.put("build", buildName);
                ltOptions.put("name", scenarioName);
                ltOptions.put("tunnel", true);
                ltOptions.put("tunnelName", tunnelName);
                ltOptions.put("w3c", true);
                ltOptions.put("console", true);
                ltOptions.put("network", true);
                ltOptions.put("visual", true);
                capabilities.addArguments("--user-agent=regional_ecom_automated_tests_user_agent");
                capabilities.setCapability("LT:Options", ltOptions);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getLTSeleniumGridHub()), capabilities );
//                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Win11_Chrome_BS -> {
                ChromeOptions capabilities  = new ChromeOptions();
//                MutableCapabilities capabilities = new MutableCapabilities();

                capabilities.setCapability("browserName", "Chrome");
                HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                browserstackOptions.put("userName", System.getenv("BROWSERSTACK_USERNAME"));
                browserstackOptions.put("accessKey", System.getenv("BROWSERSTACK_ACCESS_KEY"));
                browserstackOptions.put("os", "Windows");
                browserstackOptions.put("osVersion", "11");
                browserstackOptions.put("browserVersion", "116.0");
                browserstackOptions.put("projectName", frameworkConfigurationService.getTestEnv());
                browserstackOptions.put("buildName", buildName);
                browserstackOptions.put("sessionName", scenarioName);
                browserstackOptions.put("local", "true");
                browserstackOptions.put("localIdentifier", tunnelName);
                browserstackOptions.put("debug", "true");
                browserstackOptions.put("networkLogs", "true");
                browserstackOptions.put("video", "true");
                browserstackOptions.put("interactiveDebugging", "true");
                browserstackOptions.put("seleniumVersion", "4.7.2");
                browserstackOptions.put("seleniumLogs", "true");
                browserstackOptions.put("telemetryLogs", "true");
                browserstackOptions.put("acceptInsecureCerts", "true");
//                capabilities.addArguments("--user-agent=regional_ecom_automated_tests_user_agent");
                capabilities.setCapability("bstack:options", browserstackOptions);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getBSSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Win10_Chrome_LT_Visual -> {
                ChromeOptions capabilities = new ChromeOptions();
                capabilities.setCapability("build", buildName);
                capabilities.setCapability("name", scenarioName);
                capabilities.setCapability("platform", "Windows 11");
                capabilities.setCapability("browserName", "Chrome");
                capabilities.setCapability("version", "latest-1");
                capabilities.setCapability("resolution", "1920x1080");
                capabilities.setCapability("selenium_version", "4.7.0");
                capabilities.setCapability("tunnel", true);
                capabilities.setCapability("tunnelName", tunnelName);
                capabilities.setCapability("console", true);
                capabilities.setCapability("network", true);
                capabilities.setCapability("visual", true);
                capabilities.setCapability("smartUI.project", smartUIProjectName);
                capabilities.setCapability("smartUI.build", smartUIBuildName);
                capabilities.setCapability("smartUI.baseline", false);
                capabilities.addArguments("--user-agent=regional_ecom_automated_tests_user_agent");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getLTSeleniumGridHub()), capabilities);
//                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case OSX_Chrome_LT_perf -> {
                ChromeOptions capabilities = new ChromeOptions();
                capabilities.setCapability("build", buildName);
                capabilities.setCapability("name", scenarioName);
                capabilities.setPlatformName("MacOS Big sur");
                capabilities.setBrowserVersion("latest");
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("resolution", "1920x1080");
                ltOptions.put("build", buildName);
                ltOptions.put("name", scenarioName);
                ltOptions.put("tunnel", true);
                ltOptions.put("tunnelName", tunnelName);
                ltOptions.put("w3c", true);
                ltOptions.put("console", true);
                ltOptions.put("network", true);
                ltOptions.put("visual", true);
                capabilities.setCapability("LT:Options", ltOptions);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getLTSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Firefox_Local -> {
                FirefoxOptions capabilities = new FirefoxOptions();
                capabilities.addArguments("--user-agent=regional_ecom_automated_tests_user_agent");

                driver = new FirefoxDriver();
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Firefox_Local_ZAP -> {
                FirefoxOptions capabilities = new FirefoxOptions();
                Proxy proxy = new Proxy();
                proxy.setHttpProxy("localhost:8080");
                proxy.setFtpProxy("localhost:8080");
                proxy.setSslProxy("localhost:8080");
                capabilities.setCapability(CapabilityType.PROXY, proxy);

                driver = new FirefoxDriver();
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Firefox_Zalenium -> {
                FirefoxOptions capabilities = new FirefoxOptions();
                capabilities.setCapability("recordVideo", true);
                capabilities.setCapability("build", "1.4.1");
                capabilities.setCapability("idleTimeout", 150);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getZaleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case OSX_Firefox_CBT -> {
                FirefoxOptions capabilities = new FirefoxOptions();
                capabilities.setCapability("name", buildName);
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("platform", "Mac OSX 10.15");
                capabilities.setCapability("browserName", "Firefox");
                capabilities.setCapability("version", "81");
                capabilities.setCapability("screenResolution", "1920x1080");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case OSX_Firefox_LT -> {
                FirefoxOptions capabilities = new FirefoxOptions();
                // LambdaTest capabilities configuration
                capabilities.setPlatformName("OSX??????");
                capabilities.setBrowserVersion("latest-1");
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("resolution", "1920x1080");
                ltOptions.put("build", buildName);
                ltOptions.put("name", scenarioName);
                ltOptions.put("tunnel", true);
                ltOptions.put("tunnelName", tunnelName);
                ltOptions.put("w3c", true);
                ltOptions.put("console", true);
                ltOptions.put("network", true);
                ltOptions.put("visual", true);
                capabilities.addArguments("--user-agent=regional_ecom_automated_tests_user_agent");
                capabilities.setCapability("LT:Options", ltOptions);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getLTSeleniumGridHub()), capabilities);
//                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case OSX_Firefox_BS -> {
                FirefoxOptions capabilities = new FirefoxOptions();
                // BrowserStack capabilities configuration
                capabilities.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
                capabilities.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));
                capabilities.setPlatformName("Lion");
                capabilities.setBrowserVersion("latest-1");
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("resolution", "1920x1080");
                ltOptions.put("build", buildName);
                ltOptions.put("name", scenarioName);
                ltOptions.put("tunnel", true);
                ltOptions.put("tunnelName", tunnelName);
                ltOptions.put("w3c", true);
                ltOptions.put("console", true);
                ltOptions.put("network", true);
                ltOptions.put("visual", true);
                capabilities.addArguments("--user-agent=regional_ecom_automated_tests_user_agent");
                capabilities.setCapability("LT:Options", ltOptions);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getBSSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Win10_IE_CBT -> {
                InternetExplorerOptions capabilities = new InternetExplorerOptions();
                // CrossBrowserTesting capabilities configuration
                capabilities.setCapability("name", buildName);
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("platform", "Windows 10");
                capabilities.setCapability("browserName", "Internet Explorer");
                capabilities.setCapability("version", "11");
                capabilities.setCapability("screenResolution", "1920x1080");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Win10_IE_LT -> {
                InternetExplorerOptions capabilities = new InternetExplorerOptions();
                // LambdaTest capabilities configuration
                capabilities.setPlatformName("Windows 11");
                capabilities.setBrowserVersion("latest-1");
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("resolution", "1920x1080");
                ltOptions.put("build", buildName);
                ltOptions.put("name", scenarioName);
                ltOptions.put("tunnel", true);
                ltOptions.put("tunnelName", tunnelName);
                ltOptions.put("w3c", true);
                ltOptions.put("console", true);
                ltOptions.put("network", true);
                ltOptions.put("visual", true);
                capabilities.setCapability("LT:Options", ltOptions);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getLTSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case IE_BS -> {
                InternetExplorerOptions capabilities = new InternetExplorerOptions();
                // BrowserStack capabilities configuration
                capabilities.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
                capabilities.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getBSSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Win10_Edge_CBT -> {
//                DesiredCapabilities capabilities = new DesiredCapabilities();
                EdgeOptions capabilities = new EdgeOptions();
                // CrossBrowserTesting capabilities configuration
                capabilities.setCapability("name", buildName);
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("platform", "Windows 10");
                capabilities.setCapability("browserName", "MicrosoftEdge");
                capabilities.setCapability("version", "79");
                capabilities.setCapability("screenResolution", "1920x1080");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Win10_Edge_LT -> {
                EdgeOptions capabilities = new EdgeOptions();
                // LambdaTest capabilities configuration
                capabilities.setPlatformName("Windows 11");
                capabilities.setBrowserVersion("latest-1");
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("resolution", "1920x1080");
                ltOptions.put("build", buildName);
                ltOptions.put("name", scenarioName);
                ltOptions.put("tunnel", true);
                ltOptions.put("tunnelName", tunnelName);
                ltOptions.put("w3c", true);
                ltOptions.put("console", true);
                ltOptions.put("network", true);
                ltOptions.put("visual", true);
                capabilities.addArguments("--user-agent=regional_ecom_automated_tests_user_agent");
                capabilities.setCapability("LT:Options", ltOptions);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getLTSeleniumGridHub()), capabilities);
//                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Edge_BS -> {
                EdgeOptions capabilities = new EdgeOptions();
                // BrowserStack capabilities configuration
                capabilities.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
                capabilities.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getBSSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Edge_Local -> {
                EdgeOptions options = new EdgeOptions();
                //capabilities.setCapability("--user-agent=regional_ecom_automated_tests_user_agent"); //TODO add once selenium 4 is updated

                driver = new EdgeDriver();
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Safari_Local -> {
                //Optional<Path> browserPath = WebDriverManager.safaridriver().getBrowserPath();
                //assumeThat(browserPath).isPresent();
                // SafariOptions capabilities = new SafariOptions();

                driver = new SafariDriver();
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case OSX_Safari_CBT -> {
                SafariOptions capabilities = new SafariOptions();
                //CrossBrowserTesting capabilities configuration
                capabilities.setCapability("name", buildName);
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("platform", "Mac OSX 10.15");
                capabilities.setCapability("browserName", "Safari");
                capabilities.setCapability("version", "13");
                capabilities.setCapability("screenResolution", "1920x1080");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case OSX_Safari_LT -> {
                SafariOptions capabilities = new SafariOptions();
                // LambdaTest capabilities configuration
                capabilities.setPlatformName("MacOS Monterey");
                capabilities.setBrowserVersion("latest-1");
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("resolution", "1920x1080");
                ltOptions.put("build", buildName);
                ltOptions.put("name", scenarioName);
                ltOptions.put("tunnel", true);
                ltOptions.put("tunnelName", tunnelName);
                ltOptions.put("w3c", true);
                ltOptions.put("console", true);
                ltOptions.put("network", true);
                ltOptions.put("visual", true);
                capabilities.setCapability("LT:Options", ltOptions);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getLTSeleniumGridHub()), capabilities);
//                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case iOS_Safari_CBT -> {
                SafariOptions capabilities = new SafariOptions();
                // CrossBrowserTesting capabilities configuration
                capabilities.setCapability("name", buildName);
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("deviceName", "iPhone XR Simulator");
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("platformVersion", "12.0");
                capabilities.setCapability("browserName", "Safari");
                capabilities.setCapability("deviceOrientation", "portrait");
                capabilities.setCapability("acceptInsecureCerts", "true");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case iOS_Safari_LT -> {
//                DesiredCapabilities capabilities = new DesiredCapabilities();
                SafariOptions capabilities = new SafariOptions();
                //LambdaTest capabilities configuration
                capabilities.setCapability("build", buildName);
                capabilities.setCapability("name", scenarioName);
                capabilities.setCapability("platformName", "ios");
                capabilities.setCapability("deviceName", "iPhone 13 Pro Max");
                capabilities.setCapability("platformVersion", "15");
                capabilities.setCapability("tunnel", true);
                capabilities.setCapability("tunnelName", tunnelName);
                capabilities.setCapability("console", true);
                capabilities.setCapability("network", true);
                capabilities.setCapability("visual", true);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getLTSeleniumGridHub()), capabilities);
//                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Android_Chrome_CBT -> {
//                DesiredCapabilities capabilities = new DesiredCapabilities();
                ChromeOptions capabilities = new ChromeOptions();
                //CrossBrowserTesting capabilities configuration
                capabilities.setCapability("name", buildName);
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("deviceName", "Pixel 4");
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("platformVersion", "10.0");
                capabilities.setCapability("browserName", "Chrome");
                capabilities.setCapability("deviceOrientation", "portrait");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Android_Chrome_LT -> {
//                DesiredCapabilities capabilities = new DesiredCapabilities();
                ChromeOptions capabilities = new ChromeOptions();
//                //LambdaTest capabilities configuration
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("w3c", true);
                ltOptions.put("platformName", "android");
                ltOptions.put("deviceName", "Galaxy A51");
                ltOptions.put("platformVersion", "13");
                ltOptions.put("tunnel", true);
                ltOptions.put("tunnelName", tunnelName);
                ltOptions.put("build", buildName);
                ltOptions.put("name", scenarioName);
                ltOptions.put("video", true);
                capabilities.setCapability("lt:options", ltOptions);
                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getLTSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Android_Chrome_Local -> {
//                DesiredCapabilities capabilities = new DesiredCapabilities();
                ChromeOptions capabilities = new ChromeOptions();
                //LambdaTest capabilities configuration
                capabilities.setAndroidDeviceSerialNumber("emulator-5554");
                capabilities.setCapability("platformName", "Android");
                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getLocalSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Android_Chrome_BS -> {
                MutableCapabilities capabilities = new MutableCapabilities();
                capabilities.setCapability("browserName", "chrome");
                HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                browserstackOptions.put("osVersion", "13.0");
                browserstackOptions.put("deviceName", "Samsung Galaxy S23");
                browserstackOptions.put("userName", System.getenv("CBT_USERNAME"));
                browserstackOptions.put("accessKey", System.getenv("CBT_AUTH_KEY"));
                browserstackOptions.put("buildName", buildName);
                browserstackOptions.put("sessionName", scenarioName);
                browserstackOptions.put("local", "true");
                browserstackOptions.put("localIdentifier", tunnelName);
                browserstackOptions.put("debug", "true");
                browserstackOptions.put("networkLogs", "true");
                browserstackOptions.put("video", "true");
                browserstackOptions.put("interactiveDebugging", "true");
                browserstackOptions.put("seleniumLogs", "true");
                browserstackOptions.put("telemetryLogs", "true");
                browserstackOptions.put("acceptInsecureCerts", "true");
                capabilities.setCapability("bstack:options", browserstackOptions);
                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getBSSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
            case Ios_Chrome_BS -> {
                MutableCapabilities capabilities = new MutableCapabilities();
                capabilities.setCapability("browserName", "chrome");
                HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                browserstackOptions.put("osVersion", "16.0");
                browserstackOptions.put("deviceName", "iPhone 14");
                browserstackOptions.put("userName", System.getenv("CBT_USERNAME"));
                browserstackOptions.put("accessKey", System.getenv("CBT_AUTH_KEY"));
                browserstackOptions.put("buildName", buildName);
                browserstackOptions.put("sessionName", scenarioName);
                browserstackOptions.put("local", "true");
                browserstackOptions.put("localIdentifier", tunnelName);
                browserstackOptions.put("debug", "true");
                browserstackOptions.put("networkLogs", "true");
                browserstackOptions.put("video", "true");
                browserstackOptions.put("interactiveDebugging", "true");
                browserstackOptions.put("seleniumLogs", "true");
                browserstackOptions.put("telemetryLogs", "true");
                browserstackOptions.put("acceptInsecureCerts", "true");
                capabilities.setCapability("bstack:options", browserstackOptions);
                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getBSSeleniumGridHub()), capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
            }
        }
    }

    private static MutableCapabilities zapProxyCapabilities() {
        Proxy zapProxy = new Proxy();
        StringBuilder builder = new StringBuilder();
        String proxyAddress = builder.append("127.0.0.1").append(":").append(8080).toString();
        zapProxy.setHttpProxy(proxyAddress).setSslProxy(proxyAddress);

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, zapProxy);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        return capabilities;
    }

}
