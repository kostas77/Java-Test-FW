# Submissions Systems UI Test Framework #

This project contains the Submissions Systems UI Test framework. The framework can be ran locally or is ran via an automated pipeline.

Please see [WebDriver Framework Support](#webdriver-framework-support) for a list of supported WebDriver integrations.

## Getting Started ##

* [Pre-requisites](#pre-requisites)
* [Define Environment Variables](#define-environment-variables)
* [Running in your IDE](#running-in-your-ide)
* [Running via Maven in the command line](#running-via-maven-in-the-command-line)

### Pre-requisites ###

At a minimum, the following pre-requisites are required to run the framework for either a Remote or Local WebDriver configuration.
* [`mvn`](https://maven.apache.org/)
* [`java`](https://openjdk.java.net/install/) >= 8

#### Remote WebDriver Configuration
* [CrossBrowserTesting Authentication Key](https://crossbrowsertesting.com/)

### Define Environment Variables ###

Environment variables are required for the tests to run and for the framework to initialise in the correct state and with the correct WebDriver implementation.

| Environment Variable | Configuration       | Local/Remote | Description                                                                 | Example Values                                                                                                                                                                                                       |
|----------------------|---------------------|--------------|-----------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| CBT_AUTH_KEY         | CrossBrowserTesting | Remote       | The authentication key provided by CrossBrowserTesting.                     | u000000000001d                                                                                                                                                                                                       |
| CBT_USERNAME         | CrossBrowserTesting | Remote       | The username provided by CrossBrowserTesting.                               | a.person@elsevier.com                                                                                                                                                                                                |
| BrowserType          | All                 | Local/Remote | The webdriver configuration to run.                                         | Defaults to `Win10_Chrome_CBT`. Can be of the following values: [ `Firefox_Local`, `Firefox_Zalenium`, `OSX_Firefox_CBT`, `Chrome_Local`, `Chrome_Zalenium`, `Chrome_CBT`, `WIN10_IE_CBT`, `OSX_Safari_CBT`, `Safari_Local`, `Win10_Edge_CBT`] |
| BrowserVersion       | CrossBrowserTesting | Remote       | The version of the browser to run in a CrossBrowserTesting testing session. | Depending on version strings for specific Browser product defined above - e.g. for `Win10_Chrome_CBT` BrowserVersion could be: `77`                                                                                        |
| GECKODRIVER_PATH     | FireFox             | Local        | Path to locally installed Gecko/Firefox Driver.                             | E.g.  Windows: `C:\User\USERNAME\WebDrivers\firefox-driver.exe`  Mac: `/Users/USERNAME/WebDrivers/firefox-driver`  Linux: `/home/USERNAME/WebDrivers/firefox-driver`                                                 |
| CHROMEDRIVER_PATH    | Chrome              | Local        | Path to locally installed ChromeDriver.                                     | E.g.  Windows: `C:\User\USERNAME\WebDrivers\chromedriver.exe`  Mac: `/Users/USERNAME/WebDrivers/chrome-driver`  Linux: `/home/USERNAME/WebDrivers/chrome-driver`                                                     |
| SAFARIDRIVER_PATH    | Safari              | Local        | Path to locally installed Safari WebDriver.                                 | E.g.  Windows: `C:\User\USERNAME\WebDrivers\safaridriver.exe`  Mac: `/Users/USERNAME/WebDrivers/safari-webdriver`  Linux: `/home/USERNAME/WebDrivers/safari-webdriver`                                               |

For example, via bash you can export the following environment variables with:

```shell script
export CBT_AUTH_KEY=<A_CBT_AUTH_KEY>
export CBT_USERNAME=<A_CBT_USERNAME>
export BrowserType=Win10_Chrome_CBT
export BrowserVersion=77
```

Which will initialise the framework to use [CrossBrowserTesting](#remote-webdrivers) and use Chrome in the automated tests.

_Note: if running tests via IDE, then these same environment variables will need to be declared in a way that the IDE can pick up._

#### Install Lombok plugin in your IDE 
To add the Lombok IntelliJ plugin to add Lombok support in IntelliJ follow these steps:

* Go to File > Settings > Plugins
* Click on Browse repositories...
* Search for Lombok Plugin
* Click on Install plugin
* Restart IntelliJ IDEA

Also make sure you activate the Lombok plugin in the Project settings:

* Click Settings > Other Settings > Lombok Plugin.
* Ensure "Enable Lombock plugin" option is checked.

### Running in your IDE ###

Within your IDE, select `RunCukeTest.java` and run the test. If you are running the test in this way, you should specify the relevant environment variables within the IDE against the appropriate Run Configuration.

#### Running individually tagged scenarios in your IDE ####

You can specify individual scenarios to run in your IDE with VM options, such as the example below:
```shell script
-Dcucumber.filter.tags="@tag_name"
```

### Running via Maven in the Command Line ###

After declaring the relevant [environment variables](#define-environment-variables), you can run:

#### Single Test Execution Mode

```shell script
mvn clean test
```

#### Parallel Test Execution Mode

To run tests in parallel - use the following maven command:

```shell script
mvn clean install -P parallel
```

## WebDriver Framework Support ##

The framework currently supports multiple local web driver implementations as well as remote web drivers.

### Local WebDrivers ###
To use a local WebDriver, you must define the appropriate environment variables and manually download the appropriate WebDriver from the vendor's site.
* [ChromeDriver](https://chromedriver.chromium.org/)
* [GeckoDriver](https://github.com/mozilla/geckodriver)
* [Safari WebDriver](https://developer.apple.com/documentation/webkit/testing_with_webdriver_in_safari)

### Remote WebDrivers ###
* [Zalenium](https://github.com/zalando/zalenium)
* Via [CrossBrowserTesting](https://crossbrowsertesting.com/)
    * [ChromeDriver](https://chromedriver.chromium.org/)
    * [GeckoDriver](https://github.com/mozilla/geckodriver)
    * [InternetExplorerDriver](https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver)
    * [WebDriver for Edge](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)
    * [Safari WebDriver](https://developer.apple.com/documentation/webkit/testing_with_webdriver_in_safari)
