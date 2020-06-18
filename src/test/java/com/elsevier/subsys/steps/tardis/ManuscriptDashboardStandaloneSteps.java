package com.elsevier.subsys.steps.tardis;

import com.elsevier.subsys.framework.base.Base;
import com.elsevier.subsys.framework.base.CurrentPageContext;
import com.elsevier.subsys.framework.base.DriverContext;
import com.elsevier.subsys.framework.config.FrameworkConfigurationService;
import com.elsevier.subsys.pages.tardis.locators.ManuscriptDashboardStandalonePageLocators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import com.elsevier.subsys.pages.tardis.actions.ManuscriptDashboardStandalonePageActions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ListIterator;

@Slf4j
public class ManuscriptDashboardStandaloneSteps extends Base {

    ManuscriptDashboardStandalonePageLocators manuscriptDashboardStandalonePageLocators = GetInstance(ManuscriptDashboardStandalonePageLocators.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @Given("the user visits the Manuscript Dashboard page: (.*)?$")
    public void theUserVisitsTheManuscriptDashboardPage(String md_Url) {
        String mdUrl = "";
        switch (md_Url) {
            case "default": {
                mdUrl = frameworkConfigurationService.getManuscriptDashboardBaseUrl();
                break;
            }
            case "alternate": {
                mdUrl = frameworkConfigurationService.getManuscriptDashboardBaseUrlAlternate();
                break;
            }
            case "references": {
                mdUrl = frameworkConfigurationService.getManuscriptDashboardBaseUrlReferences();
                break;
            }
        }
        DriverContext.goToUrl(mdUrl);
        log.debug("Navigating to URL " + mdUrl);
        CurrentPageContext.setCurrentPage(GetInstance(ManuscriptDashboardStandalonePageActions.class));
        DriverContext.waitForPageToLoad();
        DriverContext.WaitUntilElementIsVisible(manuscriptDashboardStandalonePageLocators.getTilesWrapper());
        Assert.assertEquals("The new dashboard page is not displaying all 6 tiles", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).getNumberOfTilesDisplayed(), 6);
    }


    @And("^the user clicks on the Show Details button for the (.*)? tile$")
    public void theUserClicksOnTheShowDetailsButtonForTheAuthorsTile(String tileName) {
        CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).clickShowDetailsForTile(tileName);
    }

    @Then("^each Tile displays the expected heading$")
    public void theTileDisplaysTheHeading(DataTable tilesData) {
        List<List<String>> tilesTable = tilesData.asLists();
        ListIterator<List<String>> tilesIterator = tilesTable.listIterator();
        tilesIterator.next();
        while (tilesIterator.hasNext()) {
            List<String> tileData = tilesIterator.next();
            Assert.assertEquals("Tile heading is not the expected one for the " + tileData.get(1) + " tile", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).getTileHeadingTitle(tileData.get(0)), tileData.get(1));
        }
    }

    @Then("^verify the trending graph is displayed for the collapsed view$")
    public void verifyTheTrendingGraphIsDisplayedForTheCollapsedView() {
        Assert.assertTrue("The trending graph is not displayed for collapsed view",CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).verifyTrendingGraphIsDisplayedInCollapsedView());
    }

    @Then("^verify the \"([^\"]*)\" is displayed with some value between 0 to 100 inclusive$")
    public void verifyTheTileIsDisplayedWithSomeValueBetween0To100Inclusive(String tiles) {
        Assert.assertTrue("The "+tiles+" score is not between 0 and 100 ", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).verifyScoreInTileIsWithinLimits(tiles));
    }

    @And("^show details for \"([^\"]*)\" is clicked for the new dashboard$")
    public void showDetailsForTheDimensionIsClickedInNewDashboard(String tileName) {
        CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).clickShowDetailsForTile(tileName);
    }

    @Then("the Authors tile preview displays the author names")
    public void theAuthorsTilePreviewDisplaysTheAuthorNames() {
        Assert.assertTrue("The authors tile is not displaying any authors", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).verifyAtLeastOneAuthorNameIsDisplayedInAuthorsTile());
    }

    @And("the author names displayed in the Authors tile preview with hyperlink should open the correct Scopus page")
    public void theAuthorNamesDisplayedInTheAuthorsTilePreviewWithHyperlinkShouldOpenTheCorrectScopusPage() throws InterruptedException {
        Assert.assertTrue("Either there is no Author with a hyperlink or the link is not displaying the correct Author Scopus Page ", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).verifyCorrectAuthorScopusPageIsDisplayedUsingAuthorTileLink());
    }

    @And("the Authors tile details display a list of authors")
    public void theAuthorsTileDetailsDisplayAListOfAuthors() {
        Assert.assertTrue("The authors tile is not displaying any authors", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).verifyAuthorsNameIsDisplayedInAuthorsTileDetails());
    }

    @And("the author names displayed in the Authors tile details with hyperlink should open the correct Scopus page")
    public void theAuthorNamesDisplayedInTheAuthorsTileDetailsWithHyperlinkShouldOpenTheCorrectScopusPage() throws InterruptedException {
        Assert.assertTrue("Either there is no Author with a hyperlink or the link is not displaying the correct Author Scopus Page ", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).verifyCorrectAuthorScopusPageIsDisplayedUsingAuthorDetailsLink());
    }

    @And("all the references are displayed in three columns with titles {string}, {string} and {string}")
    public void allTheReferencesAreDisplayedInThreeColumnsWithTitlesAnd(String titleReferenceColumn, String titleJournalColumn, String titleYearColumn) {
        Assert.assertEquals("The first column title under references tile is incorrect", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).getHeadingOfTheTableUnderReferencesDetails(0),titleReferenceColumn);
        Assert.assertEquals("The second column title under references tile is incorrect", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).getHeadingOfTheTableUnderReferencesDetails(1),titleJournalColumn);
        Assert.assertEquals("The third column title under references tile is incorrect", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).getHeadingOfTheTableUnderReferencesDetails(2),titleYearColumn);

    }

    @Then("the {string} and {string} are displaying expected values on the References tile preview")
    public void theAndAreDisplayingExpectedValuesOnTheReferencesTilePreview(String total, String lessThan5Years) {
        Assert.assertTrue("The number of references does not seems to be correct",CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).getNumberOfReferencesDisplayedInTheTilePreview(total)>=0);
        Assert.assertTrue("The number of references less than 5 years old are more than total references", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).getNumberOfReferencesDisplayedInTheTilePreview(lessThan5Years)<=CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).getNumberOfReferencesDisplayedInTheTilePreview(total));
    }

    @And("the {string} and the {string} are displaying expected values on the References tile details")
    public void theAndTheAreDisplayingExpectedValuesOnTheReferencesTileDetails(String total, String lessThan5Years) {
        Assert.assertTrue("The number of references does not seems to be correct", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).getNumberOfReferencesDisplayedInTheTileDetail(total)>=0);
        Assert.assertTrue("The number of references less than 5 years old are more than total references", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).getNumberOfReferencesDisplayedInTheTileDetail(lessThan5Years)<=CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).getNumberOfReferencesDisplayedInTheTileDetail(total) );
    }

    @Then("sorting using the (.*)? option works as expected")
    public void sortingUsingTheOptionWorksAsExpected(String option) {
        CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).selectTheSpecifiedOptionFromTheSortByDropDown(option);
        Assert.assertTrue("The references are NOT arranged as per the option selected: -"+option, CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).verifyReferencesAreSortedBy(option));
    }

    @Then("clicking on the pubMed link for the top reference opens the correct pubMed page")
    public void clickingOnThePubMedLinkForTheTopReferenceOpensTheCorrectPubMedPage() throws InterruptedException {
        Assert.assertTrue("Pubmed journal page does not match the reference paper entry", CurrentPageContext.getCurrentPage().As(ManuscriptDashboardStandalonePageActions.class).verifyPubMedLinkIsCorrect());
    }
}
