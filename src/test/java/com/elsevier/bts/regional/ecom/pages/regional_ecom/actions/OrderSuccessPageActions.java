package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.OrderSuccessPageLocators;

public class OrderSuccessPageActions extends OrderSuccessPageLocators {

    public static String orderNumber;

    public void verifyAndGetOrderNumber() {
        if (DriverContext.doesElementExist(txtOrderNumber)) {
            orderNumber = txtOrderNumber.GetTextValue();
            System.out.println("Order was placed successfully - Order Number is: " + txtOrderNumber.GetTextValue());
        } else if (DriverContext.doesElementExist(txtOrderNumberLatam)) {
            orderNumber = txtOrderNumberLatam.GetTextValue();
            System.out.println("Order was placed successfully - Order Number is: " + txtOrderNumberLatam.GetTextValue());
        } else {
            System.out.println("Successful order URL is wrong...");
        }
    }

}
