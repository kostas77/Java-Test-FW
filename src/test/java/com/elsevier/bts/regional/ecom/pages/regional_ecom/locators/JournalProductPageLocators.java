package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.CheckBox;
import com.elsevier.bts.regional.ecom.framework.controls.elements.DropDownList;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class JournalProductPageLocators extends BasePage {

    //General Locators
    public By drpdwnSubscriberStatus = By.cssSelector("#attribute225");

    public By drpdwnSubscriberCountry = By.cssSelector("#attribute224");

    public By drpdwnSubscriptionTerm = By.cssSelector("#attribute226");

    @FindBy(how = How.CSS, using = ".close-sc.exit-sc")
    public Button btnCloseFreeShippingPopup;

    @FindBy(how = How.ID, using = "product-addtocart-button")
    public Button btnAddToCart;

    //SpainHealthStore Locators
    public By drpdwnSubscriberCountrySP = By.cssSelector("#attribute224");

    public By drpdwnSubscriptionTermSP = By.cssSelector("#attribute226");

    public By drpdwnFormatType = By.xpath("(//div[@class='product-options-wrapper']//select)[3]");

    @FindBy(how = How.XPATH, using = "(.//button[@id='product-addtocart-button'])[2]")
    public Button btnAddToCartSP;

    //UK HealthStore Locators
    @FindBy(how = How.CSS, using = "#product-addtocart-button>span")
    public Button btnBuyNowUK;

    @FindBy(how = How.ID, using = "onetrust-accept-btn-handler")
    public Button btnAcceptAllCookies;
    public By drpdwnFormatFR = By.xpath("//select[contains(@class,'filter_productformat')]");

    public By drpdwnCountryFR = By.xpath("//select[contains(@class,'your_region')]");

    @FindBy(how = How.XPATH, using = "//div[text()='Particulier']")
    public Button btnParticular;

    @FindBy(how = How.XPATH, using = "//div[text()='Étudiant']")
    public Button btnStudent;

    @FindBy(how = How.XPATH, using = "//div[text()='Institution']")
    public Button btnInstitution;

    @FindBy(how = How.XPATH, using = "//div[contains(@id,'sub_length') and text()='12 mois']")
    public Button btn12Months;

    @FindBy(how = How.XPATH, using = "//div[contains(@id,'sub_length') and text()='24 mois']")
    public Button btn24Months;

    @FindBy(how = How.XPATH, using = "//div[contains(@id,'sub_length') and text()='36 mois']")
    public Button btn36Months;

    @FindBy(how = How.XPATH, using = "//div[@class='swatch-option text selected' and text()='12 mois']")
    public Button btn12MonthsSelected;

    @FindBy(how = How.XPATH, using = "//div[@class='student-checkbox']//input")
    public CheckBox chkBoxStudent;

    @FindBy(how = How.XPATH, using = "//div[text()='Personal']")
    public Button btnPersonal;

    @FindBy(how = How.XPATH, using = "//div[contains(@id,'sub_length') and text()='1 Year']")
    public Button btn1Year;

    @FindBy(how = How.XPATH, using = "//div[@class='swatch-option text selected' and text()='Personal']")
    public Button btnPersonalSelected;

    @FindBy(how = How.XPATH, using = "//div[@class='swatch-option text selected' and text()='1 Year']")
    public Button btn1YearSelected;

    public By drpdwnJournalType = By.xpath("//select[contains(@class,'journal_type')]");

    @FindBy(how = How.XPATH, using = "//div[contains(@id,'sub_length') and text()='2 Años']")
    public Button btn2YearsSP;

    @FindBy(how = How.XPATH, using = "//div[@class='swatch-option text selected' and text()='Individual']")
    public Button btnIndividualSelected;

    @FindBy(how = How.XPATH, using = "//div[text()='Individual']")
    public Button btnIndividual;

}

