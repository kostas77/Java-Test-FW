package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.HSHomePageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.MyAccountActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
public class myAccountUSSteps extends BasePage {

    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);
    MyAccountActions myAccountActions = GetInstance(MyAccountActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @And("I navigate to my account page")
    public void i_navigate_to_my_account_page() {
        hsHomePageActions.navigateToMyAccountPage();
        DriverContext.waitForPageToLoad();
        log.debug("My Account URL: " + DriverContext.getCurrentUrl());
    }

    @Then("I edit the profile information")
    public void i_edit_the_profile_information(DataTable table) {
        CucumberUtil.ConvertDataTableToDict(table);
        myAccountActions.editProfileInformation(CucumberUtil.GetCellValueWithRowIndex("Title", 1),
                CucumberUtil.GetCellValueWithRowIndex("Profession", 1),
                CucumberUtil.GetCellValueWithRowIndex("Speciality", 1));
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the profile information.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @Then("I navigate to Address book page")
    public void i_navigate_to_address_book_page() {
        myAccountActions.btnAddressBook.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    @And("I edit the default billing address")
    public void i_edit_the_default_billing_address(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.changeBillingAddress(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the default shipping address")
    public void i_edit_the_default_shipping_address(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.changeShippingAddress(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());

    }

    @And("I add new address")
    public void i_add_new_address(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.addNewAddress(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the newly added address")
    public void i_edit_the_newly_added_address(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.editNewAddress(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I delete the newly added address")
    public void i_delete_the_newly_added_address() {
        myAccountActions.deleteNewAddress();
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You deleted the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I navigate to wishlist page")
    public void i_navigate_to_wishlist_page() {
        myAccountActions.btnMyWishlist.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    @And("I add the product to cart from wishlist page")
    public void i_add_the_product_to_cart_from_wishlist() {
        myAccountActions.addToCartFromWishlist();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("to your shopping cart."));
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        hsHomePageActions.navigateToViewCartPage();
    }

    @And("I remove the product from wishlist")
    public void i_remove_the_product_from_wishlist() {
        myAccountActions.removeFromWishlist();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("has been removed from your Wish List."));
    }

    @And("I navigate to my orders page")
    public void i_navigate_to_my_orders_page() {
        myAccountActions.btnMyOrder.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    @And("I reorder from existing purchase")
    public void i_reorder_from_existing_purchase() {
        myAccountActions.btnReOrder.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

}
