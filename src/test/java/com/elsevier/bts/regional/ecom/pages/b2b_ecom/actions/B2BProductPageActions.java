package com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators.B2BProductPageLocators;

public class B2BProductPageActions extends B2BProductPageLocators {

    public void addToCart() {
        DriverContext.jsScrollToElementAlignBottom(btnAddToCart);
        DriverContext.repeatElementClickAttempt(btnAddToCart, 3);
        DriverContext.waitForPageToLoad();
    }

    public void navigateToViewCartPage() {
        DriverContext.jsScrollToElementAlignTop(btnCartIcon);
        btnCartIcon.WaitForClickable().Click();
        DriverContext.repeatWaitForElementVisibilityAttempt(btnViewCart,2);
        btnViewCart.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

}
