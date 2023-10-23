package com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators.B2BViewCartPageLocators;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.List;

public class B2BViewCartPageActions extends B2BViewCartPageLocators {
    public static int cartItemCount;

    public void proceedToCheckout() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToCheckout, 2);
//        DriverContext.repeatWaitForElementVisibilityAttempt(txtCartItemCount,2);
//        cartItemCount = Integer.parseInt(txtCartItemCount.getText());
        DriverContext.jsClickOnElement(btnProceedToCheckout);
        DriverContext.waitForPageToLoad();
    }

    public void emptyCart() {
        String cartLocator =".action.action-delete";
        Assertions.assertTrue(DriverContext.getCurrentUrl().contains("/cart/"));
        List<WebElement> removeProducts = DriverContext.getElementsList(cartLocator);
        for (int i = 0; i < removeProducts.size(); i++) {
            WebElement removeProduct = DriverContext.getElement(cartLocator);
            if (DriverContext.doesElementExist(removeProduct)) {
                DriverContext.jsScrollToElementAlignTop(removeProduct);
                removeProduct.click();
                DriverContext.waitForPageToLoad();
            }
            System.out.println("Cart is empty");
        }
    }
}
