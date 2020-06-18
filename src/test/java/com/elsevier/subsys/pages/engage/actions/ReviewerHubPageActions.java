package com.elsevier.subsys.pages.engage.actions;

import com.elsevier.subsys.pages.engage.locators.ReviewerHubPageLocators;


public class ReviewerHubPageActions extends ReviewerHubPageLocators {

    public void ClickReviewInvitationsLink() {
        lnkReviewInvitations.ClickLink();
    }

    public void ClickJournalAAMLink() {
        lnkJournalAAM.ClickLink();
    }

    public void ClickReviewsInProgressLink() {
        lnkReviewsInProgress.ClickLink();
    }

    public void ClickReviewHistoryLink() {
        btnReviewHistory.PerformClick();
    }

    public void ClickAllJournalsLink() {
        btnReviewHistoryAllJournals.PerformClick();
    }

    public void ClickInvitationsTopManuscriptOpenEmButton() {
        btnInvitationsTopManuscriptOpenInEM.PerformClick();
    }

    public void ClickInProgressTopManuscriptOpenEmButton() {
        btnInProgressTopManuscriptOpenInEM.PerformClick();
    }

    public void ClickReviewHistoryTopManuscriptOpenEmButton() {
        btnReviewHistoryTopManuscriptOpenInEM.PerformClick();
    }

    public void ClickInvitationsTopManuscriptTitleLink() {
        lnkInvitationsTopManuscriptTitleLink.ClickLink();
    }

    public void ClickInProgressTopManuscriptTitleLink() {
        lnkInProgressTopManuscriptTitleLink.ClickLink();
    }

    public void ClickReviewHistoryTopManuscriptTitleLink() {
        lnkReviewHistoryTopManuscriptTitleLink.ClickLink();
    }

    public void ClickReviewHistoryAllJournalsLink() {
        btnReviewHistoryAllJournals.PerformClick();
    }

    public void ClickUserProfile() {
        lnkUserProfile.ClickLink();
    }

    public void ClickSignOutButton() {
        btnSignOut.PerformClick();
    }

    public void ClickLoadMoreReviewsButton() {
        btnAllReviewsLoadMore.PerformClick();
    }

    public String GetReviewInvitationsText() {
        return txtReviewInvitationsContentTitle.GetTextValue();
    }

    public String GetReviewHistoryText() {
        return txtJournalAcronym.GetTextValue();
    }

    public String GetJournalAcronymText() {
        return txtReviewInvitationsContentTitle.GetTextValue();
    }

    public String GetReviewsInProgressText() {
        return txtReviewsInProgressContentTitle.GetTextValue();
    }

    public void ClickRecognisedReviewerCertificate() {
        btnRecognisedReviewerCertificate.PerformClick();
    }

    public boolean IsReviewerSupportTitle(String title) {
        return uiReviewerHubSupportText.matches(title);
    }

    public boolean reviewersInProgressTitle() {
        return uiReviewerHubSupportText.matches("Reviewer Hub");
    }

//    private static String downloadPath = "C:\\Users\\you\\Downloads";

    public String GetDownloadedFileText() {
        return txtDownloadFile.GetTextValue();
    }

    public void ClickDownloadedFile() {
        lnkDownloadFile.ClickLink();
    }

    public boolean IsTopPanelJournalCoverDisplayed() {
        return txtTopPanelJournalCover.isDisplayed();
    }

}
