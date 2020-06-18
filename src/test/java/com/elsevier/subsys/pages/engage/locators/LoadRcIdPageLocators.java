package com.elsevier.subsys.pages.engage.locators;

import com.elsevier.subsys.framework.base.BasePage;
import com.elsevier.subsys.framework.controls.elements.Button;
import com.elsevier.subsys.framework.controls.elements.HyperLink;
import com.elsevier.subsys.framework.controls.elements.TextField;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Getter
public class LoadRcIdPageLocators extends BasePage {

    @FindBy(how = How.ID, using = "bdd-email")
    public TextField txtEmail;

    @FindBy(how = How.ID, using = "bdd-password")
    public TextField txtPassword;

    @FindBy(how = How.CSS, using = "[value=\"emailContinue\"]")
    public Button btnContinue;

    @FindBy(how = How.ID, using = "bdd-elsPrimaryBtn")
    public Button btnSignIn;

    @FindBy(how = How.ID, using = "bdd-staySignedIn")
    public Button btnStaySignedIn;

    @FindBy(how = How.CSS, using = ".els-shib-sign-in-link")
    public HyperLink lnkSignIn;

    @FindBy(how = How.CSS, using = "[name=\"els_institution\"]")
    public TextField txtInstitutionEmail;

    @FindBy(how = How.ID, using = "bdd-els-searchBtn")
    public Button btnInstitutionEmailContinue;

    @FindBy(how = How.ID, using = "bdd-elsPrimaryBtn")
    public Button btnRegisterContinue;

    @FindBy(how = How.ID, using = "givenNameLabel")
    public Button txtGivenName;

    @FindBy(how = How.ID, using = "bdd-familyName")
    public Button txtFamilyName;

    @FindBy(how = How.ID, using = "rememberMe")
    public Button btnStaySignIn;

    @FindBy(how = How.ID, using = "bdd-elsSecondaryBtn")
    public Button btnIAlreadyHaveAnAccount;

}
