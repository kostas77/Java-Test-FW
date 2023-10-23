package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.Base;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ProductPageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.SearchPageActions;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ViewCartPageActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
public class RegionalEcomFeatureSteps extends Base {

    ProductPageActions productPageActions = GetInstance(ProductPageActions.class);
    SearchPageActions searchPageActions = GetInstance(SearchPageActions.class);
    ViewCartPageActions viewCartPageActions = GetInstance(ViewCartPageActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @And("I add a review to a product")
    public void i_add_a_review_to_a_product(DataTable table) {
        List<Map<String, String>> reviewDetails = table.asMaps(String.class, String.class);
        productPageActions.closeFreeShippingPopup();
        productPageActions.addReview(reviewDetails);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(productPageActions.txtReviewSuccessMessage);
        Assertions.assertEquals("You submitted your review for moderation.", productPageActions.txtReviewSuccessMessage.GetTextValue());
    }

    @And("I add a review to a product in DE-HS")
    public void i_add_a_review_to_a_product_in_DE_HS(DataTable table) {
        List<Map<String, String>> reviewDetails = table.asMaps(String.class, String.class);
        productPageActions.closeFreeShippingPopup();
        productPageActions.addReview(reviewDetails);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(productPageActions.txtReviewSuccessMessage);
        Assertions.assertEquals("Ihre Bewertung wurde zur Moderation übermittelt.", productPageActions.txtReviewSuccessMessage.GetTextValue());
    }

    @And("I add a review to a product in ANZ-HS")
    public void i_add_a_review_to_a_product_in_ANZ_HS(DataTable table) {
        List<Map<String, String>> reviewDetails = table.asMaps(String.class, String.class);
        productPageActions.closeFreeShippingPopup();
        productPageActions.addReview(reviewDetails);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(productPageActions.txtReviewSuccessMessage);
        Assertions.assertEquals("You submitted your review for moderation.", productPageActions.txtReviewSuccessMessage.GetTextValue());
    }

    @And("I add a review to a product in FR-HS")
    public void i_add_a_review_to_a_product_in_FR_HS(DataTable table) {
        List<Map<String, String>> reviewDetails = table.asMaps(String.class, String.class);
        productPageActions.closeFreeShippingPopup();
        productPageActions.addReview(reviewDetails);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(productPageActions.txtReviewSuccessMessage);
        Assertions.assertEquals("Votre évaluation est enrégistrée avec succès. Merci.", productPageActions.txtReviewSuccessMessage.GetTextValue());
    }

    @And("I add a book to cart in DE-HS")
    public void i_add_a_book_to_cart_in_DE_HS() {
        Assertions.assertEquals("BÜCHER", searchPageActions.txtProductFormat.GetTextValue());
        searchPageActions.addToCart();
        DriverContext.repeatWaitForElementVisibilityAttempt(searchPageActions.txtCartQty, 2);
        Assertions.assertEquals("1", searchPageActions.txtCartQty.GetTextValue());
    }

    @And("I add a book to cart in FR-HS")
    public void i_add_a_book_to_cart_in_FR_HS() {
        Assertions.assertEquals("LIVRES", searchPageActions.txtProductFormat.GetTextValue());
        searchPageActions.addToCart();
        DriverContext.repeatWaitForElementVisibilityAttempt(searchPageActions.txtCartQty, 2);
       if (frameworkConfigurationService.getTestEnv().equalsIgnoreCase("prod")) {
            Assertions.assertEquals("1", searchPageActions.txtCartQty.GetTextValue());
        }
        else{
            Assertions.assertEquals("1", searchPageActions.txtCartQty.GetTextValue());
        }
    }

    @And("I remove the product from cart in DE-HS")
    public void i_remove_the_product_from_cart_in_DE_HS() {
        viewCartPageActions.clearCart();
        DriverContext.waitUntilElementIsVisible(viewCartPageActions.txtCartEmptyMessage);
        Assertions.assertEquals("Sie haben keine Artikel in Ihrem Warenkorb.", viewCartPageActions.txtCartEmptyMessage.GetTextValue());
    }

    @And("I add a book to cart in SP-HS")
    public void i_add_a_book_to_cart() {
        Assertions.assertEquals("LIBROS", searchPageActions.txtProductFormat.GetTextValue());
        searchPageActions.addToCart();
        DriverContext.repeatWaitForElementVisibilityAttempt(searchPageActions.txtCartQty, 2);
//        Assertions.assertThat( searchPageActions.txtCartQty.GetTextValue(), Matchers.anyOf(Matchers.is("1"), Matchers.is("2"))); TODO: Restore Assertion in JUnit5
    }

}
