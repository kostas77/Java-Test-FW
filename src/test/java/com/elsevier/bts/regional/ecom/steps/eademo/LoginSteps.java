package com.elsevier.bts.regional.ecom.steps.eademo;

import com.elsevier.bts.regional.ecom.framework.base.CurrentPageContext;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.pages.eademo.actions.HomePageActions;
import com.elsevier.bts.regional.ecom.pages.eademo.actions.LoginPageActions;
import com.elsevier.bts.regional.ecom.framework.base.Base;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class LoginSteps extends Base {


    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @And("^I ensure application opened$")
    public void iEnsureApplicationOpened() {
        DriverContext.goToUrl(frameworkConfigurationService.getAUT());
        log.debug("Navigated to URL " + frameworkConfigurationService.getAUT());
        CurrentPageContext.setCurrentPage(GetInstance(HomePageActions.class));
        Assertions.assertTrue(CurrentPageContext.getCurrentPage().As(HomePageActions.class).IsLoginDisplayed(), "The login page is not loaded");
    }

    @Then("^I click login link$")
    public void iClickLoginLink() {
        //Navigation to Login Page
        CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(HomePageActions.class).ClickLogin());
        DriverContext.driverSleep(25000);
    }

    @When("^I enter UserName and Password$")
    public void iEnterUserNameAndPassword(DataTable data) {
        List<List<String>> table = data.asLists();
        CurrentPageContext.getCurrentPage().As(LoginPageActions.class).Login(table.get(1).get(0), table.get(1).get(1));
    }

    @Then("^I click login button$")
    public void iClickLoginButton() {
        //Home Page
        CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(LoginPageActions.class).ClickLogin());
    }

    @Then("^I should see the username with hello$")
    public void iShouldSeeTheUsernameWithHello() throws Throwable {
        Assertions.assertEquals("The user is not admin", "Hello admin!", CurrentPageContext.getCurrentPage().As(HomePageActions.class).GetLoggedInUser());
    }


}
