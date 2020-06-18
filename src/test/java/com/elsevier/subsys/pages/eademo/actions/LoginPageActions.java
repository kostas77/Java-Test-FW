package com.elsevier.subsys.pages.eademo.actions;

import com.elsevier.subsys.pages.eademo.locators.LoginPageLocators;


public class LoginPageActions extends LoginPageLocators {

    public void Login(String userName, String password) {
        txtUserName.EnterText(userName);
        txtPassword.sendKeys(password);
    }

    public HomePageActions ClickLogin() {
        btnLogin.WaitForVisible().Click();
        return GetInstance(HomePageActions.class);
    }

}
