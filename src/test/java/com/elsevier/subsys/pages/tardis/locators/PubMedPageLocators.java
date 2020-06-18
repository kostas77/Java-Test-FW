package com.elsevier.subsys.pages.tardis.locators;

import com.elsevier.subsys.framework.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PubMedPageLocators extends BasePage {

    @FindBy(how = How.CSS, using = "#full-view-heading>h1")
    public WebElement titlePubMedPaperPage;

}
