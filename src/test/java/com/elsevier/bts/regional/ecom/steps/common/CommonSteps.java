package com.elsevier.bts.regional.ecom.steps.common;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import com.deque.html.axecore.selenium.ResultType;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.base.Base;
import com.elsevier.bts.regional.ecom.framework.base.LocalDriverContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.deque.html.axecore.selenium.AxeReporter.getReadableAxeResults;

@Slf4j
public class CommonSteps extends Base {

    @Given("^the user visits the (.*)? demo app")
    public void theUserVisitsTheZapDemoApp(String zapDemoUrl) {
        switch (zapDemoUrl) {
            case "Gruyere": {
                zapDemoUrl = "https://google-gruyere.appspot.com/489330888279986457708836679043790765097/";
                break;
            }
            case "Public Firing Range": {
                zapDemoUrl = "https://public-firing-range.appspot.com";
                break;
            }
            case "Public Firing Range - Address DOM XSS": {
                zapDemoUrl = "https://public-firing-range.appspot.com/address/index.html";
                break;
            }
//            case "Hack.me": {
//                zapDemoUrl = "https://hack.me/";
//                break;
//            }
//            case "bWAPP": {
//                zapDemoUrl = "http://www.itsecgames.com/";
//                break;
//            }
//            default: {
//                zapDemoUrl = frameworkConfigurationService.getManuscriptDashboardBaseUrlDefault();
//                break;
//            }
        }
        DriverContext.goToUrl(zapDemoUrl);
        log.debug("Navigating to URL " + zapDemoUrl);
        DriverContext.waitForPageToLoad();
    }

    @Then("^a LightHouse report is generated for that page$")
    public void aLightHouseReportIsGenerated() {
//        Adding a sleep command here intentionally, because otherwise the LightHouse report cannot be generated.
        DriverContext.driverSleep(80000);
    }

    @Then("^a ZAP passive security scan is performed$")
    public void aZapPassiveSecurityScanIsPerformed() {
        DriverContext.driverSleep(20000);
    }

    @Then("^a ZAP active security scan is performed$")
    public void aZapActiveSecurityScanIsPerformed() {
        DriverContext.driverSleep(20000);
    }

    @Then("^a ZAP spider security scan is performed$")
    public void aZapSpiderSecurityScanIsPerformed() {
        DriverContext.driverSleep(20000);
    }

    @Then("^the current page is tested for A11y$")
    public void theCurrentPageIsTestedForA11y() {
        Results result = new AxeBuilder().analyze(LocalDriverContext.getWebDriver());
        List<Rule> violations = result.getViolations();
        if (violations.size() == 0) {
            log.info("No A11y violations were found");
        } else {
            getReadableAxeResults(ResultType.Violations.getKey(), LocalDriverContext.getWebDriver(), violations);
            AxeReporter.writeResultsToJsonFile("target/accessibilityTestResults", result);
            log.info(AxeReporter.getAxeResultString());
            Assertions.assertTrue(false, "A11y violations were found");
        }
    }

}
