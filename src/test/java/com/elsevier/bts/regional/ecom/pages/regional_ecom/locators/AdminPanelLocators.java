package com.elsevier.bts.regional.ecom.pages.regional_ecom.locators;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Button;
import com.elsevier.bts.regional.ecom.framework.controls.elements.Text;
import com.elsevier.bts.regional.ecom.framework.controls.elements.TextField;
import com.github.dockerjava.api.model.Link;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AdminPanelLocators extends BasePage {

    @FindBy(how = How.ID, using = "username")
    public TextField txtFieldAdminPanelUserName;

    @FindBy(how = How.NAME, using = "login[password]")
    public TextField txtFieldAdminPanelPassword;

    @FindBy(how = How.CSS, using = ".action-login.action-primary")
    public Button btnAdminPanelLogIn;

    @FindBy(how = How.XPATH, using = "(//*[@class='action-close' and @data-role='closeBtn'])[1]")
    public Button adminPanelPopup;

    @FindBy(how = How.ID, using = "menu-magento-sales-sales")
    public Button adminPanelSalesMenu;

    @FindBy(how = How.XPATH, using = "//*[@data-ui-id='menu-magento-sales-sales-order']")
    public Button adminPanelSalesOrder;

    @FindBy(how = How.XPATH, using = "(//*[@class='admin__control-text data-grid-search-control'])[1]")
    public TextField adminPanelSalesSearchKeyword;

    @FindBy(how = How.XPATH, using = "(//*[@class='action-submit'])[2]")
    public Button adminPanelSalesSearchKeywordSubmit;

    @FindBy(how = How.XPATH, using = "//*[@id='container']//td[2]/div")
    public Text adminPanelSalesOrderNumber;

    @FindBy(how = How.XPATH, using = "//*[@id='container']//td[9]/div")
    public Text adminPanelSalesOrderStatus;

    @FindBy(how = How.XPATH, using = "(//*[@class='action-tertiary action-clear'])[1]")
    public Button adminPanelSalesSearchReset;

    @FindBy(how = How.XPATH, using = "(//*[@class='admin__data-grid-filters-current _show'])[1]")
    public Button adminPanelSalesSearchFilterApplied;

    @FindBy(how = How.XPATH, using = "//*[@id='container']//td[8]/div")
    public Text adminPanelSalesOrderTotal;
}
