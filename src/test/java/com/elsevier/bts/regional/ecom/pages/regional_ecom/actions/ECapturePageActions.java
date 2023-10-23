package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.ECapturePageLocators;

import java.util.List;
import java.util.Map;

public class ECapturePageActions extends ECapturePageLocators {
    public static boolean payPal;
    public void fillInPaymentDetails(String cardNo, String expDate, String cvv, String name) {
        if (!ClinicalKeyProductPageActions.clinicalKeyNow) {
            DriverContext.repeatWaitForElementVisibilityAttempt(creditCardCheckout, 2);
            creditCardCheckout.WaitForClickable().Click();
        }
        DriverContext.repeatWaitForElementVisibilityAttempt(iFrameCardNumber, 2);
        DriverContext.switchToFrame(iFrameCardNumber);
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldCardNumber,2);
        txtFieldCardNumber.EnterText(cardNo);
        DriverContext.switchToDefaultContent();
        DriverContext.switchToFrame(iFrameExpiryDate);
        txtFieldExpiryDate.EnterText(expDate);
        DriverContext.switchToDefaultContent();
        DriverContext.switchToFrame(iFrameCVV);
        txtFieldCVV.EnterText(cvv);
        DriverContext.switchToDefaultContent();
        txtFieldCardHolderName.EnterText(name);
    }

    public void fillUPIDetails(String UPIID){
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldUPIID, 2);
        txtFieldUPIID.sendKeys(UPIID);
    }
    public void fillInPaymentDetailsSP(String cardNo, String expDate, String cvv, String name) {
        DriverContext.repeatWaitForElementVisibilityAttempt(iFrameCardNumber, 2);
        DriverContext.switchToFrame(iFrameCardNumber);
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldCardNumber,2);
        txtFieldCardNumber.EnterText(cardNo);
        DriverContext.switchToDefaultContent();
        DriverContext.switchToFrame(iFrameExpiryDate);
        txtFieldExpiryDate.EnterText(expDate);
        DriverContext.switchToDefaultContent();
        DriverContext.switchToFrame(iFrameCVV);
        txtFieldCVV.EnterText(cvv);
        DriverContext.switchToDefaultContent();
        txtFieldCardHolderName.EnterText(name);
    }

    public void fillInPaymentDetailsGermany(String cardNo, String expDate, String cvv, String name) {
            DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldCardNumberGermany, 2);
            txtFieldCardNumberGermany.EnterText(cardNo);
            txtFieldExpiryDateGermany.EnterText(expDate);
            txtFieldCVVGermany.EnterText(cvv);
            txtFieldCardHolderNameGermany.EnterText(name);
    }

    public void submitPayment() {
        DriverContext.jsScrollToElementAlignBottom(btnSubmitPayment);
        btnSubmitPayment.WaitForClickable().Click();
    }

    public void continueUPIPayment(){
        DriverContext.jsScrollToElementAlignBottom(btnContinueUPIPayment);
        btnContinueUPIPayment.WaitForClickable().Click();
    }


    public void submitPaymentMobile() {
        DriverContext.jsScrollToElementAlignBottom(btnSubmitPayment);
        DriverContext.jsClickOnElement(btnSubmitPayment);
    }

    public void submitPaymentANZ() {
        DriverContext.jsScrollToElementAlignBottom(btnSubmitPaymentANZ);
        btnSubmitPaymentANZ.WaitForClickable().Click();
    }

    public void fillInPaymentDetailsFrance( List<Map<String, String>> paymentDetails) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldCardNumberFrance,3);
        txtFieldCardNumberFrance.EnterText(paymentDetails.get(0).get("Card Number"));
        DriverContext.selectDropdownListElementUsingVisibleText(txtFieldExpiryMonthFrance,paymentDetails.get(0).get("Expiry Month"));
        DriverContext.selectDropdownListElementUsingVisibleText(txtFieldExpiryYearFrance,paymentDetails.get(0).get("Expiry Year"));
        txtFieldCVVFrance.EnterText(paymentDetails.get(0).get("CVV"));
        txtFieldCardHolderNameFrance.EnterText(paymentDetails.get(0).get("Name on card"));
        DriverContext.selectDropdownListElementUsingVisibleText(paymentCardType, paymentDetails.get(0).get("Card type"));
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxAcceptTnC, 2);
        DriverContext.jsClickOnElement(chkboxAcceptTnC.WaitForClickable());

    }

    public void fillInPaymentDetailsANZ( List<Map<String, String>> paymentDetails) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldCardNumberFrance, 3);
        txtFieldCardNumberFrance.EnterText(paymentDetails.get(0).get("Card Number"));
        txtFieldExpiryDateANZ.EnterText(paymentDetails.get(0).get("Expiry Date"));
        txtFieldExpiryCVC.EnterText(paymentDetails.get(0).get("CVV"));
        txtFieldCardHolderNameANZ.EnterText(paymentDetails.get(0).get("Name on card"));
    }

    public void submitPaymentFrance() {
        btnSubmitPayment.WaitForClickable().Click();
        DriverContext.repeatWaitForElementVisibilityAttempt(paymentAuth,4);
        paymentAuth.WaitForClickable().Click();
    }

    public void payPalPaymentDetails(String userName, String password) {
        DriverContext.repeatWaitForElementVisibilityAttempt(payPalCheckout, 2);
        payPalCheckout.WaitForClickable().Click();
        DriverContext.repeatWaitForElementVisibilityAttempt(payPalCheckoutButton, 2);
        payPalCheckoutButton.WaitForClickable().Click();
        payPal = true;
        DriverContext.switchToTab(1);
        DriverContext.repeatWaitForElementVisibilityAttempt(payPalEmailAddress,2);
        payPalEmailAddress.clear();
        payPalEmailAddress.EnterText(userName);
        btnPayPalnext.WaitForClickable().Click();
        DriverContext.repeatWaitForElementVisibilityAttempt(payPalPassword,2);
        payPalPassword.EnterText(password);
        DriverContext.repeatWaitForElementVisibilityAttempt(payPalLogin, 2);
        payPalLogin.Click();
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(10000);// TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(payPalPaymentConfirmation, 2);
        payPalPaymentConfirmation.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.switchToTab(0);
    }

    public void enterOTP(String otp) {
        txtOTP.sendKeys(otp);
    }

    public void submitPaymentIN() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnSubmitOtp, 2);
        btnSubmitOtp.WaitForClickable().click();
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(30000);// TODO: Replace in the future with a suitable explicit wait
    }

    public void selectCreditCardPayment() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnCreditCard, 2);
        btnCreditCard.WaitForClickable().click();
        DriverContext.waitForPageToLoad();
    }

    public void selectUPIPayment() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnUPI, 2);
        btnUPI.WaitForClickable().click();
        DriverContext.waitForPageToLoad();
    }

    public void payPalPaymentExpressDetails(String userName, String password) {
        payPal = true;
        DriverContext.repeatWaitForElementVisibilityAttempt(payPalEmailAddress, 2);
        payPalEmailAddress.clear();
        payPalEmailAddress.EnterText(userName);
        DriverContext.repeatWaitForElementVisibilityAttempt(payPalPassword, 2);
        payPalPassword.EnterText(password);
        DriverContext.repeatWaitForElementVisibilityAttempt(payPalLogin, 2);
        payPalLogin.Click();
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(10000);// TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(payPalExpressPaymentConfirmation, 2);
        payPalExpressPaymentConfirmation.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void enterPasswordAndSubmitPayment() {
        DriverContext.waitForPageToLoad();
        DriverContext.switchToFrame(iFrameMasterCard);
        DriverContext.repeatWaitForElementVisibilityAttempt(txtPasswordMasterCard, 2);
        txtPasswordMasterCard.EnterText("password");
        btnOKMasterCard.WaitForClickable().Click();

    }
     public static void resetPaypal() {
        payPal = false;
    }

}
