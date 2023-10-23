package com.elsevier.bts.regional.ecom.pages.eademo.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class EmployeeListPageLocators extends BasePage {

    @FindBy(how = How.NAME, using = "searchTerm")
    public static WebElement txtSearch;

    @FindBy(how = How.LINK_TEXT, using = "Create New")
    public static WebElement lnkCreateNew;

    @FindBy(how = How.CLASS_NAME, using = "table")
    public static WebElement tblEmployeeList;

}
