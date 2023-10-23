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
public class myAccountDESteps extends BasePage {

    MyAccountActions myAccountActions = GetInstance(MyAccountActions.class);
    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @Then("I edit the profile information in DE-HS")
    public void i_edit_the_profile_information(DataTable table) {
        CucumberUtil.ConvertDataTableToDict(table);
        myAccountActions.editProfileInformation(CucumberUtil.GetCellValueWithRowIndex("Title", 1),
                CucumberUtil.GetCellValueWithRowIndex("Profession", 1),
                CucumberUtil.GetCellValueWithRowIndex("Speciality", 1));
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("Profil erfolgreich gespeichert.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the default billing address in DE-HS")
    public void i_edit_the_default_billing_address(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.changeBillingAddressUK(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("Sie haben die Adresse gespeichert.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the default shipping address in DE-HS")
    public void i_edit_the_default_shipping_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.changeShippingAddressUK(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("Sie haben die Adresse gespeichert.", myAccountActions.txtSuccessMessage.GetTextValue());

    }

    @And("I add new address in DE-HS")
    public void i_add_new_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.addNewAddressUK(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("Sie haben die Adresse gespeichert.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the newly added address in DE-HS")
    public void i_edit_the_newly_added_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.editNewAddressUK(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("Sie haben die Adresse gespeichert.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I delete the newly added address in DE-HS")
    public void i_delete_the_newly_added_address() {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        myAccountActions.deleteNewAddress();
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("Sie haben die Adresse gelöscht.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I add the product to cart from wishlist page in DE-HS")
    public void i_add_the_product_to_cart_from_wishlist() {
        myAccountActions.addToCartFromWishlist();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("zu Ihrem") || myAccountActions.txtSuccessMessage.GetTextValue().contains("Sie haben"));
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        hsHomePageActions.navigateToViewCartPage();
    }

    @And("I remove the product from wishlist in DE-HS")
    public void i_remove_the_product_from_wishlist() {
        myAccountActions.removeFromWishlist();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("wurde von Ihrer Wunschliste entfernt."));
    }

}
