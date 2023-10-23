package com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class B2BProductPageLocators extends BasePage {

    @FindAll({
            @FindBy(how = How.CSS, using = "#product-addtocart-button"),
            @FindBy(how = How.XPATH, using = ".//button[@class='action primary']")})
    public Button btnAddToCart;

    @FindBy(how = How.CSS, using = ".action.showcart")
    public Button btnCartIcon;

    @FindBy(how = How.CSS, using = ".action.viewcart")
    public Button btnViewCart;

    @FindAll({
            @FindBy(how = How.CSS, using = ".message-success.success.message"),
            @FindBy(how = How.CSS, using = ".message-error.error.message"),
            @FindBy(how = How.CSS, using = ".message-notice.notice.message")})
    public Text txtSuccessMessage;

}
