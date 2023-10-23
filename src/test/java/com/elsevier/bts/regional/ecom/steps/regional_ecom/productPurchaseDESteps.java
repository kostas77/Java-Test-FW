package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import static com.elsevier.bts.regional.ecom.steps.regional_ecom.ProductPurchaseCommonSteps.guestUserMail;

import java.sql.DriverManager;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Slf4j
public class productPurchaseDESteps extends BasePage {

    SearchPageActions searchPageActions = GetInstance(SearchPageActions.class);
    MyAccountActions myAccountActions = GetInstance(MyAccountActions.class);
    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);
    JournalProductPageActions journalProductPageActions = GetInstance(JournalProductPageActions.class);

    ViewCartPageActions viewCartPageActions = GetInstance(ViewCartPageActions.class);

    ProductPageActions productPageActions = GetInstance(ProductPageActions.class);
    ShippingPageActions shippingPageActions = GetInstance(ShippingPageActions.class);
    PaymentPageActions paymentPageActions = GetInstance(PaymentPageActions.class);
    ECapturePageActions eCapturePageActions = GetInstance(ECapturePageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @When("I add the journal to the cart in Germany HealthStore")
    public void i_add_the_journal_to_the_cart() {
        journalProductPageActions.addJournalToCart();
        DriverContext.waitForPageToLoad();
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Sie haben")
                || productPageActions.txtSuccessMessage.GetTextValue().contains("Ihr Warenkorb überschreitet die maximal zulässige Anzahl an Produkten – Sie können maximal 1 Exemplare eines Buches in den Warenkorb legen."), "Journal added to cart message does not contain - Sie haben");
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals(HSHomePageActions.productCategory, viewCartPageActions.txtProductCategory.GetTextValue());
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl());
    }

    @When("I add the product to the cart in Germany HealthStore")
    public void i_add_the_product_to_the_cart() {
        productPageActions.addToCart();
        DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Sie haben") ||
                productPageActions.txtSuccessMessage.GetTextValue().contains("Aus technischen Gründen können können eBooks und gedruckte Bücher leider nicht gleichzeitig in den Warenkorb gelegt werden - bitte bestellen Sie in separaten Kaufprozessen. Das ist leider etwas umständlicih, bitte entschuldigen Sie dies - wir arbeiten an der Behebung des Defekts."));
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals(HSHomePageActions.productCategory, viewCartPageActions.txtProductCategory.GetTextValue());
        DriverContext.driverSleep(15000); // TODO: Replace in the future with a suitable explicit wait
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://de.uat-regionalecom.tio.systems/checkout/cart/
    }

    @When("I add the E-book to the cart in Germany HealthStore")
    public void i_add_the_e_book_to_the_cart() {
        productPageActions.selectEBookCheckBox();
        productPageActions.addToCart();
        DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Sie haben") ||
                productPageActions.txtSuccessMessage.GetTextValue().contains("Ihr Warenkorb überschreitet die maximal zulässige Anzahl an Produkten – Sie können maximal 1 Exemplare eines Buches in den Warenkorb legen.") ||
                productPageActions.txtSuccessMessage.GetTextValue().contains("Aus technischen Gründen können können eBooks und gedruckte Bücher leider nicht gleichzeitig in den Warenkorb gelegt werden - bitte bestellen Sie in separaten Kaufprozessen. Das ist leider etwas umständlicih, bitte entschuldigen Sie dies - wir arbeiten an der Behebung des Defekts."), "E-book added to cart message does not contain - Sie haben");
        productPageActions.closeFreeShippingPopup();
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals("VitalSource eBook", viewCartPageActions.txtProductCategory.GetTextValue());
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
    }

    @And("I search for different product under different product type and add them to cart in Germany HealthStore")
    public void i_search_for_different_product_and_under_product_type(DataTable table) {
        List<Map<String, String>> product = table.asMaps(String.class, String.class);
        int count = 0;
        String itemCategory = "(.//span[@class='item-product-format'])";
        for (Map<String, String> productList : product) {
            hsHomePageActions.selectProductType(productList.get("Product Type"));
            hsHomePageActions.searchForProduct(productList.get("Product"));
            String ProductType = productList.get("Product Type");
            DriverContext.waitForPageToLoad();
            Assertions.assertNotNull(searchPageActions.txtPrice);
            searchPageActions.clickOnProductLink();
            DriverContext.waitForPageToLoad();
            if (ProductType.equals("eBooks")) {
                productPageActions.selectEBookCheckBox();
                productPageActions.addToCart();
                count++;
                DriverContext.waitForPageToLoad();
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Sie haben") ||
                        productPageActions.txtSuccessMessage.GetTextValue().contains("Ihr Warenkorb überschreitet die maximal zulässige Anzahl an Produkten – Sie können maximal 1 Exemplare eines Buches in den Warenkorb legen.") ||
                        productPageActions.txtSuccessMessage.GetTextValue().contains("Aus technischen Gründen können können eBooks und gedruckte Bücher leider nicht gleichzeitig in den Warenkorb gelegt werden - bitte bestellen Sie in separaten Kaufprozessen. Das ist leider etwas umständlicih, bitte entschuldigen Sie dies - wir arbeiten an der Behebung des Defekts."), "E-book added to cart message does not contain - Sie haben");
                Assertions.assertEquals("VitalSource eBook", DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            } else if (ProductType.equals("Loseblatt")) {
                journalProductPageActions.addJournalToCart();
                count++;
                DriverContext.waitForPageToLoad();
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Sie haben") || productPageActions.txtSuccessMessage.GetTextValue().contains("Ihr Warenkorb überschreitet die maximal zulässige Anzahl an Produkten – Sie können maximal 1 Exemplare eines Buches in den Warenkorb legen."), "Journal added to cart message does not contain - Sie haben");
                Assertions.assertEquals(HSHomePageActions.productCategory, DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            } else {
                productPageActions.addToCart();
                count++;
                DriverContext.waitForPageToLoad();
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Sie haben") ||
                        productPageActions.txtSuccessMessage.GetTextValue().contains("Aus technischen Gründen können können eBooks und gedruckte Bücher leider nicht gleichzeitig in den Warenkorb gelegt werden - bitte bestellen Sie in separaten Kaufprozessen. Das ist leider etwas umständlicih, bitte entschuldigen Sie dies - wir arbeiten an der Behebung des Defekts."));
                Assertions.assertEquals(HSHomePageActions.productCategory, DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            }
        }
        //productPageActions.closeFreeShippingPopup();
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
    }

    @And("I enter shipping details as a guest user in Germany Healthstore")
    public void i_enter_shipping_details_as_a_guest_user(DataTable table) {
        guestUserMail = "test.elsevier.regional.ecom+" + Instant.now().getEpochSecond() + "@gmail.com";
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        shippingPageActions.addShippingDetailsAsGuestUserEU(guestUserMail, address);
        shippingPageActions.proceedToReviewAndPayments();
        DriverContext.waitForPageToLoad();
    }

    @And("I navigate to shipping page and enter the shipping details in Germany HealthStore")
    public void i_navigate_to_shipping_page_and_enter_the_shipping_details(DataTable table) {
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        shippingPageActions.addShippingDetailsForUK(CucumberUtil.GetCellValueWithRowIndex("Street Address", 1),
                CucumberUtil.GetCellValueWithRowIndex("City", 1),
                CucumberUtil.GetCellValueWithRowIndex("ZipCode", 1),
                CucumberUtil.GetCellValueWithRowIndex("Phone Number", 1));
        shippingPageActions.proceedToReviewAndPayments();
        DriverContext.waitForPageToLoad();
    }

    @And("I navigate to payment page and enter the payment details in DE-HS")
    public void i_navigate_to_payment_page_and_enter_the_payment_details(DataTable table) {
        paymentPageActions.acceptTermsAndConditionsInDEHS();
        if (ClinicalKeyProductPageActions.clinicalKeyNow) {
            if (frameworkConfigurationService.getTestEnv().equals("PROD")) { // TODO: Replace in future when UAT Keys are fixed
                paymentPageActions.selectVisaPaymentDEHS();
            }
            //paymentPageActions.acceptEndUserLicenseAgreement();
            DriverContext.driverSleep(35000); // TODO: Replace in the future with a suitable explicit wait
            paymentPageActions.proceedToPayWithCreditCardGermany();

        } else {
            paymentPageActions.proceedToPayWithCreditCardGermany();

        }
        CucumberUtil.ConvertDataTableToDict(table);
        if (frameworkConfigurationService.getTestEnv().equals("PROD")) {
            eCapturePageActions.fillInPaymentDetailsGermany(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
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


    @And("I navigate to payment page to select the payment method and enter the payment details in DE-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details(DataTable table) {
        paymentPageActions.selectPaymentMethodInDEHS();
        DriverContext.waitForPageToLoad();
        paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        paymentPageActions.acceptTermsAndConditionsInDEHS();
        DriverContext.driverSleep(10000);
        DriverContext.waitForPageToLoad();
        paymentPageActions.proceedToPayWithCreditCardGermany();
        DriverContext.driverSleep(20000);
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetailsGermany(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page and choose paypal payment in DE")
    public void i_navigate_to_payment_page_and_choose_the_paypal_in_DE(DataTable table) {
        paymentPageActions.selectPaypalMethodInDEHS();
        DriverContext.waitForPageToLoad();
        paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        paymentPageActions.acceptTermsAndConditionsInDEHS();
        DriverContext.driverSleep(10000);
        DriverContext.waitForPageToLoad();
        paymentPageActions.proceedToPayWithCreditCardGermany();
        DriverContext.driverSleep(20000);
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.payPalPaymentExpressDetails(CucumberUtil.GetCellValueWithRowIndex("Paypal Username", 1),
                CucumberUtil.GetCellValueWithRowIndex("Password", 1));
    }

    @And("I navigate to payment page as a existing user and select different billing address and enter payment details in DE-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details_with_different_billing_address_as_existing_user(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        paymentPageActions.selectPaymentMethodInDEHS();
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(20000);
        paymentPageActions.addDifferentBillingAddressForExistingUserDE(billingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditionsInDEHS();
        DriverContext.driverSleep(10000);
        paymentPageActions.proceedToPayWithCreditCardGermany();
        DriverContext.driverSleep(20000);
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetailsGermany(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page to select the payment method and enter the payment details with different billing address in DE-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details_with_different_billing_address(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.selectPaymentMethodInDEHS();
        DriverContext.waitForPageToLoad();
        paymentPageActions.addDifferentBillingAddressDE(billingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditionsInDEHS();
        paymentPageActions.proceedToPayWithCreditCardGermany();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetailsGermany(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I place the order in German Health Store")
    public void i_place_the_order() {
        eCapturePageActions.submitPayment();
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
    }

    @Then("I add the product to wishlist from search page in DE-HS")
    public void i_add_the_product_to_wishlist_from_search_page() {
        searchPageActions.addToWishlist();
        DriverContext.waitForPageToLoad();
        log.debug("My Wishlist URL: " + DriverContext.getCurrentUrl());
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("wurde zu Ihrer Wunschliste hinzugefügt."));
    }

    @Then("I add the product to wishlist from product page in DE-HS")
    public void i_add_the_product_to_wishlist_from_product_page() {
        productPageActions.addToWishlist();
        DriverContext.waitForPageToLoad();
        log.debug("My Wishlist URL: " + DriverContext.getCurrentUrl());
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("wurde zu Ihrer Wunschliste hinzugefügt."));
    }

    @And("I navigate to payment page and enter the billing address and payment details in DE-HS")
    public void i_navigate_to_payment_page_enter_the_billing_details(DataTable table) {
        List<Map<String, String>> shippingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        if (!ProductPageActions.eBook) {
            paymentPageActions.addShippingDetailsForExistingUserDE(shippingAddress);
            DriverContext.waitForPageToLoad();
            if (!ClinicalKeyProductPageActions.clinicalKeyNowFreeTrial) {
                //paymentPageActions.selectVisaPaymentDEHS(); TODO : Commented until a fix is done
            }
            //paymentPageActions.acceptEndUserLicenseAgreement();

        } else {
            paymentPageActions.addDifferentBillingAddressFR(shippingAddress);
            DriverContext.waitForPageToLoad();
        }
        paymentPageActions.acceptTermsAndConditionsInDEHS();
        DriverContext.driverSleep(30000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardGermany();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        if (!ProductPageActions.eBook && !ClinicalKeyProductPageActions.clinicalKeyNow) {
            eCapturePageActions.fillInPaymentDetailsGermany(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                    CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                    CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                    CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
        } else {
            eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                    CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                    CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                    CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
        }
    }

    @And("I navigate to payment page and enter different billing address and enter payment details in DE-HS")
    public void i_navigate_to_payment_page_and_enter_different_billing_address_and_enter_payment_details(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.driverSleep(30000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.addDifferentBillingAddressForEBooksAndClinicalKey(billingAddress);
        DriverContext.driverSleep(20000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.acceptTermsAndConditionsInDEHS();
        if (ClinicalKeyProductPageActions.clinicalKeyNow) {
            if (frameworkConfigurationService.getTestEnv().equals("PROD")) { // TODO: Replace in future when UAT Keys are fixed
                paymentPageActions.selectVisaPaymentDEHS();
            }
            // paymentPageActions.acceptEndUserLicenseAgreement();
            DriverContext.driverSleep(35000); // TODO: Replace in the future with a suitable explicit wait
            paymentPageActions.proceedToPayWithCreditCardGermany();
        } else {
            paymentPageActions.proceedToPayWithCreditCardGermany();
        }
        CucumberUtil.ConvertDataTableToDict(table);
        if (frameworkConfigurationService.getTestEnv().equals("PROD")) {
            if (ProductPageActions.eBook) {
                eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                        CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                        CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                        CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
            } else {
                eCapturePageActions.fillInPaymentDetailsGermany(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                        CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                        CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                        CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
            }
        } else {
            eCapturePageActions.fillInPaymentDetailsSP(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                    CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                    CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                    CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
        }
    }

    @And("I navigate to payment page and enter the billing address and proceed for free trial in DE-HS")
    public void i_navigate_to_payment_page_enter_the_billing_details_for_free_trial(DataTable table) {
        //For DE Free trial to paid renewal conversion is disabled with configuration
        List<Map<String, String>> shippingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addShippingDetailsForExistingUserDE(shippingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditionsInDEHS();
        DriverContext.driverSleep(30000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardGermany();
        DriverContext.waitForPageToLoad();
    }

}
