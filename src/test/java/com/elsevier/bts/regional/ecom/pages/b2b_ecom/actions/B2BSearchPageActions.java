package com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators.B2BSearchPageLocators;

public class B2BSearchPageActions extends B2BSearchPageLocators {

    public void clickOnProductLink() {
        DriverContext.repeatWaitForElementVisibilityAttempt(lnkProduct,2);
        lnkProduct.ClickLink();
    }

}
