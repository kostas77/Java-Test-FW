package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.JavaUtil;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import static com.elsevier.bts.regional.ecom.steps.regional_ecom.ProductPurchaseCommonSteps.guestUserMail;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Slf4j
public class productPurchaseMEASteps extends BasePage {

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

    @When("I add the E-book to the cart in Middle East HealthStore")
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

    @When("I add the journal to the cart in Middle East HealthStore")
    public void i_add_the_journal_to_the_cart() {
        DriverContext.driverSleep(15000); // TODO: Replace in the future with a suitable explicit wait
        journalProductPageActions.addJournalToCart("Individual", "1 Year", "Domestic");
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum number of copies of a single product you may purchase is 1. Please contact a"), "Journal added to cart message does not contain - You added");
        DriverContext.waitForPageToLoad();
        Assertions.assertEquals(JavaUtil.convertToSingular(HSHomePageActions.productCategory), viewCartPageActions.txtProductCategory.GetTextValue());
        productPageActions.navigateToViewCartPage();
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://uk.uat-regionalecom.tio.systems/checkout/cart/
    }

    @And("I search for different product under different product type and add them to cart in Middle East HealthStore")
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
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum number of copies of a single product you may purchase is 1. Please contact a"), "E-book added to cart message does not contain - You added");
                Assertions.assertEquals("VitalSource eBook", DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
            } else if (ProductType.equals("journals") || ProductType.equals("Journals")) {
                DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
                journalProductPageActions.addJournalToCart("Individual", "1 Year", "Domestic");
                count++;
                DriverContext.waitForPageToLoad();
                DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum number of copies of a single product you may purchase is 1. Please contact a"), "Journal added to cart message does not contain - You added");
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

    @And("I navigate to shipping page and enter the shipping details in Middle East HealthStore")
    public void i_navigate_to_shipping_page_and_enter_the_shipping_details(DataTable table) {
        //viewCartPageActions.changeItemQuantity("1");
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        shippingPageActions.addShippingDetailsForMEA(CucumberUtil.GetCellValueWithRowIndex("Street Address", 1),
                CucumberUtil.GetCellValueWithRowIndex("City", 1),
                CucumberUtil.GetCellValueWithRowIndex("ZipCode", 1),
                CucumberUtil.GetCellValueWithRowIndex("Country", 1),
                CucumberUtil.GetCellValueWithRowIndex("Phone Number", 1));
        shippingPageActions.proceedToReviewAndPayments();
        DriverContext.waitForPageToLoad();
    }

    @And("I enter shipping details as a guest user in Middle East HealthStore")
    public void i_enter_shipping_details_as_a_guest_user(DataTable table) {
        guestUserMail = "test.elsevier.regional.ecom+" + Instant.now().getEpochSecond() + "@gmail.com";
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        DriverContext.waitUntilElementIsVisible(shippingPageActions.footer);
        shippingPageActions.addShippingDetailsAsGuestUserMEA(guestUserMail, address);
        shippingPageActions.proceedToReviewAndPayments();
    }

    @And("I navigate to payment page to select the payment method and enter the payment details with different billing address in MEA-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details_with_different_billing_address(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addDifferentBillingAddressMEA(billingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditions();
        DriverContext.waitUntilElementIsClickable(paymentPageActions.btnProceedToPayWithCreditCardUK);
        paymentPageActions.proceedToPayWithCreditCardUK();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

    @And("I navigate to payment page as a existing user and select different billing address and enter payment details in MEA-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details_with_different_billing_address_as_existing_user(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addDifferentBillingAddressForExistingUserMEA(billingAddress);
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

    @And("I navigate to payment page and enter the billing address and payment details in MEA-HS")
    public void i_navigate_to_payment_page_enter_the_billing_details_mea(DataTable table) {
        List<Map<String, String>> shippingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.addDifferentBillingAddressFR(shippingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditions();
        DriverContext.driverSleep(30000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.proceedToPayWithCreditCardUK();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        eCapturePageActions.fillInPaymentDetails(CucumberUtil.GetCellValueWithRowIndex("Card Number", 1),
                CucumberUtil.GetCellValueWithRowIndex("Expiry Date", 1),
                CucumberUtil.GetCellValueWithRowIndex("CVV", 1),
                CucumberUtil.GetCellValueWithRowIndex("Name on card", 1));
    }

}
