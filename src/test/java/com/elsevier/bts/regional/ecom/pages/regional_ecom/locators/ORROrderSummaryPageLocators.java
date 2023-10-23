package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ORROrderSummaryPageLocators extends BasePage {

    @FindBy(how = How.ID, using = "orderStatus")
    public Text txtOrderStatus;

    @FindBy(how = How.ID, using = "orderNumber")
    public Text txtOrderNumber;

    @FindBy(how = How.ID, using = "orderTotal")
    public Text txtOrderTotal;

    @FindBy(how = How.ID, using = "orderCustomerEmail")
    public Text txtCustomerEmail;

    @FindBy(how = How.ID, using = "orderNumItems")
    public Text txtOrderItems;

    @FindBy(how = How.ID, using = "paymentTotalGross")
    public Text txtOrderGrossTotal;

    @FindBy(how = How.ID, using = "paymentTotalDiscount")
    public Text txtOrderDiscountTotal;

    @FindBy(how = How.ID, using = "paymentTotalTax")
    public Text txtOrderTaxTotal;

    @FindBy(how = How.ID, using = "paymentCardToken")
    public Text txtPaymentCardToken;

    @FindBy(how = How.CSS, using = ".large-9 p")
    public Text txtFooterCopyright;

}
