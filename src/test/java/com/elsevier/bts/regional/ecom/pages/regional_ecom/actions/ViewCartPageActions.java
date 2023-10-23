package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.ViewCartPageLocators;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewCartPageActions extends ViewCartPageLocators {

    public static int cartItemCount;

    public void proceedToCheckout() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToCheckout, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(txtCartItemCount,2);
        cartItemCount = Integer.parseInt(txtCartItemCount.GetTextValue());
        DriverContext.jsClickOnElement(btnProceedToCheckout);
        DriverContext.waitForPageToLoad();
    }

    public void closeNewsletterPopup() {
        System.out.println("Does Newsletter Popup Element Exist? " + DriverContext.doesElementExist(btnCloseNewsletterPopup));
        if (DriverContext.doesElementExist(btnCloseNewsletterPopup)) {
            btnCloseNewsletterPopup.WaitForClickable().Click();
        }
    }

    public void changeItemQuantity(String newQty) { //TODO : Do we need parameter
        if (DriverContext.getCurrentUrl().contains("/cart/")) {
//            Integer currentQty = Integer.parseInt(txtFieldQuantity.WaitForClickable().getAttribute("value"));
            DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldQuantity,3);
            txtFieldQuantity.clear();
            txtFieldQuantity.sendKeys(newQty);
            txtFieldQuantity.sendKeys(Keys.ENTER);
            DriverContext.waitForPageToLoad();
            DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToCheckout, 2);
            btnProceedToCheckout.WaitForClickable().Click();
            DriverContext.waitForPageToLoad();
        }
    }

//    public void emptyCart() { //TODO : Need to uncomment once RSR-5072 is fixed
//        Assertions.assertTrue(DriverContext.getCurrentUrl().contains("/cart/"));
//        if (DriverContext.doesElementExist(btnEmptyCart)) {
//            DriverContext.jsScrollToElementAlignTop(btnEmptyCart);
//            btnEmptyCart.WaitForClickable().Click();
//            DriverContext.waitUntilElementIsVisible(btnOK);
//            DriverContext.jsClickOnElement(btnOK.WaitForClickable());
//            DriverContext.waitForPageToLoad();
//        } else {
//            System.out.println("Cart was already empty.");
//        }
//    }

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

    public void clearCart() {
        DriverContext.waitUntilElementIsVisible(btnRemoveProductFromCart);
        DriverContext.jsScrollToElementAlignBottom(btnRemoveProductFromCart);
        btnRemoveProductFromCart.WaitForClickable().click();
    }

    public void applyOfferCode(String offerCode){
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldOfferCode,2);
        txtFieldOfferCode.sendKeys(offerCode);
        btnApplyOfferCode.Click();
    }

    public static void resetCartItemCount() {
        cartItemCount = 0;
    }

}
