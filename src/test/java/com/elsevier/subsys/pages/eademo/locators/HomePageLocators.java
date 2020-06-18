package com.elsevier.subsys.pages.eademo.locators;

import com.elsevier.subsys.framework.base.BasePage;
import com.elsevier.subsys.framework.controls.elements.HyperLink;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class HomePageLocators extends BasePage {

    @FindBy(how = How.LINK_TEXT, using = "Login")
    public static HyperLink lnkLogin;

    @FindBy(how = How.LINK_TEXT, using = "Employee List")
    public static HyperLink lnkEmployeeList;

    @FindBy(how = How.XPATH, using = "//a[@title='Manage']")
    public static WebElement lnkUserName;

}
