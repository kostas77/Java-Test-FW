package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PaymentPageLocators extends BasePage {

    //General Locators
    @FindAll({
            @FindBy(how = How.CSS, using = "input#agreement_ecapture_1"),
            @FindBy(how = How.CSS, using = "input#agreement_ecapture_3"),
            @FindBy(how = How.CSS, using = "input#agreement_ingenico_5"),
            @FindBy(how = How.CSS, using = "input#agreement_ecapture_2"),
            @FindBy(how = How.CSS, using = "input#agreement_ecapture_7"),
            @FindBy(how = How.CSS, using = "input#agreement_ecapture_8"),
            @FindBy(how = How.CSS, using = "input#agreement_ecapture_9"),
            @FindBy(how = How.ID, using = "agreement_free_1"),
            @FindBy(how = How.CSS, using = "input#tandcCheckBox")})
    public CheckBox chkboxAcceptTnC;

    @FindBy(how = How.ID, using = "ecapture")
    public CheckBox creditCardCheckout;

    @FindBy(how = How.CSS, using = ".action.action-use")
    public Button rewardPointsButton;

    @FindBy(how = How.CSS, using = ".action.primary.checkout")
    public Button btnProceedToPay;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary checkout'])[2]")
    public Button btnProceedToPayPaypalExpress;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary checkout'])[1]")
    public Button btnProceedToPayPaypalExpressAnz;

    @FindAll({
            @FindBy(how = How.CSS, using = "input#billing-address-same-as-shipping-monetico_onetime"),
            @FindBy(how = How.CSS, using = "input#billing-address-same-as-shipping-ecapture"),
            @FindBy(how = How.CSS, using = "input#billing-address-same-as-shipping-ingenico")})
    public CheckBox chkboxShippingAndBillingAddress;

    @FindBy(how = How.CSS, using = ".grand.totals>.amount .price")
    public Text txtOrderTotalPrice;

    @FindBy(how = How.XPATH, using = ".//span[@data-th='Reward points']")
    public Text rewardPointsUsed;

    @FindBy(how = How.CSS, using = ".message.message-success.success")
    public Text txtSuccessMessage;

    @FindAll({
            @FindBy(how = How.XPATH, using = ".//span[@data-th='Cart Subtotal']"),
            @FindBy(how = How.XPATH, using = ".//span[@data-th='Subtotal de la cesta']")})
    public Text txrOrderSubTotal;

    @FindBy(how = How.XPATH, using = ".//span[@data-th='Warenkorb Zwischensumme']")
    public Text txrOrderSubTotalDE;

    @FindAll({
            @FindBy(how = How.XPATH, using = ".//span[@data-th='Tax']"),
            @FindBy(how = How.XPATH, using = "//*[@data-th='Impuestos']"),
            @FindBy(how = How.XPATH, using = ".//*[@data-th='Impuestos']//span")})
    public Text txtOrderTax;

    @FindBy(how = How.CSS, using = "tr.totals-tax>td>span")
    public Text txtOrderTaxANZ;

    @FindBy(how = How.XPATH, using = ".//span[@data-th='Steuer']")
    public Text txtOrderTaxDE;

    //Billing address Locators
    public By drpdwnTitle = By.xpath(".//div[@name='billingAddressecapture.prefix']//select");

    @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressecapture.firstname']//input")
    public TextField txtFieldFirstName;

    @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressecapture.lastname']//input")
    public TextField txtFieldLastName;

    @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressecapture.street.0']//input")
    public TextField txtFieldStreetAddress;

    @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressecapture.city']//input")
    public TextField txtFieldCity;

    @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressecapture.postcode']//input")
    public TextField txtFieldZipCode;

    public By drpdwnState = By.xpath(".//div[@name='billingAddressecapture.region_id']//select");


    public By drpdwnCountry = By.xpath(".//div[@name='billingAddressecapture.country_id']//select");

    public By drpdwnCountryFR = By.xpath("(.//select[@name='country_id'])[2]");

    @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressecapture.telephone']//input")
    public TextField txtFieldPhoneNumber;

    @FindBy(how = How.CSS, using = ".action.action-update")
    public Button btnUpdateBillingAddress;

    public By drpdwnBillingAddress = By.name("billing_address_id");

    @FindBy(how = How.XPATH, using = ".//select[@name='billing_address_id']//option[last()]")
    public TextField txtAddressList;

    @FindBy(how = How.ID, using = "ecapture")
    public Button btnPaymentMethod;
    //UKHealthStore Locator
    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary checkout'])[1]")
    public Button btnProceedToPayWithCreditCardUK;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary checkout'])[last()]")
    public Button btnProceedToPayWithCreditCardLT;

    @FindBy(how = How.CSS, using = "div.payment-method-title.field.choice>label>span")
    public CheckBox checkForPaymentMethod;

    @FindBy(how = How.CSS, using = "input#ecapture")
    public CheckBox chkboxCreditCard;

    @FindBy(how = How.CSS, using = "input#cashondelivery")
    public CheckBox chkboxCOD;

    public By drpdwnBillingAddressUK = By.xpath("//div[@class='payment-method _active']//select[@name='billing_address_id']");

    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//button[@class='action action-update']")
    public Button btnUpdateBillingAddressUK;

    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//button[@class='action action-update']")
    public Button btnUpdateBillingAddressSP;

    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//select[@name='billing_address_id']//option[2]")
    public TextField txtAddressListUK;

    @FindBy(how = How.XPATH, using = ".//select[@name='billing_address_id']//option[last()]")
    public TextField txtAddressListSP;

    //FranceHealthStore Locator
    //@FindBy(how = How.CSS, using = "input#ecapture")
    @FindAll({
            @FindBy(how = How.CSS, using = "input#ecapture"),
            @FindBy(how = How.CSS, using = "input#monetico_onetime")})
    public Button btnCIC;

    @FindBy(how = How.CSS, using = "input#paypal_express")
    public Button btnPaypalExpress;

    @FindAll({
            @FindBy(how = How.CSS, using = "input#agreement_monetico_onetime_4"),
            @FindBy(how = How.CSS, using = "input#agreement_ecapture_4")})
    public Button btnCICTnC;

    @FindBy(how = How.CSS, using = "input#agreement_paypal_express_4")
    public Button btnPaypalExpressTnC;

    @FindBy(how = How.CSS, using = "input#agreement_paypal_express_7")
    public Button btnPaypalExpressTnCANZ;

    @FindBy(how = How.CSS, using = "input#agreement_paypal_express_8")
    public Button btnPaypalExpressTnCANZUat;
    @FindBy(how = How.CSS, using = "input#agreement_paypal_express_2")
    public Button btnPaypalExpressTnCSP;

    public By drpdwnTitleFR = By.xpath("//div[@class='payment-method _active']//select[@name='prefix']");

    @FindBy(how = How.XPATH, using = "(.//input[@name='firstname'])[2]")
    public TextField txtFieldFirstNameFR;

    @FindBy(how = How.XPATH, using = "(.//input[@name='lastname'])[2]")
    public TextField txtFieldLastNameFR;

    @FindAll({
            @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressecapture.street.0']//input"),
            @FindBy(how = How.NAME, using = "street[0]"),
            @FindBy(how = How.XPATH, using = "(.//input[@name='street[0]'])[2]"),
            @FindBy(how = How.CSS, using = "input#input-text.street[0]"),
            @FindBy(how = How.XPATH, using = ".//div[@name='shippingAddress.street.0']//input")})
    public TextField txtFieldStreetAddressFR;

    @FindBy(how = How.XPATH, using = "(.//input[@name='city'])[2]")
    public TextField txtFieldCityFR;

    @FindBy(how = How.XPATH, using = "(.//input[@name='postcode'])[2]")
    public TextField txtFieldZipCodeFR;

    @FindBy(how = How.XPATH, using = "(.//input[@name='telephone'])[2]")
    public TextField txtFieldPhoneNumberFR;

    public By drpdwnBillingAddressFR = By.xpath("(.//select[@name='billing_address_id'])[1]");

    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//button[@class='action action-update']")
    public Button btnUpdateBillingAddressFR;

    @FindBy(how = How.XPATH, using = ".//select[@name='billing_address_id']//option[last()]")
    public TextField txtAddressListFR;

    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//button[@class='action primary checkout']")
    public Button btnProceedToPayFR;

    //GermanyHealthStore Locator
    @FindAll({
            @FindBy(how = How.ID, using = "agreement_ingenico_5"),
            @FindBy(how = How.ID, using = "agreement_ecapture_5"),
            @FindBy(how = How.ID, using = "agreement_free_5")})
    public CheckBox chkboxAcceptTnCDEHS;

    @FindBy(how = How.CSS, using = "input#ingenico")
    public Button btnPayWithCreditCardGermany;

    @FindAll({
            @FindBy(how = How.ID, using = "agreement_ingenico_6"),
            @FindBy(how = How.ID, using = "agreement_ingenico_7"),
            @FindBy(how = How.ID, using = "agreement_free_7"),
            @FindBy(how = How.ID, using = "agreement_ecapture_7")})
    public CheckBox chkboxEndUserLicenseAgreement;

    @FindBy(how = How.CSS, using = "#agreement_ingenico_7")
    public CheckBox chkboxEndUserLicense;

    public By drpdwnBillingAddressDE = By.xpath("(.//select[@name='billing_address_id'])[last()]");

    @FindBy(how = How.XPATH, using = "(.//select[@name='billing_address_id'])[last()]//option[last()]")
    public TextField txtAddressListDE;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action action-update'])[last()]")
    public Button btnUpdateBillingAddressDE;

    @FindBy(how = How.XPATH, using = ".//input[@value='product-1']")
    public Button btnPayWithVisa;

    @FindBy(how = How.XPATH, using = ".//input[@value='product-840']")
    public Button btnPayWithPaypal;

    @FindBy(how = How.CSS, using = "div.payment-method._active>div.payment-method-content>div.actions-toolbar>div>button")
    public Button btnProceedToPayWithCCGermany;

    public By drpdwnTitleDE = By.xpath(".//div[@name='billingAddressingenico.prefix']//select");
    @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressingenico.firstname']//input")
    public TextField txtFieldFirstNameDE;

    @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressingenico.lastname']//input")
    public TextField txtFieldLastNameDE;

    @FindAll({
            @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressingenico.street.0']//input"),
            @FindBy(how = How.XPATH, using = "//div[@class='customBillingTag']//input[@name='street[0]']")})
    public TextField txtFieldStreetAddressDE;

    @FindAll({
            @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressingenico.city']//input"),
            @FindBy(how = How.XPATH, using = "//div[@class='customBillingTag']//input[@name='city']")})
    public TextField txtFieldCityDE;

    @FindAll({
            @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressingenico.postcode']//input"),
            @FindBy(how = How.XPATH, using = "//div[@class='customBillingTag']//input[@name='postcode']")})
    public TextField txtFieldZipCodeDE;

    @FindAll({
            @FindBy(how = How.XPATH, using = ".//div[@name='billingAddressingenico.telephone']//input"),
            @FindBy(how = How.XPATH, using = "//div[@class='customBillingTag']//input[@name='telephone']")})
    public TextField txtFieldPhoneNumberDE;

    @FindBy(how = How.XPATH, using = "//div[@class='billing-address-details']//span[contains(@data-bind,'Edit')]")
    public Button btnEditBillingAddress;

    @FindAll({
            @FindBy(how = How.ID, using = "billing-save-in-address-book-ecapture"),
            @FindBy(how = How.ID, using = "billing-save-in-address-book-ingenico"),
            @FindBy(how = How.ID, using = "billing-save-in-address-book-monetico_onetime"),
            @FindBy(how = How.ID, using = "billing-address-same-as-shipping-ingenico"),
            @FindBy(how = How.CSS, using = ".action-update")})
    public CheckBox chkboxSaveToAddressBook;

    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//button[@class='action primary checkout']")
    public Button btnProceedToPaySP;

    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//button[@class='action action-update']")
    public Button btnUpdateBillingAddressEU;

    @FindBy(how = How.XPATH, using = ".//select[@name='billing_address_id']//option[last()]")
    public TextField txtAddressListEU;

    //IndiaHealthStore Locator
    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//input[@name='street[0]']")
    public TextField txtFieldStreetAddressIN;

    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//input[@name='city']")
    public TextField txtFieldCityIN;


    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//input[@name='postcode']")
    public TextField txtFieldZipCodeIN;

    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//input[@name='telephone']")
    public TextField txtFieldPhoneNumberIN;

    @FindBy(how = How.XPATH, using = ".//span[@data-th='Tax']")
    public Text txtOrderTaxUS;

    @FindBy(how = How.XPATH, using = "//div[@class='payment-method _active']//select[@name='billing_address_id']")
    public DropDownList dropDownOptionBillingAddress;

}

