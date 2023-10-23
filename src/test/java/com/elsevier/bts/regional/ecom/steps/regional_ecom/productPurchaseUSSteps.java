package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.Base;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.framework.utilities.JavaUtil;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
public class productPurchaseUSSteps extends Base {

    SearchPageActions searchPageActions = GetInstance(SearchPageActions.class);
    MyAccountActions myAccountActions = GetInstance(MyAccountActions.class);
    ProductPageActions productPageActions = GetInstance(ProductPageActions.class);
    JournalProductPageActions journalProductPageActions = GetInstance(JournalProductPageActions.class);
    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);
    CheckoutPageActions checkoutPageActions = GetInstance(CheckoutPageActions.class);
    ViewCartPageActions viewCartPageActions = GetInstance(ViewCartPageActions.class);
    ECapturePageActions eCapturePageActions = GetInstance(ECapturePageActions.class);
    ShippingPageActions shippingPageActions = GetInstance(ShippingPageActions.class);
    PaymentPageActions paymentPageActions = GetInstance(PaymentPageActions.class);
    OrderSuccessPageActions orderSuccessPageActions = GetInstance(OrderSuccessPageActions.class);

    MobileActions mobileActions = GetInstance(MobileActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @And("I search for the product {string}")
    public void i_search_for_product(String product) {
        DriverContext.waitForPageToLoad();
        hsHomePageActions.searchForProduct(product);
        DriverContext.waitForPageToLoad();
    }

    @Then("I add the product to wishlist from search page")
    public void i_add_the_product_to_wishlist_from_search_page() {
        searchPageActions.addToWishlist();
        DriverContext.waitForPageToLoad();
        log.debug("My Wishlist URL: " + DriverContext.getCurrentUrl());
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("has been added to your Wish List."));
    }

    @Then("I add the product to wishlist from product page")
    public void i_add_the_product_to_wishlist_from_product_page() {
        productPageActions.addToWishlist();
        DriverContext.waitForPageToLoad();
        log.debug("My Wishlist URL: " + DriverContext.getCurrentUrl());
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("has been added to your Wish List."));
    }

    @And("I search for the product {string} under product type {string}")
    public void i_search_for_the_product_under_product_type(String product, String productType) {
        hsHomePageActions.selectProductType(productType);
        hsHomePageActions.searchForProduct(product);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(searchPageActions.txtPrice, 2);
        Assertions.assertTrue(searchPageActions.txtPrice.isDisplayed());
        searchPageActions.clickOnProductLink();
        DriverContext.waitForPageToLoad();
    }

    @And("I search for the product {string} via mobile")
    public void i_search_for_the_product_mobile(String product) {
        mobileActions.searchForProduct(product);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(searchPageActions.txtPrice, 2);
        Assertions.assertTrue(searchPageActions.txtPrice.isDisplayed());
        searchPageActions.clickOnProductLink();
        DriverContext.waitForPageToLoad();
    }

    @And("I select {string} product in navigation bar via mobile")
    public void i_select_product_in_navigation_bar_via_mobile(String productType) {
        mobileActions.selectProductFromNavigationBar(productType);
        DriverContext.waitForPageToLoad();
        searchPageActions.clickOnProductLink();
        DriverContext.waitForPageToLoad();
    }

    @And("I search for the cknow {string} under product type {string}")
    public void i_search_for_the_cknow_under_product_type(String product, String productType) {
        hsHomePageActions.selectProductType(productType);
        hsHomePageActions.searchForProduct(product);
        DriverContext.waitForPageToLoad();
        searchPageActions.clickOnProductLink();
        DriverContext.waitForPageToLoad();
    }

    @And("I search for the journal {string} under product type {string}")
    public void i_search_for_the_journal_under_product_type(String product, String productType) {
        hsHomePageActions.selectProductType(productType);
        hsHomePageActions.searchForProduct(product);
        DriverContext.waitForPageToLoad();
        searchPageActions.clickOnProductLink();
        DriverContext.waitForPageToLoad();
    }

    @And("I search for the E-Book {string} under product type {string}")
    public void i_search_for_the_e_book_under_product_type(String product, String productType) {
        hsHomePageActions.selectProductType(productType);
        hsHomePageActions.searchForProduct(product);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(searchPageActions.txtPrice, 2);
        Assertions.assertTrue(searchPageActions.txtPrice.isDisplayed());
        searchPageActions.clickOnProductLink();
        DriverContext.waitForPageToLoad();
    }

    @And("I search for different product under different product type and add them to cart")
    public void i_search_for_different_product_and_under_product_type(DataTable table) {
        List<Map<String, String>> product = table.asMaps(String.class, String.class);
        int count = 0;
        String itemCategory = "(.//span[@class='item-product-format'])";
        for (Map<String, String> productList : product) {
            hsHomePageActions.selectProductType(productList.get("Product Type"));
            hsHomePageActions.searchForProduct(productList.get("Product"));
            String ProductType = productList.get("Product Type");
            DriverContext.waitForPageToLoad();
            DriverContext.repeatWaitForElementVisibilityAttempt(searchPageActions.txtPrice, 2);
            if (!ProductType.equals("journals") && !ProductType.equals("Journals")) {
                Assertions.assertTrue(searchPageActions.txtPrice.isDisplayed());
            }
            searchPageActions.clickOnProductLink();
            DriverContext.waitForPageToLoad();
            if (ProductType.equals("E-Books") || ProductType.equals("E-Book") || ProductType.equals("eBooks")) {
                productPageActions.selectEBookCheckBox();
                productPageActions.addToCart();
                count++;
                DriverContext.waitForPageToLoad();
                DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum number of copies of a single product you may purchase is 1. Please contact a"), "E-book added to cart message does not contain - You added");
                Assertions.assertEquals("VitalSource eBook", DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            } else if (ProductType.equals("Journals") || ProductType.equals("journals")) {
                DriverContext.driverSleep(10000);// TODO: Replace in the future with a suitable explicit wait
                journalProductPageActions.addJournalToCart("Institution", "1 Year", "Domestic (US)");
                count++;
                DriverContext.waitForPageToLoad();
                DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum number of copies of a single product you may purchase is 1. Please contact a"), "Journal added to cart message does not contain - You added");
                Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            } else {
                productPageActions.addToCart();
                count++;
                DriverContext.waitForPageToLoad();
                DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added"));
                Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            }
        }
       // ProductPageActions.eBook = false;
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
    }

    @When("I add the product to the cart")
    public void i_add_the_product_to_the_cart() {
        productPageActions.addToCart();
        DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added"));
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), viewCartPageActions.txtProductCategory.GetTextValue());
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
    }

    @When("I add the product to the cart via mobile")
    public void i_add_the_product_to_the_cart_via_mobile() {
        mobileActions.addToCartMobile();
        DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added"));
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
    }

    @When("I add the ebook product to the cart via mobile")
    public void i_add_the_ebook_product_to_the_cart_via_mobile() {
        mobileActions.eBookAddToCartMobile();
        DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added"));
        productPageActions.navigateToViewCartPageMobile();
        DriverContext.waitForPageToLoad();
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
    }

    @When("I add the journal to the cart")
    public void i_add_the_journal_to_the_cart() {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        journalProductPageActions.addJournalToCart("Institution", "1 Year", "Domestic (US)");
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum number of copies of a single product you may purchase is 1. Please contact a"), "Journal added to cart message does not contain - You added");
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), viewCartPageActions.txtProductCategory.GetTextValue());
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
    }

    @When("I add the E-book to the cart")
    public void i_add_the_e_book_to_the_cart() {
        productPageActions.selectEBookCheckBox();
        productPageActions.addToCart();
        DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum number of copies of a single product you may purchase is 1. Please contact a"), "E-book added to cart message does not contain - You added");
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals("VitalSource eBook", viewCartPageActions.txtProductCategory.GetTextValue());
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
    }

    @And("I proceed to the checkout page")
    public void i_proceed_to_the_checkout_page() {
        DriverContext.repeatWaitForElementVisibilityAttempt(viewCartPageActions.txtTaxInfo, 3);
        log.debug("- Tax info is visible on Checkout page");
        //viewCartPageActions.closeNewsletterPopup();
        viewCartPageActions.proceedToCheckout();
        log.debug("- Clicked Proceed to Checkout button");
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(shippingPageActions.footer, 2);
        log.debug("Proceeded To Checkout URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/
    }

    @And("I proceed to the checkout page with discount")
    public void i_proceed_to_the_checkout_page_discount() {
        DriverContext.repeatWaitForElementVisibilityAttempt(viewCartPageActions.txtTaxInfo, 3);
        log.debug("- Tax info is visible on Checkout page");
        float subTotal = Float.parseFloat(viewCartPageActions.txtSubTotal.GetTextValue().replace("$", "").replace(",", ""));
        String expectedDiscount = String.format("%.2f", subTotal * 0.3);
        String expectedGrandTotal = String.format("%.2f", subTotal - (subTotal * 0.3));
        DriverContext.repeatWaitForElementVisibilityAttempt(viewCartPageActions.txtDiscount, 2);
        Assertions.assertEquals(expectedDiscount, viewCartPageActions.txtDiscount.GetTextValue().replace("$", "").replace("-", ""));
        Assertions.assertEquals(expectedGrandTotal, viewCartPageActions.txtGrandTotal.GetTextValue().replace("$", "").replace(",", ""));
        //viewCartPageActions.closeNewsletterPopup();
        viewCartPageActions.proceedToCheckout();
        log.debug("- Clicked Proceed to Checkout button");
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(shippingPageActions.footer, 2);
        log.debug("Proceeded To Checkout URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/
    }

    @And("I proceed to the checkout page as a guest user")
    public void i_proceed_to_the_checkout_page_as_a_guest_user() {
        DriverContext.repeatWaitForElementVisibilityAttempt(viewCartPageActions.txtTaxInfo, 2);
        log.debug("- Tax info is visible on Checkout page");
        viewCartPageActions.proceedToCheckout();
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(15000);
        checkoutPageActions.continueAsGuest();
        DriverContext.waitForPageToLoad();
    }

    @And("I login from the checkout page")
    public void i_login_from_the_checkout_page() {
        checkoutPageActions.customerCheckoutSignIn();
        DriverContext.waitForPageToLoad();
    }

    @And("I navigate to shipping page and enter the shipping details")
    public void i_navigate_to_shipping_page_and_enter_the_shipping_details(DataTable table) {
        CucumberUtil.ConvertDataTableToDict(table);
        shippingPageActions.addShippingDetails(CucumberUtil.GetCellValueWithRowIndex("Street Address", 1),
                CucumberUtil.GetCellValueWithRowIndex("City", 1),
                CucumberUtil.GetCellValueWithRowIndex("State", 1),
                CucumberUtil.GetCellValueWithRowIndex("ZipCode", 1),
                CucumberUtil.GetCellValueWithRowIndex("Phone Number", 1));
        shippingPageActions.proceedToReviewAndPayments();
        DriverContext.waitForPageToLoad();
    }

    @And("I confirm the shipping details")
    public void i_confirm_the_shipping_details() {
        //shippingPageActions.closeNotReadyJustYetPopupIfPresent(); TODO: Replace in the future if pop up appears again
        shippingPageActions.proceedToReviewAndPayments();
        DriverContext.waitForPageToLoad();
    }

    @And("I confirm the shipping details via mobile")
    public void i_confirm_the_shipping_details_via_mobile() {
        //shippingPageActions.closeNotReadyJustYetPopupIfPresent(); TODO: Replace in the future if pop up appears again
        shippingPageActions.proceedToReviewAndPaymentsMobile();
        if (!ProductPageActions.eBook && !ClinicalKeyProductPageActions.clinicalKeyNow) {
            paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        }
        paymentPageActions.acceptTermsAndConditionsMobile();
        paymentPageActions.proceedToPay();
        DriverContext.waitForPageToLoad();
    }

    @And("I navigate to payment page and enter the payment details")
    public void i_navigate_to_payment_page_enter_the_payment_details(DataTable table) {
        if (!ProductPageActions.eBook && !ClinicalKeyProductPageActions.clinicalKeyNow) {
            paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        }
        paymentPageActions.acceptTermsAndConditions();
        paymentPageActions.proceedToPay();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page and enter the payment details via mobile")
    public void i_navigate_to_payment_page_enter_the_payment_details_via_mobile(DataTable table) {
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page and enter the billing address and payment details in EU-HS via mobile")
    public void i_navigate_to_payment_page_enter_the_billing_details_eu_via_mobile(DataTable table) {
        List<Map<String, String>> shippingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addDifferentBillingAddressFRMobile(shippingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditionsMobile();
        DriverContext.driverSleep(15000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardUK();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page and enter the payment details in Middle East HealthStore")
    public void i_navigate_to_payment_page_enter_the_payment_details_mea(DataTable table) {
        if (!ProductPageActions.eBook && !ClinicalKeyProductPageActions.clinicalKeyNow) {
            paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        }
        paymentPageActions.acceptTermsAndConditionsMEAHS();
        paymentPageActions.proceedToPay();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page enter different billing address and enter payment details")
    public void i_navigate_to_payment_page_enter_the_payment_details_with_different_billing_address(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addDifferentBillingAddress(billingAddress);
        paymentPageActions.acceptTermsAndConditions();
        DriverContext.waitUntilElementIsClickable(paymentPageActions.btnProceedToPay);
        paymentPageActions.proceedToPay();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page and select different billing address and enter payment details")
    public void i_navigate_to_payment_page_and_select_different_billing_address(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        paymentPageActions.addDifferentBillingAddressForExistingUser(billingAddress);
        paymentPageActions.acceptTermsAndConditions();
        DriverContext.waitUntilElementIsClickable(paymentPageActions.btnProceedToPay);
        paymentPageActions.proceedToPay();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I place the order")
    public void i_place_the_order() {
        if (!ECapturePageActions.payPal) {
            eCapturePageActions.submitPayment();
        }
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(orderSuccessPageActions.btnContinueShopping, 3);
    }

    @And("I place the order via mobile")
    public void i_place_the_order_via_mobile() {
        if (!ECapturePageActions.payPal) {
            eCapturePageActions.submitPaymentMobile();
        }
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(orderSuccessPageActions.btnContinueShopping, 3);
    }

    @And("I log out from the site")
    public void i_log_out_from_the_site() {
        hsHomePageActions.signOut();
        DriverContext.waitForPageToLoad();
        Assertions.assertTrue(hsHomePageActions.lnkSignIn.isDisplayed());
    }

    @And("I select books from product type")
    public void i_select_books_from_product_type() {
        hsHomePageActions.selectProductFormatFromNavigationBar();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(searchPageActions.btnAddToCart, 3);
        DriverContext.waitUntilElementIsClickable(searchPageActions.btnAddToCart);
        HSHomePageActions.productCategory="Book";

    }

    @And("I add a book to cart")
    public void i_add_a_book_to_cart() {
        Assertions.assertEquals("BOOKS", searchPageActions.txtProductFormat.GetTextValue());
        searchPageActions.addToCart();
        DriverContext.repeatWaitForElementVisibilityAttempt(searchPageActions.txtCartQty, 2);
//       Assertions.assertThat(searchPageActions.txtCartQty.GetTextValue(), Matchers.anyOf(Matchers.is("1"), Matchers.is("2"))); TODO: Restore Assertion in JUnit5
    }

    @And("I remove the product from cart")
    public void i_remove_the_product_from_cart() {
        viewCartPageActions.clearCart();
        DriverContext.waitUntilElementIsVisible(viewCartPageActions.txtCartEmptyMessage);
        Assertions.assertEquals("You have no items in your shopping cart.", viewCartPageActions.txtCartEmptyMessage.GetTextValue());
    }

    @And("I navigate to payment page and choose paypal payment")
    public void i_navigate_to_payment_page_and_choose_paypal_payment(DataTable table) {
        if (!ProductPageActions.eBook && !ClinicalKeyProductPageActions.clinicalKeyNow) {
            paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        }
        paymentPageActions.acceptTermsAndConditions();
        paymentPageActions.proceedToPay();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.payPalPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Paypal Username", 1),
                CucumberUtil.GetCellValueWithRowIndex("Password", 1));

    }

    @And("I search for the PSSi product {string}")
    public void i_search_for_the_pssi_product_under_product_type(String product) {
        hsHomePageActions.searchForProduct(product);
        DriverContext.waitForPageToLoad();
        searchPageActions.clickOnProductLink();
        DriverContext.waitForPageToLoad();
    }

    @And("I navigate to payment page and enter different billing address and enter payment details")
    public void i_navigate_to_payment_page_and_enter_different_billing_address_and_enter_payment_details_in_US_HS(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.addDifferentBillingAddressForExistingUserUS(billingAddress);
        DriverContext.driverSleep(20000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.acceptTermsAndConditionsForPSSI();
        paymentPageActions.verifyTaxPercentage(billingAddress.get(0).get("Country"));
        DriverContext.waitUntilElementIsClickable(paymentPageActions.btnProceedToPay);
        paymentPageActions.proceedToPay();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page and verify different countries not displayed for PSSI Products")
    public void i_navigate_to_payment_page_and_verify_different_countries_not_displayed_for_PSSI_products() {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.verifyDifferentBillingAddressCountriesNotAvailable();
    }

    @When("I verify the {string} PSSI product location restriction text in PDP")
    public void i_verify_the_pssi_products_location_restriction_text(String productName) {
        Assertions.assertEquals(productPageActions.txtLocationRestriction.GetTextValue(), "Individual\n" +
                        "Autumn Sale – Get 15% off using coupon PAU23 at checkout\n" +
                        "Location restrictions apply *",
                "Location Restrictions text is not displayed in the PDP");
        Assertions.assertEquals(productPageActions.txtCountriesList.GetTextValue(),
                "* This product is available for purchase in United States, Canada, Brunei, Cambodia, China, India, " +
                        "Indonesia, Japan, Laos, Malaysia, Myanmar, Singapore, South Korea, Thailand, and Timor-Leste.", "" +
                        "Countries List Text is not displayed in the PDP");
        Assertions.assertEquals(productPageActions.txtContactSalesDepartment.GetTextValue(),
                "Please contact the " + productName + " sales department if you are interested in this product and are not located in one of the countries on this list.",
                "Contact Sales Department Text is not displayed in PDP");
    }

    @And("I navigate to payment page and enter different billing address and enter payment details for PSSI Products")
    public void i_navigate_to_payment_page_and_enter_different_billing_address_and_enter_payment_details_in_US_HS_for_PSSI(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.addShippingDetailsForNewUserUS(billingAddress);
        DriverContext.driverSleep(20000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.acceptTermsAndConditionsForPSSI();
        paymentPageActions.verifyTaxPercentage(billingAddress.get(0).get("Country"));
        DriverContext.waitUntilElementIsClickable(paymentPageActions.btnProceedToPay);
        paymentPageActions.proceedToPay();
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

    @And("I place the order with Mastercard")
    public void i_place_the_order_with_mastercard() {
        eCapturePageActions.submitPayment();
        eCapturePageActions.enterPasswordAndSubmitPayment();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(orderSuccessPageActions.btnContinueShopping, 3);
    }
}
