package com.elsevier.bts.regional.ecom.pages.eademo.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class CreateEmployeePageLocators extends BasePage {

    @FindBy(how = How.ID, using = "Name")
    public static WebElement txtName;

    @FindBy(how = How.ID, using = "Salary")
    public static WebElement txtSalary;

    @FindBy(how = How.ID, using = "DurationWorked")
    public static WebElement txtDurationWorked;

    @FindBy(how = How.ID, using = "Grade")
    public static WebElement txtGrade;

    @FindBy(how = How.ID, using = "Email")
    public static WebElement txtEmail;

    @FindBy(how = How.XPATH, using = "//input[@value='Create']")
    public static WebElement btnCreateEmployee;

}
