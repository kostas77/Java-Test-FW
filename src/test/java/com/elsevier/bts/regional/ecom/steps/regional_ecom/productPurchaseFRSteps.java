package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
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

import static com.elsevier.bts.regional.ecom.steps.regional_ecom.ProductPurchaseCommonSteps.guestUserMail;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Slf4j
public class productPurchaseFRSteps extends BasePage {

    SearchPageActions searchPageActions = GetInstance(SearchPageActions.class);
    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);
    JournalProductPageActions journalProductPageActions = GetInstance(JournalProductPageActions.class);
    ClinicalKeyProductPageActions clinicalKeyProductPageActions = GetInstance(ClinicalKeyProductPageActions.class);
    ProductPageActions productPageActions = GetInstance(ProductPageActions.class);
    ViewCartPageActions viewCartPageActions = GetInstance(ViewCartPageActions.class);
    ShippingPageActions shippingPageActions = GetInstance(ShippingPageActions.class);
    PaymentPageActions paymentPageActions = GetInstance(PaymentPageActions.class);
    ECapturePageActions eCapturePageActions = GetInstance(ECapturePageActions.class);
    MyAccountActions myAccountActions = GetInstance(MyAccountActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @When("I add the journal to the cart in France HealthStore")
    public void i_add_the_journal_to_the_cart(DataTable table) {
        List<Map<String, String>> subscriptionData = table.asMaps(String.class, String.class);
        String format = subscriptionData.get(0).get("Format");
        String status = subscriptionData.get(0).get("Status");
        String country = subscriptionData.get(0).get("Country");
        String duration = subscriptionData.get(0).get("Subscription Duration");
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        journalProductPageActions.addJournalToCartFR(format, status, country, duration);
        DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Vous avez ajouté") || productPageActions.txtSuccessMessage.GetTextValue().contains("La quantité maximale autorisée dans votre panier est limitée à 1"));
        DriverContext.waitForPageToLoad();
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), viewCartPageActions.txtProductCategory.GetTextValue());
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://uk.uat-regionalecom.tio.systems/checkout/cart/
        if (frameworkConfigurationService.getTestEnv().equalsIgnoreCase("uat")) {
            //Verify the Journal Options displayed correctly in Shopping Cart
            switch (format) {
                case "Paper" ->
                        Assertions.assertEquals("Revue - Papier", productPageActions.txtFormatFR.GetTextValue());
                case "Digital + Paper" ->
                        Assertions.assertEquals("Revue - Numérique+Papier", productPageActions.txtFormatFR.GetTextValue());
                case "Digital" ->
                        Assertions.assertEquals("Revue - Numérique", productPageActions.txtFormatFR.GetTextValue());
            }
            switch (status) {
                case "Individual" ->
                        Assertions.assertEquals("Particulier", productPageActions.txtStatusFR.GetTextValue());
                case "Student" -> Assertions.assertEquals("Étudiant", productPageActions.txtStatusFR.GetTextValue());
                case "Institution" ->
                        Assertions.assertEquals("Institution", productPageActions.txtStatusFR.GetTextValue());
            }
            switch (format) {
                case "France" ->
                        Assertions.assertEquals("France (+ DOM-TOM)", productPageActions.txtCountryFR.GetTextValue());
                case "Rest of World" ->
                        Assertions.assertEquals("Reste du monde", productPageActions.txtCountryFR.GetTextValue());
                case "Switzerland" ->
                        Assertions.assertEquals("UE (+ Suisse)", productPageActions.txtCountryFR.GetTextValue());
            }
            switch (format) {
                case "12 months" -> Assertions.assertEquals("12 mois", productPageActions.txtDurationFR.GetTextValue());
                case "24 months" -> Assertions.assertEquals("24 mois", productPageActions.txtDurationFR.GetTextValue());
                case "36 months" -> Assertions.assertEquals("36 mois", productPageActions.txtDurationFR.GetTextValue());
            }
        }
    }

    @When("I add the E-book to the cart in France HealthStore")
    public void i_add_the_e_book_to_the_cart() {
        productPageActions.selectEBookCheckBox();
        productPageActions.addToCart();
        DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Vous avez ajouté") || productPageActions.txtSuccessMessage.GetTextValue().contains("La quantité maximale autorisée dans votre panier est limitée à 1"));
        productPageActions.closeFreeShippingPopup();
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals("VitalSource eBook", viewCartPageActions.txtProductCategory.GetTextValue());
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
    }

    @When("I add the product to the cart in France HealthStore")
    public void i_add_the_product_to_the_cart() {
        productPageActions.addToCart();
        DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Vous avez ajouté"));
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), viewCartPageActions.txtProductCategory.GetTextValue());
        DriverContext.driverSleep(15000); // TODO: Replace in the future with a suitable explicit wait
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
    }

    @And("I search for different product under different product type and add them to cart in France healthstore")
    public void i_search_for_different_product_and_under_product_type(DataTable table) {
        int count = 0;
        String itemCategory = "(.//span[@class='item-product-format'])";
        List<Map<String, String>> product = table.asMaps(String.class, String.class);
        for (Map<String, String> productList : product) {
            hsHomePageActions.selectProductType(productList.get("Product Type"));
            hsHomePageActions.searchForProduct(productList.get("Product"));
            String ProductType = productList.get("Product Type");
            DriverContext.waitForPageToLoad();
            if (!ProductType.equals("Revues")) {
                Assertions.assertTrue(searchPageActions.txtPrice.isDisplayed());
            }
            searchPageActions.clickOnProductLink();
            DriverContext.waitForPageToLoad();
            if (ProductType.equals("eBooks")) {
                productPageActions.selectEBookCheckBox();
                productPageActions.addToCart();
                count++;
                DriverContext.waitForPageToLoad();
                DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Vous avez ajouté") || productPageActions.txtSuccessMessage.GetTextValue().contains("La quantité maximale autorisée dans votre panier est limitée à 1") || productPageActions.txtSuccessMessage.GetTextValue().contains("Désolé, vous ne pouvez pas ajouter un livre papier et un ebook dans le même panier."));
                Assertions.assertEquals("VitalSource eBook", DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            } else if (ProductType.equals("Revues") || ProductType.equals("Revue")) {
                journalProductPageActions.addJournalToCart();
                count++;
                DriverContext.waitForPageToLoad();
                DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 3);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Vous avez ajouté") || productPageActions.txtSuccessMessage.GetTextValue().contains("La quantité maximale autorisée dans votre panier est limitée à 1"));
                Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            } else {
                productPageActions.addToCart();
                count++;
                DriverContext.waitForPageToLoad();
                DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Vous avez ajouté"));
                Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            }
        }
        //productPageActions.closeFreeShippingPopup();
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
    }

    @And("I navigate to shipping page and enter the shipping details in France HealthStore")
    public void i_navigate_to_shipping_page_and_enter_the_shipping_details(DataTable table) {
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        shippingPageActions.addShippingDetailsForUK(CucumberUtil.GetCellValueWithRowIndex("Street Address", 1),
                CucumberUtil.GetCellValueWithRowIndex("City", 1),
                CucumberUtil.GetCellValueWithRowIndex("ZipCode", 1),
                CucumberUtil.GetCellValueWithRowIndex("Phone Number", 1));
        shippingPageActions.proceedToReviewAndPayments();
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
    }

    @And("I enter shipping details as a guest user in France Healthstore")
    public void i_enter_shipping_details_as_a_guest_user(DataTable table) {
        guestUserMail = "test.elsevier.regional.ecom+" + Instant.now().getEpochSecond() + "@gmail.com";
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        DriverContext.waitUntilElementIsVisible(shippingPageActions.footer);
        shippingPageActions.addShippingDetailsAsGuestUserUK(guestUserMail, address);
        shippingPageActions.proceedToReviewAndPayments();
    }

    @And("I navigate to payment page to select the payment method and enter the payment details with different billing address in FR-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details_with_different_billing_address(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.selectCIC();
        paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsClickable(paymentPageActions.btnProceedToPayFR);
        DriverContext.driverSleep(15000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.addDifferentBillingAddressFR(billingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditionsInFRHS();
        DriverContext.waitUntilElementIsClickable(paymentPageActions.btnProceedToPayFR);
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardFR();
        List<Map<String, String>> paymentDetails = table.asMaps(String.class, String.class);
        // if (frameworkConfigurationService.getTestEnv().equalsIgnoreCase("prod")) { // To be removed after RSR-5301 is deployed to production
        //     eCapturePageActions.fillInPaymentDetailsFrance(paymentDetails);
        // }else{
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetailsSP(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
        //  }
    }

    @And("I navigate to payment page as a existing user and select different billing address and enter payment details in FR-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details_with_different_billing_address_as_existing_user(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        paymentPageActions.selectCIC();
        paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsClickable(paymentPageActions.btnProceedToPayFR);
        paymentPageActions.addDifferentBillingAddressForExistingUserFR(billingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditionsInFRHS();
        DriverContext.waitUntilElementIsClickable(paymentPageActions.btnProceedToPayFR);
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardFR();
        DriverContext.waitForPageToLoad();
        List<Map<String, String>> paymentDetails = table.asMaps(String.class, String.class);
        // if (frameworkConfigurationService.getTestEnv().equalsIgnoreCase("prod")) { // To be removed after RSR-5301 is deployed to production
        //    eCapturePageActions.fillInPaymentDetailsFrance(paymentDetails);
        // }else{
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetailsSP(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
        // }
    }

    @And("I navigate to payment page to select the payment method and enter the payment details in FR-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details(DataTable table) {
        paymentPageActions.selectCIC();
        paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        paymentPageActions.acceptTermsAndConditionsInFRHS();
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardFR();
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> paymentDetails = table.asMaps(String.class, String.class);
        //if (frameworkConfigurationService.getTestEnv().equalsIgnoreCase("prod")) { // To be removed after RSR-5301 is deployed to production
        //if (frameworkConfigurationService.getTestEnv().equalsIgnoreCase("prod")) {
        //   eCapturePageActions.fillInPaymentDetailsFrance(paymentDetails);
        // }
        // else if (!clinicalKeyNow||!eBook) {
        //     eCapturePageActions.fillInPaymentDetailsFrance(paymentDetails);
        // } else{
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetailsSP(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
        // }
    }

    @And("I navigate to payment page and enter the payment details in FR-HS")
    public void i_navigate_to_payment_page_and_enter_the_payment_details(DataTable table) {
        paymentPageActions.acceptTermsAndConditionsInFRHS();
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardFR();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetailsSP(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I place the order in France Health Store")
    public void i_place_the_order() {
        eCapturePageActions.submitPaymentFrance();
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
    }

    @Then("I add the product to wishlist from search page in FR-HS")
    public void i_add_the_product_to_wishlist_from_search_page() {
        searchPageActions.addToWishlist();
        DriverContext.waitForPageToLoad();
        log.debug("My Wishlist URL: " + DriverContext.getCurrentUrl());
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("a été ajouté à votre liste d'achats. Cliquez"));
    }

    @Then("I add the product to wishlist from product page in FR-HS")
    public void i_add_the_product_to_wishlist_from_product_page() {
        productPageActions.addToWishlist();
        DriverContext.waitForPageToLoad();
        log.debug("My Wishlist URL: " + DriverContext.getCurrentUrl());
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("a été ajouté à votre liste d'achats. Cliquez"));
    }

    @And("I remove the product from cart in FR-HS")
    public void i_remove_the_product_from_cart() {
        viewCartPageActions.emptyCart();
        DriverContext.waitUntilElementIsVisible(viewCartPageActions.txtCartEmptyMessage);
        Assertions.assertEquals("Votre panier est vide.", viewCartPageActions.txtCartEmptyMessage.GetTextValue());
    }

    @And("I navigate to payment page and enter the billing address and payment details in FR-HS")
    public void i_navigate_to_payment_page_enter_the_billing_details_fr(DataTable table) {
        List<Map<String, String>> shippingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addDifferentBillingAddressFR(shippingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditionsInFRHS();
        DriverContext.driverSleep(30000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardGermany();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetailsSP(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page and enter the billing address details in FR-HS")
    public void i_navigate_to_payment_page_and_enter_the_billing_address_details_in_fr_hs(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addBillingAddressFR(billingAddress);
        DriverContext.jsScrollToElementAlignBottom(paymentPageActions.btnUpdateBillingAddressFR);
        paymentPageActions.btnUpdateBillingAddressFR.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    @And("I navigate to payment page and select different billing address in France HealthStore")
    public void i_navigate_to_payment_page_and_select_different_billing_address_in_france_health_store(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsClickable(paymentPageActions.btnProceedToPayFR);
        paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        DriverContext.repeatWaitForElementVisibilityAttempt(paymentPageActions.chkboxShippingAndBillingAddress, 3);
        DriverContext.jsClickOnElement(paymentPageActions.chkboxShippingAndBillingAddress.WaitForClickable());
        paymentPageActions.addBillingAddressFR(billingAddress);

    }

    @Then("I select EMC from product type in navigation bar")
    public void i_select_emc_from_product_type_in_navigation_bar() {
        hsHomePageActions.selectEMCFromNavigationBar();
        DriverContext.waitForPageToLoad();
    }

    @When("I add EMC to the cart in France HealthStore")
    public void i_add_emc_to_the_cart(DataTable table) {
        List<Map<String, String>> subscriptionData = table.asMaps(String.class, String.class);
        String format = subscriptionData.get(0).get("Format");
        String status = subscriptionData.get(0).get("Status");
        String country = subscriptionData.get(0).get("Country");
        String duration = subscriptionData.get(0).get("Subscription Duration");
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        if (frameworkConfigurationService.getTestEnv().equalsIgnoreCase("prod") || frameworkConfigurationService.getTestEnv().equalsIgnoreCase("staging")) {
            journalProductPageActions.addJournalToCart();
        } else {
            journalProductPageActions.addEMCToCartFR(format, status, country, duration);
        }
        DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Vous avez ajouté") || productPageActions.txtSuccessMessage.GetTextValue().contains("La quantité maximale autorisée dans votre panier est limitée à 1"));
        DriverContext.waitForPageToLoad();
        productPageActions.navigateToViewCartPage();
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://uk.uat-regionalecom.tio.systems/checkout/cart/
        if (frameworkConfigurationService.getTestEnv().equalsIgnoreCase("uat")) {
            //Verify the Journal Options displayed correctly in Shopping Cart
            Assertions.assertTrue(productPageActions.txtFormatFR.GetTextValue().equals(format));
            Assertions.assertTrue(productPageActions.txtStatusFR.GetTextValue().equals(status));
            if (format.equals("France")) {
                Assertions.assertTrue(productPageActions.txtCountryFR.GetTextValue().equals("France (+ DOM-TOM)"));
            } else if (format.equals("Rest of World")) {
                Assertions.assertTrue(productPageActions.txtCountryFR.GetTextValue().equals("Reste du monde"));
            } else if (format.equals("Switzerland")) {
                Assertions.assertTrue(productPageActions.txtCountryFR.GetTextValue().equals("UE (+ Suisse)"));
            }
            if (format.equals("12 months")) {
                Assertions.assertTrue(productPageActions.txtDurationFR.GetTextValue().equals("12 mois"));
            } else if (format.equals("24 months")) {
                Assertions.assertTrue(productPageActions.txtDurationFR.GetTextValue().equals("24 mois"));
            } else if (format.equals("36 months")) {
                Assertions.assertTrue(productPageActions.txtDurationFR.GetTextValue().equals("36 mois"));
            }
        }
    }

    @Then("I select a product {string} from the EMC Category")
    public void i_select_a_product_from_the_emc_category(String productName) {
        searchPageActions.clickOnProduct(productName);
    }

}
