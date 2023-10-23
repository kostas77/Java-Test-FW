package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.ORRLoginPageLocators;

public class ORRLoginPageActions extends ORRLoginPageLocators {

    public void ORRLoginIn(String username, String password) {
        DriverContext.waitForPageToLoad();
        txtFieldORRUserName.EnterText(username);
        txtFieldORRPassword.EnterText(password);
        btnORRLogIn.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void navigateToORRHealthStoreSummaryPage(String orr_HS) {
        switch (orr_HS) {
            case "USHS":
                DriverContext.jsScrollToElementAlignBottom(lnkUSHS_ORR);
                lnkUSHS_ORR.Click();
                break;
            case "UKHS":
                DriverContext.jsScrollToElementAlignBottom(lnkUKHS_ORR);
                lnkUKHS_ORR.Click();
                break;
            case "EUHS":
                DriverContext.jsScrollToElementAlignBottom(lnkEUHS_ORR);
                lnkEUHS_ORR.Click();
                break;
            case "DEHS":
                DriverContext.jsScrollToElementAlignBottom(lnkDEHS_ORR);
                lnkDEHS_ORR.Click();
                break;
            case "FRHS":
                DriverContext.jsScrollToElementAlignBottom(lnkFRHS_ORR);
                lnkDEHS_ORR.Click();
                break;
            case "LATAMHS":
                DriverContext.jsScrollToElementAlignBottom(lnkLATAMHS_ORR);
                lnkLATAMHS_ORR.Click();
                break;
            case "MEHS":
                DriverContext.jsScrollToElementAlignBottom(lnkMEHS_ORR);
                lnkMEHS_ORR.Click();
                break;
            case "SPHS":
                DriverContext.jsScrollToElementAlignBottom(lnkSPHS_ORR);
                lnkSPHS_ORR.Click();
                break;
        }
        DriverContext.waitForPageToLoad();
    }
}
