# Regional eCommerce UI Test Framework #

This project contains the Regional eCommerce UI Test framework - core. The framework can be run locally or via an
automated pipeline. The framework can also be deployed as a Maven library and be used as a basis for a Selenium based UI
framework (see an example of such use in (https://github.com/elsevier-research/triage-md-ui-test).

Please see [WebDriver Framework Support](#webdriver-framework-support) for a list of supported WebDriver integrations.

## Getting Started ##

* [Pre-requisites](#pre-requisites)
* [Define Environment Variables](#define-environment-variables)
* [Running in your IDE](#running-in-your-ide)
* [Running via Maven in the command line](#running-via-maven-in-the-command-line)

### Pre-requisites ###

At a minimum, the following pre-requisites are required to run the framework for either a Remote or Local WebDriver
configuration.

* [`mvn`](https://maven.apache.org/)
* [`java`](https://openjdk.java.net/install/) >= 8

#### Remote WebDriver Configuration

* [CrossBrowserTesting Authentication Key](https://crossbrowsertesting.com/)

### Define Environment Variables ###

Environment variables are required for the tests to run and for the framework to initialise in the correct state and
with the correct WebDriver implementation.

| Environment Variable | Configuration       | Local/Remote | Description                                                                 | Example Values                                                                                                                                                                                                       |
|----------------------|---------------------|--------------|-----------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| LT_ACCESS_KEY         | CrossBrowserTesting | Remote       | The authentication key provided by LambdaTest.                     | u000000000001d                                                                                                                                                                                                       |
| LT_USERNAME         | CrossBrowserTesting | Remote       | The username provided by LambdaTest.                               | a.person                                                                                                                                                                                                |
| BrowserType          | All                 | Local/Remote | The webdriver configuration to run.                                         | Defaults to `Win10_Chrome_CBT`. Can be of the following values: [ `Firefox_Local`, `Firefox_Zalenium`, `OSX_Firefox_LT`, `Chrome_Local`, `Chrome_Zalenium`, `Chrome_LT`, `WIN10_IE_LT`, `OSX_Safari_LT`, `Safari_Local`, `Win10_Edge_LT`] |
| BrowserVersion       | CrossBrowserTesting | Remote       | The version of the browser to run in a CrossBrowserTesting testing session. | Depending on version strings for specific Browser product defined above - e.g. for `Win10_Chrome_LT` BrowserVersion could be: `77`                                                                                        |
| GECKODRIVER_PATH     | FireFox             | Local        | Path to locally installed Gecko/Firefox Driver.                             | E.g. Windows: `C:\User\USERNAME\WebDrivers\firefox-driver.exe`  Mac: `/Users/USERNAME/WebDrivers/firefox-driver`  Linux: `/home/USERNAME/WebDrivers/firefox-driver`                                                 |
| CHROMEDRIVER_PATH    | Chrome              | Local        | Path to locally installed ChromeDriver.                                     | E.g. Windows: `C:\User\USERNAME\WebDrivers\chromedriver.exe`  Mac: `/Users/USERNAME/WebDrivers/chrome-driver`  Linux: `/home/USERNAME/WebDrivers/chrome-driver`                                                     |
| SAFARIDRIVER_PATH    | Safari              | Local        | Path to locally installed Safari WebDriver.                                 | E.g. Windows: `C:\User\USERNAME\WebDrivers\safaridriver.exe`  Mac: `/Users/USERNAME/WebDrivers/safari-webdriver`  Linux: `/home/USERNAME/WebDrivers/safari-webdriver`                                               |

For example, via bash you can export the following environment variables with:

```shell script
export LT_ACCESS_KEY=<A_LT_AUTH_KEY>
export LT_USERNAME=<A_LT_USERNAME>
export BrowserType=Win10_Chrome_LT
export BrowserVersion=81
```

Which will initialise the framework to use [LambdaTest](#remote-webdrivers) and use Chrome in the automated tests.

_Note: if running tests via IDE, then these same environment variables will need to be declared in a way that the IDE
can pick up._

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

Within your IDE, select `CucumberRunner.java` and run the test. If you are running the test in this way, you should
specify the correponding tags of the tests you would like to run, as well as the relevant environment variables within
the IDE against the appropriate Run Configuration.

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

## WebDriver Framework Support ##

The framework currently supports multiple local web driver implementations as well as remote web drivers.

### Local WebDrivers ###

To use a local WebDriver, you must define the appropriate environment variables and manually download the appropriate
WebDriver from the vendor's site.

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

### Setting up test environment

#### Mac

First [download and install Chrome driver](https://chromedriver.chromium.org/downloads) somewhere. Recommended location
is _~/drivers/chromedriver_

Now set up a run configuration in IntelliJ of type Maven, with the following settings:

Parameters -> Command Line:

``clean test "-Dcucumber.filter.tags=@MD_smoke and not @ignore" -Dcucumber.execution.strict``

Runner -> Environment Variables

```BrowserName=chrome;BrowserType=Chrome_local;CHROMEDRIVER_PATH=~/drivers/chromedriver```

## ZAP passive security scan

### Setting up docker ZAP client

First, install the OWASP ZAP Docker image owasp/zap2docker-stable

```docker pull owasp/zap2docker-stable```

Run ZAP in Docker container using the following command (bash might be needed):

```docker run -u zap -p 8080:8080 -i owasp/zap2docker-stable zap.sh -daemon -host 0.0.0.0 -port 8080 -config api.disablekey=true -config api.addrs.addr.name=.* -config api.addrs.addr.regex=true```

Do a test by opening `http://127.0.0.1:8080/` and check `http://127.0.0.1:8080/OTHER/core/other/htmlreport` where the
initial report should be empty, showing 0 alerts.

Before running any ZAP test, make sure that the following environment variable is set:
``ZAP_scan_enabled = true``

Before running the Gmail API test make sure to Active the Lesssecureapps and Activate DisplayUnlockCaptcha

## Maven library Artifactory deployment

### Environment setup

In the `pom.xml` file add the following:

        <distributionManagement>
            <repository>
                <id>central</id>
                <name>rt-subsys-quality-artifactory-releases</name>
                <url>https://rt.artifactory.tio.systems/artifactory/maven-subsys-quality-releases-local</url>
            </repository>
        </distributionManagement>

In the `~/.m2/settings.xml` file add the following:

        <server>
            <id>rt-artifactory-releases</id>
            <username>~Artifactory username~</username>
            <password>~Artifactory API key~</password>
        </server>
        <server>
            <id>central</id>
            <username>~Artifactory username~</username>
            <password>~Artifactory API key~</password>
        </server>
        <server>
            <id>snapshots</id>
            <username>~Artifactory username~</username>
            <password>~Artifactory API key~</password>
        </server>

and

     <profile>
            <id>artifactory</id>
            <repositories>
                <repository>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <id>central</id>
                    <name>maven-subsys-quality-releases-virtual</name>
                    <url>https://rt.artifactory.tio.systems/artifactory/maven-subsys-quality-releases-virtual</url>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <id>central</id>
                    <name>maven-subsys-quality-releases-virtual</name>
                    <url>https://rt.artifactory.tio.systems/artifactory/maven-subsys-quality-releases-virtual</url>
                </pluginRepository>
            </pluginRepositories>
        </profile>```
 
    <activeProfiles>
        <activeProfile>artifactory</activeProfile>
    </activeProfiles>

Once the above is setup, execute the following:

- ```mvn clean package```
- ```mvn deploy```

The output of the latter command, should be similar to this:

```[INFO] --- maven-deploy-plugin:2.8.2:deploy (default-deploy) @ SubSysTestFramework-core ---
Uploading to central: https://rt.artifactory.tio.systems/artifactory/maven-subsys-quality-releases-local/com/elsevier/subsys/SubSysTestFramework-core/1.0.1/SubSysTestFramework-core-1.0.1.jar
Uploaded to central: https://rt.artifactory.tio.systems/artifactory/maven-subsys-quality-releases-local/com/elsevier/subsys/SubSysTestFramework-core/1.0.1/SubSysTestFramework-core-1.0.1.jar (139 kB at 196 kB/s)
Uploading to central: https://rt.artifactory.tio.systems/artifactory/maven-subsys-quality-releases-local/com/elsevier/subsys/SubSysTestFramework-core/1.0.1/SubSysTestFramework-core-1.0.1.pom
Uploaded to central: https://rt.artifactory.tio.systems/artifactory/maven-subsys-quality-releases-local/com/elsevier/subsys/SubSysTestFramework-core/1.0.1/SubSysTestFramework-core-1.0.1.pom (12 kB at 61 kB/s)
Downloading from central: https://rt.artifactory.tio.systems/artifactory/maven-subsys-quality-releases-local/com/elsevier/subsys/SubSysTestFramework-core/maven-metadata.xml
Downloaded from central: https://rt.artifactory.tio.systems/artifactory/maven-subsys-quality-releases-local/com/elsevier/subsys/SubSysTestFramework-core/maven-metadata.xml (427 B at 5.3 kB/s)
Uploading to central: https://rt.artifactory.tio.systems/artifactory/maven-subsys-quality-releases-local/com/elsevier/subsys/SubSysTestFramework-core/maven-metadata.xml
Uploaded to central: https://rt.artifactory.tio.systems/artifactory/maven-subsys-quality-releases-local/com/elsevier/subsys/SubSysTestFramework-core/maven-metadata.xml (379 B at 2.4 kB/s)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS```

