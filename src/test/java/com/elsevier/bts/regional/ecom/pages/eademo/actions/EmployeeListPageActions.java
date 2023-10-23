package com.elsevier.bts.regional.ecom.pages.eademo.actions;

import com.elsevier.bts.regional.ecom.pages.eademo.locators.EmployeeListPageLocators;
import org.openqa.selenium.WebElement;


public class EmployeeListPageActions extends EmployeeListPageLocators {

    public CreateEmployeePageActions ClickCreateNew() {
        lnkCreateNew.click();
        return GetInstance(CreateEmployeePageActions.class);
    }

    public WebElement GetEmployeeList() {
        return tblEmployeeList;
    }

}
