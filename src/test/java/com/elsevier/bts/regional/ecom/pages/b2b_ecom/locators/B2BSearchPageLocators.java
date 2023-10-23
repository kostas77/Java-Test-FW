package com.elsevier.bts.regional.ecom.pages.b2b_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.HyperLink;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class B2BSearchPageLocators extends BasePage {

    @FindAll({
            @FindBy(how = How.CSS, using = ".price-final_price.tax.weee .price"),
            @FindBy(how = How.CSS, using = "div.price-box.price-final_price>span>span")})
    public Text txtPrice;

    @FindBy(how = How.CSS, using = "div.search.results a.product-item-link:first-of-type")
    public HyperLink lnkProduct;

}
