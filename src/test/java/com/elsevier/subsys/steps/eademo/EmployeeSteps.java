package com.elsevier.subsys.steps.eademo;

import com.elsevier.subsys.framework.base.CurrentPageContext;
import com.elsevier.subsys.framework.base.DriverContext;
import com.elsevier.subsys.framework.utilities.CucumberUtil;
import com.elsevier.subsys.pages.eademo.actions.CreateEmployeePageActions;
import com.elsevier.subsys.pages.eademo.actions.EmployeeListPageActions;
import com.elsevier.subsys.pages.eademo.actions.HomePageActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeSteps {


    @And("^I click employeeList link$")
    public void iClickEmployeeListLink() throws Throwable {
        CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(HomePageActions.class).ClickEmployeeList());
        DriverContext.waitForPageToLoad();
//        if (!wait.until(titleContains(resultsTitle)) || !wait.until(urlContains(resultsUrl)))
//            throw new RuntimeException("results page is not displayed"); // TODO: Useful technique that verifies the page has loaded
        DriverContext.DriverSleep(25000); // TODO: Only temporary for demonstration purposes
    }

    @Then("^I click create new button$")
    public void iClickCreatenewButton() throws Throwable {
        CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(EmployeeListPageActions.class).ClickCreateNew());
        DriverContext.waitForPageToLoad();
    }

    @And("^I enter following details$")
    public void iEnterFollowingDetails(DataTable table) throws Throwable {
        CucumberUtil.ConvertDataTableToDict(table);
        CurrentPageContext.getCurrentPage().As(CreateEmployeePageActions.class).CreateEmployee(CucumberUtil.GetCellValueWithRowIndex("Name", 2), CucumberUtil.GetCellValueWithRowIndex("Salary", 2),
                CucumberUtil.GetCellValueWithRowIndex("DurationWorked", 2), CucumberUtil.GetCellValueWithRowIndex("Grade", 2), CucumberUtil.GetCellValueWithRowIndex("Email", 2));
    }

    @And("^I click create button$")
    public void iClickCreateButton() throws Throwable {
        CurrentPageContext.getCurrentPage().As(CreateEmployeePageActions.class).ClickCreateButton();
    }

}
