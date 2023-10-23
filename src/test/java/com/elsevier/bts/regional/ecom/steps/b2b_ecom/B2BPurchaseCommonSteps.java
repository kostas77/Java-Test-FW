package com.elsevier.bts.regional.ecom.steps.b2b_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions.*;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class B2BPurchaseCommonSteps extends BasePage {

    B2BHomePageActions b2bHomePageActions = GetInstance(B2BHomePageActions.class);
    IDPlusLoginPageActions iDPlusLoginPageActions = GetInstance(IDPlusLoginPageActions.class);
    B2BSearchPageActions b2bSearchPageActions = GetInstance(B2BSearchPageActions.class);
    B2BProductPageActions b2bProductPageActions = GetInstance(B2BProductPageActions.class);
    B2BViewCartPageActions b2bViewCartPageActions = GetInstance(B2BViewCartPageActions.class);
    B2BPaymentPageActions b2bPaymentPageActions = GetInstance(B2BPaymentPageActions.class);
    B2BECapturePageActions b2beCapturePageActions = GetInstance(B2BECapturePageActions.class);
    B2BOrderSuccessPageActions b2bOrderSuccessPageActions = GetInstance(B2BOrderSuccessPageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;
    public String b2bStoreUrl;
    public String userMail;

    @Given("I navigate to the {string} B2B store home page")
    public void i_navigate_to_the_regional_health_store_home_page(String countryName) {
        b2bStoreUrl = b2bHomePageActions.getb2bStoreUrl(countryName, frameworkConfigurationService);
        DriverContext.goToUrl(b2bStoreUrl);
        DriverContext.waitForPageToLoad();
        //log.debug("Navigated to URL " + DriverContext.getCurrentUrl());
    }

    @And("I sign-into B2B store with username {string} and password {string}")
    public void i_signin_with_username_and_password(String username, String password) {
        userMail = username;
        DriverContext.repeatElementClickAttempt(b2bHomePageActions.lnkSignIn, 3);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(iDPlusLoginPageActions.getFooter());
        iDPlusLoginPageActions.accountSignIn(username, password);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(b2bHomePageActions.txtLoggedIn, 3);
        b2bHomePageActions.navigateToHomepage();

    }

    @And("I search for the required product {string}")
    public void i_search_for_the_required_product(String product) {
        b2bHomePageActions.searchForProduct(product);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(b2bSearchPageActions.txtPrice, 2);
        Assertions.assertTrue(b2bSearchPageActions.txtPrice.isDisplayed());
        b2bSearchPageActions.clickOnProductLink();
        DriverContext.waitForPageToLoad();
    }

    @And("I search for the product {string} to add to wishlist")
    public void i_search_for_product(String product) {
        DriverContext.waitForPageToLoad();
        b2bHomePageActions.searchForProduct(product);
        DriverContext.repeatWaitForElementVisibilityAttempt(b2bSearchPageActions.txtPrice, 2);
        Assertions.assertTrue(b2bSearchPageActions.txtPrice.isDisplayed());
        DriverContext.waitForPageToLoad();
    }

    @When("I add the product to the cart BtoB site")
    public void i_add_the_product_to_the_cart_BtoB_site() {
        b2bProductPageActions.addToCart();
        DriverContext.repeatWaitForElementVisibilityAttempt(b2bProductPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(b2bProductPageActions.txtSuccessMessage.GetTextValue().contains("You added"));
        b2bProductPageActions.navigateToViewCartPage();
    }

    @And("I search for different product and add them to cart")
    public void i_search_for_different_product_and_under_product_type(DataTable table) {
        List<String> productName = table.asList(String.class);
        for (String product : productName) {
            b2bHomePageActions.searchForProduct(product);
            DriverContext.waitForPageToLoad();
            DriverContext.repeatWaitForElementVisibilityAttempt(b2bSearchPageActions.txtPrice, 2);
            Assertions.assertTrue(b2bSearchPageActions.txtPrice.isDisplayed());
            b2bSearchPageActions.clickOnProductLink();
            DriverContext.waitForPageToLoad();
            b2bProductPageActions.addToCart();
            DriverContext.repeatWaitForElementVisibilityAttempt(b2bProductPageActions.txtSuccessMessage, 2);
            Assertions.assertTrue(b2bProductPageActions.txtSuccessMessage.GetTextValue().contains("You added"));
        }
        b2bProductPageActions.navigateToViewCartPage();
    }

    @And("I proceed to the checkout pages")
    public void i_proceed_to_the_checkout_pages() {
        DriverContext.repeatWaitForElementVisibilityAttempt(b2bViewCartPageActions.txtSubTotal, 3);
        b2bViewCartPageActions.proceedToCheckout();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(b2bPaymentPageActions.footer, 2);
    }

    @And("I proceed further to payment page and enter the payment details")
    public void i_proceed_further_to_payment_page_and_enter_the_payment_details(DataTable table) {
        b2bPaymentPageActions.selectEcapturePayment();
        b2bPaymentPageActions.acceptTermsAndConditions();
        DriverContext.driverSleep(35000); // TODO: Replace in the future with a suitable explicit wait
        b2bPaymentPageActions.proceedToPay();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        b2beCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @Then("The order is displayed on the order thank you page")
    public void the_order_is_displayed_on_the_order_thank_you_page() {
        DriverContext.repeatWaitForElementVisibilityAttempt(b2bOrderSuccessPageActions.btnContinueShopping, 3);
        b2bOrderSuccessPageActions.verifyAndGetOrderNumber();
    }

    @Then("The B2B order is validated with ORR API request {string}")
    public void the_b2b_order_is_validated_with_ORR_API_request(String countryName) {
        baseURI = frameworkConfigurationService.getORR_BASE_URI();
        DriverContext.driverSleep(30000); //wait for Fulfilment status to update
        String paymentPageOrderSubTotalString;
        Number paymentPageOrderSubTotal;
        String paymentPageOrderTotalString;
        Number paymentPageOrderTotal;
        switch (countryName) {
            case "US EOP", "US SP" -> {
                paymentPageOrderTotalString = B2BPaymentPageActions.orderTotal;
                paymentPageOrderSubTotalString = B2BPaymentPageActions.orderSubTotal;
                if (paymentPageOrderTotalString.contains(".00")) {
                    paymentPageOrderTotal = Integer.parseInt(paymentPageOrderTotalString.replace("$", "").replace("US", "").replace(",", ".").replace(" ", "").replaceAll("\\.0*$", ""));
                } else {
                    paymentPageOrderTotal = Float.parseFloat(paymentPageOrderTotalString.replace("$", "").replace("US", "").replace(",", ""));
                }
                if (paymentPageOrderSubTotalString.contains(".00")) {
                    paymentPageOrderSubTotal = Integer.parseInt(paymentPageOrderSubTotalString.replace("$", "").replace("US", "").replace(",", ".").replace(" ", "").replaceAll("\\.0*$", ""));
                } else {
                    paymentPageOrderSubTotal = Float.parseFloat(paymentPageOrderSubTotalString.replace("$", "").replace("US", "").replace(",", ""));
                }
                given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                        .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber.replace("H", "h")))
                        .body("subtotalNetAmount", equalTo(paymentPageOrderSubTotal))
                        .body("subtotalTaxAmount", equalTo(Float.parseFloat(PaymentPageActions.orderTax.replace("$", "").replace(",", ""))))
                        .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                        .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                        //This validation needs to be used after RSR-6256 & RSR-6253 is fixed
                        //.body("totalDiscountAmount", equalTo(discountedPrice))
                        .body("customerEmail", equalTo(userMail))
                        .body("status", equalTo("OK")).log();
                //.body("items[0].fulfilmentLineStatus", equalTo("PROCESSING")).log();
            }
        }
    }

    @And("I empty the B2B store cart")
    public void i_empty_the_cart() {
        b2bHomePageActions.navigateToViewCartPageUrl();
        DriverContext.waitForPageToLoad();
        DriverContext.waitForHTMLLoad(30000, 5000);
        b2bViewCartPageActions.emptyCart();
        DriverContext.goToUrl(b2bStoreUrl);
        DriverContext.waitForPageToLoad();
    }

}

