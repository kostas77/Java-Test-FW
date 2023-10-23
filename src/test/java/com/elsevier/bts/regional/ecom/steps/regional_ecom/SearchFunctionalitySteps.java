package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.Base;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.HSHomePageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.SearchPageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.HSHomePageLocators;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SearchFunctionalitySteps extends Base {

    SearchPageActions searchPageActions = GetInstance(SearchPageActions.class);
    HSHomePageLocators homePageLocators = GetInstance(HSHomePageLocators.class);
    HSHomePageActions USHSHomePageActions = GetInstance(HSHomePageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @Given("Customer is on the main page of website")
    public void customer_is_on_the_main_page_of_website() {
        DriverContext.goToUrl(frameworkConfigurationService.getUSHS_ECOM_UAT_URL());
        DriverContext.waitForPageToLoad();
        log.debug("Navigated to URL " + DriverContext.getCurrentUrl());
        USHSHomePageActions.acceptCookies();
        System.out.println(DriverContext.isElementDisplayed(homePageLocators.getHeader()));
    }

    @When("Customer searches for a product {string}")
    public void customer_searches_for_a_product(String product) {
        USHSHomePageActions.searchForProduct(product);
    }

    @Then("search page should display the list of searched product {string}")
    public void search_page_should_display_the_list_of_searched_product(String string) {
        String Expected = "Search results for: '" + string + "'";
        System.out.println(Expected);
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals(Expected, searchPageActions.SearchResultsIsDisplayed());
        searchPageActions.productNameVerification(string);
    }

}
