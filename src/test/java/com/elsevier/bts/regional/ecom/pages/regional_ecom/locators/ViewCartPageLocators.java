package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import com.elsevier.bts.regional.ecom.framework.controls.elements.TextField;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;


public class ViewCartPageLocators extends BasePage {

    @FindBy(how = How.CSS, using = ".input-text.qty")
    public TextField txtFieldQuantity;

    @FindBy(how = How.CSS, using = "#coupon_code")
    public TextField txtFieldOfferCode;

    @FindBy(how = How.CSS, using = ".action.apply.primary")
    public Button btnApplyOfferCode;

    @FindBy(how = How.CSS, using = ".counter-number")
    public TextField txtCartItemCount;

    @FindBy(how = How.CSS, using = "tr:nth-child(2)>th")
    public Text txtDiscountTitle;

    @FindBy(how = How.CSS, using = ".item-product-format")
    public Text txtProductCategory;

    @FindAll({
            @FindBy(how = How.CSS, using = ".imp-note"),
            @FindBy(how = How.CSS, using = ".totals.sub")})
    public Text txtTaxInfo;

    @FindBy(how = How.CSS, using = "tr.totals.sub>td>span")
    public Text txtSubTotal;

    //@FindBy(how = How.CSS, using = "tr:nth-child(2)>td>span> span")
    @FindBy(how = How.XPATH, using = "(//div[@class='cart-summary']//td//span[@class='price'])[2]")
    public Text txtDiscount;

    @FindBy(how = How.CSS, using = "tr.grand.totals>td>strong>span")
    public Text txtGrandTotal;

    @FindBy(how = How.CSS, using = ".checkout.methods.items.checkout-methods-items>li>button")
    public Button btnProceedToCheckout;

    @FindBy(how = How.CSS, using = ".close-sc.exit-sc")
    public Button btnCloseNewsletterPopup;

    @FindBy(how = How.CSS, using = ".action.action-delete")
    public List<Button> btnRemoveProductFromCartList;

    @FindBy(how = How.CSS, using = ".action.action-delete")
    public Button btnRemoveProductFromCart;

    @FindBy(how = How.CSS, using = ".cart-empty>p")
    public Text txtCartEmptyMessage;

    @FindBy(how = How.ID, using = "empty_cart_button")
    public Button btnEmptyCart;

    @FindBy(how = How.XPATH, using = "//button[@class='action-primary action-accept']")
    public Button btnOK;

}
