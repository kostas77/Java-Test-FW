package com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class B2BMyAccountPageLocators extends BasePage {

    @FindBy(how = How.CSS, using = "select#prefix")
    public DropDownList drpdwnTitle;

    @FindBy(how = How.XPATH, using = "(.//a[contains(@href,'customer/address')])[3]")
    public Button btnAddressBook;

    @FindBy(how = How.XPATH, using = "(.//a[contains(@href,'address/edit')])[1]")
    public Button btnChangeBillingAddress;

    @FindBy(how = How.CSS, using = ".product-item-info")
    public HyperLink lnkProduct;

    @FindBy(how = How.CSS, using = ".btn-remove.action.delete")
    public Button btnDeleteFromWishlist;

    @FindBy(how = How.CSS, using = ".action.tocart.primary")
    public Button btnAddToCart;

    @FindBy(how = How.XPATH, using = "(.//a[contains(@href,'address/edit')])[2]")
    public Button btnChangeShippingAddress;

    @FindBy(how = How.CSS, using = ".action.primary.add")
    public Button btnAddNewAddress;

    @FindBy(how = How.XPATH, using = "(.//a[contains(@href,'address/edit')])[3]")
    public Button btnEditNewAddress;

    @FindBy(how = How.CSS, using = ".action.delete")
    public Button btnDeleteNewAddress;

    @FindBy(how = How.CSS, using = ".action-primary.action-accept")
    public Button btnAcceptDeleteNewAddress;

    //Edit Shipping & Billing address Locators

    @FindBy(how = How.CSS, using = "select#prefix")
    public DropDownList drpdwnAddressTitle;

    @FindBy(how = How.CSS, using = "input#firstname")
    public TextField txtFieldFirstName;

    @FindBy(how = How.CSS, using = "input#lastname")
    public TextField txtFieldLastName;

    @FindBy(how = How.CSS, using = "input#street_1")
    public TextField txtFieldStreetAddress;

    @FindBy(how = How.CSS, using = "input#city")
    public TextField txtFieldCity;

    @FindBy(how = How.CSS, using = "input#zip")
    public TextField txtFieldZipCode;

    public By drpdwnState = By.cssSelector("select#region_id");

    public By drpdwnCountry = By.cssSelector("select#country");

    @FindBy(how = How.CSS, using = "input#telephone")
    public TextField txtFieldPhoneNumber;

    @FindBy(how = How.CSS, using = ".action.submit.primary")
    public Button btnSaveAddress;

    @FindAll({
            @FindBy(how = How.CSS, using = ".message-success.success.message"),
            @FindBy(how = How.CSS, using = ".message-error.error.message"),
            @FindBy(how = How.CSS, using = ".message-notice.notice.message")})
    public Text txtSuccessMessage;

}
