package com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.CheckBox;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class B2BPaymentPageLocators extends BasePage {

    @FindBy(how = How.CSS, using = ".copyright")
    public WebElement footer;

    @FindBy(how = How.CSS, using = "#ecapture")
    public Button radioBtneCapture;

    @FindBy(how = How.CSS, using = "input#agreement_ecapture_1")
    public CheckBox chkboxAcceptTnC;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary checkout'])[3]")
    public Button btnProceedToPay;

    @FindBy(how = How.XPATH, using = "(.//button[@class='action primary checkout'])[2]")
    public Button btnProceedToPayJP;

    @FindBy(how = How.CSS, using = ".grand.totals>.amount .price")
    public Text txtOrderTotalPrice;


    @FindBy(how = How.XPATH, using = ".//span[@data-th='Cart Subtotal']")
    public Text txrOrderSubTotal;

}
