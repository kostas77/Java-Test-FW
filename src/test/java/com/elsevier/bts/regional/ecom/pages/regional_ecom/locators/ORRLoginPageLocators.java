package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.HyperLink;
import com.elsevier.bts.regional.ecom.framework.controls.elements.TextField;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ORRLoginPageLocators extends BasePage {

    @FindBy(how = How.NAME, using = "username")
    public TextField txtFieldORRUserName;

    @FindBy(how = How.NAME, using = "password")
    public TextField txtFieldORRPassword;

    @FindBy(how = How.CSS, using = ".btn.btn-primary")
    public Button btnORRLogIn;

    @FindBy(how = How.XPATH, using = ".//a[contains(@href,'11/summary')]")
    public HyperLink lnkUSHS_ORR;

    @FindBy(how = How.XPATH, using = ".//a[contains(@href,'13/summary')]")
    public HyperLink lnkUKHS_ORR;

    @FindBy(how = How.XPATH, using = ".//a[contains(@href,'15/summary')]")
    public HyperLink lnkEUHS_ORR;

    @FindBy(how = How.XPATH, using = ".//a[contains(@href,'16/summary')]")
    public HyperLink lnkDEHS_ORR;

    @FindBy(how = How.XPATH, using = ".//a[contains(@href,'12/summary')]")
    public HyperLink lnkFRHS_ORR;

    @FindBy(how = How.XPATH, using = ".//a[contains(@href,'4/summary')]")
    public HyperLink lnkLATAMHS_ORR;

    @FindBy(how = How.XPATH, using = ".//a[contains(@href,'3/summary')]")
    public HyperLink lnkSPHS_ORR;

    @FindBy(how = How.XPATH, using = ".//a[contains(@href,'14/summary')]")
    public HyperLink lnkMEHS_ORR;

}
