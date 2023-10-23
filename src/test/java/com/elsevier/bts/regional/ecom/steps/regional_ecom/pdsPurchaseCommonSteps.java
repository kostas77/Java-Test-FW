package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ClinicalKeyProductPageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.HSHomePageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ProductPageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.SearchPageActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

@Slf4j
public class pdsPurchaseCommonSteps extends BasePage {

    HSHomePageActions hsHomePageActions = GetInstance(HSHomePageActions.class);
    ClinicalKeyProductPageActions clinicalKeyProductPageActions = GetInstance(ClinicalKeyProductPageActions.class);
    ProductPageActions productPageActions = GetInstance(ProductPageActions.class);
    SearchPageActions searchPageActions = GetInstance(SearchPageActions.class);

    @Then("I select ClinicalKey now from product type in navigation bar")
    public void i_select_clinicalkey_now_from_product_type_in_navigation_bar() {
        hsHomePageActions.selectClinicalKeyNowFromNavigationBar();
        DriverContext.waitForPageToLoad();
    }

    @Then("I add ClinicalKey now private license for {string} to cart")
    public void i_add_clinicalkey_now_private_license(String subscriptionDuration) {
        clinicalKeyProductPageActions.addPrivateLicenseToCart(subscriptionDuration);
        DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Sie haben"));
        productPageActions.navigateToViewCartPage();
    }

    @And("I add ClinicalKey subscription to cart")
    public void i_add_clinicalkey_subscription_to_Cart(DataTable table) {
        List<Map<String, String>> subscriptionData = table.asMaps(String.class, String.class);
        clinicalKeyProductPageActions.selectCKFormat(subscriptionData.get(0).get("Format"));
        DriverContext.driverSleep(20000); //TODO: Remove in the future if performance issue is resolved
        clinicalKeyProductPageActions.addCKSubscriptionToCart(subscriptionData.get(0).get("Format"), subscriptionData.get(0).get("Subscription duration"));
        DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("Vous avez ajouté"));
        productPageActions.navigateToViewCartPage();
        DriverContext.driverSleep(15000); // TODO: Replace in the future with a suitable explicit wait
        log.debug("View Cart URL: " + DriverContext.getCurrentUrl()); //https://us.uat-regionalecom.tio.systems/checkout/cart/
    }

    @Then("I select a product based on the ClinicalKey Now Specialties {string}")
    public void i_select_a_product_based_on_clinicalkey_now_specialties(String specialty) {
        if (DriverContext.getCurrentUrl().contains("uat")) {
            clinicalKeyProductPageActions.selectSpecialty(specialty);
            searchPageActions.clickOnProductLink();
            DriverContext.waitForPageToLoad();
        } else {
            searchPageActions.clickOnProductLink();
            DriverContext.waitForPageToLoad();
        }
    }

    @And("I select {string} from product type in navigation bar")
    public void i_select_from_product_type_in_navigation_bar(String subscription) {
        hsHomePageActions.selectClinicalKeyFromNavigationBar(subscription);
        DriverContext.waitForPageToLoad();
    }

    @Then("I select a product based on the ClinicalKey Specialties {string}")
    public void i_select_a_product_based_on_clinicalkey_specialties(String specialty) {
        clinicalKeyProductPageActions.selectClinicalSpecialty(specialty);
        DriverContext.waitForPageToLoad();
    }

    @Then("I select a Q-Bank {string}")
    public void i_select_a_q_bank(String qBank) {
        clinicalKeyProductPageActions.selectQBank(qBank);
        DriverContext.waitForPageToLoad();
    }


    @Then("I add subscription to cart {string}")
    public void i_add_subscription_to_cart(String subscriptionDuration) {
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        clinicalKeyProductPageActions.addClinicalKeyPlanToCart(subscriptionDuration);
        DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added")||productPageActions.txtSuccessMessage.GetTextValue().contains("Both subscriptions were successfully added to cart."));
        productPageActions.navigateToViewCartPage();
    }

    @And("I add CK Student subscription to cart {string}")
    public void i_add_ck_student_subscription_to_cart(String subscriptionDuration) {
        clinicalKeyProductPageActions.addClinicalKeyStudentPlanToCart(subscriptionDuration);
        DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added"));
        productPageActions.navigateToViewCartPage();
    }

    @Then("I add ClinicalKey now license for {string} to cart")
    public void i_add_clinicalkey_now_license(String subscriptionDuration) {
        DriverContext.waitForPageToLoad();
        clinicalKeyProductPageActions.addClinicalKeyNowLicenseToCartIN(subscriptionDuration);
        DriverContext.waitUntilElementIsVisible(productPageActions.txtSuccessMessage);
        Assertions.assertTrue(productPageActions.txtSuccessMessage.GetTextValue().contains("You added"));
        productPageActions.navigateToViewCartPage();
    }

    @Then("I select a product based on the ClinicalKey Now {string} and select the Product {string}")
    public void i_select_a_product_based_on_clinicalkey_now_specialties(String specialty, String productName) {
        if (DriverContext.getCurrentUrl().contains("uat")) {
            clinicalKeyProductPageActions.selectSpecialty(specialty);
        }
        searchPageActions.clickOnProduct(productName);
        DriverContext.waitForPageToLoad();
    }

    @Then("I select ClinicalKey Residents")
    public void i_select_clinicalkey_residents() {
        clinicalKeyProductPageActions.selectClinicalKeyResidents();
        DriverContext.waitForPageToLoad();
    }
}
