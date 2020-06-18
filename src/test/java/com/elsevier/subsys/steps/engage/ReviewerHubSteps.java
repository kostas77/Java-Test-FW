package com.elsevier.subsys.steps.engage;

import com.deque.axe.AXE;
import com.elsevier.subsys.framework.base.Base;
import com.elsevier.subsys.framework.base.CurrentPageContext;
import com.elsevier.subsys.framework.base.DriverContext;
import com.elsevier.subsys.framework.base.LocalDriverContext;
import com.elsevier.subsys.framework.config.FrameworkConfigurationService;
import com.elsevier.subsys.pages.engage.actions.LoadRcIdPageActions;
import com.elsevier.subsys.pages.engage.actions.ReviewerHubPageActions;
import com.elsevier.subsys.pages.engage.locators.LoadRcIdPageLocators;
import com.elsevier.subsys.pages.engage.locators.ReviewerHubPageLocators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ReviewerHubSteps extends Base {

    LoadRcIdPageLocators loadRcIdPageLocators = GetInstance(LoadRcIdPageLocators.class);
    ReviewerHubPageLocators reviewerHubPageLocators = GetInstance(ReviewerHubPageLocators.class);

    @Autowired
    private FrameworkConfigurationService frameworkConfigurationService;

    @Given("^a user logs in to the Reviewer Hub$")
    public void userIsLoggedInToTheReviewerHub()  {
        DriverContext.goToUrl(frameworkConfigurationService.getReviewerHubBaseUrl());
        DriverContext.WaitUntilElementIsVisible(loadRcIdPageLocators.getTxtEmail());
        log.debug("Navigated to URL " + frameworkConfigurationService.getReviewerHubBaseUrl());
        CurrentPageContext.setCurrentPage(GetInstance(LoadRcIdPageActions.class));
        CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(LoadRcIdPageActions.class).SignIn("r.hudson@elsevier.com", "reviewerHubSecretOnRC")); // TODO - Hide credentials
        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getTxtReviewerHubHeaderTitle());
    }

    @Then("^the Reviewer Hub Header is displayed$")
    public void theReviewerHubHeaderIsDisplayed()  {
        Assert.assertTrue("The User Profile link is not displayed", DriverContext.IsElementDisplayed(reviewerHubPageLocators.lnkUserProfile));
        Assert.assertTrue("The Reviewer Hub page is not loaded", CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).IsReviewerSupportTitle("Reviewer Hub"));
        Assert.assertTrue("The Elsevier Logo is not displayed", DriverContext.IsElementDisplayed(reviewerHubPageLocators.txtElsevierLogo));
//        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).txtReviewerHubHeaderTitle.toString());
    }

    @Then("^each left side panel section is displayed as expected and can be accessed$")
    public void verifyReviewerHubPageSection(DataTable data)  {
//        List<List<String>> table = data.asLists(); // TODO: Not being used currently
//        log.debug(table.toString());
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewInvitationsLink();
        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getTxtReviewInvitationsContentTitle());
        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).GetReviewInvitationsText());
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewsInProgressLink();
        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getTxtReviewsInProgressContentTitle());
        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).GetReviewsInProgressText());
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewHistoryLink();
        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getReviewHistorySidePanel());
//        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewHistoryAllJournalsLink();
//        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getTxtAllJournalsPanelTitle());
    }

    @Then("^the user can logout from the Reviewer Hub$")
    public void theUserCanLogoutFromTheReviewerHub()  {
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickUserProfile();
        DriverContext.DriverSleep(3000); // TODO: Only temporary for demonstration purposes - will be removed
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickSignOutButton();
        DriverContext.DriverSleep(3000); // TODO: Only temporary for demonstration purposes - will be removed
//        Assert.assertTrue("The Reviewer Hub page is not loaded", CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).IsNonLoggedInReviewerHubPage());
        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).lnkNonLoggedInReviewerHubPage.toString());
        // TODO - Currently the Log Out functionality is not working fully (i.e. The logout button is just a link to the reviewers page). There will be an extra step here where the user revisits the Reviewer Hub and is expected to be redirected to the login page.
    }

    @And("^the user has outstanding review invitations$")
    public void theUserHasOutstandingReviewInvitations() {
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewInvitationsLink();
        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getTableHeaderReviewInvitations());
//        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).GetReviewInvitationsText());
//        System.out.println("List size is: " + DriverContext.getElementsList(reviewerHubPageLocators.locReviewInvitations).size());
        Assert.assertTrue("There are not any outstanding Review Invitations", DriverContext.getElementsList(reviewerHubPageLocators.locReviewInvitations).size() > 0);
    }

    @Then("^the user is able to view and access their review invitations$")
    public void theUserIsAbleToViewTheirOutstandingReviewInvitations() {
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewInvitationsLink();
        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getTableHeaderReviewInvitations());
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickInvitationsTopManuscriptOpenEmButton();
        DriverContext.switchToWindowHandle(DriverContext.getWindowHandlesSet().toArray()[1].toString());
        DriverContext.waitForPageToLoad();
//        System.out.println(DriverContext.getCurrentUrl());
        DriverContext.DriverSleep(5000); // TODO: Only temporary for demonstration purposes - will be removed
        Assert.assertEquals("Editorial Manager page did not open as expected", "Aries Systems Corporation", DriverContext.getPageTitle());
        DriverContext.switchToWindowHandle(DriverContext.getWindowHandlesSet().toArray()[0].toString());
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickInvitationsTopManuscriptTitleLink();
        DriverContext.waitForPageToLoad();
        DriverContext.DriverSleep(5000); // TODO: Only temporary for demonstration purposes - will be removed
        DriverContext.switchToWindowHandle(DriverContext.getWindowHandlesSet().toArray()[2].toString());
//        System.out.println(DriverContext.getCurrentUrl());
        Assert.assertEquals("Editorial Manager page did not open as expected", "Aries Systems Corporation", DriverContext.getPageTitle());
    }

    @And("^the user has some existing reviews in progress$")
    public void theUserHasReviewsInProgress() {
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewsInProgressLink();
        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getTableHeaderReviewsInProgress());
        Assert.assertTrue("There are not any outstanding Reviews In Progress", DriverContext.getElementsList(reviewerHubPageLocators.locReviewsInProgress).size() > 0);
    }

    @And("^they have completed more than 10 reviews for Elsevier journals$")
    public void theyHaveCompletedMoreThanNumberReviewsForElsevierJournals() {
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewHistoryLink();
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickAllJournalsLink();
//        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getTableHeaderReviewHistory());
        log.debug("Number of reviews shown: " + DriverContext.getElementsList(reviewerHubPageLocators.locCompletedReviews).size());
        Assert.assertTrue("There are less than 10 completed reviews", DriverContext.getElementsList(reviewerHubPageLocators.locCompletedReviews).size() > 9);
    }

    @And("^their completed reviews are paginated in the Review History section$")
    public void theirCompletedReviewsArePaginated() {
        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getBtnAllReviewsLoadMore());
        Assert.assertTrue("The Load More button does not exist", DriverContext.IsElementDisplayed(reviewerHubPageLocators.getBtnAllReviewsLoadMore()));
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickLoadMoreReviewsButton();
        DriverContext.waitForPageToLoad();
        log.debug("Number of reviews shown: " + DriverContext.getElementsList(reviewerHubPageLocators.locCompletedReviews).size());
        Assert.assertTrue("There are no more than 10 completed reviews shown", DriverContext.getElementsList(reviewerHubPageLocators.locCompletedReviews).size() > 10);
        Assert.assertTrue("There are more than 20 completed reviews shown", DriverContext.getElementsList(reviewerHubPageLocators.locCompletedReviews).size() < 21);
    }

    @Then("^the user is able to view and access their reviews in progress$")
    public void theUserIsAbleToAccessTheirReviewsInProgress() {
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewsInProgressLink();
//        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).GetReviewInvitationsText());
        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getTableHeaderReviewsInProgress());
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickInProgressTopManuscriptOpenEmButton();
        DriverContext.switchToWindowHandle(DriverContext.getWindowHandlesSet().toArray()[1].toString());
        DriverContext.waitForPageToLoad();
        DriverContext.DriverSleep(5000); // TODO: Only temporary for demonstration purposes - will be removed
        log.debug(DriverContext.getCurrentUrl());
        Assert.assertEquals("Editorial Manager page did not open as expected", "Aries Systems Corporation", DriverContext.getPageTitle());
        DriverContext.switchToWindowHandle(DriverContext.getWindowHandlesSet().toArray()[0].toString());
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickInProgressTopManuscriptTitleLink();
        DriverContext.switchToWindowHandle(DriverContext.getWindowHandlesSet().toArray()[2].toString());
        DriverContext.waitForPageToLoad();
        DriverContext.DriverSleep(5000); // TODO: Only temporary for demonstration purposes - will be removed
        log.debug(DriverContext.getCurrentUrl());
        Assert.assertEquals("Editorial Manager page did not open as expected", "Aries Systems Corporation", DriverContext.getPageTitle());
    }

    @And("^the user has completed reviews in different journals$")
    public void theUserHasCompletedReviewsInDifferentJournals() {
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewHistoryLink();
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickAllJournalsLink();
        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getTableHeaderReviewHistory());
        Assert.assertTrue("There are not any Completed Reviews", DriverContext.getElementsList(reviewerHubPageLocators.locCompletedReviews).size() > 0);
    }

    @Then("^the user is able to view and access their completed reviews in all journals$")
    public void theUserIsAbleToAccessTheirCompletedReviewsInAllJournals() {
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickAllJournalsLink();
        DriverContext.WaitUntilElementIsVisible(reviewerHubPageLocators.getTableHeaderReviewHistory());
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewHistoryTopManuscriptOpenEmButton();
        DriverContext.switchToWindowHandle(DriverContext.getWindowHandlesSet().toArray()[1].toString());
        DriverContext.waitForPageToLoad();
        DriverContext.DriverSleep(5000); // TODO: Only temporary for demonstration purposes - will be removed
        log.debug(DriverContext.getCurrentUrl());
        Assert.assertEquals("Editorial Manager page did not open as expected", "Aries Systems Corporation", DriverContext.getPageTitle());
        DriverContext.switchToWindowHandle(DriverContext.getWindowHandlesSet().toArray()[0].toString());
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewHistoryTopManuscriptTitleLink();
        DriverContext.switchToWindowHandle(DriverContext.getWindowHandlesSet().toArray()[2].toString());
        DriverContext.waitForPageToLoad();
        DriverContext.DriverSleep(5000); // TODO: Only temporary for demonstration purposes - will be removed
        log.debug(DriverContext.getCurrentUrl());
        Assert.assertEquals("Editorial Manager page did not open as expected", "Aries Systems Corporation", DriverContext.getPageTitle());
    }

//    @And("^the user has completed reviews in a specific journal$")
//    public void theUserHasCompletedReviewsInASpecificJournal() throws ClassNotFoundException {
//        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickReviewHistoryLink();
//        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickJournalAAMLink();
//        DriverContext.DriverSleep(2000); // TODO: Only temporary for demonstration purposes - will be removed
//        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).GetReviewHistoryText());
////        Assert.assertTrue("The reviews you are currently working on", CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).uiReviewsInProgress());
//    }

//    @Then("^the user is able to view and access their completed reviews in a specific journal$")
//    public void theUserIsAbleToViewTheirCompletedReviewsInASpecificJournal() {
//        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickJournalAAMLink();
//        DriverContext.DriverSleep(2000); // TODO: Only temporary for demonstration purposes - will be removed
//        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).GetReviewHistoryText());
////        Assert.assertTrue("Test title (AAM) 3", CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).uiReviewHistoryManuscriptTitle());
////        Assert.assertTrue("18 January 2020", CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).uiReviewHistoryCompletedDate());
//    }

//    @Then("^the user is able to view the top panel of a specific journal$")
//    public void theUserIsAbleToViewTheTopPanelOfASpecificJournal() {
//        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickJournalAAMLink();
//        DriverContext.DriverSleep(5000); // TODO: Only temporary for demonstration purposes - will be removed
//        Assert.assertTrue("The Reviewer Hub page is not loaded", CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).IsTopPanelJournalCoverDisplayed());
////        Assert.assertTrue("The Reviewer Hub page is not loaded", CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).IsTopPanelJournalTitleDisplayed());
//        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).txtTopPanelJournalCover.toString());
//        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).txtTopPanelJournalTitle.toString());
//    }

//    @Then("^the user is able to view the top panel of all journals$")
//    public void theUserIsAbleToViewTheTopPanelOfAllJournals() {
//        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickAllJournalsLink();
//        DriverContext.DriverSleep(5000); // TODO: Only temporary for demonstration purposes - will be removed
////        Assert.assertTrue("The Reviewer Hub page is not loaded", CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).IsTopPanelJournalCoverDisplayed());
////        Assert.assertTrue("The Reviewer Hub page is not loaded", CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).IsTopPanelJournalTitleDisplayed());
//        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).txtTopPanelJournalCover.toString());
//        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).txtTopPanelJournalTitle.toString());
//    }

    @Then("^they should be able to download reviewer certificates at the journal level$")
    public void theyShouldBeAbleToDownloadReviewerCertificatesAtTheJournalLevel() {
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickRecognisedReviewerCertificate();
        DriverContext.DriverSleep(2000); // TODO: Only temporary for demonstration purposes - will be removed
        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).GetJournalAcronymText());
        DriverContext.goToUrl(frameworkConfigurationService.getReviewerHubBaseUrl());
        log.debug("Navigated to URL " + frameworkConfigurationService.getReviewerHubBaseUrl());
        DriverContext.DriverSleep(2000); // TODO: Only temporary for demonstration purposes - will be removed
        log.debug(CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).GetDownloadedFileText());
        Assert.assertEquals("Reviewer recognition certificate downloaded", "certificate.pdf", CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).GetDownloadedFileText());
    }

    @And("^the reviewer certificate contains$")
    public void theReviewerCertificateContains() {
        CurrentPageContext.getCurrentPage().As(ReviewerHubPageActions.class).ClickDownloadedFile();
    }

    @Given("^the current page is tested for A11y$")
    public void theCurrentPageIsTestedForA11y() throws JSONException {
        String pageUrl = DriverContext.getCurrentUrl();
        JSONObject responseJSON = new AXE.Builder(LocalDriverContext.getWebDriver(), axeScriptUrl).analyze();
        JSONArray violations = responseJSON.getJSONArray("violations");
        if (violations.length() == 0) {
            log.debug("No A11y violations were found");
        } else {
//            AXE.writeResults("a11yTestName", responseJSON); // TODO: Decide if we want to write the A11y report to a *.json file
            log.debug("A11y violations report for page (" + pageUrl + ") => " + AXE.report(violations));
//            Assert.assertTrue("A11y violations were found", false); //TODO: Decide whether we want to fail the test for A11y violations
        }
    }


}
