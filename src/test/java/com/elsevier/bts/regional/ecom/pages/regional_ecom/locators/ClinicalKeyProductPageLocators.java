package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.HyperLink;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Getter
public class ClinicalKeyProductPageLocators extends BasePage {

    @FindBy(how = How.ID, using = "product-addtocart-button")
    public Button btnAddToCart;

    @FindBy(how = How.CSS, using = ".close-sc.exit-sc")
    public Button btnCloseFreeShippingPopup;

    // US
    @FindBy(how = How.CSS, using = ".pagebuilder-banner-button.pagebuilder-button-primary")
    public Button btnTryFifteenDay;

    @FindBy(how = How.XPATH, using = "(.//a[@class='pagebuilder-button-primary'])[2]")
    public Button btnBuyEssentials;

    //To be replaced by following after RSR-6561
    //public By drpdwnClinicalSpecialty = By.cssSelector(".choose-specialty__select");
    public By drpdwnClinicalSpecialty = By.cssSelector("div:nth-child(4) > div > div > select.choose-specialty__select");

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary'])[1]")
    public Button btnFifteenDayTrialUS;

    @FindBy(how = How.XPATH, using = "(.//button[@class='pagebuilder-banner-button pagebuilder-button-primary'])[1]")
    public static Button btnCkStudentTrialUS;
    @FindBy(how = How.XPATH, using = ".//button[@class='button primary']")
    public Button btnBuyBundleNow;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary'])[1]")
    public Button btnBuyBundleNowTrial;

    @FindBy(how = How.CSS, using = "div:nth-child(2) > form > button.action")
    public Button btnOneMonthPrivateLicenceUS;

    @FindBy(how = How.CSS, using = "div.subscription-plan.subscription-plan__featured > form > button.action")
    public Button btnTwelveMonthsPrivateLicenceUS;

    @FindBy(how = How.CSS, using = ".pagebuilder-banner-button.pagebuilder-button-primary")
    public Button btnStudentStepOneFree;

    @FindBy(how = How.XPATH, using = "(.//a[@class='pagebuilder-button-primary']//span)[2]")
    public Button btnStudentStepOneQBank;

    @FindAll({
    @FindBy(how = How.XPATH, using = "(.//a[@class='pagebuilder-button-primary']//span)[3]"),
    @FindBy(how = How.XPATH, using = "(.//a[@class='pagebuilder-button-primary']//span)[1]")})
    public Button btnStudentStepTwoQBank;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary'])[4]")
    public Button btnOneMonthStudentLicence;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary'])[3]")
    public Button btnThreeMonthStudentLicence;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary'])[2]")
    public Button btnSixMonthStudentLicence;



    //DE
    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary'])[1]")
    public Button btnFifteenDayTrial;

    @FindAll({
            @FindBy(how = How.XPATH, using = "(.//button[@class='action secondary'])[1]"),
            @FindBy(how = How.XPATH, using = "(//p[text()='1 Monat']/following::button/span)[1]")})
    public Button btnOneMonthPrivateLicence;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action__subscription primary'])[3]")
    public Button btnThreeMonthsPrivateLicence;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action__subscription primary'])[4]")
    public Button btnTwelveMonthsPrivateLicence;

    //FR
    public By drpdwnClinicalKeyNowSpecialties = By.cssSelector(".ck");

    @FindBy(how = How.CSS, using = ".els-tertiary-button")
    public Button btnSearchAllSpecialties;

    @FindBy(how = How.XPATH, using = ".//*[@data-format='emc-classic']")
    public Button btnEMCWeb;

    @FindBy(how = How.CSS, using = "div:nth-child(2)>div.subscription-linked-product-details-description>a")
    public Button btnEMCWebPaper;

    @FindBy(how = How.CSS, using = "div:nth-child(1)>div.subscription-linked-product-details-description>a")
    public Button btnEMCWebApp;

    @FindBy(how = How.XPATH, using = ".//*[@data-format='emc-gold']")
    public Button btnEMCWebMobilePaper;

    @FindBy(how = How.XPATH, using = "(.//div[@class='superAttributeList']/ul/li[1])[2]")
    public Button btnFranceCK;

    @FindBy(how = How.XPATH, using = "(.//div[@class='superAttributeList']/ul/li[2])[1]")
    public Button btnEU_SuisseCK;

    @FindBy(how = How.XPATH, using = "(.//div[@class='superAttributeList']/ul/li[3])[1]")
    public Button btnBeligiumCK;

    @FindBy(how = How.XPATH, using = "(.//div[@class='superAttributeList']/ul/li[1])[2]")
    public Button btnTwelveMonthsCK;

    @FindBy(how = How.XPATH, using = "(.//div[@class='superAttributeList']/ul/li[3])[1]")
    public Button btnTwentyFourMonthsCK;

    @FindBy(how = How.XPATH, using = "(.//div[@class='superAttributeList']/ul/li[3])[1]")
    public Button btnThirtySixMonthsCK;

    @FindBy(how = How.XPATH, using = "(//button[@class='action primary'])[2]")
    public Button btnWebSixMonths;

    @FindBy(how = How.XPATH, using = "(//button[@class='action primary'])[3]")
    public Button btnWebTwelveMonths;

    @FindBy(how = How.XPATH, using = "(//button[@class='action primary'])[4]")
    public Button btnWebTwentyFourMonths;

    @FindBy(how = How.XPATH, using = "(//button[@class='action primary'])[2]")
    public Button btnWPTwelveMonths;

    //IN
    @FindBy(how = How.XPATH, using = "(//button[@class='action primary'])[1]")
    public Button btnTryFifteenDayIN;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary'])[4]")
    public Button btnTwelveMonthsPrivateLicenceIN;

    @FindBy(how = How.XPATH, using = "(//button[@class='action primary'])[3]")
    public Button btnOneMonthPrivateLicenceIN;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary'])[2]")
    public Button btnThreeMonthsPrivateLicenceIN;

    @FindBy(how = How.XPATH, using = "//a[text()='Residents Click Here']")
    public HyperLink linkResidents;

}
