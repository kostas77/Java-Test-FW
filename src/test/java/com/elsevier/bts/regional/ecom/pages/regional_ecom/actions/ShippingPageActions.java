package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.ShippingPageLocators;

import java.util.List;
import java.util.Map;

public class ShippingPageActions extends ShippingPageLocators {

    public void addShippingDetails(String Street, String city, String state, String zipcode, String PhoneNumber) {
        if(!DriverContext.doesElementExist(btnAddNewAddress)) {
            DriverContext.waitUntilElementIsVisible(txtFieldStreetAddress);
            txtFieldStreetAddress.sendKeys(Street);
            DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
            txtFieldCity.sendKeys(city);
            DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnState));
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnState, state);
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
            txtFieldZipCode.sendKeys(zipcode);
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
            txtFieldPhoneNumber.sendKeys(PhoneNumber);
        }
    }

    public void addShippingDetailsForUK(String Street, String city, String zipcode, String PhoneNumber) {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnAddNewAddress, 2);
        if (!DriverContext.doesElementExist(btnAddNewAddress)) {
            DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldStreetAddress, 3);
            txtFieldStreetAddress.sendKeys(Street);
            DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
            txtFieldCity.sendKeys(city);
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
            txtFieldZipCode.sendKeys(zipcode);
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
            txtFieldPhoneNumber.sendKeys(PhoneNumber);
        }
    }

    public void addShippingDetailsAsGuestUserUK(String Email, List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(footer, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldEmailAddress, 3);
        txtFieldEmailAddress.sendKeys(Email);
        DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement(drpdwnTitle),2);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
    }

    public void addShippingDetailsAsGuestUser(String Email, List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldEmailAddress, 5);
        txtFieldEmailAddress.sendKeys(Email);
        DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement(drpdwnTitle),2);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnState));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnState, address.get(0).get("State"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
    }

    public void addShippingDetailsForEU(String street, String city, String zipcode, String country, String PhoneNumber) {
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(15000 );  // TODO: Replace in the future with a suitable explicit wait
        if (!DriverContext.doesElementExist(btnAddNewAddress)) {
            DriverContext.waitUntilElementIsVisible(txtFieldStreetAddress);
            txtFieldStreetAddress.sendKeys(street);
            DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
            txtFieldCity.sendKeys(city);
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
            txtFieldZipCode.sendKeys(zipcode);
            DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnCountry));
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, country);
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
            txtFieldPhoneNumber.sendKeys(PhoneNumber);
        }
    }

    public void addShippingDetailsAsGuestUserEU(String Email, List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldEmailAddress, 5);
        txtFieldEmailAddress.sendKeys(Email);
        DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement(drpdwnTitle),2);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnCountry));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
    }

    public void closeNotReadyJustYetPopupIfPresent() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnCloseNotReadyJustYetPopup, 3);
        try {
            if (DriverContext.doesElementExist(btnCloseNotReadyJustYetPopup)) {
                btnCloseNotReadyJustYetPopup.WaitForClickable().Click();
            }
        } catch (Exception e) {
            System.out.println("- Caught exception while trying to close 'Not Ready Just Yet?' popup");
        }
    }

    public void proceedToReviewAndPayments() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnShippingContinue, 3);
        DriverContext.jsScrollToElementAlignBottom(btnShippingContinue);
        DriverContext.repeatElementClickAttempt(btnShippingContinue, 2);
        DriverContext.waitForPageToLoad();
    }

    public void proceedToReviewAndPaymentsMobile() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnShippingContinue, 3);
        DriverContext.jsScrollToElementAlignBottom(btnShippingContinue);
        DriverContext.repeatElementClickAttempt(btnShippingContinue, 2);
        DriverContext.waitForPageToLoad();
    }

    public void addShippingDetailsForMEA(String street, String city, String zipcode, String country, String PhoneNumber) {
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(15000 );  // TODO: Replace in the future with a suitable explicit wait
        if (!DriverContext.doesElementExist(btnAddNewAddress)) {
            DriverContext.waitUntilElementIsVisible(txtFieldStreetAddress);
            txtFieldStreetAddress.sendKeys(street);
            DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
            txtFieldCity.sendKeys(city);
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
            txtFieldZipCode.sendKeys(zipcode);
            DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnCountry));
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, country);
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
            txtFieldPhoneNumber.sendKeys(PhoneNumber);
        }
    }

    public void addShippingDetailsAsGuestUserMEA(String Email, List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldEmailAddress, 5);
        txtFieldEmailAddress.sendKeys(Email);
        DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement(drpdwnTitle),2);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnCountry));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
    }

}
