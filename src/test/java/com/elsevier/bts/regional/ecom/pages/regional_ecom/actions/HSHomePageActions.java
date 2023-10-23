package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.HSHomePageLocators;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

@Slf4j
public class HSHomePageActions extends HSHomePageLocators {

    String healthStoreUrl;
    public static String productCategory;

    public void searchForProduct(String product) {
        txtFieldSearch.sendKeys(product);
        DriverContext.repeatElementClickAttempt(btnSearch, 2);
    }

//    public String getHealthStoreUrl(String countryName, FrameworkConfigurationService frameworkConfigurationService) {
//        System.out.println("------------------" + countryName);
//        System.out.println(frameworkConfigurationService);
//        System.out.println(frameworkConfigurationService.getBrowserVersion());
//        return "Test";
//    }

    public String getHealthStoreUrl(String countryName, FrameworkConfigurationService frameworkConfigurationService) {
        switch (countryName) {
            case "Germany" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "DEV" -> healthStoreUrl = frameworkConfigurationService.getDEHS_ECOM_DEV_URL();
                    case "UAT" -> healthStoreUrl = frameworkConfigurationService.getDEHS_ECOM_UAT_URL();
                    case "STAGING" -> healthStoreUrl = frameworkConfigurationService.getDEHS_ECOM_STAGING_URL();
                    case "IDPLUS" -> healthStoreUrl = frameworkConfigurationService.getDEHS_ECOM_IDPLUS_URL();
                    case "PROD" -> healthStoreUrl = frameworkConfigurationService.getDEHS_ECOM_PROD_URL();
                }
            }
            case "India" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "DEV" -> healthStoreUrl = frameworkConfigurationService.getINHS_ECOM_DEV_URL();
                    case "UAT" -> healthStoreUrl = frameworkConfigurationService.getINHS_ECOM_UAT_URL();
                    case "STAGING" -> healthStoreUrl = frameworkConfigurationService.getINHS_ECOM_STAGING_URL();
                    case "IDPLUS" -> healthStoreUrl = frameworkConfigurationService.getINHS_ECOM_IDPLUS_URL();
                    case "PROD" -> healthStoreUrl = frameworkConfigurationService.getINHS_ECOM_PROD_URL();
                }
            }
            case "US" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "DEV" -> healthStoreUrl = frameworkConfigurationService.getUSHS_ECOM_DEV_URL();
                    case "UAT" -> healthStoreUrl = frameworkConfigurationService.getUSHS_ECOM_UAT_URL();
                    case "STAGING" -> healthStoreUrl = frameworkConfigurationService.getUSHS_ECOM_STAGING_URL();
                    case "IDPLUS" -> healthStoreUrl = frameworkConfigurationService.getUSHS_ECOM_IDPLUS_URL();
                    case "PROD" -> healthStoreUrl = frameworkConfigurationService.getUSHS_ECOM_PROD_URL();
                }
            }
            case "EU" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "DEV" -> healthStoreUrl = frameworkConfigurationService.getEUHS_ECOM_DEV_URL();
                    case "UAT" -> healthStoreUrl = frameworkConfigurationService.getEUHS_ECOM_UAT_URL();
                    case "STAGING" -> healthStoreUrl = frameworkConfigurationService.getEUHS_ECOM_STAGING_URL();
                    case "IDPLUS" -> healthStoreUrl = frameworkConfigurationService.getEUHS_ECOM_IDPLUS_URL();
                    case "PROD" -> healthStoreUrl = frameworkConfigurationService.getEUHS_ECOM_PROD_URL();
                }
            }
            case "France" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "DEV" -> healthStoreUrl = frameworkConfigurationService.getFRHS_ECOM_DEV_URL();
                    case "UAT" -> healthStoreUrl = frameworkConfigurationService.getFRHS_ECOM_UAT_URL();
                    case "STAGING" -> healthStoreUrl = frameworkConfigurationService.getFRHS_ECOM_STAGING_URL();
                    case "IDPLUS" -> healthStoreUrl = frameworkConfigurationService.getFRHS_ECOM_IDPLUS_URL();
                    case "PROD" -> healthStoreUrl = frameworkConfigurationService.getFRHS_ECOM_PROD_URL();
                }
            }
            case "UK" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "DEV" -> healthStoreUrl = frameworkConfigurationService.getUKHS_ECOM_DEV_URL();
                    case "UAT" -> healthStoreUrl = frameworkConfigurationService.getUKHS_ECOM_UAT_URL();
                    case "STAGING" -> healthStoreUrl = frameworkConfigurationService.getUKHS_ECOM_STAGING_URL();
                    case "IDPLUS" -> healthStoreUrl = frameworkConfigurationService.getUKHS_ECOM_IDPLUS_URL();
                    case "PROD" -> healthStoreUrl = frameworkConfigurationService.getUKHS_ECOM_PROD_URL();
                }
            }
            case "LATAM" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "DEV" -> healthStoreUrl = frameworkConfigurationService.getLATAMHS_ECOM_DEV_URL();
                    case "UAT" -> healthStoreUrl = frameworkConfigurationService.getLATAMHS_ECOM_UAT_URL();
                    case "STAGING" -> healthStoreUrl = frameworkConfigurationService.getLATAMHS_ECOM_STAGING_URL();
                    case "IDPLUS" -> healthStoreUrl = frameworkConfigurationService.getLATAMHS_ECOM_IDPLUS_URL();
                    case "PROD" -> healthStoreUrl = frameworkConfigurationService.getLATAMHS_ECOM_PROD_URL();
                }
            }
            case "Spain" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "DEV" -> healthStoreUrl = frameworkConfigurationService.getSPHS_ECOM_DEV_URL();
                    case "UAT" -> healthStoreUrl = frameworkConfigurationService.getSPHS_ECOM_UAT_URL();
                    case "STAGING" -> healthStoreUrl = frameworkConfigurationService.getSPHS_ECOM_STAGING_URL();
                    case "IDPLUS" -> healthStoreUrl = frameworkConfigurationService.getSPHS_ECOM_IDPLUS_URL();
                    case "PROD" -> healthStoreUrl = frameworkConfigurationService.getSPHS_ECOM_PROD_URL();
                }
            }
            case "Middle East" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "DEV" -> healthStoreUrl = frameworkConfigurationService.getMEHS_ECOM_DEV_URL();
                    case "UAT" -> healthStoreUrl = frameworkConfigurationService.getMEHS_ECOM_UAT_URL();
                    case "STAGING" -> healthStoreUrl = frameworkConfigurationService.getMEHS_ECOM_STAGING_URL();
                    case "IDPLUS" -> healthStoreUrl = frameworkConfigurationService.getMEHS_ECOM_IDPLUS_URL();
                    case "PROD" -> healthStoreUrl = frameworkConfigurationService.getMEHS_ECOM_PROD_URL();
                }
            }
            case "ANZ" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "DEV" -> healthStoreUrl = frameworkConfigurationService.getANZHS_ECOM_DEV_URL();
                    case "UAT" -> healthStoreUrl = frameworkConfigurationService.getANZHS_ECOM_UAT_URL();
                    case "STAGING" -> healthStoreUrl = frameworkConfigurationService.getANZHS_ECOM_STAGING_URL();
                    case "IDPLUS" -> healthStoreUrl = frameworkConfigurationService.getANZHS_ECOM_IDPLUS_URL();
                    case "PROD" -> healthStoreUrl = frameworkConfigurationService.getANZHS_ECOM_PROD_URL();
                }
            }
            case "ASIA" -> {
                switch (frameworkConfigurationService.getTestEnv()) {
                    case "DEV" -> healthStoreUrl = frameworkConfigurationService.getASIAHS_ECOM_DEV_URL();
                    case "UAT" -> healthStoreUrl = frameworkConfigurationService.getASIAHS_ECOM_UAT_URL();
                    case "STAGING" -> healthStoreUrl = frameworkConfigurationService.getASIAHS_ECOM_STAGING_URL();
                    case "IDPLUS" -> healthStoreUrl = frameworkConfigurationService.getASIAHS_ECOM_IDPLUS_URL();
                    case "PROD" -> healthStoreUrl = frameworkConfigurationService.getASIAHS_ECOM_PROD_URL();
                }
            }
        }
        return healthStoreUrl;
    }

    public void acceptCookies() {
        DriverContext.repeatElementClickAttempt(btnAcceptAllCookies, 1);
    }

    public void navigateToViewCartPageUrl() {
        DriverContext.goToUrl(healthStoreUrl + "checkout/cart/");
        DriverContext.waitForPageToLoad();
    }

    public void selectProductType(String ProductType) {
        productCategory=ProductType;
        DriverContext.selectDropdownListElementUsingVisibleText(drpdwnProductType, ProductType);
        //DriverContext.selectDropDownListElementUsingValue(drpdwnProductType, "5621");
    }

    public void navigateToViewCartPage() {
        DriverContext.hoverOverElement(btnCartIcon);
        if (DriverContext.doesElementExist(btnViewCart)) {
            btnViewCart.WaitForClickable().Click();
        }
    }

    public void selectProductFormatFromNavigationBar() {
        DriverContext.hoverOverElement(btnProductFormat);
        btnBody.WaitForClickable().Click();
    }

    public void navigateToHomePage() {
        DriverContext.jsScrollToElementAlignBottom(Logo);
        Logo.click();
        DriverContext.waitForPageToLoad();
    }

    public void navigateToMyAccountPage() {
        DriverContext.hoverOverElement(btnAccountMenu);
        btnMyAccount.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void navigateToWishlistPage() {
        DriverContext.hoverOverElement(btnAccountMenu);
        btnMyWishlist.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void signOut() {
        if (DriverContext.doesElementExist(txtLoggedIn)) {
            DriverContext.hoverOverElement(btnAccountMenu);
            btnSignOut.WaitForClickable().Click();
        }
    }

    public void selectClinicalKeyNowFromNavigationBar() {
        DriverContext.hoverOverElement(btnProductFormat);
        btnClinicalKeyNow.WaitForClickable().Click();
    }

    public void closePromotionalPopup() {
        log.debug("Does 'Promotional Popup' element exist? " + DriverContext.doesElementExist(btnPromotionalPopUp));
        if (DriverContext.doesElementExist(btnPromotionalPopUp)) {
            DriverContext.repeatElementClickAttempt(btnPromotionalPopUp, 2);
        }
    }

    public void selectClinicalKeyFromNavigationBar(String subscription) {
        DriverContext.hoverOverElement(btnProductFormat);
        if (DriverContext.getCurrentUrl().contains("us")) {
            DriverContext.hoverOverElement(btnDigitalSubscription);
            switch (subscription) {
                case "ClinicalKey" -> btnClinicalKeyUS.WaitForClickable().Click();
                case "Clinical Pharmacology" -> btnClinicalKeyPharmacology.WaitForClickable().Click();
                case "ClinicalKey Student" -> {
                    DriverContext.goToUrl(DriverContext.getCurrentUrl() + "/clinicalkey-student");
                    ClinicalKeyProductPageActions.selectClinicalKeyStudent();
                }
            }
        } else {
            btnClinicalKey.Click();
        }
        //btnClinicalKeyStudent.WaitForClickable().Click();
    }

    public void nostoPopUp() {
        DriverContext.repeatElementClickAttempt(btnNostoPopUpClose, 1);
    }

    public void navigateToClinicalKeyNowPageInIndiaHS() {
        btnPlansAndPricing.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void selectEMCFromNavigationBar() {
        DriverContext.hoverOverElement(btnProductFormat);
        btnEMC.WaitForClickable().Click();
    }

    public void selectNewTitles(String countryName) {
        if(countryName.equals("FR")){
            btnNewTitlesFR.WaitForClickable().Click();
        }else if(countryName.equals("DE")){
            DriverContext.hoverOverElement(btnNewTitlesDE);
            btnNewInShop.WaitForClickable().Click();
        }else{
            btnNewTitles.WaitForClickable().Click();
        }
    }

    public void selectBestSellersFromMenu() {
        btnBestSellers.WaitForClickable().Click();
        Assertions.assertEquals(txtPageTitlePLP.getText(), "BESTSELLERS", "Best Sellers Page is not displayed");
    }

    public void selectAustralianTitlesFromMenu() {
        btnAustralianTitles.WaitForClickable().Click();
        Assertions.assertEquals(txtPageTitlePLP.getText(), "AUSTRALIAN TITLES", "Australian Titles Page is not displayed");
    }
}
