package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.HyperLink;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchPageLocators extends BasePage {

    @FindBy(how = How.CSS, using = "li.item.search")
    public Text searchResults;

    @FindBy(how = How.CSS, using = ".product-item-link:first-of-type")
    public Text txtProductName;

    @FindAll({
            @FindBy(how = How.CSS, using = "div.search.results a.product-item-link:first-of-type"),
            @FindBy(how = How.CSS, using = "div.products.wrapper.grid.products-grid > ol > li:nth-child(1) > div > div > strong > a.product-item-link:first-of-type")})
    public HyperLink lnkProduct;

    @FindAll({
            @FindBy(how = How.CSS, using = "div.search.results .action.towishlist"),
            @FindBy(how = How.CSS, using = ".action.towishlist")})
    public Button btnWishlist;

    @FindBy(how = How.CSS, using = ".price-final_price.tax.weee .price")
    public Text txtPrice;

    @FindBy(how = How.CSS, using = ".base")
    public Text txtProductFormat;

    @FindBy(how = How.CSS, using = ".action.tocart.primary")
    public Button btnAddToCart;

    @FindBy(how = How.CSS, using = ".counter.qty span.counter-number")
    public Text txtCartQty;

    @FindAll({
            @FindBy(how = How.XPATH, using = "(//a[normalize-space()='Book']//following::a[@class='product-item-link'])[1]"),
            @FindBy(how = How.XPATH, using = "(//a[normalize-space()='Buch']//following::a[@class='product-item-link'])[1]")})
    public HyperLink linkBookProductName;

}
