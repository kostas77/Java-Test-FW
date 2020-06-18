package com.elsevier.subsys.pages.eademo.actions;

import com.elsevier.subsys.pages.eademo.locators.EmployeeListPageLocators;
import org.openqa.selenium.WebElement;


public class EmployeeListPageActions extends EmployeeListPageLocators {

    public CreateEmployeePageActions ClickCreateNew()
    {
        lnkCreateNew.click();
        return GetInstance(CreateEmployeePageActions.class);
    }

    public WebElement GetEmployeeList()
    {
        return tblEmployeeList;
    }

}
