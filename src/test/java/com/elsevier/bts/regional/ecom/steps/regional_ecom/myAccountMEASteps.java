package com.elsevier.bts.regional.ecom.steps.regional_ecom;

import com.elsevier.bts.regional.ecom.framework.base.BasePage;
import com.elsevier.bts.regional.ecom.framework.base.DriverContext;
import com.elsevier.bts.regional.ecom.framework.config.FrameworkConfigurationService;
import com.elsevier.bts.regional.ecom.pages.regional_ecom.actions.MyAccountActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
public class myAccountMEASteps extends BasePage {

    MyAccountActions myAccountActions = GetInstance(MyAccountActions.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @And("I edit the default billing address in MEA-HS")
    public void i_edit_the_default_billing_address(DataTable table) {
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.changeBillingAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the default shipping address in MEA-HS")
    public void i_edit_the_default_shipping_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.changeShippingAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I add new address in MEA-HS")
    public void i_add_new_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.addNewAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

    @And("I edit the newly added address in MEA-HS")
    public void i_edit_the_newly_added_address(DataTable table) {
        DriverContext.driverSleep(10000); // TODO: Replace in the future with a suitable explicit wait
        List<Map<String, String>> address = table.asMaps(String.class, String.class);
        myAccountActions.editNewAddressEU(address);
        DriverContext.waitForPageToLoad();
        DriverContext.waitUntilElementIsVisible(myAccountActions.txtSuccessMessage);
        Assertions.assertEquals("You saved the address.", myAccountActions.txtSuccessMessage.GetTextValue());
    }

}
