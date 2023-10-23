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

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.elsevier.bts.regional.ecom.steps.regional_ecom.ProductPurchaseCommonSteps.guestUserMail;

@Slf4j
public class productPurchaseUKSteps extends BasePage {

    SearchPageActions searchPageActions = GetInstance(SearchPageActions.class);
    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);
    JournalProductPageActions journalProductPageActions = GetInstance(JournalProductPageActions.class);
    ProductPageActions productPageActions = GetInstance(ProductPageActions.class);
    ViewCartPageActions viewCartPageActions = GetInstance(ViewCartPageActions.class);
    ShippingPageActions shippingPageActions = GetInstance(ShippingPageActions.class);
    PaymentPageActions paymentPageActions = GetInstance(PaymentPageActions.class);
    ECapturePageActions eCapturePageActions = GetInstance(ECapturePageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @And("I search for different product under different product type and add them to cart in UK healthstore")
    public void i_search_for_different_product_and_under_product_type(DataTable table) {
        List<Map<String, String>> product = table.asMaps(String.class, String.class);
        int count = 0;
        String itemCategory = "(.//span[@class='item-product-format'])";
        for (Map<String, String> productList : product) {
            hsHomePageActions.selectProductType(productList.get("Product Type"));
            hsHomePageActions.searchForProduct(productList.get("Product"));
            String ProductType = productList.get("Product Type");
            DriverContext.waitForPageToLoad();
            if (!ProductType.equals("journals") && !ProductType.equals("Journals")) {
                Assertions.assertTrue(searchPageActions.txtPrice.isDisplayed());
            }
            searchPageActions.clickOnProductLink();
            DriverContext.waitForPageToLoad();
            if (ProductType.equals("eBooks")) {
                productPageActions.selectEBookCheckBox();
                productPageActions.addToCart();
                count++;
                DriverContext.waitForPageToLoad();
                DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum quantity you may purchase is limited to 1 print books and 1 single copy per eBook, with a limit of 5 eBooks per order."), "E-book added to cart message does not contain - You added");
                Assertions.assertEquals("VitalSource eBook", DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            } else if (ProductType.equals("journals") || ProductType.equals("Journals")) {
                DriverContext.driverSleep(20000);
                if (frameworkConfigurationService.getTestEnv().equalsIgnoreCase("prod") || frameworkConfigurationService.getTestEnv().equalsIgnoreCase("staging")) {
                    journalProductPageActions.addJournalToCart("Individual", "1 Year", "Domestic");
                } else {
                    journalProductPageActions.addJournalToCart("Personal", "1 Year", "Domestic");
                }
                count++;
                DriverContext.waitForPageToLoad();
                DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum quantity you may purchase is limited to 1 print books and 1 single copy per eBook, with a limit of 5 eBooks per order."), "Journal added to cart message does not contain - You added");
                Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            } else {
                productPageActions.addToCart();
                count++;
                DriverContext.waitForPageToLoad();
                DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added"));
                Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            }
        }
        //productPageActions.closeFreeShippingPopup();
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
    }

    @When("I add the journal to the cart in UK HealthStore")
    public void i_add_the_journal_to_the_cart_in_uk_healthstore() {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        if (frameworkConfigurationService.getTestEnv().equalsIgnoreCase("prod") || frameworkConfigurationService.getTestEnv().equalsIgnoreCase("staging")) {
            journalProductPageActions.addJournalToCart("Individual", "1 Year", "Domestic");
        } else {
            journalProductPageActions.addJournalToCart("Personal", "1 Year", "Domestic");
        }
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum quantity you may purchase is limited to 1 print books and 1 single copy per eBook, with a limit of 5 eBooks per order."), "Journal added to cart message does not contain - You added");
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), viewCartPageActions.txtProductCategory.GetTextValue());
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://uk.uat-regionalecom.tio.systems/checkout/cart/
    }

    @When("I add the E-book to the cart in UK HealthStore")
    public void i_add_the_e_book_to_the_cart() {
        productPageActions.selectEBookCheckBox();
        productPageActions.addToCart();
        DriverContext.repeatWaitForElementVisibilityAttempt(productPageActions.txtSuccessMessage, 2);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum quantity you may purchase is limited to 1 print books and 1 single copy per eBook, with a limit of 5 eBooks per order."), "E-book added to cart message does not contain - You added");
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals("VitalSource eBook", viewCartPageActions.txtProductCategory.GetTextValue());
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
    }

    @And("I enter shipping details as a guest user in UK Healthstore")
    public void i_enter_shipping_details_as_a_guest_user(DataTable table) {
        guestUserMail = "test.elsevier.regional.ecom+" + Instant.now().getEpochSecond() + "@gmail.com";
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        DriverContext.waitUntilElementIsVisible(shippingPageActions.footer);
        shippingPageActions.addShippingDetailsAsGuestUserUK(guestUserMail, address);
        shippingPageActions.proceedToReviewAndPayments();
    }

    @And("I navigate to shipping page and enter the shipping details in UK HealthStore")
    public void i_navigate_to_shipping_page_and_enter_the_shipping_details(DataTable table) {
        //viewCartPageActions.changeItemQuantity("1");
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        shippingPageActions.addShippingDetailsForUK(CucumberUtil.GetCellValueWithRowIndex("Street Address", 1),
                CucumberUtil.GetCellValueWithRowIndex("City", 1),
                CucumberUtil.GetCellValueWithRowIndex("ZipCode", 1),
                CucumberUtil.GetCellValueWithRowIndex("Phone Number", 1));
        DriverContext.driverSleep(10000); //TODO: Remove in the future if performance issue is resolved
        shippingPageActions.proceedToReviewAndPayments();
        DriverContext.waitForPageToLoad();
    }

    @And("I navigate to payment page to select the payment method and enter the payment details")
    public void i_navigate_to_payment_page_enter_the_payment_details(DataTable table) {
        DriverContext.waitForPageToLoad();
        if (!ProductPageActions.eBook) {
            paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        }
        DriverContext.driverSleep(10000); //To be replaced in future with explicit wait
        paymentPageActions.acceptTermsAndConditions();
        DriverContext.driverSleep(25000);//To be replaced in future with explicit wait
        paymentPageActions.proceedToPayWithCreditCardUK();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page to select the payment method and enter the payment details in ASIA HS")
    public void i_navigate_to_payment_page_enter_the_payment_details_asia(DataTable table) {
        DriverContext.waitForPageToLoad();
        if (!ProductPageActions.eBook) {
            paymentPageActions.verifyShippingAndBillingAddressCheckbox();
        }
        DriverContext.driverSleep(10000); //To be replaced in future with explicit wait
        paymentPageActions.acceptTermsAndConditions();
        DriverContext.driverSleep(25000);//To be replaced in future with explicit wait
        paymentPageActions.proceedToPayWithCreditCardUK();
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


    @And("I navigate to payment page to select the payment method and enter the payment details with different billing address in UK-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details_with_different_billing_address(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.driverSleep(25000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.waitForPageToLoad();
        paymentPageActions.addDifferentBillingAddressUK(billingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditions();
        paymentPageActions.proceedToPayWithCreditCardUK();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page as a existing user and select different billing address and enter payment details in UK-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details_with_different_billing_address_as_existing_user(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addDifferentBillingAddressForExistingUserUK(billingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditions();
        paymentPageActions.proceedToPayWithCreditCardUK();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page and enter the billing address and payment details in UK-HS")
    public void i_navigate_to_payment_page_enter_the_billing_details_uk(DataTable table) {
        List<Map<String, String>> shippingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addDifferentBillingAddressFR(shippingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditions();
        DriverContext.driverSleep(30000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardUK();
        ;
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

}

