package com.elsevier.subsys.pages.engage.actions;

import com.elsevier.subsys.pages.engage.locators.LoadRcIdPageLocators;


public class LoadRcIdPageActions extends LoadRcIdPageLocators {

    public ReviewerHubPageActions SignIn(String email, String password) {
        txtEmail.EnterText(email);
        btnContinue.PerformConditionalClick();
        txtPassword.WaitForVisible();
        txtPassword.EnterText(password);
        btnSignIn.PerformConditionalClick();
        return GetInstance(ReviewerHubPageActions.class);
    }

}
