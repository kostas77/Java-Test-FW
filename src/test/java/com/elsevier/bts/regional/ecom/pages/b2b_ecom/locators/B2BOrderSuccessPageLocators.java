package com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class B2BOrderSuccessPageLocators extends BasePage {

    @FindBy(how = How.CSS, using = ".order-number")
    public Text txtOrderNumber;

    @FindBy(how = How.CSS, using = " .action.primary.continue")
    public Button btnContinueShopping;

}
