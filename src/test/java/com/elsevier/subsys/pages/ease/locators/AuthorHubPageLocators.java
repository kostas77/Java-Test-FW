package com.elsevier.subsys.pages.ease.locators;

import com.elsevier.subsys.framework.base.BasePage;
import com.elsevier.subsys.framework.controls.elements.Text;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


@Getter
public class AuthorHubPageLocators extends BasePage {

    @FindBy(how = How.CSS, using = ".text-block-12")
    public Text txtAuthorHubHeaderTitle;

}
