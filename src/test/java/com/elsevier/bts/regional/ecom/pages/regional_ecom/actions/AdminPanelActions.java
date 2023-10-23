package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.AdminPanelLocators;
import org.testng.Assert;

public class AdminPanelActions extends AdminPanelLocators {

    public void adminPanelLoginIn(String username, String password) {
        DriverContext.waitForPageToLoad();
        txtFieldAdminPanelUserName.EnterText(username);
        txtFieldAdminPanelPassword.EnterText(password);
        btnAdminPanelLogIn.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void verifyOrderInMagento() {
        DriverContext.driverSleep(8000);
        DriverContext.repeatWaitForElementVisibilityAttempt(adminPanelSalesMenu, 2);
        adminPanelSalesMenu.waitAndPerformClick();
//        DriverContext.repeatWaitForElementVisibilityAttempt(adminPanelSalesOrder,2);
        adminPanelSalesOrder.waitAndPerformClick();
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(5000);
        DriverContext.repeatWaitForElementVisibilityAttempt(adminPanelSalesSearchKeyword, 2);
        if (DriverContext.doesElementExist(adminPanelSalesSearchFilterApplied)) {
            adminPanelSalesSearchReset.waitAndPerformClick();
            DriverContext.waitForPageToLoad();
            DriverContext.driverSleep(5000);
        }
        adminPanelSalesSearchKeyword.EnterText(OrderSuccessPageActions.orderNumber);
        DriverContext.repeatWaitForElementVisibilityAttempt(adminPanelSalesSearchKeywordSubmit, 2);
        DriverContext.driverSleep(3000);
        adminPanelSalesSearchKeywordSubmit.click();
        DriverContext.waitForPageToLoad();
        DriverContext.driverSleep(5000);

    }
}
