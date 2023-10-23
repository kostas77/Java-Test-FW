package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ShippingPageLocators extends BasePage {

    //General Locators
    @FindBy(how = How.NAME, using = "username")
    public TextField txtFieldEmailAddress;

    public By drpdwnTitle = By.name("prefix");

    @FindBy(how = How.NAME, using = "firstname")
    public TextField txtFieldFirstName;

    @FindBy(how = How.NAME, using = "lastname")
    public TextField txtFieldLastName;

    @FindAll({
            @FindBy(how = How.XPATH, using = "(//input[@class='input-text' and @name='street[0]'])[2]"),
            @FindBy(how = How.NAME, using = "street[0]"),
            @FindBy(how = How.XPATH, using = "(//*[@name='street[0]'])[2]")})
    public TextField txtFieldStreetAddress;

    @FindBy(how = How.NAME, using = "city")
    public TextField txtFieldCity;

    @FindBy(how = How.NAME, using = "postcode")
    public TextField txtFieldZipCode;

    public By drpdwnState = By.xpath("(//select[@class='select'])[3]");

    @FindBy(how = How.NAME, using = "telephone")
    public TextField txtFieldPhoneNumber;

    @FindBy(how = How.CSS, using = ".shipping-address-item.selected-item")
    public WebElement selectShippingAddress;

    @FindBy(how = How.CSS, using = ".action.action-select-shipping-item")
    public Button btnShipHere;

    @FindBy(how = How.CSS, using = ".button.action.continue.primary")
    public Button btnShippingContinue;

    @FindBy(how = How.CSS, using = ".action.action-show-popup")
    public Button btnAddNewAddress;

    @FindBy(how = How.CSS, using = ".close-sc.exit-sc")
    public Button btnCloseNotReadyJustYetPopup;

    @FindBy(how = How.CSS, using = ".copyright")
    public WebElement footer;

    public By drpdwnCountry = By.cssSelector("select[name=country_id]");

    public By drpdwnRegion = By.cssSelector("select[name=region_id]");

    //UK Health Store
    @FindBy(how = How.XPATH, using = "(//input[@class='input-text'])[5]")
    public TextField txtFieldCityUK;

    @FindBy(how = How.XPATH, using = "(//input[@class='input-text'])[7]")
    public TextField txtFieldZipCodeUK;

    @FindBy(how = How.XPATH, using = "(//input[@class='input-text'])[9]")
    public TextField txtFieldPhoneNumberUK;

}
