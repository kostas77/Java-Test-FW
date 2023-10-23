package com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.HyperLink;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import com.elsevier.bts.regional.ecom.framework.controls.elements.TextField;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class B2BHomepageLocators extends BasePage {

    @FindBy(how = How.CSS, using = ".logged-in")
    public Button btnAccountMenu;

    @FindBy(how = How.XPATH, using = "(.//a[contains(@href,'account')])[1]")
    public Button btnMyAccount;

    @FindBy(how = How.CSS, using = ".pagebuilder-button-primary")
    public HyperLink lnkSignIn;

    @FindBy(how = How.CSS, using = ".logged-in")
    public Text txtLoggedIn;

    @FindBy(how = How.CSS, using = ".ammenu-logo")
    public Button siteLogo;

    @FindBy(how = How.ID, using = "search_mini_form")
    public Button btnSearch;

    @FindBy(how = How.CSS, using = "#search")
    public TextField txtSearch;
}
