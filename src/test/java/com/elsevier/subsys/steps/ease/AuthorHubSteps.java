package com.elsevier.subsys.steps.ease;

import com.elsevier.subsys.framework.base.Base;
import com.elsevier.subsys.framework.base.CurrentPageContext;
import com.elsevier.subsys.framework.base.DriverContext;
import com.elsevier.subsys.framework.base.LocalDriverContext;
import com.elsevier.subsys.framework.config.FrameworkConfigurationService;
import com.elsevier.subsys.pages.ease.locators.AuthorHubPageLocators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AuthorHubSteps extends Base {

    AuthorHubPageLocators authorHubPageLocators = GetInstance(AuthorHubPageLocators.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @Given("^a user logs in to the Author Hub$")
    public void userIsLoggedInToTheAuthorHub()  {
        DriverContext.goToUrl(frameworkConfigurationService.getAuthorHubBaseUrl());
        log.debug("Navigated to URL " + frameworkConfigurationService.getAuthorHubBaseUrl());
        DriverContext.WaitUntilElementIsVisible(authorHubPageLocators.getTxtAuthorHubHeaderTitle());
    }


}
