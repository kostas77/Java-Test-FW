package com.elsevier.subsys.steps.eademo;

import com.elsevier.subsys.framework.base.Base;
import com.elsevier.subsys.framework.base.CurrentPageContext;
import com.elsevier.subsys.framework.base.DriverContext;
import com.elsevier.subsys.framework.config.FrameworkConfigurationService;
import com.elsevier.subsys.pages.eademo.actions.HomePageActions;
import com.elsevier.subsys.pages.eademo.actions.LoginPageActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class LoginSteps extends Base {


    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @And("^I ensure application opened$")
    public void iEnsureApplicationOpened()  {
        DriverContext.goToUrl(frameworkConfigurationService.getAUT());
        log.debug("Navigated to URL " + frameworkConfigurationService.getAUT());
        CurrentPageContext.setCurrentPage(GetInstance(HomePageActions.class));
        Assert.assertTrue("The login page is not loaded", CurrentPageContext.getCurrentPage().As(HomePageActions.class).IsLoginDisplayed());
    }

    @Then("^I click login link$")
    public void iClickLoginLink()  {
        //Navigation to Login Page
        CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(HomePageActions.class).ClickLogin());
        DriverContext.DriverSleep(25000);
    }

    @When("^I enter UserName and Password$")
    public void iEnterUserNameAndPassword(DataTable data)  {
        List<List<String>> table = data.asLists();
        CurrentPageContext.getCurrentPage().As(LoginPageActions.class).Login(table.get(1).get(0), table.get(1).get(1));
    }

    @Then("^I click login button$")
    public void iClickLoginButton()  {
        //Home Page
        CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(LoginPageActions.class).ClickLogin());
    }

    @Then("^I should see the username with hello$")
    public void iShouldSeeTheUsernameWithHello() throws Throwable {
        Assert.assertEquals("The user is not admin", "Hello admin!", CurrentPageContext.getCurrentPage().As(HomePageActions.class).GetLoggedInUser());
    }


}
