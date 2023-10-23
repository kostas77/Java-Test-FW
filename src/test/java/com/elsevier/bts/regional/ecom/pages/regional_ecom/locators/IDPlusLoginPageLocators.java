package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.*;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Getter
public class IDPlusLoginPageLocators extends BasePage {

    @FindBy(how = How.ID, using = "bdd-email")
    public TextField txtFieldEmail;

    @FindBy(how = How.ID, using = "bdd-elsPrimaryBtn")
    public Button btnContinue;

    @FindBy(how = How.ID, using = "bdd-password")
    public TextField txtFieldPassword;

    @FindBy(how = How.ID, using = "bdd-givenName")
    public TextField txtFieldGivenName;

    @FindBy(how = How.ID, using = "bdd-familyName")
    public TextField txtFieldFamilyName;

    @FindAll({
    @FindBy(how = How.CSS, using = ".els-footer"),
    @FindBy(how = How.CSS, using = ".page-footer")})
    public WebElement Footer;



}
