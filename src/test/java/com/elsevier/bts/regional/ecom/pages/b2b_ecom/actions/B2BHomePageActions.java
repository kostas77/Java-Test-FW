package com.elsevier.bts.regional.ecom.pages.b2b_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators.B2BHomepageLocators;
import org.openqa.selenium.Keys;

public class B2BHomePageActions extends B2BHomepageLocators {

    String b2bStoreUrl;

    public void navigateToMyAccountPage() {
        DriverContext.hoverOverElement(btnAccountMenu);
        btnMyAccount.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public String getb2bStoreUrl(String countryName, FrameworkConfigurationService frameworkConfigurationService) {
        switch (countryName) {
            case "US SP" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "UAT" -> b2bStoreUrl = frameworkConfigurationService.getUS_SP_UAT_URL();
                }
            }
            case "US EOP" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "UAT" -> b2bStoreUrl = frameworkConfigurationService.getUS_EOP_UAT_URL();
                }
            }
            case "UK EOP" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "UAT" -> b2bStoreUrl = frameworkConfigurationService.getUK_EOP_UAT_URL();
                }
            }
            case "JP EOP" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "UAT" -> b2bStoreUrl = frameworkConfigurationService.getJP_EOP_UAT_URL();
                }
            }
            case "EU EOP" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "UAT" -> b2bStoreUrl = frameworkConfigurationService.getEU_EOP_UAT_URL();
                }
            }
        }
        return b2bStoreUrl;
    }

    public void navigateToHomepage(){
        siteLogo.WaitForClickable().Click();
    }

    public void searchForProduct(String product) {
        btnSearch.WaitForClickable().Click();
        DriverContext.repeatWaitForElementVisibilityAttempt(txtSearch,2);
        txtSearch.sendKeys(product);
        txtSearch.sendKeys(Keys.ENTER);
    }

    public void navigateToViewCartPageUrl() {
        DriverContext.goToUrl(b2bStoreUrl + "checkout/cart/");
        DriverContext.waitForPageToLoad();
    }

}
