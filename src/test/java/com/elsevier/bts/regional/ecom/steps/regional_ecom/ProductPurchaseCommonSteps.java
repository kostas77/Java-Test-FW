package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Slf4j
public class ProductPurchaseCommonSteps extends BasePage {

    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);
    MyAccountActions myAccountActions = GetInstance(MyAccountActions.class);
    ViewCartPageActions viewCartPageActions = GetInstance(ViewCartPageActions.class);
    IDPlusLoginPageActions iDPlusLoginPageActions = GetInstance(IDPlusLoginPageActions.class);
    ProductPageActions productPageActions = GetInstance(ProductPageActions.class);
    CheckoutPageActions checkoutPageActions = GetInstance(CheckoutPageActions.class);
    ShippingPageActions shippingPageActions = GetInstance(ShippingPageActions.class);
    OrderSuccessPageActions orderSuccessPageActions = GetInstance(OrderSuccessPageActions.class);
    ORRLoginPageActions orrLoginPageActions = GetInstance(ORRLoginPageActions.class);
    AdminPanelActions adminPanelActions = GetInstance(AdminPanelActions.class);
    ORROrderSummaryPageActions orrOrderSummaryPageActions = GetInstance(ORROrderSummaryPageActions.class);
    PaymentPageActions paymentPageActions = GetInstance(PaymentPageActions.class);
    ECapturePageActions eCapturePageActions = GetInstance(ECapturePageActions.class);
    SearchPageActions searchPageActions = GetInstance(SearchPageActions.class);
    MobileActions mobileActions = GetInstance(MobileActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    public String healthStoreUrl;
    public static String guestUserMail;
    public static String mailID, userMail;

    public static String expectedDiscountValue;
    public static int discountedPrice = 0;
    public static int cartItemCount;

    @Given("I navigate to the {string} regional health store home page")
    public void i_navigate_to_the_regional_health_store_home_page(String countryName) {
        IDPlusLoginPageActions.resetSignedIn();
        ViewCartPageActions.resetCartItemCount();
        ProductPageActions.resetEBook();
        ECapturePageActions.resetPaypal();
        ClinicalKeyProductPageActions.resetClinicalKey();
        healthStoreUrl = hsHomePageActions.getHealthStoreUrl(countryName, frameworkConfigurationService);
        DriverContext.goToUrl(healthStoreUrl);
        DriverContext.waitForPageToLoad();
        log.debug("Navigated to URL " + DriverContext.getCurrentUrl());
        if (!countryName.equalsIgnoreCase("India")) {
            DriverContext.repeatWaitForElementVisibilityAttempt(hsHomePageActions.btnAcceptAllCookies, 2);
            if (DriverContext.doesElementExist(hsHomePageActions.btnAcceptAllCookies)) {
                hsHomePageActions.acceptCookies();
            }
        }
        if (frameworkConfigurationService.getTestEnv().equalsIgnoreCase("prod") && countryName.equals("US")) {
            //hsHomePageActions.nostoPopUp(); Commenting this code for the Sale in US store
        }
    }

    @Given("I navigate to the {string} regional health store home page via mobile")
    public void i_navigate_to_the_regional_health_store_home_page_via_mobile(String countryName) {
        IDPlusLoginPageActions.resetSignedIn();
        ViewCartPageActions.resetCartItemCount();
        ProductPageActions.resetEBook();
        ClinicalKeyProductPageActions.resetClinicalKey();
        healthStoreUrl = hsHomePageActions.getHealthStoreUrl(countryName, frameworkConfigurationService);
        DriverContext.goToUrl(healthStoreUrl);
        DriverContext.waitForPageToLoad();
        log.debug("Navigated to URL " + DriverContext.getCurrentUrl());
        if (!countryName.equalsIgnoreCase("India")) {
            if (DriverContext.doesElementExist(hsHomePageActions.btnAcceptAllCookies)) {
                hsHomePageActions.acceptCookies();
            }
        }
    }

    @And("Switch Test Environment to {string}")
    public void switch_test_environment(String environment) {
        frameworkConfigurationService.setTestEnv(environment);
    }

    @And("I sign-up as a new user")
    public void i_sign_up_as_a_new_user(DataTable table) {
        List<Map<String, String>> accountDetails = table.asMaps(String.class, String.class);
        DriverContext.repeatElementClickAttempt(hsHomePageActions.createAnAccount, 3);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(iDPlusLoginPageActions.getFooter());
        userMail = "test.elsevier.regional.ecom+" + Instant.now().getEpochSecond() + "@gmail.com";
        iDPlusLoginPageActions.createAnAccount(userMail, accountDetails);
        DriverContext.waitForPageToLoad();
        myAccountActions.addProfileInformation(accountDetails);
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        System.out.println(userMail);
    }

    @And("I sign-up as a new user in the checkout")
    public void i_sign_up_as_a_new_user_checkout(DataTable table) {
        List<Map<String, String>> accountDetails = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        if (!ProductPageActions.eBook && !ClinicalKeyProductPageActions.clinicalKey) {
            checkoutPageActions.customerCheckoutSignIn();
            DriverContext.waitForPageToLoad();
        }
        DriverContext.waitUntilElementIsVisible(iDPlusLoginPageActions.getFooter());
        userMail = "test.elsevier.regional.ecom+" + Instant.now().getEpochSecond() + "@gmail.com";
        iDPlusLoginPageActions.createAnAccount(userMail, accountDetails);
        DriverContext.waitForPageToLoad();
        //myAccountActions.addProfileInformation(accountDetails);
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        System.out.println(userMail);
    }

    @And("I sign-up as a new user via mobile")
    public void i_sign_up_as_a_new_user_via_mobile(DataTable table) {
        List<Map<String, String>> accountDetails = table.asMaps(String.class, String.class);
        DriverContext.repeatElementClickAttempt(hsHomePageActions.mobileUserIcon, 3);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(iDPlusLoginPageActions.getFooter());
        userMail = "test.elsevier.regional.ecom+" + Instant.now().getEpochSecond() + "@gmail.com";
        iDPlusLoginPageActions.createAnAccountMobile(userMail, accountDetails);
        DriverContext.waitForPageToLoad();
        myAccountActions.addProfileInformation(accountDetails);
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
    }

    @And("I sign-in with username {string} and password {string}")
    public void i_signin_with_username_and_password(String username, String password) {
        userMail = username;
        DriverContext.repeatElementClickAttempt(hsHomePageActions.lnkSignIn, 3);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(iDPlusLoginPageActions.getFooter());
        iDPlusLoginPageActions.accountSignIn(username, password);
        DriverContext.waitForPageToLoad();
        hsHomePageActions.closePromotionalPopup();
        DriverContext.repeatWaitForElementVisibilityAttempt(hsHomePageActions.txtLoggedIn, 3);
    }

    @And("I sign-in with username {string} and password {string} via mobile")
    public void i_signin_with_username_and_password_via_mobile(String username, String password) {
        userMail = username;
        DriverContext.repeatWaitForElementVisibilityAttempt(mobileActions.mobileUserIcon, 2);
        DriverContext.repeatElementClickAttempt(mobileActions.mobileUserIcon, 2);
        iDPlusLoginPageActions.accountSignIn(username, password);
        DriverContext.waitForPageToLoad();
    }

    @And("I login from the checkout page with username {string} and password {string}")
    public void i_login_from_the_checkout_page_with_username_and_password(String username, String password) {
        userMail = username;
        checkoutPageActions.customerCheckoutSignIn();
        iDPlusLoginPageActions.accountSignIn(username, password);
        DriverContext.waitForPageToLoad();
    }

    @And("I enter shipping details as a guest user")
    public void i_enter_shipping_details_as_a_guest_user(DataTable table) {
        guestUserMail = "test.elsevier.regional.ecom+" + Instant.now().getEpochSecond() + "@gmail.com";
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        DriverContext.repeatWaitForElementVisibilityAttempt(shippingPageActions.footer, 2);
        shippingPageActions.addShippingDetailsAsGuestUser(guestUserMail, address);
        shippingPageActions.proceedToReviewAndPayments();
        DriverContext.waitForPageToLoad();
    }

    @And("I empty the cart")
    public void i_empty_the_cart() {
        if (DriverContext.doesElementExist(hsHomePageActions.btnAcceptAllCookies)) {
            hsHomePageActions.acceptCookies();
        }
        hsHomePageActions.navigateToViewCartPageUrl();
        DriverContext.waitForPageToLoad();
        DriverContext.waitForHTMLLoad(30000, 5000);
//        DriverContext.waitUntilElementIsVisible(viewCartPageActions.txtCartEmptyMessage);
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
        viewCartPageActions.emptyCart();
        DriverContext.goToUrl(healthStoreUrl);
        DriverContext.waitForPageToLoad();
    }

    @And("I empty the cart via mobile")
    public void i_empty_the_cart_via_mobile() {
        if (DriverContext.doesElementExist(hsHomePageActions.btnAcceptAllCookies)) {
            hsHomePageActions.acceptCookies();
        }
        hsHomePageActions.navigateToViewCartPageUrl();
        DriverContext.waitForPageToLoad();
        DriverContext.waitForHTMLLoad(30000, 5000);
//        DriverContext.waitUntilElementIsVisible(viewCartPageActions.txtCartEmptyMessage);
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
        mobileActions.emptyCartMobile();
        DriverContext.goToUrl(healthStoreUrl);
        DriverContext.waitForPageToLoad();
    }


    @Then("I apply for a offer code {string} in {string}")
    public void i_apply_for_a_offer_code(String offerCode, String countryName) {
        float subTotal;
        String expectedDiscount, expectedGrandTotal;
        switch (countryName) {
            case "US" -> {
                DriverContext.repeatWaitForElementVisibilityAttempt(viewCartPageActions.txtTaxInfo, 3);
                viewCartPageActions.applyOfferCode(offerCode);
                DriverContext.waitForPageToLoad();
                if (frameworkConfigurationService.getTestEnv().equals("UAT")) {
                    DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
                    Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Coupon code \"30offtest\" was applied."));
                    subTotal = Float.parseFloat(viewCartPageActions.txtSubTotal.GetTextValue().replace("$", "").replace(",", ""));
                    expectedDiscount = String.format("%.2f", subTotal * 0.3);
                    expectedGrandTotal = String.format("%.2f", subTotal - (subTotal * 0.3));
                    Assertions.assertTrue(viewCartPageActions.txtDiscountTitle.GetTextValue().contains(offerCode));
                    Assertions.assertEquals(expectedDiscount, viewCartPageActions.txtDiscount.GetTextValue().replace("$", "").replace("-", ""));
                    Assertions.assertEquals(expectedGrandTotal, viewCartPageActions.txtGrandTotal.GetTextValue().replace("$", "").replace(",", ""));
                }
            }
            case "EU" -> {
                DriverContext.repeatWaitForElementVisibilityAttempt(viewCartPageActions.txtTaxInfo, 3);
                viewCartPageActions.applyOfferCode(offerCode);
                DriverContext.waitForPageToLoad();
                if (frameworkConfigurationService.getTestEnv().equals("UAT")) {
                    DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
                    Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Coupon code \"FixedDiscount25\" was applied."));
                    subTotal = Float.parseFloat(viewCartPageActions.txtSubTotal.GetTextValue().replace("€", "").replace(",", ""));
                    expectedGrandTotal = String.format("%.2f", subTotal - 25);
                    Assertions.assertTrue(viewCartPageActions.txtDiscountTitle.GetTextValue().contains("25 fixed discount on products in cart"));
                    Assertions.assertEquals("25.00", viewCartPageActions.txtDiscount.GetTextValue().replace("€", "").replace("-", ""));
                    Assertions.assertEquals(expectedGrandTotal, viewCartPageActions.txtGrandTotal.GetTextValue().replace("€", "").replace(",", ""));
                }
            }

            case "FR", "DE" -> {
                DriverContext.repeatWaitForElementVisibilityAttempt(viewCartPageActions.txtTaxInfo, 3);
                viewCartPageActions.applyOfferCode(offerCode);
                DriverContext.waitForPageToLoad();
            }
        }
    }

    @Then("The order is displayed on the thank you page")
    public void the_order_is_displayed_on_the_thank_you_page() {
        DriverContext.repeatWaitForElementVisibilityAttempt(orderSuccessPageActions.btnContinueShopping, 3);
        orderSuccessPageActions.verifyAndGetOrderNumber();
    }

    @When("I login into {string} ORR")
    public void i_login_to_orr(String orr_HS) {
        DriverContext.goToUrl(frameworkConfigurationService.getORR_Url());
        DriverContext.waitForPageToLoad();
        orrLoginPageActions.ORRLoginIn(frameworkConfigurationService.getORR_USERNAME(), frameworkConfigurationService.getORR_PASSWORD());
        orrLoginPageActions.navigateToORRHealthStoreSummaryPage(orr_HS);
    }

    @Then("The order is validated with ORR API request {string}")
    public void the_order_is_validated_with_ORR_API_request(String countryName) {
        baseURI = frameworkConfigurationService.getORR_BASE_URI();
        if (!IDPlusLoginPageActions.signedIn) {
            mailID = guestUserMail;
        } else {
            mailID = userMail;
        }
        DriverContext.driverSleep(30000); //wait for Fulfilment status to update
        String paymentPageOrderSubTotalString;
        Number paymentPageOrderSubTotal;
        String paymentPageOrderTotalString;
        Number paymentPageOrderTotal;
        String paymentPageOrderTaxTotalString;
        Number paymentPageOrderTaxTotal;
        switch (countryName) {
            case "US" -> {
                paymentPageOrderTotalString = PaymentPageActions.orderTotal;
                paymentPageOrderSubTotalString = PaymentPageActions.orderSubTotal;
                paymentPageOrderTaxTotalString = PaymentPageActions.orderTax;
                if (paymentPageOrderTotalString.contains(".00")) {
                    paymentPageOrderTotal = Integer.parseInt(paymentPageOrderTotalString.replaceAll("[^\\d.]", "").replaceAll("\\.0*$", ""));
                } else {
                    paymentPageOrderTotal = Float.parseFloat(paymentPageOrderTotalString.replaceAll("[^\\d.]", ""));
                }
                if (paymentPageOrderSubTotalString.contains(".00")) {
                    paymentPageOrderSubTotal = Integer.parseInt(paymentPageOrderSubTotalString.replaceAll("[^\\d.]", "").replaceAll("\\.0*$", ""));
                } else {
                    paymentPageOrderSubTotal = Float.parseFloat(paymentPageOrderSubTotalString.replaceAll("[^\\d.]", ""));
                }
                log.info("Payment page order total: " +paymentPageOrderTotal);
                log.info("Payment page order sub total: " +paymentPageOrderSubTotal);

                if (ProductPageActions.eBook || JournalProductPageActions.journal) {
                    given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                            .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber.replace("H", "h")))
                            .body("subtotalNetAmount", equalTo(paymentPageOrderSubTotal))
                            .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                            .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                            //This validation needs to be used after RSR-6256 & RSR-6253 is fixed
                            //.body("totalDiscountAmount", equalTo(discountedPrice))
                            .body("customerEmail", equalTo(mailID))
                            .body("status", equalTo("OK")).log();
                } else {
                    if (paymentPageOrderTaxTotalString.equals("0")) {
                        paymentPageOrderTaxTotal = Integer.parseInt(paymentPageOrderTaxTotalString.replaceAll("[^\\d.]", ""));
                    }else{
                        paymentPageOrderTaxTotal = Float.parseFloat(paymentPageOrderTaxTotalString.replaceAll("[^\\d.]", ""));
                    }
                    log.info("Payment page order tax: " +paymentPageOrderTaxTotal);
                    given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                            .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber.replace("H", "h")))
                            .body("subtotalNetAmount", equalTo(paymentPageOrderSubTotal))
                            .body("subtotalTaxAmount", equalTo(paymentPageOrderTaxTotal))
                            .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                            .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                            //This validation needs to be used after RSR-6256 & RSR-6253 is fixed
                            //.body("totalDiscountAmount", equalTo(discountedPrice))
                            .body("customerEmail", equalTo(mailID))
                            .body("status", equalTo("OK")).log();
                }
            }
//                 if (!JournalProductPageActions.journal) {
//                     given()
//                             //.auth().oauth2(AWSHelpers.getSecret(frameworkConfigurationService.getAWS_SecretName())) //TODO: Remove comment when AWS access key is available
//                             .get("/orders/" + OrderSuccessPageActions.orderNumber + "/events").then().statusCode(200)
//                             .body("[0].type", equalTo("SEND_TO_DELTA")).body("[0].status", equalTo("Success"))
//                             .body("[1].type", equalTo("SEND_TO_FULFILMENT")).body("[1].status", equalTo("Success"))
//                             .body("[4].type", equalTo("ROUTING_TO_DELTA")).body("[4].status", equalTo("Success"))
//                             .log();
//                 } else if (ViewCartPageActions.cartItemCount >= 2) {
//                     given()
//                             //.auth().oauth2(AWSHelpers.getSecret(frameworkConfigurationService.getAWS_SecretName())) //TODO: Remove comment when AWS access key is available
//                             .get("/orders/" + OrderSuccessPageActions.orderNumber + "/events").then().statusCode(200)
//                             .body("[0].type", equalTo("SEND_TO_DELTA")).body("[0].status", equalTo("Success"))
//                             .body("[1].type", equalTo("SEND_TO_FULFILMENT")).body("[1].status", equalTo("Success"))
//                             .body("[3].type", equalTo("ROUTING_TO_DELTA")).body("[3].status", equalTo("Success"))
//                             .body("[4].type", equalTo("ROUTING_TO_ADVANTAGE")).body("[4].status", equalTo("Success"))
//                             .body("[6].type", equalTo("NOTHING_TO_PROCESS")).body("[6].status", equalTo("Success"))
//                             .body("[8].type", equalTo("FETCHING_TOKEN")).body("[8].status", equalTo("Success"))
//                             .log();
//                 } else {
//                     given()
//                             //.auth().oauth2(AWSHelpers.getSecret(frameworkConfigurationService.getAWS_SecretName())) //TODO: Remove comment when AWS access key is available
//                             .get("/orders/" + OrderSuccessPageActions.orderNumber + "/events").then().statusCode(200)
//                             .body("[1].type", equalTo("ROUTING_TO_ADVANTAGE")).body("[1].status", equalTo("Success"))
//                             .body("[3].type", equalTo("NOTHING_TO_PROCESS")).body("[3].status", equalTo("Success"))
//                             .body("[5].type", equalTo("FETCHING_TOKEN")).body("[5].status", equalTo("Success"))
//                             .log();
//                 }
            case "UK" -> {
                paymentPageOrderTotalString = PaymentPageActions.orderTotal;
                if (paymentPageOrderTotalString.contains(".00")) {
                    paymentPageOrderTotal = Integer.parseInt(paymentPageOrderTotalString.replaceAll("[^\\d.]", "").replaceAll("\\.0*$", ""));
                } else {
                    paymentPageOrderTotal = Float.parseFloat(paymentPageOrderTotalString.replaceAll("[^\\d.]", "").replace(" ", ""));
                }
                given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                        .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                        .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                        .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                        .body("customerEmail", equalTo(mailID))
                        .body("status", equalTo("OK")).log();
            }
            //.body("items[0].fulfilmentLineStatus", equalTo("PROCESSING")).log().all();
//                 given()
//                         //.auth().oauth2(AWSHelpers.getSecret(frameworkConfigurationService.getAWS_SecretName()))
//                         .get("/orders/" +OrderSuccessPageActions.orderNumber + "/events").then().statusCode(200)
//                         .body("[0].status", equalTo("Success")).log().all();
            case "DE" -> {
                if (PaymentPageActions.orderTotal.contains(",00")) {
                    int totalGrossAmount = Integer.parseInt(PaymentPageActions.orderTotal.replace("€", "").replace(",00", "").replace(" ", ""));
                    if (ProductPageActions.eBook) {
                        given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                                .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                                .body("totalGrossAmount", equalTo(totalGrossAmount))
                                .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                                //This validation needs to be used after RSR-6256 & RSR-6253 is fixed
                                //.body("totalDiscountAmount", equalTo(discountedPrice))
                                .body("customerEmail", equalTo(mailID))
                                .body("status", equalTo("OK")).log();
                        //.body("items[0].fulfilmentLineStatus", equalTo("PROCESSING")).log().all();
                    } else if (ClinicalKeyProductPageActions.clinicalKeyNow) {
                        given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                                .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                                .body("totalGrossAmount", equalTo(totalGrossAmount))
                                .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                                .body("customerEmail", equalTo(mailID))
                                .body("status", equalTo("OK")).log();
                    } else {
                        given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                                .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                                .body("totalGrossAmount", equalTo(totalGrossAmount))
                                .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                                .body("customerEmail", equalTo(mailID))
                                //This validation needs to be used after RSR-6256 & RSR-6253 is fixed
                                //.body("totalDiscountAmount", equalTo(discountedPrice))
                                .body("status", equalTo("OK")).log();
                        //.body("items[0].fulfilmentLineStatus", equalTo("PROCESSING")).log().all();
                    }
                } else {
                    if (ProductPageActions.eBook) {
                        given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                                .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                                .body("totalGrossAmount", equalTo(Float.parseFloat(PaymentPageActions.orderTotal.replace("€", "").replace(",", ".").replace(" ", ""))))
                                .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                                //This validation needs to be used after RSR-6256 & RSR-6253 is fixed
                                //.body("totalDiscountAmount", equalTo(discountedPrice))
                                .body("customerEmail", equalTo(mailID))
                                .body("status", equalTo("OK")).log();
                        //.body("items[0].fulfilmentLineStatus", equalTo("PROCESSING")).log().all();
                    } else {
                        given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                                .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                                .body("totalGrossAmount", equalTo(Float.parseFloat(PaymentPageActions.orderTotal.replace("€", "").replace(",", ".").replace(" ", ""))))
                                .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                                //This validation needs to be used after RSR-6256 & RSR-6253 is fixed
                                //.body("totalDiscountAmount", equalTo(discountedPrice))
                                .body("customerEmail", equalTo(mailID))
                                .body("status", equalTo("OK")).log();
                        //.body("items[0].fulfilmentLineStatus", equalTo("PROCESSING")).log().all();
                    }
                }
            }
//                 given()
//                         //.auth().oauth2(AWSHelpers.getSecret(frameworkConfigurationService.getAWS_SecretName()))
//                         .get("/orders/"+ OrderSuccessPageActions.orderNumber + "/events").then().statusCode(200)
//                         .body("[0].status", equalTo("Success")).log().all();
            case "FR" -> {
                paymentPageOrderTotalString = PaymentPageActions.orderTotal;
                if (paymentPageOrderTotalString.contains(",00")) {
                    if (paymentPageOrderTotalString.length() >= 9) {
                        paymentPageOrderTotal = Integer.parseInt(paymentPageOrderTotalString.replace(" ", "").replace(" ", "").replace("€", "").replace(",00", ""));
                    } else {
                        paymentPageOrderTotal = Integer.parseInt(paymentPageOrderTotalString.replace("€", "").replace(" ", "").replace(",00", ""));
                    }
                } else {
                    if (paymentPageOrderTotalString.length() >= 9) {
                        paymentPageOrderTotal = Float.parseFloat(paymentPageOrderTotalString.replace(" ", "").replace(" ", "").replace("€", "").replace(",", "."));
                    } else {
                        paymentPageOrderTotal = Float.parseFloat(paymentPageOrderTotalString.replace(" ", "").replace("€", "").replace(",", "."));
                    }
                }
                given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                        .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                        .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                        .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                        //This validation needs to be used after RSR-6256 & RSR-6253 is fixed
                        //.body("totalDiscountAmount", equalTo(discountedPrice))
                        .body("customerEmail", equalTo(mailID))
                        .body("status", equalTo("OK")).log();
                given()
                        //.auth().oauth2(AWSHelpers.getSecret(frameworkConfigurationService.getAWS_SecretName()))
                        .get("/orders/" + OrderSuccessPageActions.orderNumber + "/events").then().statusCode(200)
                        .body("[0].status", equalTo("Success")).log();
            }
            case "EU" -> {
                paymentPageOrderSubTotalString = PaymentPageActions.orderSubTotal;
                paymentPageOrderTotalString = PaymentPageActions.orderTotal;
                if (paymentPageOrderTotalString.contains(".00")) {
                    paymentPageOrderTotal = Integer.parseInt(paymentPageOrderTotalString.replace("€", "").replace(",", ".").replace(" ", "").replaceAll("\\.0*$", ""));
                } else {
                    paymentPageOrderTotal = Float.parseFloat(paymentPageOrderTotalString.replace("€", "").replace(",", "").replace(",", ""));
                }
                if (paymentPageOrderSubTotalString.contains(".00")) {
                    paymentPageOrderSubTotal = Integer.parseInt(paymentPageOrderSubTotalString.replace("€", "").replace(",", ".").replace(" ", "").replaceAll("\\.0*$", ""));
                } else {
                    paymentPageOrderSubTotal = Float.parseFloat(paymentPageOrderSubTotalString.replace("€", "").replace(",", "").replace(",", ""));
                }
                if (JournalProductPageActions.journal) {
                    System.out.println(paymentPageOrderSubTotalString);
                    if (paymentPageOrderSubTotalString.contains(".00")) {
                        paymentPageOrderSubTotal = Integer.parseInt(paymentPageOrderSubTotalString.replace("€", "").replace(",", ".").replace(" ", "").replaceAll("\\.0*$", ""));
                        System.out.println("paymentPageOrderSubTotal amount includes .00");
                    } else {
                        paymentPageOrderSubTotal = Float.parseFloat(paymentPageOrderSubTotalString.replace("€", "").replace(",", ".").replace(" ", "").replaceAll("\\.0*$", ""));
                        System.out.println("paymentPageOrderSubTotal amount DOES NOT include .00");
                    }
                    System.out.println(paymentPageOrderTotalString);
                    if (paymentPageOrderTotalString.contains(".00")) {
                        paymentPageOrderTotal = Integer.parseInt(paymentPageOrderTotalString.replace("€", "").replace(",", ".").replace(" ", "").replaceAll("\\.0*$", ""));
                        System.out.println("paymentPageOrderTotal amount includes .00");
                    } else {
                        paymentPageOrderTotal = Float.parseFloat(paymentPageOrderTotalString.replace("€", "").replace(",", ".").replace(" ", "").replaceAll("\\.0*$", ""));
                        System.out.println("paymentPageOrderTotal amount DOES NOT include .00");
                    }
                    given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                            .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                            .body("subtotalNetAmount", equalTo(paymentPageOrderSubTotal))
                            .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                            //This validation needs to be used after RSR-6256 & RSR-6253 is fixed
                            //.body("totalDiscountAmount", equalTo(discountedPrice))
                            .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                            .body("customerEmail", equalTo(mailID))
                            .body("status", equalTo("OK")).log();
                    //.body("items[0].fulfilmentLineStatus", equalTo("PROCESSING")).log().all();
                } else if (ProductPageActions.eBook) {
                    given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                            .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                            .body("subtotalNetAmount", equalTo(Float.parseFloat(PaymentPageActions.orderSubTotal.replace("€", "").replace(",", ".").replace(" ", ""))))
                            .body("totalGrossAmount", equalTo(Float.parseFloat(PaymentPageActions.orderTotal.replace("€", "").replace(",", ".").replace(" ", ""))))
                            .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                            //This validation needs to be used after RSR-6256 & RSR-6253 is fixed
                            //.body("totalDiscountAmount", equalTo(discountedPrice))
                            .body("customerEmail", equalTo(mailID))
                            .body("status", equalTo("OK")).log();
//                            .body("items[0].fulfilmentLineStatus", equalTo("PROCESSING")).log().all();
                } else {
                    System.out.println("This line is executed if no e-book or journal");
                    given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                            .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                            .body("subtotalNetAmount", equalTo(paymentPageOrderSubTotal))
                            .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                            //This validation needs to be used after RSR-6256 & RSR-6253 is fixed
                            //.body("totalDiscountAmount", equalTo(discountedPrice))
                            .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                            .body("customerEmail", equalTo(mailID))
                            .body("status", equalTo("OK")).log();
                    //.body("items[0].fulfilmentLineStatus", equalTo("PROCESSING")).log().all();
                }
            }
//                 given()
//                         //.auth().oauth2(AWSHelpers.getSecret(frameworkConfigurationService.getAWS_SecretName()))
//                         .get("/orders/" +OrderSuccessPageActions.orderNumber + "/events").then().statusCode(200)
//                         .body("[0].status", equalTo("Success")).log().all();
            case "MEA" -> {
                paymentPageOrderTotalString = PaymentPageActions.orderTotal;
                if (paymentPageOrderTotalString.contains(".00")) {
                    paymentPageOrderTotal = Integer.parseInt(paymentPageOrderTotalString.replace("$", "").replace("US", "").replace(",", ".").replace(" ", "").replaceAll("\\.0*$", ""));
                } else {
                    paymentPageOrderTotal = Float.parseFloat(paymentPageOrderTotalString.replace("$", "").replace("US", "").replace(",", ""));
                }
                if (ProductPageActions.eBook) {
                    given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                            .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                            .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                            .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                            .body("customerEmail", equalTo(mailID))
                            .body("status", equalTo("OK")).log();
                } else {
                    given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                            .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                            .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                            .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                            .body("customerEmail", equalTo(mailID))
                            .body("status", equalTo("OK")).log();
                    //.body("items[0].fulfilmentLineStatus", equalTo("PROCESSING")).log().all();
                }
                given()
                        //.auth().oauth2(AWSHelpers.getSecret(frameworkConfigurationService.getAWS_SecretName()))
                        .get("/orders/" + OrderSuccessPageActions.orderNumber + "/events").then().statusCode(200)
                        .body("[0].status", equalTo("Success")).log();
            }
            case "SP" -> {
                DriverContext.driverSleep(60000); //wait for Fulfilment status to update
                if (ProductPageActions.eBook) {
                    given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                            .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                            .body("subtotalNetAmount", equalTo(Float.parseFloat(PaymentPageActions.orderSubTotal.replace("€", "").replace(",", ".").replace(" ", ""))))
                            .body("totalGrossAmount", equalTo(Float.parseFloat(PaymentPageActions.orderTotal.replace("€", "").replace(",", ".").replace(" ", ""))))
                            .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                            .body("customerEmail", equalTo(mailID))
                            .body("status", equalTo("OK")).log();
                    given()
                            //.auth().oauth2(AWSHelpers.getSecret(frameworkConfigurationService.getAWS_SecretName()))
                            .get("/orders/" + OrderSuccessPageActions.orderNumber + "/events").then().statusCode(200)
                            .body("[0].status", equalTo("Success"))
                            .log();
                } else {
                    given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                            .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                            .body("subtotalNetAmount", equalTo(Float.parseFloat(PaymentPageActions.orderSubTotal.replace("€", "").replace(",", ".").replace(" ", ""))))
                            .body("subtotalTaxAmount", equalTo(Float.parseFloat(PaymentPageActions.orderTax.replace("€", "").replace(",", ".").replace(" ", ""))))
                            .body("totalGrossAmount", equalTo(Float.parseFloat(PaymentPageActions.orderTotal.replace("€", "").replace(",", ".").replace(" ", ""))))
                            .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                            .body("customerEmail", equalTo(mailID))
                            .body("status", equalTo("OK")).log();
                    given()
                            //.auth().oauth2(AWSHelpers.getSecret(frameworkConfigurationService.getAWS_SecretName()))
                            .get("/orders/" + OrderSuccessPageActions.orderNumber + "/events").then().statusCode(200)
                            .body("[0].status", equalTo("Success"))
                            .log();
                }
            }
            case "LATAM" -> given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                    .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                    .body("totalGrossAmount", equalTo(Float.parseFloat(PaymentPageActions.orderTotal.replace("USD ", ""))))
                    .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                    .body("customerEmail", equalTo(mailID))
                    .body("status", equalTo("OK")).log();
            case "ANZ" -> {
                paymentPageOrderTotalString = PaymentPageActions.orderTotal;
                if (paymentPageOrderTotalString.contains(".00")) {
                    paymentPageOrderTotal = Integer.parseInt(paymentPageOrderTotalString.replace("AU$", "").replace(" ", "").replaceAll("\\.0*$", ""));
                } else {
                    paymentPageOrderTotal = Float.parseFloat(paymentPageOrderTotalString.replace("AU$", "").replace(" ", ""));
                }
                given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                        .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                        .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                        .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                        .body("customerEmail", equalTo(mailID))
                        .body("status", equalTo("OK")).log();
            }
            //  .body("items[0].fulfilmentLineStatus", equalTo("PROCESSING")).log().all();
//                 given()
//                         //.auth().oauth2(AWSHelpers.getSecret(frameworkConfigurationService.getAWS_SecretName()))
//                         .get("/orders/"+ OrderSuccessPageActions.orderNumber + "/events").then().statusCode(200)
//                         .body("[0].status", equalTo("Success")).log().all();
            case "IN" -> {
                paymentPageOrderTotalString = PaymentPageActions.orderTotal;
                paymentPageOrderSubTotalString = PaymentPageActions.orderSubTotal;
                paymentPageOrderTaxTotalString = PaymentPageActions.orderTax;
                if (paymentPageOrderTotalString.contains(".00")) {
                    paymentPageOrderTotal = Integer.parseInt(paymentPageOrderTotalString.replace("₹", "").replace(",", "").replaceAll("\\.0*$", ""));
                } else {
                    paymentPageOrderTotal = Float.parseFloat(paymentPageOrderTotalString.replace("₹", "").replace(",", ""));
                }
                if (paymentPageOrderSubTotalString.contains(".00")) {
                    paymentPageOrderSubTotal = Integer.parseInt(paymentPageOrderSubTotalString.replace("₹", "").replace(",", "").replaceAll("\\.0*$", ""));
                } else {
                    paymentPageOrderSubTotal = Float.parseFloat(paymentPageOrderSubTotalString.replace("₹", "").replace(",", ""));
                }
                if (ClinicalKeyProductPageActions.clinicalKeyNowFreeTrial) {
                    given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                            .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                            .body("subtotalNetAmount", equalTo(paymentPageOrderSubTotal))
                            .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                            .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                            .body("customerEmail", equalTo(mailID))
                            .body("status", equalTo("ERROR")).log();
                    System.out.println("Clinical Key Free Trial");
                } else {
                    if (paymentPageOrderTaxTotalString.contains(".00")) {
                        paymentPageOrderTaxTotal = Integer.parseInt(paymentPageOrderTaxTotalString.replace("₹", "").replace(",", "").replaceAll("\\.0*$", ""));
                    } else {
                        paymentPageOrderTaxTotal = Float.parseFloat(paymentPageOrderTaxTotalString.replace("₹", "").replace(",", ""));
                    }
                    given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                            .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                            .body("subtotalNetAmount", equalTo(paymentPageOrderSubTotal))
                            .body("subtotalTaxAmount", equalTo(paymentPageOrderTaxTotal))
                            .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                            .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                            .body("customerEmail", equalTo(mailID))
                            .body("status", equalTo("OK")).log();
                    System.out.println("Clinical Key Subscription for 1 Month or 3 Months or 12 Months");
                }

            }

            case "ASIA" -> {
                paymentPageOrderTotalString = PaymentPageActions.orderTotal;
                if (paymentPageOrderTotalString.contains(".00")) {
                    paymentPageOrderTotal = Integer.parseInt(paymentPageOrderTotalString.replace("$", "").replace(".00", ""));
                } else {
                    paymentPageOrderTotal = Float.parseFloat(paymentPageOrderTotalString.replace("$", ""));
                }
                given().get("/orders/" + OrderSuccessPageActions.orderNumber).then().statusCode(200)
                        .body("elsevierOrderNo", equalTo(OrderSuccessPageActions.orderNumber))
                        .body("totalGrossAmount", equalTo(paymentPageOrderTotal))
                        .body("itemCount", equalTo(ViewCartPageActions.cartItemCount))
                        .body("customerEmail", equalTo(mailID))
                        .body("status", equalTo("OK")).log();
            }
        }

    }

    @Then("The order is displayed and validated in ORR API1")
    public void the_order_is_displayed_and_validated_in_ORR_APIa() {
        String a = "$1680.00";
        Float b = Float.parseFloat(a.replace("$", "").replace(",", "").replaceAll("\\.0*$", ""));
        System.out.println(b);
        baseURI = frameworkConfigurationService.getORR_BASE_URI();
        given().get("/orders/" + "h9V00078699").then().statusCode(200)
                .body("subtotalNetAmount", equalTo(b)).log();
    }

    @Then("The order is displayed and validated in ORR {string}")
    public void the_order_is_displayed_and_validated_in_ORR(String countryName) {
        switch (countryName) {
            case "US":
                DriverContext.goToUrl(frameworkConfigurationService.getORR_USHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                log.info("ORR order link: " + frameworkConfigurationService.getORR_USHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                DriverContext.waitForPageToLoad();
                Assertions.assertEquals(Character.toLowerCase(OrderSuccessPageActions.orderNumber.charAt(0)) + OrderSuccessPageActions.orderNumber.substring(1), orrOrderSummaryPageActions.txtOrderNumber.GetTextValue());
                Assertions.assertEquals(PaymentPageActions.orderTotal, orrOrderSummaryPageActions.txtOrderTotal.GetTextValue().replace("US", ""));
                Assertions.assertEquals("OK", orrOrderSummaryPageActions.txtOrderStatus.GetTextValue());
                break;
            case "UK":
                DriverContext.goToUrl(frameworkConfigurationService.getORR_UKHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                log.info("ORR order link: " + frameworkConfigurationService.getORR_UKHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                DriverContext.waitForPageToLoad();
                Assertions.assertEquals(OrderSuccessPageActions.orderNumber, orrOrderSummaryPageActions.txtOrderNumber.GetTextValue());
                Assertions.assertEquals(PaymentPageActions.orderTotal, orrOrderSummaryPageActions.txtOrderTotal.GetTextValue());
                Assertions.assertEquals("OK", orrOrderSummaryPageActions.txtOrderStatus.GetTextValue());
                break;
            case "DE":
                DriverContext.goToUrl(frameworkConfigurationService.getORR_DEHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                log.info("ORR order link: " + frameworkConfigurationService.getORR_DEHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                DriverContext.waitForPageToLoad();
                Assertions.assertEquals(OrderSuccessPageActions.orderNumber, orrOrderSummaryPageActions.txtOrderNumber.GetTextValue());
                Assertions.assertEquals(PaymentPageActions.orderTotal.replace("€", "").replace(",", ".").replace(" ", ""), orrOrderSummaryPageActions.txtOrderTotal.GetTextValue().replace("€", ""));
                Assertions.assertEquals("OK", orrOrderSummaryPageActions.txtOrderStatus.GetTextValue());
                break;
            case "FR":
                DriverContext.goToUrl(frameworkConfigurationService.getORR_FRHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                log.info("ORR order link: " + frameworkConfigurationService.getORR_FRHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                DriverContext.waitForPageToLoad();
                Assertions.assertEquals(OrderSuccessPageActions.orderNumber, orrOrderSummaryPageActions.txtOrderNumber.GetTextValue());
                Assertions.assertEquals(PaymentPageActions.orderTotal.replace("€", "").replace(",", ".").replace(" ", ""), orrOrderSummaryPageActions.txtOrderTotal.GetTextValue().replace("€", ""));
                Assertions.assertEquals("OK", orrOrderSummaryPageActions.txtOrderStatus.GetTextValue());
                break;
            case "EU":
                DriverContext.goToUrl(frameworkConfigurationService.getORR_EUHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                log.info("ORR order link: " + frameworkConfigurationService.getORR_EUHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                DriverContext.waitForPageToLoad();
                Assertions.assertEquals(OrderSuccessPageActions.orderNumber, orrOrderSummaryPageActions.txtOrderNumber.GetTextValue());
                Assertions.assertEquals(PaymentPageActions.orderTotal.replace("€", "").replace(",", ".").replace(" ", ""), orrOrderSummaryPageActions.txtOrderTotal.GetTextValue().replace("€", ""));
                Assertions.assertEquals("OK", orrOrderSummaryPageActions.txtOrderStatus.GetTextValue());
                break;
            case "MEA":
                DriverContext.goToUrl(frameworkConfigurationService.getORR_MEHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                log.info("ORR order link: " + frameworkConfigurationService.getORR_MEHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                DriverContext.waitForPageToLoad();
                Assertions.assertEquals(OrderSuccessPageActions.orderNumber, orrOrderSummaryPageActions.txtOrderNumber.GetTextValue());
                Assertions.assertEquals(PaymentPageActions.orderTotal, orrOrderSummaryPageActions.txtOrderTotal.GetTextValue());
                Assertions.assertEquals("OK", orrOrderSummaryPageActions.txtOrderStatus.GetTextValue());
                break;
            case "SP":
                DriverContext.goToUrl(frameworkConfigurationService.getORR_SPHS_Order_Url());
                //DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.getElement("table.layout a[href='/site/3/orders/"+ orderNumber + "']"), 2);
                DriverContext.goToUrl(frameworkConfigurationService.getORR_SPHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                log.info("ORR order link: " + frameworkConfigurationService.getORR_SPHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                DriverContext.waitForPageToLoad();
                Assertions.assertEquals(OrderSuccessPageActions.orderNumber, orrOrderSummaryPageActions.txtOrderNumber.GetTextValue());
                Assertions.assertEquals(PaymentPageActions.orderTotal.replace("€", "").replace(",", ".").replace(" ", ""), orrOrderSummaryPageActions.txtOrderTotal.GetTextValue().replace("€", "").replace(",", "."));
                Assertions.assertEquals("OK", orrOrderSummaryPageActions.txtOrderStatus.GetTextValue());
                break;
            case "LATAM":
                DriverContext.goToUrl(frameworkConfigurationService.getORR_LATAMHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                log.info("ORR order link: " + frameworkConfigurationService.getORR_LATAMHS_Order_Url() + OrderSuccessPageActions.orderNumber);
                DriverContext.waitForPageToLoad();
                Assertions.assertEquals(OrderSuccessPageActions.orderNumber, orrOrderSummaryPageActions.txtOrderNumber.GetTextValue());
                Assertions.assertEquals(PaymentPageActions.orderTotal.replace("D ", ""), orrOrderSummaryPageActions.txtOrderTotal.GetTextValue().replace("$", ""));
                Assertions.assertEquals("OK", orrOrderSummaryPageActions.txtOrderStatus.GetTextValue());
                break;
        }
        if (!IDPlusLoginPageActions.signedIn) {
            Assertions.assertEquals(guestUserMail, orrOrderSummaryPageActions.txtCustomerEmail.GetTextValue());
        }
    }

    @And("I navigate to payment page and enter the shipping details and payment details")
    public void i_navigate_to_payment_page_enter_the_shipping_details(DataTable table) {
        List<Map<String, String>> shippingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addShippingDetailsForExistingUserUS(shippingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditions();
        DriverContext.driverSleep(35000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPay();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetailsSP(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page and enter the shipping details and payment details via mobile")
    public void i_navigate_to_payment_page_enter_the_shipping_details_via_mobile(DataTable table) {
        List<Map<String, String>> shippingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addShippingDetailsForExistingUserUSMobile(shippingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditionsMobile();
        DriverContext.driverSleep(7000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPay();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @Then("ETax percentage is compared between UAT and PROD")
    public void etax_percentage_is_compared_between_uat_and_prod() {
        System.out.println("UAT: " + PaymentPageActions.taxPercentageUAT + " & " + "PROD: " + PaymentPageActions.taxPercentagePROD);
        Assert.assertEquals(Math.round(PaymentPageActions.taxPercentageUAT), Math.round(PaymentPageActions.taxPercentagePROD));
    }

    @Then("The order is validated in Magento Admin {string}")
    public void the_order_is_validated_in_magento_admin(String countryName) {

        adminPanelActions.verifyOrderInMagento();
        switch (countryName) {
            case "FR", "UK" -> {
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderNumber.GetTextValue(), OrderSuccessPageActions.orderNumber);
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderTotal.GetTextValue().replace(" ", "").replace(" ", "").replace("€", "").replace(".00", "").replace(",", ""), PaymentPageActions.orderTotal.replace(" ", "").replace(" ", "").replace("€", "").replace(",00", "").replace(",", ""));
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderStatus.GetTextValue(), "Sent to ORR");
                adminPanelActions.adminPanelSalesSearchReset.waitAndPerformClick();
                DriverContext.waitForPageToLoad();
            }
            case "US" -> {
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderNumber.GetTextValue(), OrderSuccessPageActions.orderNumber);
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderTotal.GetTextValue(), PaymentPageActions.orderTotal);
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderStatus.GetTextValue(), "Sent to ORR");
                adminPanelActions.adminPanelSalesSearchReset.waitAndPerformClick();
                DriverContext.waitForPageToLoad();
            }
            case "MEA" -> {
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderNumber.GetTextValue(), OrderSuccessPageActions.orderNumber);
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderTotal.GetTextValue().replace(" ", "").replace(" ", "").replace("US", "").replace(".00", "").replace(",", ""), PaymentPageActions.orderTotal.replace(" ", "").replace(" ", "").replace("US", "").replace(",00", "").replace(",", ""));
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderStatus.GetTextValue(), "Sent to ORR");
                adminPanelActions.adminPanelSalesSearchReset.waitAndPerformClick();
                DriverContext.waitForPageToLoad();
            }
            case "SP", "DE", "EU" -> {
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderNumber.GetTextValue(), OrderSuccessPageActions.orderNumber);
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderTotal.GetTextValue().replace(" ", "").replace(" ", "").replace("€", "").replace(".00", "").replace(",", "").replace(".", ""), PaymentPageActions.orderTotal.replace(" ", "").replace(" ", "").replace("€", "").replace(",00", "").replace(",", "").replace(".", ""));
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderStatus.GetTextValue(), "Sent to ORR");
                adminPanelActions.adminPanelSalesSearchReset.waitAndPerformClick();
                DriverContext.waitForPageToLoad();
            }
            case "LATAM" -> {
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderNumber.GetTextValue(), OrderSuccessPageActions.orderNumber);
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderTotal.GetTextValue().replace(" ", "").replace(" ", "").replace("$", "").replace(".00", "").replace(",", "."), PaymentPageActions.orderTotal.replace(" ", "").replace(" ", "").replace("USD", "").replace(",00", "").replace(",", "."));
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderStatus.GetTextValue(), "Sent to ORR");
                adminPanelActions.adminPanelSalesSearchReset.waitAndPerformClick();
                DriverContext.waitForPageToLoad();
            }
            case "ASIA" -> {
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderNumber.GetTextValue(), OrderSuccessPageActions.orderNumber);
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderTotal.GetTextValue().replace(" ", "").replace(" ", "").replace("$", "").replace(".00", "").replace(",", "").replace(".", ""), PaymentPageActions.orderTotal.replace(" ", "").replace(" ", "").replace("$", "").replace(",00", "").replace(",", "").replace(".00", "").replace(".", ""));
                Assert.assertEquals(adminPanelActions.adminPanelSalesOrderStatus.GetTextValue(), "Sent to ORR");
                adminPanelActions.adminPanelSalesSearchReset.waitAndPerformClick();
                DriverContext.waitForPageToLoad();
            }
        }
    }

    @And("I login into Magento Admin with username {string} and password {string}")
    public void i_login_into_magento_admin_with_username_and_password(String username, String password) {
        if (DriverContext.getCurrentUrl().contains("staging")) {
            DriverContext.goToUrl(frameworkConfigurationService.getADMIN_PANEL_STAGING_URL());
            DriverContext.waitForPageToLoad();
            adminPanelActions.adminPanelLoginIn(username, password);
            DriverContext.waitForPageToLoad();
        } else if (DriverContext.getCurrentUrl().contains("uat")) {
            DriverContext.goToUrl(frameworkConfigurationService.getADMIN_PANEL_UAT_URL());
            DriverContext.waitForPageToLoad();
            adminPanelActions.adminPanelLoginIn(username, password);
            DriverContext.waitForPageToLoad();
            DriverContext.repeatWaitForElementVisibilityAttempt(adminPanelActions.adminPanelPopup, 2);
            if (DriverContext.doesElementExist(adminPanelActions.adminPanelPopup)) {
                DriverContext.driverSleep(5000);
                adminPanelActions.adminPanelPopup.Click();
            }
            DriverContext.waitForPageToLoad();
        }
    }

    @Then("I apply for a offer code {string} and validate price details in {string}")
    public void i_apply_for_a_offer_code_and_validate_price_details(String offerCode, String countryName) {
        float subTotal;
        String expectedGrandTotal;
        float ActualGrandTotal;
        String ActualGrandTotalValue;
        viewCartPageActions.applyOfferCode(offerCode);
        DriverContext.driverSleep(10000);
        DriverContext.waitForPageToLoad();
        if (frameworkConfigurationService.getTestEnv().equals("UAT")) {
            if (countryName.equals("FR")) {
                DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Le code de réduction " + '"' + offerCode + '"' + " a été appliqué."));
            } else {
                DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Coupon code " + '"' + offerCode + '"' + " was applied."));
            }
            if (countryName.equals("UK")) {
                String subTotalUK = (viewCartPageActions.txtSubTotal.GetTextValue().replace("£", "").replace(",", ""));
                subTotal = Float.parseFloat(subTotalUK);
            } else {
                subTotal = Float.parseFloat(viewCartPageActions.txtSubTotal.GetTextValue().replace("US", "").replace("AU", "").replace("$", "").replace("€", "").replace(",", ""));
            }
            if (offerCode.equals("FixedDiscount25")) {
                DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtCartItemCount, 2);
                cartItemCount = Integer.parseInt(productPageActions.txtCartItemCount.GetTextValue());
                discountedPrice = cartItemCount * 25;//Fixed discount 25 applied for all the products in cart
                expectedDiscountValue = String.valueOf(discountedPrice);
                if ((countryName.equals("FR")) || (countryName.equals("DE"))) {
                    expectedGrandTotal = String.format("%.2f", subTotal - (cartItemCount * 25 * 100));
                } else {
                    expectedGrandTotal = String.format("%.2f", subTotal - (cartItemCount * 25));
                }
                DriverContext.driverSleep(35000);
                float GrandTotal = Float.parseFloat(productPageActions.textGrandTotal.GetTextValue().replace(",", "")
                        .replace("€", "").replace("£", "").replace("US", "").replace("AU", "").replace("$", ""));
                if (DriverContext.doesElementExist(productPageActions.textShippingCharge)) {
                    float shippingCharge = Float.parseFloat(productPageActions.textShippingCharge.GetTextValue().replace(",", "")
                            .replace("€", "").replace("US", "").replace("AU", "").replace("$", "").trim());
                    ActualGrandTotal = GrandTotal - shippingCharge;
                } else {
                    ActualGrandTotal = Float.parseFloat(productPageActions.textGrandTotal.GetTextValue().replace("€", "").replace("US", "").replace("AU", "").replace("$", "").replace("£", "")
                            .replace(".00", "").replace(",", "").trim());
                }
                ActualGrandTotalValue = String.format("%.2f", ActualGrandTotal);
                Assertions.assertEquals(expectedDiscountValue.replace(".00", ""), viewCartPageActions.txtDiscount.GetTextValue().replace("€", "").replace("US", "").replace("AU", "").replace("$", "").replace("£", "").replace("-", "").replace(".00", "").replace(",00", "").trim());
                Assertions.assertEquals(expectedGrandTotal.replace(".00", ""), ActualGrandTotalValue.replace(".00", ""));
            } else if (offerCode.equals("WholeCartFixedDiscount40")) {
                discountedPrice = 40;//Fixed discount 40 applied to whole cart using WholeCartFixedDiscount40 coupon
                if (countryName.equals("ASIA")) {
                    expectedDiscountValue = String.valueOf(discountedPrice);
                } else {
                    expectedDiscountValue = discountedPrice + ",00";
                }
                if ((countryName.equals("FR")) || (countryName.equals("DE"))) {
                    expectedGrandTotal = String.format("%.2f", subTotal - (discountedPrice * 100));
                } else {
                    expectedGrandTotal = String.format("%.2f", subTotal - (discountedPrice));
                }
                Assertions.assertEquals(expectedDiscountValue.replace(",00", ""), viewCartPageActions.txtDiscount.GetTextValue().replace("€", "").replace("$", "").replace("US", "").replace("AU", "").replace("-", "").replace("£", "").replace(",00", "").replace(".00", "").trim());
                Assertions.assertEquals(expectedGrandTotal.replace(".00", ""), productPageActions.textGrandTotal.GetTextValue().replace("€", "").replace("US", "").replace("$", "").replace("AU", "").replace("£", "").replace(".00", "").replace(",", "").trim());
            } else if (offerCode.equals("60%off")) {
                float discount = (float) (subTotal * 0.6);
                if ((countryName.equals("FR")) || (countryName.equals("DE"))) {
                    discountedPrice = (int) (subTotal * 0.6);//60% offer applied on product price
                    expectedDiscountValue = String.valueOf(discountedPrice);
                    expectedGrandTotal = String.format("%.2f", subTotal - (discountedPrice));
                    Assertions.assertEquals(expectedDiscountValue, viewCartPageActions.txtDiscount.GetTextValue().replace("€", "").replace("$", "").replace("-", "").replace(",", "").trim());
                    Assertions.assertEquals(expectedGrandTotal.replace(".00", ""), productPageActions.textGrandTotal.GetTextValue().replace("$", "").replace("€", "").replace(",", "").trim());
                } else if (countryName.equals("SP")) {
                    expectedDiscountValue = String.format("%.0f", discount);
                    String expectedGrandTotalPrice = String.format("%.0f", subTotal - (discount));
                    Assertions.assertEquals(expectedDiscountValue, viewCartPageActions.txtDiscount.GetTextValue().replace("€", "").replace("$", "").replace("US", "").replace("-", "").replace(",", "").trim());
                    Assertions.assertEquals(expectedGrandTotalPrice, productPageActions.textGrandTotal.GetTextValue().replace("€", "").replace("US", "").replace("$", "").replace(",", "").replaceAll(".00", "").trim());
                } else {
                    expectedDiscountValue = String.format("%.2f", discount);
                    String expectedGrandTotalPrice = String.format("%.2f", subTotal - (discount));
                    Assertions.assertEquals(expectedDiscountValue, viewCartPageActions.txtDiscount.GetTextValue().replace("£", "").replace("€", "").replace("$", "").replace("US", "").replace("AU", "").replace("-", "").replace(",", "").trim());
                    Assertions.assertEquals(expectedGrandTotalPrice.replace(".00", ""), productPageActions.textGrandTotal.GetTextValue().replace("£", "").replace("€", "").replace("US", "").replace("AU", "").replace("$", "").replace(",", "").replaceAll(".00", "").trim());
                }
            }
        }
    }

    @And("I navigate to payment page and choose paypalExpress payment")
    public void i_navigate_to_payment_page_and_choose_paypalExpress_payment(DataTable table) {
        paymentPageActions.selectPaypalExpress();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.payPalPaymentExpressDetails(CucumberUtil.GetCellValueWithRowIndex("Paypal Username", 1),
                CucumberUtil.GetCellValueWithRowIndex("Password", 1));
    }

    @And("I search for a Book product in {string} from the New Titles Menu")
    public void i_search_for_a_book_product_from_new_titles_menu(String countryName) {
        hsHomePageActions.selectNewTitles(countryName);
        DriverContext.waitForPageToLoad();
        searchPageActions.clickOnBookProductLink();
        if (countryName.equals("FR")) {
            HSHomePageActions.productCategory = "Livre";
        } else if (countryName.equals("DE")) {
            HSHomePageActions.productCategory = "Buch";
        } else {
            HSHomePageActions.productCategory = "Book";
        }
        DriverContext.waitForPageToLoad();
    }

    @And("I search for a product from the Best Sellers Menu")
    public void i_search_for_a_product_from_best_sellers_menu() {
        hsHomePageActions.selectBestSellersFromMenu();
        DriverContext.waitForPageToLoad();
        searchPageActions.clickOnBookProductLink();
        HSHomePageActions.productCategory = "Book";
        DriverContext.waitForPageToLoad();
    }

    @And("I search for a product from the Australian Titles Menu")
    public void i_search_for_a_product_from_Australian_Titles_menu() {
        hsHomePageActions.selectAustralianTitlesFromMenu();
        DriverContext.waitForPageToLoad();
        searchPageActions.clickOnBookProductLink();
        HSHomePageActions.productCategory = "Book";
        DriverContext.waitForPageToLoad();
    }
}

