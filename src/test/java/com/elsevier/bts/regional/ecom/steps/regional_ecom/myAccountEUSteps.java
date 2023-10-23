package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.HSHomePageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.MyAccountActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
public class myAccountEUSteps extends BasePage {

    MyAccountActions myAccountActions = GetInstance(MyAccountActions.class);
    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @And("I edit the default billing address in EU-HS")
    public void i_edit_the_default_billing_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.changeBillingAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the default shipping address in EU-HS")
    public void i_edit_the_default_shipping_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.changeShippingAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I add new address in EU-HS")
    public void i_add_new_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.addNewAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the newly added address in EU-HS")
    public void i_edit_the_newly_added_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.editNewAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I add the product to cart from wishlist page in EU-HS")
    public void i_add_the_product_to_cart_from_wishlist() {
        myAccountActions.addToCartFromWishlist();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("to your shopping cart.") || myAccountActions.txtSuccessMessage.GetTextValue().contains("The maximum quantity you may purchase is limited to 5 print books and 1 single copy per eBook, with a limit of 5 eBooks per order."));
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        hsHomePageActions.navigateToViewCartPage();
    }

}
