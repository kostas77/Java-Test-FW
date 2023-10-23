package com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.TextField;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.ECapturePageActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class B2BECapturePageLocators extends BasePage {
    @FindBy(how = How.CSS, using = ".js-iframe")
    public TextField iFrameCardNumber;

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
    public TextField txtFieldCardHolderName;

}
