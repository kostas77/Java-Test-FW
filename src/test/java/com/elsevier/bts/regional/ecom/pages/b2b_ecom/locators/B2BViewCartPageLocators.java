package com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import com.elsevier.bts.regional.ecom.framework.controls.elements.TextField;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class B2BViewCartPageLocators extends BasePage {
    @FindBy(how = How.CSS, using = ".checkout.methods.items.checkout-methods-items>li>button")
    public Button btnProceedToCheckout;

    @FindBy(how = How.XPATH, using = ".//input[@class='input-text qty']")
    public TextField txtCartItemCount;

    @FindBy(how = How.CSS, using = "tr.totals.sub>td>span")
    public Text txtSubTotal;

}
