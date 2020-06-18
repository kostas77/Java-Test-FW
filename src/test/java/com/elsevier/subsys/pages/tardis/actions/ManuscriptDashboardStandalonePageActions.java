package com.elsevier.subsys.pages.tardis.actions;

import com.elsevier.subsys.framework.base.CurrentPageContext;
import com.elsevier.subsys.framework.base.DriverContext;
import com.elsevier.subsys.framework.base.LocalDriverContext;
import com.elsevier.subsys.pages.tardis.locators.PubMedPageLocators;
import com.elsevier.subsys.pages.tardis.locators.ScopusPagePeopleLocators;
import com.elsevier.subsys.pages.tardis.locators.ManuscriptDashboardStandalonePageLocators;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;



@Slf4j
public class ManuscriptDashboardStandalonePageActions extends ManuscriptDashboardStandalonePageLocators {
    private ScopusPagePeopleLocators scopusPaperPeopleLocators = new ScopusPagePeopleLocators();
    private PubMedPageLocators pubMedPageLocators = new PubMedPageLocators();
    Actions actions = new Actions(LocalDriverContext.getWebDriver());
    List<WebElement> originalListOfReferences = new ArrayList<>();
    public int getNumberOfTilesDisplayed() {
        DriverContext.WaitUntilElementIsVisible(getTilesWrapper());
        return DriverContext.getElementsList(locManuscriptDashboardTiles).size();
    }

    public String getTileHeadingTitle(String tileName) {
        switch(tileName) {
            case "references":
                return txtReferencesTileHeading.GetTextValue();
            case "plagiarism":
                return txtPlagiarismTileHeading.GetTextValue();
            case "authors":
                return txtAuthorsTileHeading.GetTextValue();
            case "scope_match":
                return txtScope_matchTileHeading.GetTextValue();
            case "trending":
                return txtTrendingTileHeading.GetTextValue();
            case "originality":
                return txtOriginalityTileHeading.GetTextValue();
            default:
                return "This tile does not exist";
        }
    }
// TODO - Try stream API for the above
//    public String getTileHeadingDisplayed(String tileHeadingToTest) {
//        List<Text> textList = testTileHeadings.stream().filter(tileHeading -> tileHeading.getText().equals(tileHeadingToTest)).collect(Collectors.toList());
//        return textList.size() ;

//    }

    public boolean verifyTrendingGraphIsDisplayedInCollapsedView() {
        DriverContext.WaitUntilElementIsVisible(trendingTileGraphCollapsed);
        return trendingTileGraphCollapsed.isDisplayed();
    }

    public void clickShowDetailsForTile(String tileName) {
        switch(tileName) {
            case "references":
                btnReferencesTileShowDetails.PerformClick();
                return;
            case "authors":
                btnAuthorsTileShowDetails.PerformClick();
                return;
            case "scope_match":
                btnScopeMatchTileShowDetails.PerformClick();
                return;
            case "trending":
                btnTrendingTileShowDetails.PerformClick();
                return;
            case "originality":
                btnOriginalityTileShowDetails.PerformClick();
                return;
            default:
                System.out.println("The Show Details expand button does not exist");
                return;
        }
    }

    public boolean verifyScoreInTileIsWithinLimits(String tiles) {
//        this.score=this.getOnlyNumbersWithDecimals(driver.findElement(By.cssSelector("div#Tile-tile_"+tiles+" div.generic-Score__wrapper>div")).getText());
//        if(0<=score && score<=100){
//            return true;
//        }
        return false;

    }

    public boolean verifyAtLeastOneAuthorNameIsDisplayedInAuthorsTile() {
        if (DriverContext.getElementsList(listOfAuthorsInAuthorTile).size() == 0) {
            return false;
        }
        return true;
    }

    public boolean verifyCorrectAuthorScopusPageIsDisplayedUsingAuthorTileLink() throws InterruptedException {
        String firstAuthorName=DriverContext.getElementsList(listOfAuthorsInAuthorTileWithLinks).get(0).getText();
        DriverContext.WaitUntilElementIsVisible(DriverContext.getElementsList(listOfAuthorsInAuthorTileWithLinks).get(0));
        DriverContext.getElementsList(listOfAuthorsInAuthorTileWithLinks).get(0).click();
        Thread.sleep(5000);
        DriverContext.switchToNewTab();
        String scopusPageTitle = DriverContext.getPageTitle();
        CurrentPageContext.setCurrentPage(GetInstance(ScopusPaperPeopleActions.class));
        String[] lastNameOfFirstAuthor=firstAuthorName.split(",");
        if(scopusPageTitle.contains(lastNameOfFirstAuthor[0])){
            return true;
        }else{
            return false;
        }
    }

    public boolean verifyAuthorsNameIsDisplayedInAuthorsTileDetails() {
        if (DriverContext.getElementsList(listOfAuthorsInAuthorTileDetails).size() == 0) {
            return false;
        }
        return true;
    }

    public boolean verifyCorrectAuthorScopusPageIsDisplayedUsingAuthorDetailsLink() throws InterruptedException {
        String firstAuthorName=DriverContext.getElementsList(listOfAuthorsInAuthorTileDetailsWithLinks).get(0).getText();
        DriverContext.WaitUntilElementIsVisible(DriverContext.getElementsList(listOfAuthorsInAuthorTileDetailsWithLinks).get(0));
        DriverContext.getElementsList(listOfAuthorsInAuthorTileDetailsWithLinks).get(0).click();
        Thread.sleep(5000);
        DriverContext.switchToNewTab();
        String scopusPageTitle = DriverContext.getPageTitle();
        CurrentPageContext.setCurrentPage(GetInstance(ScopusPaperPeopleActions.class));
        log.debug("The Scopus page heading is: -"+CurrentPageContext.getCurrentPage().As(ScopusPaperPeopleActions.class).getAuthorNameOnTheScopusAuthorDetailsPage());
        String[] lastNameOfFirstAuthor=firstAuthorName.split(",");
        if(scopusPageTitle.contains(lastNameOfFirstAuthor[0])){
            return true;
        }else{
            return false;
        }
    }

    public String getHeadingOfTheTableUnderReferencesDetails(int i) {
        return DriverContext.getElementsList(referencesTileDetailsTitleContainer).get(i).getText();
    }

    public int getNumberOfReferencesDisplayedInTheTilePreview(String typeOfReference) {
        System.out.println(typeOfReference);
        switch(typeOfReference) {
            case "Number of references":
                System.out.println(numberOfReferencesInTilePreview.getText());
                return Integer.parseInt(numberOfReferencesInTilePreview.getText());
            case "References older than 5 years":
                System.out.println(lessThan5YearsReferencesInTilePreview.getText());
                return Integer.parseInt(lessThan5YearsReferencesInTilePreview.getText());
            default:
                System.out.println("None of the other references are displayed on tile preview ");
                return -1;
        }
    }

    public int getNumberOfReferencesDisplayedInTheTileDetail(String typeOfReference) {
        switch(typeOfReference) {
            case "Number of references":
                System.out.println(numberOfReferencesInTileDetail.getText());
                return Integer.parseInt(numberOfReferencesInTileDetail.getText());
            case "References older than 5 years":
                System.out.println(lessThan5YearsReferencesInTileDetail.getText());
                return Integer.parseInt(lessThan5YearsReferencesInTileDetail.getText());
            default:
                System.out.println("None of the other references are displayed on tile preview ");
                return -1;
        }
    }

    public void selectTheSpecifiedOptionFromTheSortByDropDown(String option) {
        switch (option){
            case "Newest":
                btnReferencesDetailsSortByList.PerformClick();
                actions.sendKeys(Keys.TAB).build().perform();
                actions.sendKeys(Keys.TAB).build().perform();
                actions.sendKeys(Keys.ENTER).build().perform();
                break;
            case "References":
                originalListOfReferences=DriverContext.getElementsList(listOfReferencesTileDetailsPaperTitle);
                btnReferencesDetailsSortByList.PerformClick();
                actions.sendKeys(Keys.TAB).build().perform();
                actions.sendKeys(Keys.ENTER).build().perform();
                break;
        }


    }


    public boolean verifyPubMedLinkIsCorrect() throws InterruptedException {
        String titleOnTheRefDetails=CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).titleOfTheFirstPaperWithPubMedLink.getText();
        CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).firstPubMedLink.click();
        Thread.sleep(5000);
        DriverContext.switchToNewTab();
        CurrentPageContext.setCurrentPage(GetInstance(PubMedPageActions.class));
        String titleOnThePubMedPage = CurrentPageContext.getCurrentPage().As(PubMedPageActions.class).titlePubMedPaperPage.getText();
        titleOnThePubMedPage=titleOnThePubMedPage.replace(".", "");
        if(titleOnThePubMedPage.toLowerCase().contains(titleOnTheRefDetails.toLowerCase())){
            return true;
        }
        return false;
    }

    public boolean verifyReferencesAreSortedBy(String option) {
        boolean results=true;
        switch (option){
            case "Newest":
                for(int i=0;i<(DriverContext.getElementsList(listOfReferencesTileDetailsYears).size()-1);i++){
                    int x=Integer.parseInt((DriverContext.getElementsList(listOfReferencesTileDetailsYears).get(i).getText()).replaceAll("[^0-9\\.]", ""));
                    int y=Integer.parseInt((DriverContext.getElementsList(listOfReferencesTileDetailsYears).get(i+1).getText()).replaceAll("[^0-9\\.]", ""));
                    if(x<y){
                        results=false;
                    }
                }
                return results;
            case "References":
                for(int i=0;i<(DriverContext.getElementsList(listOfReferencesTileDetailsPaperTitle).size()-1);i++){
                    if(!originalListOfReferences.get(i).getText().equals(DriverContext.getElementsList(listOfReferencesTileDetailsPaperTitle).get(i).getText())){
                        return false;
                    }
                }
        }
        return true;
    }
}
