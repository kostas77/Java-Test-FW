package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.base.LocalDriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.SearchPageLocators;
import org.openqa.selenium.By;

public class SearchPageActions extends SearchPageLocators {

    public String SearchResultsIsDisplayed() {
        return searchResults.GetTextValue();
    }

    public void productNameVerification(String product) {
        String productName = txtProductName.getText();
        if (productName.contains(product)) {
            System.out.println("Searched product item name contains " + product);
        } else {
            System.out.println("Searched product item doesn't name contains " + product);
        }
    }

    public void addToCart() {
        btnAddToCart.WaitForClickable().Click();
    }

    public void clickOnProductLink() {
        DriverContext.repeatWaitForElementVisibilityAttempt(lnkProduct,2);
        lnkProduct.ClickLink();
    }

    public void addToWishlist() {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnWishlist,2);
        btnWishlist.WaitForClickable().Click();
    }

    public void clickOnProduct(String productName) {
        String prodNameXpath = "//a[@class='product-item-link' and normalize-space()='" + productName + "']";
        LocalDriverContext.getWebDriver().findElement(By.xpath(prodNameXpath)).click();
    }

    public void clickOnBookProductLink() {
        if(DriverContext.doesElementExist(linkBookProductName)) {
            linkBookProductName.ClickLink();
        }else{
            lnkProduct.ClickLink();
        }
    }
}
