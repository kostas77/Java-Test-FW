package com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators.B2BMyAccountPageLocators;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.Map;

public class B2BMyAccountPageActions extends B2BMyAccountPageLocators {

    public void addToCartFromWishlist(){
        DriverContext.hoverOverElement(lnkProduct);
        btnAddToCart.WaitForClickable().Click();
    }
    public void changeBillingAddress(List<Map<String, String>> address) {
        btnChangeBillingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        txtFieldFirstName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnState));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnState, address.get(0).get("State"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnSaveAddress);
        btnSaveAddress.Click();
    }

    public void changeBillingAddressEU(List<Map<String, String>> address) {
        btnChangeBillingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        txtFieldFirstName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnCountry));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnState));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnState, address.get(0).get("State"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnSaveAddress);
        btnSaveAddress.Click();
    }

    public void changeBillingAddressUK(List<Map<String, String>> address) {
        btnChangeBillingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        txtFieldFirstName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnSaveAddress);
        btnSaveAddress.Click();
    }

}
