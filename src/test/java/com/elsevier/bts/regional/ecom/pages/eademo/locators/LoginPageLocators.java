package com.elsevier.bts.regional.ecom.pages.eademo.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.TextField;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class LoginPageLocators extends BasePage {

    @FindBy(how = How.NAME, using = "UserName")
    public static TextField txtUserName;

    @FindBy(how = How.NAME, using = "Password")
    public static TextField txtPassword; //TODO - Change to TextBox and test

    @FindBy(how = How.CSS, using = "[class*='btn-default']")
    public static Button btnLogin;

}
