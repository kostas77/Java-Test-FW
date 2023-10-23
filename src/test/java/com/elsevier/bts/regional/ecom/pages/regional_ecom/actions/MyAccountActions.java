package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.MyAccountLocators;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class MyAccountActions extends MyAccountLocators {


    public void editProfileInformation(String title,String profession,String speciality) {
        btnProfileEdit.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement(drpdwnTitle),2);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, title);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnProfession, profession);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSpeciality, speciality);
        btnSaveProfileInformation.WaitForClickable().Click();
    }

    public void addProfileInformation(List<Map<String, String>>accountDetails) {
        DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement(drpdwnTitle), 2);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle,accountDetails.get(0).get("Title"));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnProfession,accountDetails.get(0).get("Profession"));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSpeciality,accountDetails.get(0).get("Speciality"));
        if(!DriverContext.getCurrentUrl().contains("in") || DriverContext.getCurrentUrl().contains("staging")) {
            chkboxAcceptTNC.WaitForClickable().Click();
        }
        btnSaveProfileInformation.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }


    //AddressBook Actions
    public void changeBillingAddress(List<Map<String, String>> address) {
        btnChangeBillingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
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

    public void changeShippingAddress(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnChangeShippingAddress,3);
        btnChangeShippingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
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

    public void addNewAddress(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnAddNewAddress,3);
        btnAddNewAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
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

    public void editNewAddress(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnEditNewAddress,3);
        btnEditNewAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
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

    public void deleteNewAddress(){
        DriverContext.repeatWaitForElementVisibilityAttempt(btnDeleteNewAddress,3);
        btnDeleteNewAddress.click();
        btnAcceptDeleteNewAddress.WaitForClickable().click();
    }

    //Wishlist Actions
    public void addToCartFromWishlist(){
        DriverContext.hoverOverElement(lnkProduct);
        btnAddToCart.WaitForClickable().Click();
    }
    public void removeFromWishlist(){
        String productInfo ="div.products-grid.wishlist li";
        List<WebElement> listProduct=DriverContext.getElementsList(productInfo);
        for (int i = 0; i < listProduct.size(); i++) {
            DriverContext.hoverOverElement(lnkProduct);
            btnDeleteFromWishlist.WaitForClickable().Click();
            DriverContext.waitForPageToLoad();
            DriverContext.driverSleep(3000);
        }
    }

    //Address book page UK
    public void changeBillingAddressUK(List<Map<String, String>> address) {
        btnChangeBillingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
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

    public void changeShippingAddressUK(List<Map<String, String>> address) {
        btnChangeShippingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
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

    public void addNewAddressUK(List<Map<String, String>> address) {
        btnAddNewAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
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

    public void editNewAddressUK(List<Map<String, String>> address) {
        btnAddNewAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
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

    //EU healthStore
    public void changeBillingAddressEU(List<Map<String, String>> address) {
        btnChangeBillingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnCountry));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnSaveAddress);
        btnSaveAddress.Click();
    }

    public void changeShippingAddressEU(List<Map<String, String>> address) {
        btnChangeShippingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnCountry));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnSaveAddress);
        btnSaveAddress.Click();
    }

    public void addNewAddressEU(List<Map<String, String>> address) {
        btnAddNewAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnCountry));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnSaveAddress);
        btnSaveAddress.Click();
    }

    public void editNewAddressEU(List<Map<String, String>> address) {
        btnAddNewAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnCountry));
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"),address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnSaveAddress);
        btnSaveAddress.Click();
    }

}
