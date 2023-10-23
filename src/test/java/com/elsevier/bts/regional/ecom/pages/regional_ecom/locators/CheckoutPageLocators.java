package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.TextField;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CheckoutPageLocators extends BasePage {

    @FindBy(how = How.CSS, using = ".action.login.primary")
    public Button btnSignIn;

    @FindBy(how = How.ID, using = "bdd-email")
    public TextField txtFieldEmail;

    @FindBy(how = How.ID, using = "bdd-elsPrimaryBtn")
    public Button btnContinue;

    @FindBy(how = How.ID, using = "bdd-password")
    public TextField txtFieldPassword;

    @FindBy(how = How.CSS, using = ".action.proceed-as-guest.primary")
    public Button btnContinueAsGuest;

}
