package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrderSuccessPageLocators extends BasePage {

    @FindAll({
            @FindBy(how = How.CSS, using = ".order-number>a"),
            @FindBy(how = How.CSS, using = ".order-number>span"),
            @FindBy(how = How.CSS, using = ".order-number>strong")})
    public Text txtOrderNumber;

    @FindBy(how = How.CSS, using = "tr.tax_amount.bg-semidark>td.amount>span")
    public Text txtOrderTax;

    @FindBy(how = How.CSS, using = ".order-number")
    public Text txtOrderNumberLatam;
    
    @FindBy(how = How.CSS, using = ".action.primary.continue")
    public Button btnContinueShopping;

}
