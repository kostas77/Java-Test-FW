package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.IDPlusLoginPageLocators;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class IDPlusLoginPageActions extends IDPlusLoginPageLocators {

    public static boolean signedIn;

    public void accountSignIn(String username, String password) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldEmail,2);
        txtFieldEmail.EnterText(username);
        btnContinue.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldPassword,3);
        txtFieldPassword.EnterText(password);
        btnContinue.WaitForClickable().Click();
        signedIn = true;
    }

    public void createAnAccount(String mailID, List<Map<String, String>>accountDetails) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldEmail,2);
        txtFieldEmail.EnterText(mailID);
        btnContinue.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldGivenName,3);
        txtFieldGivenName.EnterText(accountDetails.get(0).get("Given Name"));
        txtFieldFamilyName.EnterText(accountDetails.get(0).get("Family Name"));
        txtFieldPassword.EnterText(accountDetails.get(0).get("Password"));
        btnContinue.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(btnContinue,2);
        btnContinue.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        signedIn = true;
    }

    public void createAnAccountMobile(String mailID, List<Map<String, String>>accountDetails) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldEmail,2);
        txtFieldEmail.EnterText(mailID);
        btnContinue.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldGivenName,3);
        txtFieldGivenName.EnterText(accountDetails.get(0).get("Given Name"));
        txtFieldFamilyName.EnterText(accountDetails.get(0).get("Family Name"));
        txtFieldPassword.EnterText(accountDetails.get(0).get("Password"));
        DriverContext.repeatWaitForElementVisibilityAttempt(btnContinue,2);
        DriverContext.jsClickOnElement(btnContinue);
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(btnContinue,2);
        DriverContext.jsClickOnElement(btnContinue);
        DriverContext.waitForPageToLoad();
        signedIn = true;
    }

    public static void resetSignedIn() {
        signedIn = false;
    }

}
