package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.CheckBox;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import com.elsevier.bts.regional.ecom.framework.controls.elements.TextField;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPageLocators extends BasePage {
    @FindAll({
            @FindBy(how = How.ID, using = "product-addtocart-button"),
            @FindBy(how = How.CSS, using = ".action.tocart.primary")})
    public Button btnAddToCart;

    @FindBy(how = How.CSS, using = ".action.showcart")
    public Button btnCartIcon;

    @FindBy(how = How.CSS, using = ".action.viewcart")
    public Button btnViewCart;

    @FindBy(how = How.CSS, using = "span.counter.qty:nth-of-type(2)")
    public Text txtCartCount1;

    @FindBy(how = How.CSS, using = "span.counter-number")
    public Text txtCartCount2;

    @FindBy(how = How.CSS, using = ".close-sc.exit-sc")
    public Button btnCloseFreeShippingPopup;

    @FindAll({
            @FindBy(how = How.CSS, using = ".ebook-vir.a"),
            @FindBy(how = How.CSS, using = ".ebook-vir.s"),
            @FindBy(how = How.CSS, using = ".ebook-vir"),
            @FindBy(how = How.CSS, using = ".print-ebook")
    })
    public CheckBox chkboxEBook;

    @FindBy(how = How.CSS, using = ".action.towishlist")
    public Button btnWishlist;

    @FindAll({
            @FindBy(how = How.CSS, using = ".message-success.success.message"),
            @FindBy(how = How.CSS, using = ".message-error.error.message"),
            @FindBy(how = How.CSS, using = ".message-notice.notice.message")})
    public Text txtSuccessMessage;

    //Review locators
    @FindBy(how = How.ID, using = "tab-label-reviews-title")
    public Button btnReviews;

    @FindAll({
            @FindBy(how = How.ID, using = "rating_5_label"),
            @FindBy(how = How.ID, using = "Evaluation_5_label")})
    public Button btnStarRating;

    @FindBy(how = How.ID, using = "nickname_field")
    public TextField txtfldNickName;

    @FindBy(how = How.ID, using = "summary_field")
    public TextField txtfldSummary;

    @FindBy(how = How.ID, using = "review_field")
    public TextField txtfldReview;

    @FindBy(how = How.CSS, using = ".action.submit.primary")
    public Button btnSubmitReview;

    @FindBy(how = How.CSS, using = ".message-success.success.message>div")
    public Text txtReviewSuccessMessage;

    @FindBy(how = How.CSS, using = ".counter-number")
    public TextField txtCartItemCount;

    @FindBy(how = How.CSS, using = "tr.grand.totals>td>strong>span")
    public Text textGrandTotal;

    @FindBy(how = How.CSS, using = "tr.totals.shipping.excl>td.amount>span")
    public Text textShippingCharge;

    @FindBy(how = How.XPATH, using = "(//p[@class='subscription-plan-license'])[1]")
    public Text txtLocationRestriction;

    @FindBy(how = How.XPATH, using = "(//div[@class='product attribute ']//div[contains(@class,'static-block')])[1]//p[1]")
    public Text txtCountriesList;

    @FindBy(how = How.XPATH, using = "(//div[@class='product attribute ']//div[contains(@class,'static-block')])[1]//p[2]")
    public Text txtContactSalesDepartment;

    @FindBy(how = How.XPATH, using = "//dl[@class='item-options']//dt[contains(text(), 'Format')]/following-sibling::dd[1]")
    public Text txtFormatFR;

    @FindBy(how = How.XPATH, using = "//dl[@class='item-options']//dt[contains(text(), 'Statut')]/following-sibling::dd[1]")
    public Text txtStatusFR;

    @FindBy(how = How.XPATH, using = "//dl[@class='item-options']//dt[contains(text(), 'Pays')]/following-sibling::dd[1]")
    public Text txtCountryFR;

    @FindBy(how = How.XPATH, using = "//dl[@class='item-options']//dt[contains(text(), 'Durée')]/following-sibling::dd[1]")
    public Text txtDurationFR;

}
