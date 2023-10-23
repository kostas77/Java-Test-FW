package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
public class myAccountSPSteps extends BasePage {

    MyAccountActions myAccountActions = GetInstance(MyAccountActions.class);
    SearchPageActions searchPageActions = GetInstance(SearchPageActions.class);
    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);
    ProductPageActions productPageActions = GetInstance(ProductPageActions.class);
    ViewCartPageActions viewCartPageActions = GetInstance(ViewCartPageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @Then("I edit the profile information in SP-HS")
    public void i_edit_the_profile_information(DataTable table) {
        CucumberUtil.ConvertDataTableToDict(table);
        myAccountActions.editProfileInformation(CucumberUtil.GetCellValueWithRowIndex("Title", 1),
                CucumberUtil.GetCellValueWithRowIndex("Profession", 1),
                CucumberUtil.GetCellValueWithRowIndex("Speciality", 1));
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("La información del perfil se ha guardado correctamente.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the default billing address in SP-HS")
    public void i_edit_the_default_billing_address(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.changeBillingAddressUK(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("Guardó la dirección.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the default shipping address in SP-HS")
    public void i_edit_the_default_shipping_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.changeShippingAddressUK(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("Guardó la dirección.", myAccountActions.txtSuccessMessage.GetTextValue());

    }

    @And("I add new address in SP-HS")
    public void i_add_new_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.addNewAddressUK(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("Guardó la dirección.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the newly added address in SP-HS")
    public void i_edit_the_newly_added_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.editNewAddressUK(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("Guardó la dirección.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I delete the newly added address in SP-HS")
    public void i_delete_the_newly_added_address() {
        myAccountActions.deleteNewAddress();
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("Borró la dirección.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @Then("I add the product to wishlist from search page in SP-HS")
    public void i_add_the_product_to_wishlist_from_search_page() {
        searchPageActions.addToWishlist();
        DriverContext.waitForPageToLoad();
        log.debug("My Wishlist URL: " + DriverContext.getCurrentUrl());
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("se ha agregado a su lista de deseos."));
    }

    @Then("I add the product to wishlist from product page in SP-HS")
    public void i_add_the_product_to_wishlist_from_product_page() {
        productPageActions.addToWishlist();
        DriverContext.waitForPageToLoad();
        log.debug("My Wishlist URL: " + DriverContext.getCurrentUrl());
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("se ha agregado a su lista de deseos."));
    }

    @And("I remove the product from wishlist in SP-HS")
    public void i_remove_the_product_from_wishlist() {
        myAccountActions.removeFromWishlist();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("se ha eliminado de tu Lista de Deseos."));
    }

    @And("I add the product to cart from wishlist page in SP-HS")
    public void i_add_the_product_to_cart_from_wishlist() {
        myAccountActions.addToCartFromWishlist();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("Añadiste") || myAccountActions.txtSuccessMessage.GetTextValue().contains("Ha agregado"));
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        hsHomePageActions.navigateToViewCartPage();
    }

    @And("I add a review to a product in SP-HS")
    public void i_add_a_review_to_a_product(DataTable table) {
        List<Map<String, String>> reviewDetails = table.asMaps(String.class, String.class);
        productPageActions.closeFreeShippingPopup();
        productPageActions.addReview(reviewDetails);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(productPageActions.txtReviewSuccessMessage);
        Assertions.assertEquals("Usted presentó su reseña a moderación.", productPageActions.txtReviewSuccessMessage.GetTextValue());
    }

    @And("I remove the product from cart in SP-HS")
    public void i_remove_the_product_from_cart() {
        viewCartPageActions.emptyCart();
        DriverContext.waitUntilElementIsVisible(viewCartPageActions.txtCartEmptyMessage);
        Assertions.assertEquals("No tienes ningún artículo en tu cesta.", viewCartPageActions.txtCartEmptyMessage.GetTextValue());
    }

}
