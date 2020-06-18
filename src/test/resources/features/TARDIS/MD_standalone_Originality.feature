Feature: Tardis team manuscript dashboard standalone tests - Originality Tile

  @TDS-534 @CI @FullRegression @SmokeTest
  Scenario: Verify Originality tile displays the list of papers that are most similar work by submissions authors
    Given the user opens the new model output page
    When the new  manuscript dashboard page is loaded
    Then verify the list of papers that are most similar work by submissions authors are displayed

  @TDS-534 @CI @FullRegression @SmokeTest
  Scenario: Verify originality tile displays the author originality score with a value between 0 to 100 %, inclusive
    Given the user opens the new model output page
    When the new  manuscript dashboard page is loaded
    Then verify the "<tiles>" is displayed with some value between 0 to 100 inclusive

  @TDS-534 @CI @FullRegression
  Scenario: Verify the list of papers displayed in the Originality tile have hyperlinks to their Scopus pages
    Given the user opens the new model output page
    When the new  manuscript dashboard page is loaded
    And click on first similar work by the submission paper
    Then verify that the correct Scopus page opens up on the new tab

  @TDS-534 @CI @FullRegression @SmokeTest
  Scenario: Verify Originality tile details display the correct title and definition
    Given the user opens the new model output page
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    Then the "<tiles>" details heading is correct
    And the "<tiles>" details definition is as expected

  @TDS-534 @CI @FullRegression
  Scenario: Verify originality tile displays the Submission score section
    Given the user opens the new model output page
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    Then verify the Submission score section of the "<tiles>" is displayed

  @TDS-534 @CI @FullRegression
  Scenario: Verify originality tile displays the Submission score i hover over displays expected information
    Given the user opens the new model output page
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    And hover over the i icon in the Submission score section of "<tiles>"
    Then verify hover over info on the Submission Score help icon displays expected info for this "<tiles>"

  @TDS-534 @CI @FullRegression
  Scenario: Verify originality tile displays the Submission score i hover over displays expected answer one
    Given the user opens the new model output page
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    And hover over the i icon in the Submission score section of "<tiles>"
    Then verify hover over info on the Submission Score contains expected answer one for this "<tiles>"

  @TDS-534 @CI @FullRegression
  Scenario: Verify originality tile displays the Submission score i hover over displays expected answer two
    Given the user opens the new model output page
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    And hover over the i icon in the Submission score section of "<tiles>"
    Then verify hover over info on the Submission Score contains expected answer two for this "<tiles>"

  @TDS-532 @CI @FullRegression @SmokeTest
  Scenario: Verify originality tile displays the topic similarity score and Journal average score with a value between 0 to 100 %, inclusive
    Given the user opens the new model output page
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    Then verify the "<tiles>" score in details section is displayed with some value between 0 to 100 inclusive
    Then verify the "<tiles>" Journal average score is displayed with some value between 0 to 100 inclusive

  @TDS-534 @CI @FullRegression @SmokeTest
  Scenario: Verify the author names are displayed under the paper tiles and at least one is bold
    Given the user opens the new model output page
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    Then papers in the originality tile details displays the author names under it with minimum one to maximum ten
    And current submission author name is displayed in bold within all author names in the tile details

  @TDS-534 @CI @FullRegression
  Scenario: Verify clicking on the paper name in the tile details section opens up the correct Scopus page
    Given the user opens the new model output page
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    And click on first similar work by the submission author in tile details
    Then verify that the correct Scopus page opens up on the new tab
