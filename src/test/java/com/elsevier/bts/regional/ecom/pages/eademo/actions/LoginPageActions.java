package com.elsevier.bts.regional.ecom.pages.eademo.actions;

import com.elsevier.bts.regional.ecom.pages.eademo.locators.LoginPageLocators;


public class LoginPageActions extends LoginPageLocators {

    public void Login(String userName, String password) {
        txtUserName.EnterText(userName);
        txtPassword.sendKeys(password);
    }

    public HomePageActions ClickLogin() {
        btnLogin.WaitForClickable().Click();
        return GetInstance(HomePageActions.class);
    }

}
