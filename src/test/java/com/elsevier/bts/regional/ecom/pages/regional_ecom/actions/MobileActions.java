package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.AdminPanelLocators;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.MobileLocators;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MobileActions extends MobileLocators {
    public void emptyCartMobile() {
        String cartLocator =".action.action-delete";
        Assertions.assertTrue(DriverContext.getCurrentUrl().contains("/cart/"));
        List<WebElement> removeProducts = DriverContext.getElementsList(cartLocator);
        for (int i = 0; i < removeProducts.size(); i++) {
            WebElement removeProduct = DriverContext.getElement(cartLocator);
            if (DriverContext.doesElementExist(removeProduct)) {
                DriverContext.jsScrollToElementAlignTop(removeProduct);
                DriverContext.repeatElementClickAttempt(removeProduct, 2);
                DriverContext.jsClickOnElement(removeProduct);
                DriverContext.waitForPageToLoad();
            }
            System.out.println("Cart is empty");
        }
    }
    public void searchForProduct(String product) {
        DriverContext.repeatWaitForElementVisibilityAttempt(mobileSearchIcon, 2);
        mobileSearchIcon.Click();
        DriverContext.repeatWaitForElementVisibilityAttempt(mobileTextFieldBox, 2);
        mobileTextFieldBox.Click();
        DriverContext.repeatWaitForElementVisibilityAttempt(mobileTextFieldSearch, 2);
        mobileTextFieldSearch.sendKeys(product);
        DriverContext.repeatElementClickAttempt(mobileBtnSearch, 2);
    }
    public void selectProductFromNavigationBar(String productType) {
        DriverContext.repeatWaitForElementVisibilityAttempt(mobileHamburgerMenu, 2);
        mobileHamburgerMenu.Click();
        DriverContext.jsScrollToElementAlignBottom(mobileProductFormatMenuEbook);
//        DriverContext.repeatWaitForElementVisibilityAttempt(mobileHamburgerMenu, 2);
        DriverContext.jsClickOnElement(mobileProductFormatMenuEbook);
//        mobileProductFormatMenuEbook.Click();
//        DriverContext.hoverOverElement(btnProductFormat);
//        switch (subscription) {
//            case "ClinicalKey" -> btnClinicalKeyUS.WaitForClickable().Click();
//            case "Clinical Pharmacology" -> btnClinicalKeyPharmacology.WaitForClickable().Click();
//            case "ClinicalKey Student" -> {
//                DriverContext.goToUrl(DriverContext.getCurrentUrl() + "/clinicalkey-student");
//                ClinicalKeyProductPageActions.selectClinicalKeyStudent();
//            }
//
//            //btnClinicalKeyStudent.WaitForClickable().Click();
//        }
    }


    public void addToCartMobile() {
        DriverContext.repeatWaitForElementVisibilityAttempt(mobileBtnAddToCart, 2);
        mobileBtnAddToCart.click();
        DriverContext.repeatWaitForElementVisibilityAttempt(mobileBtnAddToCartConfirm, 2);
        DriverContext.jsClickOnElement(mobileBtnAddToCartConfirm);
        DriverContext.waitForPageToLoad();
    }
    public void eBookAddToCartMobile() {
        DriverContext.repeatWaitForElementVisibilityAttempt(mobileBtnAddToCart, 2);
        mobileBtnAddToCart.click();
        DriverContext.repeatWaitForElementVisibilityAttempt(mobileBtnAddToCartConfirmForEbook, 2);
        DriverContext.jsClickOnElement(mobileBtnAddToCartConfirmForEbook);
        DriverContext.waitForPageToLoad();
    }

    }

