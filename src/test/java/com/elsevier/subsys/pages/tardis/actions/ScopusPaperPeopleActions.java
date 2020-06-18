package com.elsevier.subsys.pages.tardis.actions;

import com.elsevier.subsys.pages.tardis.locators.ScopusPagePeopleLocators;

public class ScopusPaperPeopleActions extends ScopusPagePeopleLocators {

    public String getAuthorNameOnTheScopusAuthorDetailsPage() {
        return headingAuthorScopusPage.getText();
    }

    public String getAuthorNameOnScopusPage(){
        return txtHeadingAuthorScopusPage.GetTextValue();
    }

}
