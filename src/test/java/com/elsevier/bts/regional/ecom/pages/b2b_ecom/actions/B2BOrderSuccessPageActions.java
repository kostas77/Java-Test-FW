package com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators.B2BOrderSuccessPageLocators;

public class B2BOrderSuccessPageActions extends B2BOrderSuccessPageLocators {
    public static String orderNumber;

    public void verifyAndGetOrderNumber() {
        if (DriverContext.doesElementExist(txtOrderNumber)) {
            orderNumber = txtOrderNumber.GetTextValue();
            System.out.println("Order was placed successfully - Order Number is: " + txtOrderNumber.GetTextValue());
        } else {
            System.out.println("Successful order URL is wrong...");
        }
    }
}
