package com.elsevier.bts.regional.ecom.pages.regional_ecom.actions;

import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.locators.ClinicalKeyProductPageLocators;

public class ClinicalKeyProductPageActions extends ClinicalKeyProductPageLocators {


    public static boolean clinicalKeyNowFreeTrial;

    public static boolean clinicalKeyNow,clinicalKey;

    public void addCKSubscriptionToCart(String Format, String subscriptionDuration) {
        clinicalKey=true;
        switch (Format) {
            case "Web" -> {
                switch (subscriptionDuration) {
                    case "Trial" ->{
                        DriverContext.jsScrollToElementAlignBottom(btnFifteenDayTrial);
                        btnFifteenDayTrial.WaitForClickable().Click();
                        DriverContext.waitForPageToLoad();
                    }
                    case "6 months" -> {
                        DriverContext.jsScrollToElementAlignBottom(btnWebSixMonths);
                        btnWebSixMonths.WaitForClickable().Click();
                        DriverContext.waitForPageToLoad();
                    }
                    case "12 months" -> {
                        DriverContext.jsScrollToElementAlignBottom(btnWebTwelveMonths);
                        btnWebTwelveMonths.WaitForClickable().Click();
                        DriverContext.waitForPageToLoad();
                    }
                    case "24 months" -> {
                        DriverContext.jsScrollToElementAlignBottom(btnWebTwentyFourMonths);
                        btnWebTwentyFourMonths.WaitForClickable().Click();
                        DriverContext.waitForPageToLoad();
                    }
                }
            }
            case "Web + Paper", "Web + App" -> {
                if (subscriptionDuration.equals("12 months")) {
                    DriverContext.jsScrollToElementAlignBottom(btnWPTwelveMonths);
                    btnWPTwelveMonths.WaitForClickable().Click();
                    DriverContext.waitForPageToLoad();
                } else {
                    DriverContext.jsScrollToElementAlignBottom(btnFifteenDayTrial);
                    btnFifteenDayTrial.WaitForClickable().Click();
                    DriverContext.waitForPageToLoad();
                }

            }
        }
    }

    public void addPrivateLicenseToCart(String subscriptionDuration) {
        clinicalKey=true;
        clinicalKeyNow = true;
        switch (subscriptionDuration) {
            case "1 month" -> {
                DriverContext.jsScrollToElementAlignBottom(btnOneMonthPrivateLicence);
                btnOneMonthPrivateLicence.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
            case "3 months" -> {
                DriverContext.jsScrollToElementAlignBottom(btnThreeMonthsPrivateLicence);
                btnThreeMonthsPrivateLicence.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
            case "12 months" -> {
                DriverContext.jsScrollToElementAlignBottom(btnTwelveMonthsPrivateLicence);
                btnTwelveMonthsPrivateLicence.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
            case "15 days trial" -> {
                clinicalKeyNowFreeTrial = true;
                DriverContext.jsScrollToElementAlignBottom(btnFifteenDayTrial);
                btnFifteenDayTrial.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
        }
    }

    public void addClinicalKeyPlanToCart(String subscriptionDuration) {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnFifteenDayTrialUS, 2);
        clinicalKeyNow = true;
        clinicalKey=true;
        switch (subscriptionDuration) {
            case "1 month" -> {
                DriverContext.jsScrollToElementAlignBottom(btnOneMonthPrivateLicenceUS);
                btnOneMonthPrivateLicenceUS.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
            case "12 months" -> {
                DriverContext.jsScrollToElementAlignBottom(btnTwelveMonthsPrivateLicenceUS);
                btnTwelveMonthsPrivateLicenceUS.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
            case "15 days trial" -> {
                clinicalKeyNowFreeTrial = true;
                DriverContext.jsScrollToElementAlignBottom(btnFifteenDayTrialUS);
                btnFifteenDayTrialUS.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
            case "Buy Bundle pack" -> {
                DriverContext.jsScrollToElementAlignBottom(btnBuyBundleNow);
                btnBuyBundleNow.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
            case "Buy Bundle pack Trial" -> {
                DriverContext.jsScrollToElementAlignBottom(btnBuyBundleNowTrial);
                btnBuyBundleNowTrial.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
        }
    }

    public void addClinicalKeyStudentPlanToCart(String subscriptionDuration) {
        clinicalKeyNow = true;
        switch (subscriptionDuration) {
            case "1 month" -> {
                DriverContext.jsScrollToElementAlignBottom(btnOneMonthStudentLicence);
                btnOneMonthStudentLicence.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
            case "3 months" -> {
                DriverContext.jsScrollToElementAlignBottom(btnThreeMonthStudentLicence);
                btnThreeMonthStudentLicence.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
            case "6 months" -> {
                DriverContext.jsScrollToElementAlignBottom(btnSixMonthStudentLicence);
                btnSixMonthStudentLicence.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
            case "15 days trial" -> {
                DriverContext.jsScrollToElementAlignBottom(btnFifteenDayTrialUS);
                btnFifteenDayTrialUS.WaitForClickable().Click();
                DriverContext.waitForPageToLoad();
            }
        }
    }

    public void selectClinicalSpecialty(String specialty) {
        DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement(drpdwnClinicalSpecialty), 3);
        if (specialty.equals("Try Essentials")) {
            DriverContext.jsScrollToElementAlignBottom(btnTryFifteenDay);
            DriverContext.repeatElementClickAttempt(btnTryFifteenDay, 2);
        } else if (specialty.equals("Buy Essentials")) {
            DriverContext.jsScrollToElementAlignBottom(btnBuyEssentials);
            DriverContext.repeatElementClickAttempt(btnBuyEssentials, 2);
            DriverContext.waitForPageToLoad();
        } else {
            DriverContext.jsScrollToElementAlignBottom(DriverContext.findElement(drpdwnClinicalSpecialty));
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnClinicalSpecialty, specialty);
            DriverContext.waitForPageToLoad();
        }
    }

    public static void selectClinicalKeyStudent() {
        DriverContext.jsScrollToElementAlignBottom(btnCkStudentTrialUS);
        btnCkStudentTrialUS.WaitForClickable().Click();
        DriverContext.waitForPageToLoad();
    }

    public void selectQBank(String qBank) {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnStudentStepOneFree, 2);
        switch (qBank) {
            case "Try Step 1 for free - 3 Days" -> {
                DriverContext.jsScrollToElementAlignBottom(btnStudentStepOneFree);
                DriverContext.repeatElementClickAttempt(btnStudentStepOneFree, 2);
            }
            case "Buy Step 1 Q-Bank" -> {
                DriverContext.jsScrollToElementAlignBottom(btnStudentStepOneQBank);
                DriverContext.repeatElementClickAttempt(btnStudentStepOneQBank, 2);
            }
            case "Buy Step 2 Q-Bank" -> {
                DriverContext.jsScrollToElementAlignBottom(btnStudentStepTwoQBank);
                DriverContext.repeatElementClickAttempt(btnStudentStepTwoQBank, 2);
            }
        }
    }

    public void selectSpecialty(String specialty) {
        DriverContext.repeatWaitForElementVisibilityAttempt(DriverContext.findElement(drpdwnClinicalKeyNowSpecialties), 3);
        if (specialty.equals("All")) {
            DriverContext.jsScrollToElementAlignBottom(btnSearchAllSpecialties);
            DriverContext.repeatElementClickAttempt(btnSearchAllSpecialties, 2);
        } else {
            DriverContext.selectDropdownListElementUsingVisibleText(drpdwnClinicalKeyNowSpecialties, specialty);
            DriverContext.waitForPageToLoad();
        }
    }

    public void selectCKFormat(String format) {
        DriverContext.repeatWaitForElementVisibilityAttempt(btnEMCWebPaper, 3);
        DriverContext.jsScrollToElementAlignBottom(btnEMCWebPaper);
        clinicalKeyNow = true;
        switch (format) {
            // case "Web" -> DriverContext.repeatElementClickAttempt(btnEMCWeb, 2);
            case "Web + Paper" -> DriverContext.repeatElementClickAttempt(btnEMCWebPaper, 2);
            case "Web + App" -> DriverContext.repeatElementClickAttempt(btnEMCWebApp, 2);
            // case "Web + Paper + Mobile" -> DriverContext.repeatElementClickAttempt(btnEMCWebMobilePaper, 2);
        }
    }

    public void selectCountry(String country) {
        switch (country) {
            case "France" -> DriverContext.repeatElementClickAttempt(btnFranceCK, 2);
            case "UE + Suisse" -> DriverContext.repeatElementClickAttempt(btnEU_SuisseCK, 2);
            case "Belgique" -> DriverContext.repeatElementClickAttempt(btnBeligiumCK, 2);
        }

    }

    public void selectSubscription(String Subscription) {
        switch (Subscription) {
            case "12 months" -> DriverContext.repeatElementClickAttempt(btnTwelveMonthsCK, 2);
            case "24 months" -> DriverContext.repeatElementClickAttempt(btnTwentyFourMonthsCK, 2);
            case "36 months" -> DriverContext.repeatElementClickAttempt(btnThirtySixMonthsCK, 2);
        }
    }

    public void addClinicalKeyNowLicenseToCartIN(String Subscription) {
        clinicalKey=true;
        DriverContext.repeatWaitForElementVisibilityAttempt(btnTryFifteenDayIN, 2);
        switch (Subscription) {
            case "15 Days trial" -> {
                clinicalKeyNowFreeTrial = true;
                DriverContext.repeatElementClickAttempt(btnTryFifteenDayIN, 2);
            }
            case "1 Month" -> {
                DriverContext.jsScrollToElementAlignBottom(btnOneMonthPrivateLicenceIN);
                DriverContext.repeatElementClickAttempt(btnOneMonthPrivateLicenceIN, 2);
            }
            case "3 Months" -> {
                DriverContext.jsScrollToElementAlignBottom(btnThreeMonthsPrivateLicenceIN);
                DriverContext.repeatElementClickAttempt(btnThreeMonthsPrivateLicenceIN, 2);
            }
            case "12 Months" -> {
                DriverContext.jsScrollToElementAlignBottom(btnTwelveMonthsPrivateLicenceIN);
                DriverContext.repeatElementClickAttempt(btnTwelveMonthsPrivateLicenceIN, 2);
            }
        }
    }

    public static void resetClinicalKey() {
        clinicalKeyNow = false;
    }

    public void selectClinicalKeyResidents() {
        DriverContext.repeatWaitForElementVisibilityAttempt(linkResidents, 2);
        linkResidents.WaitForClickable().Click();
    }
}
