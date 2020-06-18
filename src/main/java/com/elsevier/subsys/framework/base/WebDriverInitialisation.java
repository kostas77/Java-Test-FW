package com.elsevier.subsys.framework.base;

import com.elsevier.subsys.framework.config.FrameworkConfigurationService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class WebDriverInitialisation {


    private FrameworkConfigurationService frameworkConfigurationService;

    public WebDriverInitialisation(FrameworkConfigurationService frameworkConfigurationService)
    {
        this.frameworkConfigurationService = frameworkConfigurationService;
    }

    public void initialiseWebDriverWithFrameworkSettings() throws MalformedURLException {
        initialiseWebDriver(frameworkConfigurationService);
    }

    private void initialiseWebDriver(FrameworkConfigurationService frameworkConfigurationService) throws MalformedURLException {

        System.out.println("OS/Browser configuration used:" + System.getenv("BrowserType")); //TODO: Temporary for debugging CI/CD issues
        WebDriver driver;
        switch (frameworkConfigurationService.getBrowserType())
        {
            case Chrome_Local:
            {
                System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_PATH"));
                ChromeOptions capabilities= new ChromeOptions();

                driver = new ChromeDriver(capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case Chrome_Zalenium:
            {
                ChromeOptions capabilities = new ChromeOptions();
                capabilities.setCapability("recordVideo", true);
                capabilities.setCapability("build", "1.4.1");
                capabilities.setCapability("idleTimeout", 150);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getZaleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case Win10_Chrome_CBT:
            {
                ChromeOptions capabilities = new ChromeOptions();
                capabilities.setCapability("name", "Placeholder");
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("platform", "Windows 10");
                capabilities.setCapability("browserName", "chrome");
//                capabilities.setCapability("version", System.getenv("BrowserVersion"));
                capabilities.setCapability("version", "81");
                capabilities.setCapability("screenResolution", "1920x1080");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case Chrome_BS:
            {
                ChromeOptions capabilities = new ChromeOptions();
                // BrowserStack capabilities configuration
                capabilities.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
                capabilities.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getBSSeleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case Firefox_Local:
            {
                System.setProperty("webdriver.chrome.driver", System.getenv("GECKODRIVER_PATH"));
                FirefoxOptions capabilities = new FirefoxOptions();

                driver = new FirefoxDriver(capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case Firefox_Zalenium:
            {
                FirefoxOptions capabilities = new FirefoxOptions();
                capabilities.setCapability("recordVideo", true);
                capabilities.setCapability("build", "1.4.1");
                capabilities.setCapability("idleTimeout", 150);

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getZaleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case OSX_Firefox_CBT:
            {
                FirefoxOptions capabilities = new FirefoxOptions();
                capabilities.setCapability("name", "Placeholder");
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("platform", "Mac OSX 10.14");
                capabilities.setCapability("browserName", "Firefox");
                capabilities.setCapability("version", "74");
                capabilities.setCapability("screenResolution", "1920x1080");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case Firefox_BS:
            {
                FirefoxOptions capabilities = new FirefoxOptions();
                // BrowserStack capabilities configuration
                capabilities.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
                capabilities.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getBSSeleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case Win10_IE_CBT:
            {
                InternetExplorerOptions capabilities = new InternetExplorerOptions();
                // CrossBrowserTesting capabilities configuration
                capabilities.setCapability("name", "Placeholder");
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("platform", "Windows 10");
                capabilities.setCapability("browserName", "Internet Explorer");
                capabilities.setCapability("version", "11");
                capabilities.setCapability("screenResolution", "1920x1080");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case IE_BS:
            {
                InternetExplorerOptions capabilities = new InternetExplorerOptions();
                // BrowserStack capabilities configuration
                capabilities.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
                capabilities.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getBSSeleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case Win10_Edge_CBT:
            {
//                DesiredCapabilities capabilities = new DesiredCapabilities();
                EdgeOptions capabilities = new EdgeOptions();
                // CrossBrowserTesting capabilities configuration
                capabilities.setCapability("name", "Placeholder");
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("platform", "Windows 10");
                capabilities.setCapability("browserName", "MicrosoftEdge");
                capabilities.setCapability("version", "79");
                capabilities.setCapability("screenResolution", "1920x1080");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case Edge_BS:
            {
                EdgeOptions capabilities = new EdgeOptions();
                // BrowserStack capabilities configuration
                capabilities.setCapability("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
                capabilities.setCapability("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getBSSeleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case Safari_Local:
            {
                System.setProperty("safaridriver", System.getenv("SAFARIDRIVER_PATH"));
                SafariOptions capabilities = new SafariOptions();

                driver = new SafariDriver(capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case OSX_Safari_CBT:
            {
                SafariOptions capabilities = new SafariOptions();
                //CrossBrowserTesting capabilities configuration
                capabilities.setCapability("name", "Placeholder");
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("platform", "Mac OSX 10.15");
                capabilities.setCapability("browserName", "Safari");
                capabilities.setCapability("version", "13");
                capabilities.setCapability("screenResolution", "1920x1080");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case iOS_Safari_CBT:
            {
                SafariOptions capabilities = new SafariOptions();
                // CrossBrowserTesting capabilities configuration
                capabilities.setCapability("name", "Placeholder");
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("deviceName", "iPhone XR Simulator");
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("platformVersion", "12.0");
                capabilities.setCapability("browserName", "Safari");
                capabilities.setCapability("deviceOrientation", "portrait");
                capabilities.setCapability("acceptInsecureCerts", "true");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }
            case Android_Chrome_CBT:
            {
                SafariOptions capabilities = new SafariOptions();
                //CrossBrowserTesting capabilities configuration
                capabilities.setCapability("name", "Placeholder");
                capabilities.setCapability("username", System.getenv("CBT_USERNAME"));
                capabilities.setCapability("password", System.getenv("CBT_AUTH_KEY"));
                capabilities.setCapability("deviceName", "Pixel 4");
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("platformVersion", "10.0");
                capabilities.setCapability("browserName", "Chrome");
                capabilities.setCapability("deviceOrientation", "portrait");
                capabilities.setCapability("record_video", "true");

                driver = new RemoteWebDriver(new URL(frameworkConfigurationService.getCBTSeleniumGridHub()),capabilities);
                LocalDriverContext.bindWebDriverToThreadLocal(new WebDriverContext(driver, frameworkConfigurationService));
                break;
            }

        }
    }

}
