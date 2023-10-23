package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.framework.utilities.CucumberUtil;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.HSHomePageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.MyAccountActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ProductPageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ViewCartPageActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
public class myAccountLATAMSteps extends BasePage {

    MyAccountActions myAccountActions = GetInstance(MyAccountActions.class);
    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);
    ProductPageActions productPageActions = GetInstance(ProductPageActions.class);
    ViewCartPageActions viewCartPageActions = GetInstance(ViewCartPageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @Then("I edit the profile information in LATAM-HS")
    public void i_edit_the_profile_information(DataTable table) {
        CucumberUtil.ConvertDataTableToDict(table);
        myAccountActions.editProfileInformation(CucumberUtil.GetCellValueWithRowIndex("Title", 1),
                CucumberUtil.GetCellValueWithRowIndex("Profession", 1),
                CucumberUtil.GetCellValueWithRowIndex("Speciality", 1));
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("La información del perfil se ha guardado correctamente.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the default billing address in LATAM-HS")
    public void i_edit_the_default_billing_address(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.changeBillingAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I add new address in LATAM-HS")
    public void i_add_new_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.addNewAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the newly added address in LATAM-HS")
    public void i_edit_the_newly_added_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.editNewAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @Then("I add the product to wishlist from product page in LATAM-HS")
    public void i_add_the_product_to_wishlist_from_product_page() {
        productPageActions.addToWishlist();
        DriverContext.waitForPageToLoad();
        log.debug("My Wishlist URL: " + DriverContext.getCurrentUrl());
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("se ha agregado a su lista de deseos.") || myAccountActions.txtSuccessMessage.GetTextValue().contains("has been added to your Wish List. Haga clic aquí para continuar comprando."));
    }

    @And("I add the product to cart from wishlist page in LATAM-HS")
    public void i_add_the_product_to_cart_from_wishlist() {
        myAccountActions.addToCartFromWishlist();
        DriverContext.repeatWaitForElementVisibilityAttempt(myAccountActions.txtSuccessMessage, 2);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("You added") || myAccountActions.txtSuccessMessage.GetTextValue().contains("ha añadido a") || myAccountActions.txtSuccessMessage.GetTextValue().contains("La cantidad solicitada excede la cantidad máxima permitida en el carrito de compras 1"));
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        hsHomePageActions.navigateToViewCartPage();
    }

    @And("I remove the product from wishlist in LATAM-HS")
    public void i_remove_the_product_from_wishlist() {
        myAccountActions.removeFromWishlist();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertTrue(myAccountActions.txtSuccessMessage.GetTextValue().contains("se ha eliminado de tu Lista de Deseos.") || myAccountActions.txtSuccessMessage.GetTextValue().contains(" has been removed from your Wish List."));
    }

    @And("I remove the product from cart in LATAM-HS")
    public void i_remove_the_product_from_cart() {
        viewCartPageActions.emptyCart();
        DriverContext.waitUntilElementIsVisible(viewCartPageActions.txtCartEmptyMessage);
        Assertions.assertEquals("No tienen artículos en su cesta de la compras.", viewCartPageActions.txtCartEmptyMessage.GetTextValue());
    }

    @And("I add a review to a product in LATAM-HS")
    public void i_add_a_review_to_a_product(DataTable table) {
        List<Map<String, String>> reviewDetails = table.asMaps(String.class, String.class);
        productPageActions.closeFreeShippingPopup();
        productPageActions.addReview(reviewDetails);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(productPageActions.txtReviewSuccessMessage);
        Assertions.assertEquals("Su reseña ha sido enviada para ser moderada", productPageActions.txtReviewSuccessMessage.GetTextValue());
    }

}
