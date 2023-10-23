package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.CheckoutPageLocators;

public class CheckoutPageActions extends CheckoutPageLocators {

    public void customerCheckoutSignIn() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnSignIn, 2);
        DriverContext.jsScrollToElementAlignBottom(btnSignIn);
        DriverContext.repeatElementClickAttempt(btnSignIn,3);
        DriverContext.waitForPageToLoad();
    }

    public void continueAsGuest() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnContinueAsGuest,2);
        DriverContext.jsClickOnElement(btnContinueAsGuest.WaitForClickable());
    }

    public void customerSignInLATAM(String username, String password) {
        DriverContext.waitForPageToLoad();
        txtFieldEmail.EnterText(username);
        btnContinue.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        txtFieldPassword.EnterText(password);
        btnContinue.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        IDPlusLoginPageActions.signedIn=true;
    }

}
