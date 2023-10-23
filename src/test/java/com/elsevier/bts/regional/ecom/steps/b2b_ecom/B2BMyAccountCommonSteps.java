package com.elsevier.bts.regional.ecom.steps.b2b_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions.B2BHomePageActions;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions.B2BMyAccountPageActions;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions.B2BProductPageActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;


public class B2BMyAccountCommonSteps extends BasePage {
    B2BHomePageActions b2bHomePageActions = GetInstance(B2BHomePageActions.class);
    B2BMyAccountPageActions b2bMyAccountPageActions = GetInstance(B2BMyAccountPageActions.class);
    B2BProductPageActions b2bProductPageActions = GetInstance(B2BProductPageActions.class);

    @And("I navigate to my account page in B2B health store")
    public void i_navigate_to_my_account_page() {
        b2bHomePageActions.navigateToMyAccountPage();
        DriverContext.waitForPageToLoad();
    }

    @Then("I navigate to Address book page in B2B health store")
    public void i_navigate_to_address_book_page() {
        b2bMyAccountPageActions.btnAddressBook.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    @And("I edit the default billing address in B2B health store")
    public void i_edit_the_default_billing_address(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        b2bMyAccountPageActions.changeBillingAddress(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(b2bMyAccountPageActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", b2bMyAccountPageActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the default billing address in B2B EU store")
    public void i_edit_the_default_billing_address_EU(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        b2bMyAccountPageActions.changeBillingAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(b2bMyAccountPageActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", b2bMyAccountPageActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the default billing address in B2B UK store")
    public void i_edit_the_default_billing_address_b2b_uk(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        b2bMyAccountPageActions.changeBillingAddressUK(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(b2bMyAccountPageActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", b2bMyAccountPageActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the default billing address in B2B JP store")
    public void i_edit_the_default_billing_address_b2b_jp(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        b2bMyAccountPageActions.changeBillingAddressUK(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(b2bMyAccountPageActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", b2bMyAccountPageActions.txtSuccessMessage.GetTextValue());
    }

    @And("I add the product to cart from B2B wishlist page")
    public void i_add_the_product_to_cart_from_b2b_wishlist() {
        b2bMyAccountPageActions.addToCartFromWishlist();
        DriverContext.waitUntilElementIsVisible(b2bMyAccountPageActions.txtSuccessMessage);
        Assertions.assertTrue(b2bMyAccountPageActions.txtSuccessMessage.GetTextValue().contains("to your shopping cart."));
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        b2bProductPageActions.navigateToViewCartPage();
    }

}
