package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.JournalProductPageLocators;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.DeclareRoles;
import java.util.Objects;

public class JournalProductPageActions extends JournalProductPageLocators {

    public static boolean journal;

    public void addJournalToCartPROD(String subscriberStatus, String subscriberCountry, String subscriptionTerm) {
        journal = true;
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriberStatus, subscriberStatus);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriberCountry, subscriberCountry);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriptionTerm, subscriptionTerm);
        DriverContext.jsScrollToElementAlignBottom(btnAddToCart);
        btnAddToCart.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addJournalToCart(String subscriberStatus) {
        journal = true;
        if (DriverContext.doesElementExist(DriverContext.findElement(drpdwnSubscriberStatus))){
            if (DriverContext.isElementDisplayed(DriverContext.findElement(drpdwnSubscriberStatus))) {
                DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriberStatus, subscriberStatus);
            }
        }
        DriverContext.repeatWaitForElementVisibilityAttempt(btnAddToCart, 2);
        btnAddToCart.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatElementClickAttempt(btnCloseFreeShippingPopup, 1);
    }

    public void addJournalToCartUK(String subscriberStatus, String subscriberCountry, String subscriptionTerm) {
        if (btnBuyNowUK.getText().equalsIgnoreCase("Buy Now")) {
            DriverContext.jsScrollToElementAlignBottom(btnBuyNowUK);
            DriverContext.repeatElementClickAttempt(btnBuyNowUK, 2);
            DriverContext.waitForPageToLoad();
            DriverContext.repeatWaitForElementVisibilityAttempt(btnAcceptAllCookies, 1);
            if (DriverContext.doesElementExist(btnAcceptAllCookies)) {
                btnAcceptAllCookies.WaitForClickable().Click();
            }
            DriverContext.driverSleep(20000); // TODO: Replace in the future with a suitable explicit wait
            DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement(drpdwnSubscriberStatus), 1);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriberStatus, subscriberStatus);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriberCountry, subscriberCountry);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriptionTerm, subscriptionTerm);
            DriverContext.jsScrollToElementAlignBottom(btnAddToCart);
            btnAddToCart.WaitForClickable().Click();
            DriverContext.waitForPageToLoad();
            DriverContext.repeatElementClickAttempt(btnCloseFreeShippingPopup, 3);
        }
    }

    public void addJournalToCart() {
        journal = true;
        DriverContext.jsScrollToElementAlignBottom(btnAddToCart);
        DriverContext.repeatElementClickAttempt(btnAddToCart, 2);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatElementClickAttempt(btnCloseFreeShippingPopup, 3);
    }

    public void addJournalToCartSPPROD(String subscriberCountry, String subscriptionTerm, String FormatType) {
        while (DriverContext.isElementEnabled(Objects.requireNonNull(DriverContext.findElement(drpdwnFormatType)))) {
            DriverContext.waitForPageToLoad();
        }
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriberCountrySP, subscriberCountry);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriptionTermSP, subscriptionTerm);
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnFormatType, FormatType);
            DriverContext.jsScrollToElementAlignBottom(btnAddToCartSP);
            btnAddToCartSP.WaitForClickable().Click();
            DriverContext.waitForPageToLoad();
            DriverContext.repeatElementClickAttempt(btnCloseFreeShippingPopup, 3);
    }

    public void addJournalToCartSP(String subscriberCountry, String subscriptionTerm, String FormatType) {
       DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountryFR, subscriberCountry);
       DriverContext.selectDropdownListElementUsingVisibleText(drpdwnJournalType, FormatType);
       if(subscriptionTerm.equals("2 Años")){
            btn2YearsSP.WaitForClickable().click();
       }
        DriverContext.jsScrollToElementAlignBottom(btnAddToCartSP);
        btnAddToCartSP.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
        DriverContext.repeatElementClickAttempt(btnCloseFreeShippingPopup, 3);
    }

    public void addJournalToCartMEA(String subscriberCountry, String subscriptionTerm, String subscriberStatus) {
        journal = true;
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriberCountry, subscriberCountry);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriptionTerm, subscriptionTerm);
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnSubscriberStatus, subscriberStatus);
        DriverContext.jsScrollToElementAlignBottom(btnAddToCart);
        btnAddToCart.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addJournalToCartFR(String format, String status, String country, String duration) {
        journal = true;
        selectFormatAndStatus(format, status);
        selectCountryFR(country);
        selectSubscriptionDurationFR(duration);
        if(status.equals("Student")){
            chkBoxStudent.WaitForClickable().click();
        }
        DriverContext.jsScrollToElementAlignBottom(btnAddToCart);
        btnAddToCart.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void selectFormatAndStatus(String format, String status){
        switch (format) {
            case "Paper" -> {
                DriverContext.selectDropdownListElementUsingVisibleText(drpdwnFormatFR, "Revue - Papier");
                switch (status) {
                    case "Individual" ->  DriverContext.jsClickOnElement(btnParticular);
                    case "Student" ->     DriverContext.jsClickOnElement(btnStudent);
                    case "Institution" -> DriverContext.jsClickOnElement(btnInstitution);
                }
            }
            case "Digital + Paper" -> {
                DriverContext.selectDropdownListElementUsingVisibleText(drpdwnFormatFR, "Revue - Numérique+Papier");
                switch (status) {
                    case "Individual" ->   btnParticular.WaitForClickable().click();
                    case "Student" ->     DriverContext.jsClickOnElement(btnStudent);
                }
            }
            case "Digital" -> {
                DriverContext.selectDropdownListElementUsingVisibleText(drpdwnFormatFR, "Revue - Numérique");
                switch (status) {
                    case "Individual" ->   btnParticular.WaitForClickable().click();
                    case "Student" ->     DriverContext.jsClickOnElement(btnStudent);
                }
            }
        }
    }
    public void selectCountryFR(String country) {
        switch (country) {
            case "France" -> DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountryFR, "France (+ DOM-TOM)");
            case "Rest of World" -> DriverContext.selectDropdownListElementUsingVisibleText(drpdwnFormatFR, "Reste du monde");
            case "Switzerland" -> DriverContext.selectDropdownListElementUsingVisibleText(drpdwnFormatFR, "UE (+ Suisse)");
        }
    }
    public void selectSubscriptionDurationFR(String duration) {
        switch (duration) {
            case "12 months" -> {
                if(!DriverContext.doesElementExist(btn12MonthsSelected)){
                    btn12Months.WaitForClickable().click();
                }
            }
            case "24 months" -> btn24Months.WaitForClickable().click();
            case "36 months" -> btn36Months.WaitForClickable().click();
        }
    }

    public void addJournalToCart(String subscriberStatus, String subscriptionTerm, String subscriberCountry) {
        journal = true;
        if(subscriberStatus.equals("Personal")){
            if(!DriverContext.doesElementExist(btnPersonalSelected)){
                btnPersonal.WaitForClickable().click();
            }
        }else  if(subscriberStatus.equals("Individual")){
            if(!DriverContext.doesElementExist(btnIndividualSelected)){
                btnIndividual.WaitForClickable().click();
            }
        }
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnCountryFR, subscriberCountry);
        if(subscriptionTerm.equals("1 Year")){
            if(!DriverContext.doesElementExist(btn1YearSelected)){
                btn1Year.WaitForClickable().click();
            }
        }
        DriverContext.jsScrollToElementAlignBottom(btnAddToCart);
        btnAddToCart.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void addEMCToCartFR(String format, String status, String country, String duration) {
        journal = true;
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnFormatFR, format);
        if(!status.equals("Particulier")){
            btnParticular.WaitForClickable().click();
        }
        selectSubscriptionDurationFR(duration);
        selectCountryFR(country);
        DriverContext.jsScrollToElementAlignBottom(btnAddToCart);
        btnAddToCart.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }
}
