package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.framework.utilities.JavaUtil;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
public class productPurchaseLATAMSteps extends BasePage {

    SearchPageActions searchPageActions = GetInstance(SearchPageActions.class);
    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);
    ProductPageActions productPageActions = GetInstance(ProductPageActions.class);
    ViewCartPageActions viewCartPageActions = GetInstance(ViewCartPageActions.class);
    CheckoutPageActions checkoutPageActions = GetInstance(CheckoutPageActions.class);
    PaymentPageActions paymentPageActions = GetInstance(PaymentPageActions.class);
    ECapturePageActions eCapturePageActions = GetInstance(ECapturePageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @And("I search for the E-Book related to {string}")
    public void i_search_for_the_e_book(String product) {
        hsHomePageActions.searchForProduct(product);
        DriverContext.waitForPageToLoad();
        searchPageActions.clickOnProductLink();
        DriverContext.waitForPageToLoad();
    }

    @When("I add the E-book to the cart in LATAM health store")
    public void i_add_the_e_book_to_the_cart() {
        productPageActions.addToCart();
        productPageActions.closeFreeShippingPopup();
        DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("La cantidad solicitada excede la cantidad máxima permitida en el carrito de compras 1"), "E-book added to cart message does not contain - You added");
        productPageActions.navigateToViewCartPage();
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl());
        Assertions.assertEquals("VitalSource eBook", viewCartPageActions.txtProductCategory.GetTextValue());
    }

    @And("I login from the checkout page with username {string} and password {string} in LATAM Health store")
    public void i_login_from_the_checkout_page_with_username_and_password(String username, String password) {
        ProductPurchaseCommonSteps.userMail = username;
        checkoutPageActions.customerSignInLATAM(username, password);
        DriverContext.waitForPageToLoad();
    }

    @And("I navigate to payment page and enter the billing address and payment details in LATAM Health store")
    public void i_navigate_to_payment_page_enter_the_billing_details_eu(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addDifferentBillingAddressLatam(billingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditions();
        DriverContext.driverSleep(30000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardLATAM();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetailsSP(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page to select the payment method and enter the payment details in LATAM Health store")
    public void i_navigate_to_payment_page_enter_the_payment_details(DataTable table) {
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(10000); //To be replaced in future with explicit wait
        paymentPageActions.acceptTermsAndConditionsInLATAMHS();
        DriverContext.driverSleep(35000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardLATAM();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        if (frameworkConfigurationService.getTestEnv().equals("PROD")) {
            eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                    CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                    CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                    CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
        } else {
            eCapturePageActions.fillInPaymentDetailsSP(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                    CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                    CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                    CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
        }
    }

}
