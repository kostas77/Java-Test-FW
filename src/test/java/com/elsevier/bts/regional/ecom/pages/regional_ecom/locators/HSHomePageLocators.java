package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.*;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Getter
public class HSHomePageLocators extends BasePage {

    @FindBy(how = How.CSS, using = ".flex-header")
    public WebElement header;

    @FindBy(how = How.ID, using = "onetrust-accept-btn-handler")
    public Button btnAcceptAllCookies;

    @FindBy(how = How.CSS, using = "input#search")
    public TextField txtFieldSearch;

    @FindBy(how = How.CSS, using = ".action.search")
    public Button btnSearch;

//    @FindAll({
//            @FindBy(how = How.XPATH, using = "//select[@id='product_type']"),
//            @FindBy(how = How.CSS, using = ".product_type"),
//            @FindBy(how = How.ID, using = "product_type")})
//    public DropDownList drpdwnProductType;
    public By drpdwnProductType = By.cssSelector(".product_type");

    @FindBy(how = How.CSS, using = ".authorization-link")
    public HyperLink lnkSignIn;

    @FindBy(how = How.CSS, using = ".register-link")
    public HyperLink createAnAccount;

    @FindBy(how = How.XPATH, using = "//*[@class='account-menu login']")
    public HyperLink mobileUserIcon;

    @FindBy(how = How.CSS, using = ".logo:first-of-type")
    public WebElement Logo;

    @FindBy(how = How.CSS, using = ".action.showcart")
    public Button btnCartIcon;

    @FindBy(how = How.CSS, using = ".action.viewcart")
    public Button btnViewCart;

    @FindBy(how = How.CSS, using = "span.counter-number")
    public Text txtCartCount;

    @FindBy(how = How.CSS, using = ".logged-in")
    public Button btnAccountMenu;

    @FindBy(how = How.XPATH, using = "(.//a[contains(@href,'account')])[1]")
    public Button btnMyAccount;

    @FindBy(how = How.XPATH, using = "(.//a[contains(@href,'wishlist')])[1])")
    public Button btnMyWishlist;

    @FindBy(how = How.CSS, using = ".logged-in")
    public Text txtLoggedIn;

    @FindBy(how = How.XPATH, using = "(.//a[contains(@href,'logout')])[1]")
    public Button btnSignOut;

    @FindBy(how = How.CSS, using = ".header.content>section>nav>ul> li:nth-child(2)")
    public Button btnProductFormat;

    @FindBy(how = How.CSS, using = ".header.content>section>nav>ul> li:nth-child(2) li:nth-child(1) > a > span")
    public Button btnBody;

    @FindAll({
            @FindBy(how = How.XPATH, using = "(.//span[@class='ammenu-wrapper' and contains(text(),'ClinicalKey Now')])[1]"),
            @FindBy(how = How.XPATH, using = "//a[contains(@href,'/clinicalkey-now-fr')]"),
            @FindBy(how = How.XPATH, using = "//a[contains(@href,'clinicalkeynow.html')]"),
            @FindBy(how = How.XPATH, using = "//a[contains(@href,'clinicalkey-now-deutsch-privatlizenz.html')]")})
    public Button btnClinicalKeyNow;

    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Product Format']//following::span[text()='ClinicalKey']")
    public Button btnClinicalKeyUS;

    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Product Format']//following::span[text()='Clinical Pharmacology']")
    public Button btnClinicalKeyPharmacology;

    @FindBy(how = How.XPATH, using = "(//ul/li//span[text()='ClinicalKey Student'])[2]")
    public Button btnClinicalKeyStudent;

    @FindBy(how = How.CSS, using = ".close-sc.exit-sc")
    public Button btnPromotionalPopUp;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'NostoOverlayClose')]")
    public Button btnNostoPopUpClose;

    @FindBy(how = How.XPATH, using = "//button[text()='Plans & Pricing']")
    public Button btnPlansAndPricing;

    @FindBy(how = How.XPATH, using = "(//ul//li//a[normalize-space()='Digital Subscriptions'])[1]")
    public Button btnDigitalSubscription;

    @FindBy(how = How.XPATH, using = "(.//a[contains(@href,'clinicalkey')])[1]//span")
    public Button btnClinicalKey;

    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Produits']//following::span[normalize-space()='EMC']")
    public Button btnEMC;

    @FindBy(how = How.XPATH, using = "(//a[@title='Nouveautés'])[1]")
    public Button btnNewTitlesFR;

    @FindBy(how = How.XPATH, using = "(//a[@title='New Titles'])[1]")
    public Button btnNewTitles;

    @FindBy(how = How.XPATH, using = "(//a[@title='NEUERSCHEINUNGEN'])[1]")
    public Button btnNewTitlesDE;

    @FindBy(how = How.XPATH, using = "(//span[text()='Neu im Shop'])[1]")
    public Button btnNewInShop;

    @FindBy(how = How.XPATH, using = "(//a[@title='Bestsellers'])[1]")
    public Button btnBestSellers;

    @FindBy(how = How.XPATH, using = "(//a[@title='Australian Titles'])[2]")
    public Button btnAustralianTitles;

    @FindBy(how = How.XPATH, using = "//h1[@class='page-title']//span")
    public Text txtPageTitlePLP;
}
