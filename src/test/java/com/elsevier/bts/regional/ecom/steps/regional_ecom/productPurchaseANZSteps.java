package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.framework.utilities.JavaUtil;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.elsevier.bts.regional.ecom.steps.regional_ecom.ProductPurchaseCommonSteps.guestUserMail;

public class productPurchaseANZSteps extends BasePage {

    SearchPageActions searchPageActions = GetInstance(SearchPageActions.class);
    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);
    JournalProductPageActions journalProductPageActions = GetInstance(JournalProductPageActions.class);
    ProductPageActions productPageActions = GetInstance(ProductPageActions.class);
    ShippingPageActions shippingPageActions = GetInstance(ShippingPageActions.class);
    PaymentPageActions paymentPageActions = GetInstance(PaymentPageActions.class);
    ECapturePageActions eCapturePageActions = GetInstance(ECapturePageActions.class);

    OrderSuccessPageActions orderSuccessPageActions = GetInstance(OrderSuccessPageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @And("I search for different product under different product type and add them to cart in ANZ healthstore")
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
            if (ProductType.equals("E-Book") || ProductType.equals("E-Books")) {
                productPageActions.selectEBookCheckBox();
                productPageActions.addToCart();
                count++;
                DriverContext.waitForPageToLoad();
                DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
                Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added") || productPageActions.txtSuccessMessage.GetTextValue().contains("The maximum quantity you may purchase is limited to 1 print books and 1 single copy per eBook, with a limit of 5 eBooks per order."), "E-book added to cart message does not contain - You added");
                Assertions.assertEquals("VitalSource eBook", DriverContext.getElementByXpath(itemCategory + "[" + count + "]").getText());
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
        ProductPageActions.eBook = false;
        productPageActions.navigateToViewCartPage();
        DriverContext.waitForPageToLoad();
    }

    @And("I enter shipping details as a guest user in ANZ Healthstore")
    public void i_enter_shipping_details_as_a_guest_user(DataTable table) {
        guestUserMail = "test.elsevier.regional.ecom+" + Instant.now().getEpochSecond() + "@gmail.com";
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        DriverContext.waitUntilElementIsVisible(shippingPageActions.footer);
        shippingPageActions.addShippingDetailsAsGuestUserUK(guestUserMail, address);
        shippingPageActions.proceedToReviewAndPayments();
    }

    @And("I navigate to payment page to select the payment method and enter the payment details with different billing address in ANZ-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details_with_different_billing_address(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(60000); // TODO: Replace in the future with a suitable explicit wait
        paymentPageActions.selectCreditCardPaymentMethod();
        paymentPageActions.addDifferentBillingAddressEU(billingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditionsANZHS();
        DriverContext.driverSleep(10000);
        paymentPageActions.proceedToPayInSP();
        DriverContext.waitForPageToLoad();
        List<Map<String, String>> paymentDetails = table.asMaps(String.class, String.class);
        if (!frameworkConfigurationService.getTestEnv().equals("PROD")) {
            eCapturePageActions.fillInPaymentDetailsANZ(paymentDetails);
        }
    }

    @And("I navigate to shipping page and enter the shipping details in ANZ HealthStore")
    public void i_navigate_to_shipping_page_and_enter_the_shipping_details_anz(DataTable table) {
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

    @And("I navigate to payment page as a existing user and select different billing address and enter payment details in ANZ-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details_with_different_billing_address_as_existing_user(DataTable table) {
        List<Map<String, String>> billingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.selectCreditCardPaymentMethod();
        paymentPageActions.addDifferentBillingAddressForExistingUserEU(billingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditionsANZHS();
        DriverContext.driverSleep(30000);
        paymentPageActions.proceedToPayInSP();
        DriverContext.waitForPageToLoad();
        CucumberUtil.ConvertDataTableToDict(table);
        List<Map<String, String>> paymentDetails = table.asMaps(String.class, String.class);
        if (!frameworkConfigurationService.getTestEnv().equals("PROD")) {
            eCapturePageActions.fillInPaymentDetailsANZ(paymentDetails);
        }
    }

    @And("I navigate to payment page and enter the billing address and payment details in ANZ-HS")
    public void i_navigate_to_payment_page_enter_the_billing_details_anz(DataTable table) {
        List<Map<String, String>> shippingAddress = table.asMaps(String.class, String.class);
        DriverContext.waitForPageToLoad();
        paymentPageActions.selectCreditCardPaymentMethod();
        paymentPageActions.addDifferentBillingAddressFR(shippingAddress);
        DriverContext.waitForPageToLoad();
        paymentPageActions.acceptTermsAndConditionsANZHS();
        DriverContext.driverSleep(30000);
        paymentPageActions.proceedToPayInSP();
        List<Map<String, String>> paymentDetails = table.asMaps(String.class, String.class);
        eCapturePageActions.fillInPaymentDetailsANZ(paymentDetails);
    }

    @And("I navigate to payment page and enter payment details in ANZ-HS")
    public void i_navigate_to_payment_page_enter_the_payment_details(DataTable table) {
        List<Map<String, String>> paymentDetails = table.asMaps(String.class, String.class);
        paymentPageActions.selectCreditCardPaymentMethod();
        paymentPageActions.acceptTermsAndConditionsANZHS();
        DriverContext.driverSleep(10000);
        paymentPageActions.proceedToPayInSP();
        DriverContext.waitForPageToLoad();
        if (!frameworkConfigurationService.getTestEnv().equals("PROD")) {
            eCapturePageActions.fillInPaymentDetailsANZ(paymentDetails);
        }
    }

    @And("I place the order for ANZ-HS")
    public void i_place_the_order_ANZ() {
        eCapturePageActions.submitPaymentANZ();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(orderSuccessPageActions.btnContinueShopping, 3);
    }

    @And("I use reward points to purchase in ANZ")
    public void i_use_reward_points_to_purchase() {
        paymentPageActions.useRewardPoints();
        DriverContext.waitForPageToLoad();
    }

}
