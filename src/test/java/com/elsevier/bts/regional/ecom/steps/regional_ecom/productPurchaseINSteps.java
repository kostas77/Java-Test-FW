package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ECapturePageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.HSHomePageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.OrderSuccessPageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.PaymentPageActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class productPurchaseINSteps extends BasePage {

    PaymentPageActions paymentPageActions = GetInstance(PaymentPageActions.class);
    ECapturePageActions eCapturePageActions = GetInstance(ECapturePageActions.class);
    OrderSuccessPageActions orderSuccessPageActions = GetInstance(OrderSuccessPageActions.class);
    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @And("I navigate to payment page and enter the billing address and payment details in IN-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details(DataTable table) {
        List<Map<String, String>> PaymentDetails = table.asMaps(String.class, String.class);
        DriverContext.driverSleep(30000);
        String paymentMethod = paymentPageActions.findPaymentMethodIN();
        if (!(paymentMethod.equals("No Payment Information Required"))) {
            paymentPageActions.selectPaymentMethodInINHS(PaymentDetails);
            DriverContext.waitForPageToLoad();
            paymentPageActions.addBillingDetailsForExistingUserIN(PaymentDetails);
            DriverContext.waitForPageToLoad();
            DriverContext.driverSleep(10000);
            paymentPageActions.proceedToPayWithCreditCardIndia();
            DriverContext.waitForPageToLoad();
            if (PaymentDetails.get(0).get("Payment Method").equals("Credit Card")) {
                eCapturePageActions.selectCreditCardPayment();
                CucumberUtil.ConvertDataTableToDict(table);
                eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                        CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                        CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                        CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
                eCapturePageActions.submitPayment();
                DriverContext.driverSleep(50000);
                eCapturePageActions.enterOTP(CucumberUtil.GetCellValueWithRowIndex("OTP", 1));

            } else if (PaymentDetails.get(0).get("Payment Method").equals("UPI")) {
                eCapturePageActions.selectUPIPayment();
                CucumberUtil.ConvertDataTableToDict(table);
                eCapturePageActions.fillUPIDetails(CucumberUtil.GetCellValueWithRowIndex("UPI ID", 1));
                eCapturePageActions.continueUPIPayment();
                DriverContext.waitForPageToLoad();
                if (frameworkConfigurationService.getTestEnv().equals("UAT")) {
                    DriverContext.driverSleep(300000);
                }
                //DriverContext.consoleData();
                //DriverContext.driverSleep(2000000000);
            } else {
                System.out.println("No Payment method is displayed.");
                paymentPageActions.addBillingDetailsForExistingUserIN(PaymentDetails);
                DriverContext.waitForPageToLoad();
            }
        }

    }

    @And("I place the order in Indian Health Store")
    public void i_place_the_order() {
        DriverContext.driverSleep(30000);
        eCapturePageActions.submitPaymentIN();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(orderSuccessPageActions.btnContinueShopping, 3);
    }

    @And("I navigate to Clinical Key Now Page in India Health Store")
    public void i_navigate_to_clinical_key_now_page() {
        hsHomePageActions.navigateToClinicalKeyNowPageInIndiaHS();
    }

    @And("I navigate to payment page as a existing user and select a billing address and enter payment details IN-HS")
    public void i_navigate_to_payment_page_as_existing_user_and_enter_the_payment_details(DataTable table) {
        DriverContext.waitForPageToLoad();
        paymentPageActions.proceedToPayWithCreditCardIndia();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }
}
