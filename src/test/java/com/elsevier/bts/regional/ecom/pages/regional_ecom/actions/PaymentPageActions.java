package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.MobileLocators;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.PaymentPageLocators;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ClinicalKeyProductPageActions.clinicalKey;
import static com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ClinicalKeyProductPageActions.clinicalKeyNow;
import static com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ClinicalKeyProductPageActions.clinicalKeyNowFreeTrial;
import static com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.JournalProductPageActions.journal;
import static com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ProductPageActions.eBook;

public class PaymentPageActions extends PaymentPageLocators {
    MobileActions mobileActions = GetInstance(MobileActions.class);
    private FrameworkConfigurationService frameworkConfigurationService;
    public static String orderSubTotal;
    public static String orderTotal;
    public static String orderTax;

    public static float taxPercentageUAT;

    public static float taxPercentagePROD;
    public static String rewardPoints;

    public void verifyShippingAndBillingAddressCheckbox() {
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        if (!chkboxShippingAndBillingAddress.isSelected()) {
            DriverContext.repeatElementClickAttempt(chkboxShippingAndBillingAddress, 3);
            DriverContext.waitForPageToLoad();
            System.out.println("Shipping and billing address checkbox checked");
        } else {
            System.out.println("Shipping and billing address checkbox is already checked");
        }
    }

    public void addDifferentBillingAddress(List<Map<String, String>> address) {
        DriverContext.driverSleep(15000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPay, 3);
        DriverContext.repeatElementClickAttempt(chkboxShippingAndBillingAddress, 2);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(btnUpdateBillingAddress, 3);
        btnUpdateBillingAddress.WaitForClickable();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnState, address.get(0).get("State"));
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddress);
        btnUpdateBillingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressForExistingUser(List<Map<String, String>> address) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.repeatElementClickAttempt(chkboxShippingAndBillingAddress, 2);
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        if (txtAddressList.getText().equals("New Address")) {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnBillingAddress, "New Address");
            btnUpdateBillingAddress.WaitForClickable();
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
            txtFieldFirstName.clear();
            txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
            txtFieldLastName.clear();
            txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
            txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
            txtFieldStreetAddress.sendKeys(Keys.TAB);
            DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
            txtFieldCity.sendKeys(address.get(0).get("City"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnState, address.get(0).get("State"));
            txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
            txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
            chkboxSaveToAddressBook.WaitForClickable().Click();
        } else {
            DriverContext.selectDropdownListElementUsingIndex(drpdwnBillingAddress, 1);
        }
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddress);
        btnUpdateBillingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressForExistingUserUK(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 3);
        DriverContext.jsScrollToElementAlignBottom(chkboxShippingAndBillingAddress);
        DriverContext.waitUntilElementIsClickable(chkboxShippingAndBillingAddress);
        DriverContext.jsClickOnElement(chkboxShippingAndBillingAddress);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(txtAddressListUK, 3);
        if (txtAddressListUK.getText().equals("New Address")) {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnBillingAddressUK, "New Address");
            btnUpdateBillingAddressUK.WaitForClickable();
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
            txtFieldFirstName.clear();
            txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
            txtFieldLastName.clear();
            txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
            txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
            txtFieldCity.sendKeys(address.get(0).get("City"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
            txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
            txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
            chkboxSaveToAddressBook.WaitForClickable().Click();
        } else {
            DriverContext.selectDropdownListElementUsingIndex(drpdwnBillingAddressUK, 1);
        }
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressUK);
        btnUpdateBillingAddressUK.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressForExistingUserDE(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.repeatElementClickAttempt(chkboxShippingAndBillingAddress, 2);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(txtAddressListDE, 3);
        if (!DriverContext.doesElementExist(txtAddressListDE)) {
            txtFieldStreetAddressDE.sendKeys(address.get(0).get("Street Address"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldCityDE);
            txtFieldCityDE.sendKeys(address.get(0).get("City"));
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountryFR, address.get(0).get("Country"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeDE);
            txtFieldZipCodeDE.sendKeys(address.get(0).get("ZipCode"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberDE);
            txtFieldPhoneNumberDE.sendKeys(address.get(0).get("Phone Number"));
            chkboxSaveToAddressBook.WaitForClickable().Click();
            DriverContext.waitForPageToLoad();
            DriverContext.jsScrollToElementAlignBottom(chkboxSaveToAddressBook);
            chkboxSaveToAddressBook.WaitForClickable().Click();
        }
        if (!clinicalKeyNow) {
            if (txtAddressListDE.getText().equals("Neue Adresse")) {
                DriverContext.selectDropdownListElementUsingVisibleText(drpdwnBillingAddressDE, "Neue Adresse");
                btnUpdateBillingAddressDE.WaitForClickable();
                DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitleDE, address.get(0).get("Title"));
                txtFieldFirstNameDE.clear();
                txtFieldFirstNameDE.sendKeys(address.get(0).get("First Name"));
                txtFieldLastNameDE.clear();
                txtFieldLastNameDE.sendKeys(address.get(0).get("Last Name"));
                txtFieldStreetAddressDE.sendKeys(address.get(0).get("Street Address"));
                DriverContext.jsScrollToElementAlignBottom(txtFieldCityDE);
                txtFieldCityDE.sendKeys(address.get(0).get("City"));
                DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeDE);
                txtFieldZipCodeDE.sendKeys(address.get(0).get("ZipCode"));
                DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberDE);
                txtFieldPhoneNumberDE.sendKeys(address.get(0).get("Phone Number"));
                chkboxSaveToAddressBook.WaitForClickable().Click();
            } else {
                DriverContext.selectDropdownListElementUsingIndex(drpdwnBillingAddressDE, 1);
            }
            DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressDE);
            btnUpdateBillingAddressDE.WaitForClickable().Click();
            DriverContext.waitForPageToLoad();
        } else {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitleDE, address.get(0).get("Title"));
            txtFieldFirstNameDE.clear();
            txtFieldFirstNameDE.sendKeys(address.get(0).get("First Name"));
            txtFieldLastNameDE.clear();
            txtFieldLastNameDE.sendKeys(address.get(0).get("Last Name"));
            txtFieldStreetAddressDE.sendKeys(address.get(0).get("Street Address"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldCityDE);
            txtFieldCityDE.sendKeys(address.get(0).get("City"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeDE);
            txtFieldZipCodeDE.sendKeys(address.get(0).get("ZipCode"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberDE);
            txtFieldPhoneNumberDE.sendKeys(address.get(0).get("Phone Number"));
            chkboxSaveToAddressBook.WaitForClickable().Click();
        }
    }

    public void addShippingDetailsForExistingUserDE(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldStreetAddressDE, 3);
        txtFieldStreetAddressDE.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCityDE);
        txtFieldCityDE.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeDE);
        txtFieldZipCodeDE.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberDE);
        txtFieldPhoneNumberDE.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressDE);
        DriverContext.repeatElementClickAttempt(btnUpdateBillingAddressDE, 2);
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressDE(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.repeatElementClickAttempt(chkboxShippingAndBillingAddress, 2);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(btnUpdateBillingAddressDE, 2);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitleDE, address.get(0).get("Title"));
        txtFieldFirstNameDE.clear();
        txtFieldFirstNameDE.sendKeys(address.get(0).get("First Name"));
        txtFieldLastNameDE.clear();
        txtFieldLastNameDE.sendKeys(address.get(0).get("Last Name"));
        txtFieldStreetAddressDE.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCityDE);
        txtFieldCityDE.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeDE);
        txtFieldZipCodeDE.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberDE);
        txtFieldPhoneNumberDE.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressDE);
        btnUpdateBillingAddressDE.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressUK(List<Map<String, String>> address) {
        DriverContext.waitUntilElementIsClickable(btnProceedToPayWithCCGermany);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 3);
        DriverContext.waitUntilElementIsClickable(btnProceedToPayWithCreditCardUK);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.jsScrollToElementAlignBottom(chkboxShippingAndBillingAddress);
        DriverContext.waitUntilElementIsClickable(chkboxShippingAndBillingAddress);
        DriverContext.jsClickOnElement(chkboxShippingAndBillingAddress);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsClickable(btnUpdateBillingAddressUK);
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
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddress);
        btnUpdateBillingAddressUK.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressEU(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.jsClickOnElement(chkboxShippingAndBillingAddress.WaitForClickable());
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(btnUpdateBillingAddressEU, 3);
        btnUpdateBillingAddressEU.WaitForClickable();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressEU);
        btnUpdateBillingAddressEU.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressLatam(List<Map<String, String>> address) {
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(btnUpdateBillingAddressEU, 3);
        btnUpdateBillingAddressEU.WaitForClickable();
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressEU);
        btnUpdateBillingAddressEU.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressASIA(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.jsClickOnElement(chkboxShippingAndBillingAddress.WaitForClickable());
        DriverContext.waitForPageToLoad();
        //DriverContext.repeatWaitForElementVisibilityAttempt(btnUpdateBillingAddressEU, 3);
        //btnUpdateBillingAddressEU.WaitForClickable();
        if(DriverContext.doesElementExist(dropDownOptionBillingAddress)) {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnBillingAddress, "New Address");
            DriverContext.waitForPageToLoad();
        }
        DriverContext.driverSleep(10000);
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
        DriverContext.driverSleep(20000);
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressEU);
        btnUpdateBillingAddressEU.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressForExistingUserEU(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.jsClickOnElement(chkboxShippingAndBillingAddress.WaitForClickable());
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(10000);
        if (txtAddressListEU.getText().equals("New Address")) {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnBillingAddress, "New Address");
            btnUpdateBillingAddressUK.WaitForClickable();
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
            txtFieldFirstName.clear();
            txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
            txtFieldLastName.clear();
            txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
            txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
            txtFieldCity.sendKeys(address.get(0).get("City"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
            txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
            txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
            chkboxSaveToAddressBook.WaitForClickable().Click();
        } else {
            DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement((drpdwnBillingAddressUK)), 3);
            DriverContext.selectDropdownListElementUsingIndex(drpdwnBillingAddressUK, 1);
        }
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressUK);
        btnUpdateBillingAddressUK.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressForExistingUserSP(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPaySP, 3);
        DriverContext.jsClickOnElement(chkboxShippingAndBillingAddress.WaitForClickable());
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(btnUpdateBillingAddressSP, 3);
        btnUpdateBillingAddressSP.WaitForClickable();
        if (txtAddressListSP.getText().equals("Nueva dirección")) {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnBillingAddress, "Nueva dirección");
            btnUpdateBillingAddressSP.WaitForClickable();
            DriverContext.repeatElementClickAttempt(btnUpdateBillingAddressSP, 2);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
            txtFieldFirstName.clear();
            txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
            txtFieldLastName.clear();
            txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
            txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
            txtFieldCity.sendKeys(address.get(0).get("City"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
            txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
            txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
            chkboxSaveToAddressBook.WaitForClickable().Click();
        } else {
            DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement((drpdwnBillingAddressUK)), 3);
            DriverContext.selectDropdownListElementUsingIndex(drpdwnBillingAddressUK, 1);
        }
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressSP);
        btnUpdateBillingAddressSP.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void acceptTermsAndConditions() {
        //DriverContext.waitForHTMLLoad(30, 3);
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPay, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxAcceptTnC, 2);
        DriverContext.jsScrollToElementAlignBottom(chkboxAcceptTnC);
        DriverContext.jsClickOnElement(chkboxAcceptTnC);
        DriverContext.driverSleep(20000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 2);
        orderTotal = txtOrderTotalPrice.GetTextValue();
        if (DriverContext.getCurrentUrl().contains("us.") || DriverContext.getCurrentUrl().contains("eu.")) {
            orderSubTotal = txrOrderSubTotal.GetTextValue();
            if (!eBook && !journal) {
                if (!clinicalKey || !DriverContext.getCurrentUrl().contains("eu")){
                    if (orderSubTotal.replaceAll("\\p{Sc}", "").equals("0.00")) { //Added for PDS free trial
                        orderTax = String.valueOf(0);
                    } else {
                        orderTax = txtOrderTax.GetTextValue();
                    }
                if (DriverContext.getCurrentUrl().contains("uat") && (DriverContext.getCurrentUrl().contains("us.") || DriverContext.getCurrentUrl().contains("eu."))) {
                    taxPercentageUAT = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
                } else if (DriverContext.getCurrentUrl().contains("www") && (DriverContext.getCurrentUrl().contains("us.") || DriverContext.getCurrentUrl().contains("eu."))) {
                    taxPercentagePROD = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
                }
            }               
            } else if (DriverContext.getCurrentUrl().contains("eu.")) {
                orderTax = txtOrderTax.GetTextValue();
                if (DriverContext.getCurrentUrl().contains("uat") && (DriverContext.getCurrentUrl().contains("us.") || DriverContext.getCurrentUrl().contains("eu."))) {
                    taxPercentageUAT = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
                } else if (DriverContext.getCurrentUrl().contains("www") && (DriverContext.getCurrentUrl().contains("us.") || DriverContext.getCurrentUrl().contains("eu."))) {
                    taxPercentagePROD = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
                }
            }
        }

    }

    public void acceptTermsAndConditionsMobile() {
        //DriverContext.waitForHTMLLoad(30, 3);
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPay, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxAcceptTnC, 2);
        DriverContext.jsScrollToElementAlignBottom(chkboxAcceptTnC);
        DriverContext.jsClickOnElement(chkboxAcceptTnC);
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(mobileActions.mobileTxtOrderTotalPrice, 2);
        orderTotal = mobileActions.mobileTxtOrderTotalPrice.GetTextValue();
    }

    public void acceptTermsAndConditionsANZHS() {
        //DriverContext.waitForHTMLLoad(30, 3);
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPay, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxAcceptTnC, 2);
        DriverContext.jsScrollToElementAlignBottom(chkboxAcceptTnC);
        DriverContext.jsClickOnElement(chkboxAcceptTnC);
        DriverContext.driverSleep(20000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 2);
        orderTotal = txtOrderTotalPrice.GetTextValue();
        if (DriverContext.getCurrentUrl().contains(".au") || DriverContext.getCurrentUrl().contains("anz.")) {
            orderSubTotal = txrOrderSubTotal.GetTextValue();
            orderTax = txtOrderTaxANZ.GetTextValue();
            if (DriverContext.getCurrentUrl().contains("uat")) {
                taxPercentageUAT = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", ""))-(Float.parseFloat(orderTax.replaceAll("[^\\d.]", ""))))) * 100;
            } else if (DriverContext.getCurrentUrl().contains(".au")) {
                taxPercentagePROD = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", ""))-(Float.parseFloat(orderTax.replaceAll("[^\\d.]", ""))))) * 100;
            }
        }

    }

    public void acceptTermsAndConditionsMEAHS() {
        //DriverContext.waitForHTMLLoad(30, 3);
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPay, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxAcceptTnC, 2);
        DriverContext.jsScrollToElementAlignBottom(chkboxAcceptTnC);
        DriverContext.jsClickOnElement(chkboxAcceptTnC);
        DriverContext.driverSleep(20000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 2);
        orderTotal = txtOrderTotalPrice.GetTextValue();
//        if (eBook) {
//            orderSubTotal = txrOrderSubTotal.GetTextValue();
//            orderTax = txtOrderTax.GetTextValue();
//            if (DriverContext.getCurrentUrl().contains("uat")) {
//                taxPercentageUAT = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
//            } else {
//                taxPercentagePROD = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
//            }
//        }
    }

    public void acceptTermsAndConditionsInLATAMHS() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayWithCreditCardLT, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxAcceptTnC, 2);
        DriverContext.jsClickOnElement(chkboxAcceptTnC.WaitForClickable());
        orderTotal = txtOrderTotalPrice.GetTextValue();
    }

    public void acceptTermsAndConditionsInDEHS() {
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxAcceptTnCDEHS, 2);
        DriverContext.waitUntilElementIsClickable(chkboxAcceptTnCDEHS);
        DriverContext.waitUntilElementIsClickable(btnProceedToPayWithCCGermany);
        DriverContext.jsScrollToElementAlignBottom(btnProceedToPayWithCCGermany);
        DriverContext.jsClickOnElement(chkboxAcceptTnCDEHS.WaitForClickable());
        if (clinicalKeyNow) {
            DriverContext.jsScrollToElementAlignBottom(chkboxEndUserLicenseAgreement);
            DriverContext.jsClickOnElement(chkboxEndUserLicenseAgreement.WaitForClickable());
        }
        DriverContext.driverSleep(20000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 2);
        orderTotal = txtOrderTotalPrice.GetTextValue();
        orderSubTotal = txrOrderSubTotalDE.GetTextValue();
        if (eBook || clinicalKeyNow) {
            if(!ClinicalKeyProductPageActions.clinicalKeyNowFreeTrial) { // Condition added to ignore validation for free trial PDS DE
                //For DE PDS Free trial to paid conversion is not automatic, Paid subscription needs to be activated with separate order
                DriverContext.driverSleep(20000);
                orderTax = txtOrderTaxDE.GetTextValue();
                if (DriverContext.getCurrentUrl().contains("uat")) {
                    taxPercentageUAT = (Float.parseFloat(orderTax.replace("€", "").replace(",", ".")) / (Float.parseFloat(orderSubTotal.replace("€", "").replace(",", ".")))) * 100;
                } else {
                    taxPercentagePROD = (Float.parseFloat(orderTax.replace("€", "").replace(",", ".")) / (Float.parseFloat(orderSubTotal.replace("€", "").replace(",", ".")))) * 100;
                }
            }
        }
    }

    public void proceedToPay() {
        orderTotal = txtOrderTotalPrice.GetTextValue();
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPay, 3);
        DriverContext.jsScrollToElementAlignBottom(btnProceedToPay);
        DriverContext.repeatElementClickAttempt(btnProceedToPay, 2);
    }

    public void proceedToPayMobile() {
        orderTotal = txtOrderTotalPrice.GetTextValue();
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPay, 3);
        DriverContext.jsScrollToElementAlignBottom(btnProceedToPay);
        DriverContext.repeatElementClickAttempt(btnProceedToPay, 2);
    }

    public void proceedToPayWithCreditCardLATAM() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayWithCreditCardLT, 3);
        DriverContext.waitUntilElementIsClickable(btnProceedToPayWithCreditCardLT);
        DriverContext.jsScrollToElementAlignBottom(btnProceedToPayWithCreditCardLT);
        DriverContext.jsClickOnElement(btnProceedToPayWithCreditCardLT);
    }

    public void proceedToSelectPayment(){
        DriverContext.waitUntilElementIsClickable(btnPaymentMethod);
        DriverContext.jsClickOnElement(btnPaymentMethod);
    }

    public void proceedToPayWithCreditCardUK() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayWithCreditCardUK, 3);
        DriverContext.waitUntilElementIsClickable(btnProceedToPayWithCreditCardUK);
        DriverContext.jsScrollToElementAlignBottom(btnProceedToPayWithCreditCardUK);
        DriverContext.jsClickOnElement(btnProceedToPayWithCreditCardUK);
    }

    public void selectVisaPaymentDEHS() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnPayWithVisa, 2);
        DriverContext.waitUntilElementIsClickable(btnPayWithVisa);
        DriverContext.jsScrollToElementAlignBottom(btnPayWithVisa);
        DriverContext.jsClickOnElement(btnPayWithVisa);
    }

    public void selectPaymentMethodInDEHS() {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 2);
        if (!clinicalKeyNow) {
            DriverContext.repeatWaitForElementVisibilityAttempt(btnPayWithCreditCardGermany, 2);
            DriverContext.waitUntilElementIsClickable(btnPayWithCreditCardGermany);
            DriverContext.jsClickOnElement(btnPayWithCreditCardGermany.WaitForClickable());
        }
        DriverContext.repeatWaitForElementVisibilityAttempt(btnPayWithVisa, 2);
        DriverContext.waitUntilElementIsClickable(btnPayWithVisa);
        DriverContext.jsScrollToElementAlignBottom(btnPayWithVisa);
        DriverContext.jsClickOnElement(btnPayWithVisa);
    }

    public void selectPaypalMethodInDEHS() {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 2);
        if (!clinicalKeyNow) {
            DriverContext.repeatWaitForElementVisibilityAttempt(btnPayWithCreditCardGermany, 2);
            DriverContext.waitUntilElementIsClickable(btnPayWithCreditCardGermany);
            DriverContext.jsClickOnElement(btnPayWithCreditCardGermany.WaitForClickable());
        }
        DriverContext.repeatWaitForElementVisibilityAttempt(btnPayWithPaypal, 2);
        DriverContext.waitUntilElementIsClickable(btnPayWithPaypal);
        DriverContext.jsScrollToElementAlignBottom(btnPayWithPaypal);
        DriverContext.jsClickOnElement(btnPayWithPaypal);
    }

    public void proceedToPayWithCreditCardGermany() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayWithCCGermany, 2);
        DriverContext.waitUntilElementIsClickable(btnProceedToPayWithCCGermany);
        DriverContext.jsScrollToElementAlignBottom(btnProceedToPayWithCCGermany);
        orderTotal = txtOrderTotalPrice.GetTextValue();
        DriverContext.jsClickOnElement(btnProceedToPayWithCCGermany);
    }

    public void selectPaymentMethodInINHS(List<Map<String, String>> PaymentDetails) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 2);
        switch (PaymentDetails.get(0).get("Payment Method")) {
            case "Credit Card","UPI" -> {
                DriverContext.repeatWaitForElementVisibilityAttempt(chkboxCreditCard, 2);
                DriverContext.waitUntilElementIsClickable(chkboxCreditCard);
                DriverContext.jsClickOnElement(chkboxCreditCard.WaitForClickable());
            }
            case "COD" -> {
                DriverContext.repeatWaitForElementVisibilityAttempt(chkboxCOD, 2);
                DriverContext.waitUntilElementIsClickable(chkboxCOD);
                DriverContext.jsClickOnElement(chkboxCOD.WaitForClickable());
            }
        }
    }

    public void selectPaymentMethod() {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxCreditCard, 2);
        DriverContext.waitUntilElementIsClickable(chkboxCreditCard);
        DriverContext.jsScrollToElementAlignBottom(chkboxCreditCard);
        DriverContext.repeatElementClickAttempt(chkboxCreditCard, 3);
    }

    public void selectPaymentMethodInSP() {
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxCreditCard, 2);
        DriverContext.waitUntilElementIsClickable(chkboxCreditCard);
        DriverContext.jsScrollToElementAlignBottom(chkboxCreditCard);
        DriverContext.repeatElementClickAttempt(chkboxCreditCard, 3);
    }

    public void selectCIC() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnCIC, 3);
        DriverContext.jsScrollToElementAlignBottom(btnCIC);
        DriverContext.repeatElementClickAttempt(btnCIC, 3);
    }

    public void selectPaypalExpress() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnPaypalExpress, 1);
        DriverContext.jsScrollToElementAlignBottom(btnPaypalExpress);
        DriverContext.repeatElementClickAttempt(btnPaypalExpress, 1);
        if (DriverContext.getCurrentUrl().contains("anz.")) {
            if (DriverContext.getCurrentUrl().contains("staging")) {
                DriverContext.repeatWaitForElementVisibilityAttempt(btnPaypalExpressTnCANZ, 1);
                DriverContext.jsScrollToElementAlignBottom(btnPaypalExpressTnCANZ);
                DriverContext.jsClickOnElement(btnPaypalExpressTnCANZ);
                DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayPaypalExpressAnz, 3);
                DriverContext.jsScrollToElementAlignBottom(btnProceedToPayPaypalExpressAnz);
                DriverContext.repeatElementClickAttempt(btnProceedToPayPaypalExpressAnz, 2);
            } else {
                DriverContext.repeatWaitForElementVisibilityAttempt(btnPaypalExpressTnCANZUat, 1);
                DriverContext.jsScrollToElementAlignBottom(btnPaypalExpressTnCANZUat);
                DriverContext.jsClickOnElement(btnPaypalExpressTnCANZUat);
                DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayPaypalExpressAnz, 3);
                DriverContext.jsScrollToElementAlignBottom(btnProceedToPayPaypalExpressAnz);
                DriverContext.repeatElementClickAttempt(btnProceedToPayPaypalExpressAnz, 2);
            }
        } else if (DriverContext.getCurrentUrl().contains("sp.")) {
            DriverContext.repeatWaitForElementVisibilityAttempt(btnPaypalExpressTnCSP, 1);
            DriverContext.jsScrollToElementAlignBottom(btnPaypalExpressTnCSP);
            DriverContext.jsClickOnElement(btnPaypalExpressTnCSP);
            DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayPaypalExpress, 3);
            DriverContext.jsScrollToElementAlignBottom(btnProceedToPayPaypalExpress);
            DriverContext.repeatElementClickAttempt(btnProceedToPayPaypalExpress, 2);
        } else {
            DriverContext.repeatWaitForElementVisibilityAttempt(btnPaypalExpressTnC, 1);
            DriverContext.jsScrollToElementAlignBottom(btnPaypalExpressTnC);
            DriverContext.jsClickOnElement(btnPaypalExpressTnC);
            DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayPaypalExpress, 3);
            DriverContext.jsScrollToElementAlignBottom(btnProceedToPayPaypalExpress);
            DriverContext.repeatElementClickAttempt(btnProceedToPayPaypalExpress, 2);
        }
        orderTotal = txtOrderTotalPrice.GetTextValue();
    }

    public void acceptTermsAndConditionsInFRHS() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnCICTnC, 3);
        DriverContext.jsScrollToElementAlignBottom(btnCICTnC);
        DriverContext.jsClickOnElement(btnCICTnC);
        orderTotal = txtOrderTotalPrice.GetTextValue();
    }

    public void proceedToPayWithCreditCardFR() {
        DriverContext.waitUntilElementIsClickable(btnProceedToPayFR);
        DriverContext.jsScrollToElementAlignBottom(btnProceedToPayFR);
        DriverContext.jsClickOnElement(btnProceedToPayFR);
    }

    public void acceptEndUserLicenseAgreement() {
        DriverContext.jsScrollToElementAlignBottom(chkboxEndUserLicenseAgreement);
        DriverContext.jsClickOnElement(chkboxEndUserLicenseAgreement);
    }

    public void acceptTermsAndConditionsInSP() {
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxAcceptTnC, 2);
        DriverContext.jsClickOnElement(chkboxAcceptTnC);
        orderTotal = txtOrderTotalPrice.GetTextValue();
        orderSubTotal = txrOrderSubTotal.GetTextValue();
        orderTax = txtOrderTax.GetTextValue();
        DriverContext.waitForPageToLoad();
        if (DriverContext.getCurrentUrl().contains("uat")) {
            taxPercentageUAT = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
        } else {
            taxPercentagePROD = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
        }
    }

    public void proceedToPayInSP() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPaySP, 3);
        DriverContext.waitUntilElementIsClickable(btnProceedToPaySP);
        DriverContext.jsClickOnElement(btnProceedToPaySP);
    }

    public void addDifferentBillingAddressForExistingUserFR(List<Map<String, String>> address) {
        DriverContext.waitUntilElementIsClickable(btnProceedToPayFR);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.jsClickOnElement(chkboxShippingAndBillingAddress.WaitForClickable());
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(txtAddressListFR, 3);
        if (!DriverContext.doesElementExist(txtAddressListFR)) {
            txtFieldStreetAddressFR.sendKeys(address.get(0).get("Street Address"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldCityFR);
            txtFieldCityFR.sendKeys(address.get(0).get("City"));
            DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement((drpdwnCountryFR)));
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountryFR, address.get(0).get("Country"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeFR);
            txtFieldZipCodeFR.sendKeys(address.get(0).get("ZipCode"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberFR);
            txtFieldPhoneNumberFR.sendKeys(address.get(0).get("Phone Number"));
            DriverContext.waitForPageToLoad();
            DriverContext.jsScrollToElementAlignBottom(chkboxSaveToAddressBook);
            chkboxSaveToAddressBook.WaitForClickable().Click();
        } else if (txtAddressListFR.getText().equals("Nouvelle adresse")) {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnBillingAddressFR, "Nouvelle adresse");
            btnUpdateBillingAddressFR.WaitForClickable();
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitleFR, address.get(0).get("Title"));
            txtFieldFirstNameFR.clear();
            txtFieldFirstNameFR.sendKeys(address.get(0).get("First Name"));
            txtFieldLastNameFR.clear();
            txtFieldLastNameFR.sendKeys(address.get(0).get("Last Name"));
            txtFieldStreetAddressFR.sendKeys(address.get(0).get("Street Address"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldCityFR);
            txtFieldCityFR.sendKeys(address.get(0).get("City"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeFR);
            txtFieldZipCodeFR.sendKeys(address.get(0).get("ZipCode"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberFR);
            txtFieldPhoneNumberFR.sendKeys(address.get(0).get("Phone Number"));
            DriverContext.waitForPageToLoad();
            DriverContext.jsScrollToElementAlignBottom(chkboxSaveToAddressBook);
            chkboxSaveToAddressBook.WaitForClickable().Click();
        } else {
            DriverContext.selectDropdownListElementUsingIndex(drpdwnBillingAddressFR, 1);
        }
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressFR);
        btnUpdateBillingAddressFR.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressFR(List<Map<String, String>> address) {
        if (!eBook&&!clinicalKey) {
            DriverContext.waitForPageToLoad();
            DriverContext.driverSleep(15000); // TODO: Replace in the future with a suitable explicit wait
            DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayFR, 3);
            DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
            DriverContext.waitUntilElementIsClickable(chkboxShippingAndBillingAddress);
            DriverContext.jsClickOnElement(chkboxShippingAndBillingAddress.WaitForClickable());
            DriverContext.waitForPageToLoad();
            btnUpdateBillingAddressFR.WaitForClickable();
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitleFR, address.get(0).get("Title"));
            txtFieldFirstNameFR.sendKeys(address.get(0).get("First Name"));
            txtFieldLastNameFR.sendKeys(address.get(0).get("Last Name"));
        }
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldStreetAddressFR, 3);
        txtFieldStreetAddressFR.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCityFR);
        txtFieldCityFR.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeFR);
        txtFieldZipCodeFR.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberFR);
        txtFieldPhoneNumberFR.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressFR);
        btnUpdateBillingAddressFR.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressFRMobile(List<Map<String, String>> address) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldStreetAddressFR, 3);
        txtFieldStreetAddressFR.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCityFR);
        txtFieldCityFR.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeFR);
        txtFieldZipCodeFR.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberFR);
        txtFieldPhoneNumberFR.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressFR);
        DriverContext.jsClickOnElement(btnUpdateBillingAddressFR);
        DriverContext.waitForPageToLoad();
    }

    public void addBillingAddressFR(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldStreetAddressFR, 3);
        txtFieldStreetAddressFR.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCityFR);
        txtFieldCityFR.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeFR);
        txtFieldZipCodeFR.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberFR);
        txtFieldPhoneNumberFR.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressMEA(List<Map<String, String>> address) {
        DriverContext.driverSleep(20000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.waitUntilElementIsClickable(btnProceedToPayWithCreditCardUK);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        //DriverContext.jsClickOnElement(chkboxShippingAndBillingAddress.WaitForClickable());
        DriverContext.repeatElementClickAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(btnUpdateBillingAddressUK, 3);
        btnUpdateBillingAddressUK.WaitForClickable();
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
        txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddress);
        btnUpdateBillingAddressUK.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressForExistingUserMEA(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayWithCreditCardUK, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.jsClickOnElement(chkboxShippingAndBillingAddress.WaitForClickable());
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        if (txtAddressList.getText().equals("New Address")) {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnBillingAddress, "New Address");
            btnUpdateBillingAddressUK.WaitForClickable();
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
            txtFieldFirstName.clear();
            txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
            txtFieldLastName.clear();
            txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
            txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
            txtFieldStreetAddress.sendKeys(Keys.TAB);
            DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
            txtFieldCity.sendKeys(address.get(0).get("City"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
            txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
            txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
            chkboxSaveToAddressBook.WaitForClickable().Click();
        } else {
            DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement(drpdwnBillingAddressUK), 3);
            DriverContext.selectDropdownListElementUsingIndex(drpdwnBillingAddressUK, 1);
        }
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressUK);
        btnUpdateBillingAddressUK.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressSP(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPaySP, 3);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
        DriverContext.jsClickOnElement(chkboxShippingAndBillingAddress.WaitForClickable());
        DriverContext.waitForPageToLoad();
        DriverContext.repeatWaitForElementVisibilityAttempt(btnUpdateBillingAddressSP, 3);
        btnUpdateBillingAddressSP.WaitForClickable();
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
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddress);
        btnUpdateBillingAddressSP.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addShippingDetailsForExistingUserUS(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldStreetAddress, 3);
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        if (address.get(0).get("Country").equals("United States")) {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnState, address.get(0).get("State"));
        }
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddress);
        btnUpdateBillingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addShippingDetailsForExistingUserUSMobile(List<Map<String, String>> address) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldStreetAddress, 3);
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        if (address.get(0).get("Country").equals("United States")) {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnState, address.get(0).get("State"));
        }
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddress);
        DriverContext.jsClickOnElement(btnUpdateBillingAddress);
        DriverContext.waitForPageToLoad();
    }

    public void addBillingDetailsForExistingUserIN(List<Map<String, String>> PaymentDetails) {
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldStreetAddressIN, 3);
        txtFieldStreetAddressIN.sendKeys(PaymentDetails.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCityIN);
        txtFieldCityIN.sendKeys(PaymentDetails.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeIN);
        txtFieldZipCodeIN.sendKeys(PaymentDetails.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberIN);
        txtFieldPhoneNumberIN.sendKeys(PaymentDetails.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddressUK);
        DriverContext.jsClickOnElement(btnUpdateBillingAddressUK);
        DriverContext.waitForPageToLoad();
    }

    public void addDifferentBillingAddressForEBooksAndClinicalKey(List<Map<String, String>> address) {
        DriverContext.waitForPageToLoad();
        if (DriverContext.doesElementExist(btnEditBillingAddress)) {
            DriverContext.repeatWaitForElementVisibilityAttempt(btnEditBillingAddress, 3);
            DriverContext.jsClickOnElement(btnEditBillingAddress);
            DriverContext.repeatWaitForElementVisibilityAttempt(txtAddressListDE, 3);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnBillingAddressDE, "Neue Adresse");
            btnUpdateBillingAddressDE.WaitForClickable();
            DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldStreetAddressDE, 3);
            txtFieldStreetAddressDE.sendKeys(address.get(0).get("Street Address"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldCityDE);
            txtFieldCityDE.sendKeys(address.get(0).get("City"));
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountryFR, address.get(0).get("Country"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeDE);
            txtFieldZipCodeDE.sendKeys(address.get(0).get("ZipCode"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberDE);
            txtFieldPhoneNumberDE.sendKeys(address.get(0).get("Phone Number"));
            chkboxSaveToAddressBook.WaitForClickable().Click();
        } else {
            txtFieldStreetAddressDE.sendKeys(address.get(0).get("Street Address"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldCityDE);
            txtFieldCityDE.sendKeys(address.get(0).get("City"));
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountryFR, address.get(0).get("Country"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldZipCodeDE);
            txtFieldZipCodeDE.sendKeys(address.get(0).get("ZipCode"));
            DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumberDE);
            txtFieldPhoneNumberDE.sendKeys(address.get(0).get("Phone Number"));
            chkboxSaveToAddressBook.WaitForClickable().Click();
        }
        btnUpdateBillingAddressDE.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void useRewardPoints(){
        DriverContext.repeatWaitForElementVisibilityAttempt(rewardPointsButton, 2);
        rewardPointsButton.WaitForClickable().Click();
        DriverContext.driverSleep(20000);
        DriverContext.repeatWaitForElementVisibilityAttempt(txtSuccessMessage, 2);
        Assertions.assertTrue(txtSuccessMessage.GetTextValue().contains("Your reward point was successfully applied"));
        orderSubTotal = txrOrderSubTotal.GetTextValue();
        rewardPoints = rewardPointsUsed.GetTextValue();
        Float orderSubTotalValue = Float.valueOf(orderSubTotal.replace("AU$",""));
        Float rewardPointsValue = Float.valueOf(rewardPoints.replace("AU$","").replace("-",""));
        String subTotalAfterUseRewardPoints = String.format("%.2f", orderSubTotalValue-rewardPointsValue);
        orderTotal = txtOrderTotalPrice.GetTextValue();
        Assertions.assertEquals(orderTotal.replace("AU$",""), subTotalAfterUseRewardPoints);
    }

    public void selectCreditCardPaymentMethod(){
        DriverContext.repeatWaitForElementVisibilityAttempt(creditCardCheckout, 2);
        creditCardCheckout.WaitForClickable().Click();
    }

    public void acceptTermsAndConditionsInASIA() {
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxAcceptTnC, 2);
        DriverContext.jsClickOnElement(chkboxAcceptTnC);
        orderTotal = txtOrderTotalPrice.GetTextValue();
        orderSubTotal = txrOrderSubTotal.GetTextValue();
    }

    public void proceedToPayWithCreditCardIndia() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPayWithCCGermany, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTax, 2);
        DriverContext.waitUntilElementIsClickable(btnProceedToPayWithCCGermany);
        DriverContext.jsScrollToElementAlignBottom(btnProceedToPayWithCCGermany);
        orderTotal = txtOrderTotalPrice.GetTextValue();
        orderSubTotal = txrOrderSubTotal.GetTextValue();
        if(!clinicalKeyNowFreeTrial){
            orderTax = txtOrderTax.GetTextValue();
            DriverContext.waitForPageToLoad();
            if (DriverContext.getCurrentUrl().contains("uat")) {
                taxPercentageUAT = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
            } else {
                taxPercentagePROD = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
            }
        }
        DriverContext.jsClickOnElement(btnProceedToPayWithCCGermany);
    }

    public void addDifferentBillingAddressForExistingUserUS(List<Map<String, String>> address) {
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        if(DriverContext.doesElementExist(btnEditBillingAddress)){
            DriverContext.repeatWaitForElementVisibilityAttempt(btnEditBillingAddress, 3);
            DriverContext.jsClickOnElement(btnEditBillingAddress);
        }else{
            DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
            DriverContext.repeatElementClickAttempt(chkboxShippingAndBillingAddress, 2);
            DriverContext.waitForPageToLoad();
        }
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnBillingAddress, "New Address");
        btnUpdateBillingAddress.WaitForClickable();
        boolean isAsiaCountryDisplayed=verifyCountriesInBillingAddressDropdown();
        System.out.println("Is Asian Countries displayed in Countries Dropdown : "+isAsiaCountryDisplayed);
        Assertions.assertTrue(isAsiaCountryDisplayed, "Asian Countries is not displayed in the Country DropDown");
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.clear();
        txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
        txtFieldLastName.clear();
        txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        txtFieldStreetAddress.sendKeys(Keys.TAB);
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        if(DriverContext.isElementDisplayed(DriverContext.findElement(drpdwnState))){
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnState, address.get(0).get("State"));
        }
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
        chkboxSaveToAddressBook.WaitForClickable().Click();
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddress);
        btnUpdateBillingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void verifyTaxPercentage(String country) {
        int productCount=DriverContext.getElementsList("strong.product-item-name").size();
        if(productCount == 1){
            if (DriverContext.doesElementExist(txtOrderTaxUS)){
                String taxPaymentsPage = txtOrderTax.GetTextValue();
                String orderSubTotalPaymentsPage = txrOrderSubTotal.GetTextValue();
                float taxPercentage = (Float.parseFloat(taxPaymentsPage.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotalPaymentsPage.replaceAll("[^\\d.]", "")))) * 100;
                System.out.println("Tax percentage in "+country+" : "+taxPercentage);
                if(country.equals("India")){
                    Assertions.assertEquals(18, taxPercentage, "Tax percentage mismatch");
                }else if(country.equals("Japan")){
                    Assertions.assertEquals(10, taxPercentage, "Tax percentage mismatch");
                }
            }else{
                System.out.println("Tax is not displayed for "+country);
            }

        }else if(productCount>1){
            String taxPaymentsPage = txtOrderTax.GetTextValue();
            String orderSubTotalPaymentsPage = txrOrderSubTotal.GetTextValue();
            float taxPercentage = (Float.parseFloat(taxPaymentsPage.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotalPaymentsPage.replaceAll("[^\\d.]", "")))) * 100;
            System.out.println("Tax percentage in "+country+" : "+taxPercentage);
        }
    }

    public boolean verifyCountriesInBillingAddressDropdown() {
        List<String> countries= new ArrayList<>(Arrays.asList(
                "United States", "Canada", "Brunei", "Cambodia", "China", "India", "Indonesia", "Japan", "Laos",
                "Malaysia", "Myanmar (Burma)", "Singapore", "South Korea", "Thailand", "Timor-Leste"));
        List<String> countryList = new ArrayList<>();
        List<WebElement> countryDropdown=DriverContext.getElementsList("div[name=\"billingAddressecapture.country_id\"] select option");
        for (WebElement eachCountry:countryDropdown) {
            countryList.add(eachCountry.getText());
        }
        return countryList.containsAll(countries);
    }

    public void verifyDifferentBillingAddressCountriesNotAvailable() {
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        if(DriverContext.doesElementExist(btnEditBillingAddress)){
            DriverContext.repeatWaitForElementVisibilityAttempt(btnEditBillingAddress, 3);
            DriverContext.jsClickOnElement(btnEditBillingAddress);
        }else{
            DriverContext.repeatWaitForElementVisibilityAttempt(chkboxShippingAndBillingAddress, 3);
            DriverContext.repeatElementClickAttempt(chkboxShippingAndBillingAddress, 2);
            DriverContext.waitForPageToLoad();
        }
        DriverContext.driverSleep(5000); // TODO: Replace in the future with a suitable explicit wait
        boolean isAsiaCountryDisplayed=verifyCountriesInBillingAddressDropdown();
        System.out.println("Is Asian Countries displayed in Countries Dropdown : "+isAsiaCountryDisplayed);
        Assertions.assertTrue(!isAsiaCountryDisplayed, "Asian Countries is displayed in the Country DropDown");
    }

    public void acceptTermsAndConditionsForPSSI() {
        //DriverContext.waitForHTMLLoad(30, 3);
        DriverContext.repeatWaitForElementVisibilityAttempt(btnProceedToPay, 2);
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxAcceptTnC, 2);
        DriverContext.jsScrollToElementAlignBottom(chkboxAcceptTnC);
        DriverContext.jsClickOnElement(chkboxAcceptTnC);
        DriverContext.driverSleep(20000); // TODO: Replace in the future with a suitable explicit wait
        DriverContext.repeatWaitForElementVisibilityAttempt(txtOrderTotalPrice, 2);
        orderTotal = txtOrderTotalPrice.GetTextValue();
        orderSubTotal = txrOrderSubTotal.GetTextValue();
        if (!eBook && !journal) {
            if(DriverContext.doesElementExist(txtOrderTax)) {
                if (orderSubTotal.replaceAll("\\p{Sc}", "").equals("0.00")) { //Added for PDS free trial
                    orderTax = String.valueOf(0);
                } else {
                    orderTax = txtOrderTax.GetTextValue();
                }
                if (DriverContext.getCurrentUrl().contains("uat") && (DriverContext.getCurrentUrl().contains("us.") || DriverContext.getCurrentUrl().contains("eu."))) {
                    taxPercentageUAT = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
                } else if (DriverContext.getCurrentUrl().contains("www") && (DriverContext.getCurrentUrl().contains("us.") || DriverContext.getCurrentUrl().contains("eu."))) {
                    taxPercentagePROD = (Float.parseFloat(orderTax.replaceAll("[^\\d.]", "")) / (Float.parseFloat(orderSubTotal.replaceAll("[^\\d.]", "")))) * 100;
                }
            }else{
                System.out.println("Tax is not displayed");
            }
        }
    }

    public void addShippingDetailsForNewUserUS(List<Map<String, String>> address) {
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnTitle, address.get(0).get("Title"));
        txtFieldFirstName.clear();
        txtFieldFirstName.sendKeys(address.get(0).get("First Name"));
        txtFieldLastName.clear();
        txtFieldLastName.sendKeys(address.get(0).get("Last Name"));
        boolean isAsiaCountryDisplayed=verifyCountriesInBillingAddressDropdown();
        System.out.println("Is Asian Countries displayed in Countries Dropdown : "+isAsiaCountryDisplayed);
        Assertions.assertTrue(isAsiaCountryDisplayed, "Asian Countries is not displayed in the Country DropDown");
        DriverContext.repeatWaitForElementVisibilityAttempt(txtFieldStreetAddress, 3);
        txtFieldStreetAddress.sendKeys(address.get(0).get("Street Address"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldCity);
        txtFieldCity.sendKeys(address.get(0).get("City"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldZipCode);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountry, address.get(0).get("Country"));
        if (address.get(0).get("Country").equals("United States")) {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnState, address.get(0).get("State"));
        }
        txtFieldZipCode.sendKeys(address.get(0).get("ZipCode"));
        DriverContext.jsScrollToElementAlignBottom(txtFieldPhoneNumber);
        txtFieldPhoneNumber.sendKeys(address.get(0).get("Phone Number"));
        DriverContext.jsScrollToElementAlignBottom(btnUpdateBillingAddress);
        btnUpdateBillingAddress.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public String findPaymentMethodIN(){
        String paymentCheck = checkForPaymentMethod.getText();
        orderTotal = txtOrderTotalPrice.GetTextValue();
        orderSubTotal = txrOrderSubTotal.GetTextValue();
        if(clinicalKeyNowFreeTrial){
            Assertions.assertEquals(paymentCheck,"No Payment Information Required");
        }
        return paymentCheck;
    }

}

