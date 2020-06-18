Feature: Tardis team manuscript dashboard standalone tests - Trending Tile

  @TDS-532 @MD_trending
  Scenario: Verify Trending tile is displayed with the correct title
    Given the user visits the Manuscript Dashboard page: default
     Then verify the trending graph is displayed for the collapsed view
     Then verify the trending tile is displayed with some value between 0 to 100 inclusive

  @TDS-532 @MD_trending
  Scenario: Verify trending tile displays the topic similarity score and Journal average score with a value between 0 to 100 %, inclusive
    Given the user visits the Manuscript Dashboard page: default
     Then the user clicks on the Show Details button for the Trending tile
     Then verify the trending score in details section is displayed with some value between 0 to 100 inclusive
     Then verify the trending Journal average score is displayed with some value between 0 to 100 inclusive

  @TDS-532 @MD_trending
  Scenario: Verify trending tile displays the Submission score section
    Given the user visits the Manuscript Dashboard page: default
     Then the user clicks on the Show Details button for the Trending tile
     Then verify the Submission score section of the "<tiles>" is displayed
      And hover over the i icon in the Submission score section of "<tiles>"
     Then verify hover over info on the Submission Score help icon displays expected info for this "<tiles>"
     Then verify hover over info on the Submission Score contains expected answer one for this "<tiles>"
     Then verify hover over info on the Submission Score contains expected answer two for this "<tiles>"

  @TDS-532 @MD_trending
  Scenario: Verify the graph elements in tile details the hover over displays information
    Given the user visits the Manuscript Dashboard page: default
     Then the user clicks on the Show Details button for the Trending tile
     Then hovering over the years display a tooltip with info in tile details section graph
     Then verify clicking on the dots for the years displays the respective list of papers
     Then clicking on first paper from the paper list opens up the correct scopus page
