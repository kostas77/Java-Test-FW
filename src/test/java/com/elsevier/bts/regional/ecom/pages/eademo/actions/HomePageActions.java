package com.elsevier.bts.regional.ecom.pages.eademo.actions;

import com.elsevier.bts.regional.ecom.pages.eademo.locators.HomePageLocators;


public class HomePageActions extends HomePageLocators {

    public LoginPageActions ClickLogin() {
        lnkLogin.ClickLink();
        return GetInstance(LoginPageActions.class);
    }

    public boolean IsLoginDisplayed() {
        return lnkLogin.isDisplayed();
    }

    public String GetLoggedInUser() {
        return lnkUserName.getText();
    }

    public EmployeeListPageActions ClickEmployeeList() {
        lnkEmployeeList.WaitForClickable().Click();
        return GetInstance(EmployeeListPageActions.class);
    }
}
