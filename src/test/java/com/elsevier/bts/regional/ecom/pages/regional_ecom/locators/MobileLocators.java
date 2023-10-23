package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import com.elsevier.bts.regional.ecom.framework.controls.elements.TextField;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MobileLocators extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[@class='account-menu login']")
    public Button mobileUserIcon;

    @FindBy(how = How.XPATH, using = "//*[@id='search']")
    public Button mobileTextFieldBox;

    @FindBy(how = How.XPATH, using = "//*[@id='search_mini_form']")
    public Button mobileSearchIcon;

    @FindBy(how = How.XPATH, using = "//*[@id='search']")
    public TextField mobileTextFieldSearch;

    @FindBy(how = How.CSS, using = ".action.search")
    public Button mobileBtnSearch;

    @FindBy(how = How.XPATH, using = "//*[@class='addCartbtn']")
    public Button mobileBtnAddToCart;

    @FindBy(how = How.XPATH, using = "(//*[@class='hm-cart'])[1]")
    public Button mobileBtnAddToCartConfirm;

    @FindBy(how = How.XPATH, using = "(//*[@class='hm-cart'])[2]")
    public Button mobileBtnAddToCartConfirmForEbook;
    @FindBy(how = How.XPATH, using = "//*[@class='estimated-price']")
    public Text mobileTxtOrderTotalPrice;

    @FindBy(how = How.XPATH, using = "//*[@class='ammenu-menu-toggle -hamburger']")
    public Button mobileHamburgerMenu;

    @FindBy(how = How.XPATH, using = "(//a[normalize-space()='Product Format']//following::span[text()='eBooks'])[2]")
    public Button mobileProductFormatMenuEbook;
}
