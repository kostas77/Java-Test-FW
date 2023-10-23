package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.ProductPageLocators;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.Map;

public class ProductPageActions extends ProductPageLocators {

    public static boolean eBook;

    public void addToCart() {
        if (DriverContext.getCurrentUrl().contains("latam")) {
            eBook = true;
        }
        DriverContext.jsScrollToElementAlignBottom(btnAddToCart);
        DriverContext.repeatElementClickAttempt(btnAddToCart, 3);
        DriverContext.waitForPageToLoad();
        DriverContext.repeatElementClickAttempt(btnCloseFreeShippingPopup, 1);
    }

    public void closeFreeShippingPopup() {
        System.out.println("doesElementExist? " + DriverContext.doesElementExist(btnCloseFreeShippingPopup));
        if (DriverContext.doesElementExist(btnCloseFreeShippingPopup)) {
            btnCloseFreeShippingPopup.WaitForClickable().Click();
        }
    }

    public void navigateToViewCartPage() {
        DriverContext.jsScrollToElementAlignTop(btnCartIcon);
        DriverContext.hoverOverElement(btnCartIcon);
        DriverContext.repeatWaitForElementVisibilityAttempt(btnViewCart, 2);
        btnViewCart.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void navigateToViewCartPageMobile() {
        DriverContext.jsScrollToElementAlignTop(btnCartIcon);
        DriverContext.hoverOverElement(btnCartIcon);
//        DriverContext.repeatWaitForElementVisibilityAttempt(btnViewCart,2);
//        btnViewCart.click();
        DriverContext.waitForPageToLoad();
    }


    public void selectEBookCheckBox() {
        eBook = true;
        DriverContext.repeatWaitForElementVisibilityAttempt(chkboxEBook, 2);
        chkboxEBook.WaitForClickable().Click();
    }

    public void addToWishlist() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnWishlist, 2);
        btnWishlist.WaitForClickable().Click();
    }

    public void addReview(List<Map<String, String>> reviewDetails) {
        DriverContext.jsScrollToElementAlignBottom(btnReviews);
        btnReviews.WaitForClickable().Click();
        DriverContext.waitUntilElementIsVisible(btnStarRating);
        DriverContext.jsScrollToElementAlignBottom(btnSubmitReview);
        DriverContext.jsClickOnElement(btnStarRating);
        txtfldNickName.sendKeys(Keys.chord(Keys.CONTROL, "a"), reviewDetails.get(0).get("Nick Name"));
        DriverContext.jsScrollToElementAlignTop(txtfldSummary);
        txtfldSummary.sendKeys(reviewDetails.get(0).get("Summary"));
        txtfldReview.sendKeys(reviewDetails.get(0).get("Review"));
        btnSubmitReview.WaitForClickable().Click();
    }

    public static void resetEBook() {
        eBook = false;
    }

}
