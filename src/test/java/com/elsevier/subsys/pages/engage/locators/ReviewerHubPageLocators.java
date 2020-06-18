package com.elsevier.subsys.pages.engage.locators;

import com.elsevier.subsys.framework.base.BasePage;
import com.elsevier.subsys.framework.controls.elements.Button;
import com.elsevier.subsys.framework.controls.elements.HyperLink;
import com.elsevier.subsys.framework.controls.elements.Text;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


@Getter
public class ReviewerHubPageLocators extends BasePage {

    public String uiReviewerHubSupportText = "Reviewer Hub";

    public String uiReviewerInvitationJournalText = "Journal of Contemporary Accounting & Economics";

    public String uiReviewersInProgressJournalText = "Journal of Contemporary Accounting & Economics";

    public String uiReviewInvitationText = "Your pending review invitations";

    public String uiReviewsInProgressText = "The reviews you are currently working on";

    public String locReviewInvitations = "[data-testid*=manuscript-title][id*=invitations-manuscript]";

    public String locReviewsInProgress = "[data-testid*=manuscript-title][id*=in-progress-manuscript]";

    public String locCompletedReviews = "[data-testid*=manuscript-title][id*=manuscript]";

    @FindBy(how = How.ID, using = "user-profile-modal-trigger")
    public HyperLink lnkUserProfile;

    @FindBy(how = How.ID, using = "header-heading")
    public Text txtReviewerHubHeaderTitle;

    @FindBy(how = How.ID, using = "side-bar-AAM-history-link")
    public HyperLink lnkJournalAAM;

    @FindBy(how = How.CSS, using = ".es-header-product-text")
    public Text txtReviewerSupportTitle;

    @FindBy(how = How.ID, using = "side-bar-invitations-link")
    public HyperLink lnkReviewInvitations;

    @FindBy(how = How.ID, using = "side-bar-in-progress-link")
    public HyperLink lnkReviewsInProgress;

    @FindBy(how = How.ID, using = "side-bar-toggle-history-sub-menu-button")
    public Button btnReviewHistory;

    @FindBy(how = How.ID, using = "side-bar-all-history-link")
    public Button btnReviewHistoryAllJournals;

    @FindBy(how = How.ID, using = "recognised-reviewer-certificate-button")
    public Button btnRecognisedReviewerCertificate;

    @FindBy(how = How.ID, using = "review-invitations-title")
    public Text txtReviewInvitationsContentTitle;

    @FindBy(how = How.ID, using = "skip-navigation-link")
    public Text txtJournalAcronym;

    @FindBy(how = How.ID, using = "reviews-in-progress-title")
    public Text txtReviewsInProgressContentTitle;

    @FindBy(how = How.ID, using = "top-panel-heading")
    public Text txtAllJournalsPanelTitle;

    @FindBy(how = How.CSS, using = ".side-panel-sub")
    public WebElement reviewHistorySidePanel;

    @FindBy(how = How.CSS, using = ".gh-logo")
    public Text txtElsevierLogo;

    @FindBy(how = How.ID, using = "header-popup-profile-sign-out")
    public Button btnSignOut;

    @FindBy(how = How.ID, using = "header-popup-close-button")
    public Button btnClosePopUpUserDetail;

    @FindBy(how = How.XPATH, using = "/h1[@class='wordmark-product']")
    public HyperLink lnkNonLoggedInReviewerHubPage;

    @FindBy(how = How.LINK_TEXT, using = "certificate.pdf")
    public HyperLink lnkDownloadFile;

    @FindBy(how = How.LINK_TEXT, using = "certificate.pdf")
    public Text txtDownloadFile;

    @FindBy(how = How.ID, using = "table-header-invitations-manuscript")
    public WebElement tableHeaderReviewInvitations;

    @FindBy(how = How.ID, using = "table-header-in-progress-manuscript")
    public WebElement tableHeaderReviewsInProgress;

    @FindBy(how = How.ID, using = "table-header-manuscript")
    public WebElement tableHeaderReviewHistory;

    @FindBy(how = How.ID, using = "table-row-0-manuscript-link")
    public HyperLink lnkInvitationsTopManuscriptTitleLink;

    @FindBy(how = How.ID, using = "table-row-0-manuscript-link")
    public HyperLink lnkInProgressTopManuscriptTitleLink;

    @FindBy(how = How.ID, using = "table-row-0-manuscript-link")
    public HyperLink lnkReviewHistoryTopManuscriptTitleLink;

    @FindBy(how = How.ID, using = "button-0-actions")
    public Button btnInvitationsTopManuscriptOpenInEM;

    @FindBy(how = How.ID, using = "button-0-actions")
    public Button btnInProgressTopManuscriptOpenInEM;

    @FindBy(how = How.ID, using = "button-0-actions")
    public Button btnReviewHistoryTopManuscriptOpenInEM;

    @FindBy(how = How.ID, using = "table-row-0-invitations-manuscript")
    public Text txtInvitationsManuscript;

    @FindBy(how = How.ID, using = "table-row-0-in-progress-manuscript")
    public Text txtInProgressManuscript;

    @FindBy(how = How.ID, using = "table-row-0-manuscript")
    public Text txtCompletedManuscript;

    @FindBy(how = How.ID, using = "table-row-0-invitations-journal")
    public WebElement txtInvitationsJournal;

    @FindBy(how = How.ID, using = "table-row-0-in-progress-journal")
    public WebElement txtInProgressJournal;

    @FindBy(how = How.ID, using = "table-row-0-journal")
    public WebElement txtAllJournalsJournalTitle;

    @FindBy(how = How.ID, using = "table-row-0-invitations-date-invited")
    public WebElement txtInvitationsDateInvited;

    @FindBy(how = How.ID, using = "table-row-0-in-progress-date-accepted")
    public WebElement txtInProgressDateAccepted;

    @FindBy(how = How.ID, using = "table-row-0-completed-date")
    public Text txtReviewHistoryCompletedDate;

    @FindBy(how = How.ID, using = "table-row-0-date-completed")
    public Text txtAllJournalsDateCompleted;

    @FindBy(how = How.ID, using = "table-row-0-invitations-expires")
    public Text txtTopManuscriptInvitationsExpires;

    @FindBy(how = How.ID, using = "table-row-0-in-progress-expires")
    public Text txtTopManuscriptInProgressExpires;

    @FindBy(how = How.ID, using = "top-panel-journal-cover")
    public WebElement txtTopPanelJournalCover;

    @FindBy(how = How.ID, using = "top-panel-journal-title")
    public WebElement txtTopPanelJournalTitle;

    @FindBy(how = How.ID, using = "button-all-reviews-show-more")
    public Button btnAllReviewsLoadMore;

}
