package com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators.B2BECapturePageLocators;

public class B2BECapturePageActions extends B2BECapturePageLocators {

    public void fillInPaymentDetails(String cardNo, String expDate, String cvv, String name) {
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
}
