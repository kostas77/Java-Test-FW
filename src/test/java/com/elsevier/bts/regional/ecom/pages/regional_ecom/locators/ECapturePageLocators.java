package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ECapturePageLocators extends BasePage {

    @FindBy(how = How.CSS, using = ".js-iframe")
    public TextField iFrameCardNumber;

    @FindBy(how = How.XPATH, using = ".//input[@name='virtualPaymentAddress']")
    public TextField txtFieldUPIID;

    @FindAll({
            @FindBy(how = How.ID, using = "ecapture"),
            @FindBy(how = How.CSS, using = ".payment-radio-input"),
            @FindBy(how = How.XPATH, using = "(//span[contains(@class,'adyen-checkout__payment-method')])[1]"),})
    public CheckBox creditCardCheckout;

    @FindAll({
            @FindBy(how = How.CSS, using = "input#agreement_ecapture_8"),
            @FindBy(how = How.CSS, using = "input#tandcCheckBox")})
    public CheckBox chkboxAcceptTnC;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'encryptedCardNumber')]")
    public TextField txtFieldCardNumber;

    @FindBy(how = How.CSS, using = ".adyen-checkout__card__exp-cvc > .adyen-checkout__field--expiryDate > label >.adyen-checkout__input-wrapper > .adyen-checkout__card__exp-date__input > iframe.js-iframe")
    public TextField iFrameExpiryDate;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'encryptedExpiryDate')]")
    public TextField txtFieldExpiryDate;

    @FindBy(how = How.CSS, using = ".adyen-checkout__field--securityCode > label > .adyen-checkout__input-wrapper > .adyen-checkout__card__cvc__input > iframe.js-iframe")
    public TextField iFrameCVV;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'encryptedSecurityCode')]")
    public TextField txtFieldCVV;

    @FindBy(how = How.CSS, using = ".adyen-checkout__card__holderName__input")
    public TextField iFrameCardHolderName;

    @FindBy(how = How.CSS, using = ".adyen-checkout__card__holderName__input")
    public TextField txtFieldCardHolderName;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//button[contains(@class,'adyen-checkout__button--pay')]"),
            @FindBy(how = How.CSS, using = "#cardButton > button.adyen-checkout__button--pay"),
            @FindBy(how = How.CSS, using = "button#primaryButton"),
            @FindBy(how = How.CSS, using = ".image")})
    public Button btnSubmitPayment;

    @FindBy(how = How.XPATH, using = "(//button[contains(@class,'adyen-checkout__button--pay')])[2]")
    public Button btnContinueUPIPayment;

    @FindBy(how = How.CSS, using = "#submit-pay-button")
    public Button btnSubmitPaymentANZ;

    //GermanyHealthStore Locator
    @FindBy(how = How.CSS, using = "input#cardNumber")
    public TextField txtFieldCardNumberGermany;

    @FindBy(how = How.CSS, using = "input#expiryDate")
    public TextField txtFieldExpiryDateGermany;

    @FindBy(how = How.CSS, using = "input#cvv")
    public TextField txtFieldCVVGermany;

    @FindBy(how = How.CSS, using = "input#cardholderName")
    public TextField txtFieldCardHolderNameGermany;

    //French HealthStore Locator
    @FindBy(how = How.ID, using = "input-card-number")
    public TextField txtFieldCardNumberFrance;

    public By txtFieldExpiryMonthFrance = By.id("input-expiry-month");

    public By txtFieldExpiryYearFrance = By.id("input-expiry-year");

    public By paymentCardType = By.id("input-card-type");

    @FindBy(how = How.ID, using = "input-cvv-number")
    public TextField txtFieldCVVFrance;

    @FindBy(how = How.ID, using = "input-cardholder-family-name")
    public TextField txtFieldCardHolderNameFrance;

    @FindBy(how = How.CSS, using = "input[type=submit]:nth-child(3)")
    public Button paymentAuth;

    @FindBy(how = How.CSS, using = ".btn.btn-tertiary.btn-block")
    public Button btnSelectSavedCard;


    @FindAll({
            @FindBy(how = How.ID, using = "payPalCheckoutRadio"),
            @FindBy(how = How.XPATH, using = ".//*[@class='adyen-checkout__payment-method__radio']")})
    public RadioButton payPalCheckout;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//*[@class='paypal-button-content']"),
            @FindBy(how = How.XPATH, using = "//*[contains(@class,'paypal-button')]")})
    public CheckBox payPalCheckoutButton;

    @FindBy(how = How.XPATH, using = "//*[@id='email']")
    public TextField payPalEmailAddress;

    @FindBy(how = How.XPATH, using = "//*[@id='password']")
    public TextField payPalPassword;

    @FindBy(how = How.XPATH, using = "//*[@id='btnLogin']")
    public Button payPalLogin;

    @FindBy(how = How.ID, using = "btnNext")
    public Button btnPayPalnext;

    @FindBy(how = How.XPATH, using = "//*[@id='consentButton']")
    public Button payPalPaymentConfirmation;

    @FindBy(how = How.XPATH, using = "//input[@name='customerpin']")
    public TextField txtOTP;

    @FindAll({
    @FindBy(how = How.CSS, using = "div.payment-method._active>div.payment-method-content>div.actions-toolbar>div>button"),
    @FindBy(how = How.XPATH, using = "//button[text()='Submit']")})
    public Button btnSubmitOtp;

    @FindBy(how = How.XPATH, using = "//button[contains(@class,'adyen-checkout__payment-method')]")
    public RadioButton btnCreditCard;

    @FindBy(how = How.XPATH, using = "(.//button[contains(@class,'adyen-checkout__payment-method')])[2]")
    public RadioButton btnUPI;

    @FindBy(how = How.XPATH, using = "//*[@id='payment-submit-btn']")
    public Button payPalExpressPaymentConfirmation;

    //ANZ HealthStore
    @FindBy(how = How.ID, using = "input-expiry-date")
    public TextField txtFieldExpiryDateANZ;

    @FindBy(how = How.ID, using = "input-security-code")
    public TextField txtFieldExpiryCVC;

    @FindBy(how = How.ID, using = "input-cardholder-name")
    public TextField txtFieldCardHolderNameANZ;

    @FindBy(how = How.NAME, using = "threeDSIframe")
    public TextField iFrameMasterCard;

    @FindBy(how = How.XPATH, using = "//input[@name='answer']")
    public TextField txtPasswordMasterCard;

    @FindBy(how = How.XPATH, using = "//button[@id='buttonSubmit']")
    public Button btnOKMasterCard;


}
