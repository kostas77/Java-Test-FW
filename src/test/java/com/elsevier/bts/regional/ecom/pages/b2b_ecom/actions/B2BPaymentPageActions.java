package com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.controls.elements.CheckBox;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators.B2BPaymentPageLocators;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.JournalProductPageActions.journal;
import static com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ProductPageActions.eBook;

public class B2BPaymentPageActions extends B2BPaymentPageLocators {

    public static String orderTotal;
    public static String orderSubTotal;

    public void selectEcapturePayment(){
        DriverContext.repeatWaitForElementVisibilityAttempt(radioBtneCapture,2);
        radioBtneCapture.WaitForClickable().click();
        DriverContext.waitForPageToLoad();
    }
    public void acceptTermsAndConditions() {
        //DriverContext.waitForHTMLLoad(30, 3);
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPay, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxAcceptTnC, 2);
        DriverContext.jsScrollToElementAlignBottom(chkboxAcceptTnC);
        DriverContext.jsClickOnElement(chkboxAcceptTnC);
        DriverContext.driverSleep(20000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 2);
        orderTotal = txtOrderTotalPrice.GetTextValue();
        orderSubTotal = txrOrderSubTotal.GetTextValue();
    }

    public void proceedToPay() {
        if(DriverContext.getCurrentUrl().contains("sd-yen")){
            DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayJP, 3);
            DriverContext.jsScrollToElementAlignBottom(btnProceedToPayJP);
            btnProceedToPayJP.WaitForClickable().Click();
        }else{
            DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPay, 3);
            DriverContext.jsScrollToElementAlignBottom(btnProceedToPay);
            btnProceedToPay.WaitForClickable().Click();
        }
    }
}