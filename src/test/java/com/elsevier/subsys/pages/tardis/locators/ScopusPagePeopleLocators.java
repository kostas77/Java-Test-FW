package com.elsevier.subsys.pages.tardis.locators;

import lombok.Getter;
import com.elsevier.subsys.framework.base.BasePage;
import com.elsevier.subsys.framework.controls.elements.Text;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Getter
public class ScopusPagePeopleLocators extends BasePage {

    @FindBy(how = How.CSS, using = "h2.wordBreakWord")
    public WebElement headingAuthorScopusPage;

    @FindBy(how = How.CSS, using = "h2.wordBreakWord")
    public Text txtHeadingAuthorScopusPage;

}
