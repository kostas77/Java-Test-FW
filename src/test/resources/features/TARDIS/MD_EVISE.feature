Feature: Manuscript Dashboard EVISE integration tests

  @TDS-530
  Scenario Outline: Verify Reference tile is displayed with the correct title
    Given the user sign in to Evise as an Editor
    And the user opens the new model output page for "<tiles>"
    When the new  manuscript dashboard page is loaded
    Then the "<tiles>" heading is correct

    Examples:
      |   tiles      |
      |   references    |

  @MD_EVISE
  Scenario: Open the new framework page for the manuscript dashboard and the page is displayed
    Given the user opens the new model output page
    Then the new manuscript dashboard page is loaded
#    And manuscript dashboard is displayed as the title // TODO - Manuscript dashboard non-prod page doesn't have a title

  @MD_EVISE
  Scenario Outline: Open the new manuscript page and verify that all 6 tiles are present and display the expected headings
    Given the user opens the new model output page
    When the new manuscript dashboard page is loaded
    Then the "<tileName>" tile displays the expected heading "<tileHeading>"
    Examples:
      | tileName    |  tileHeading                                     |
      | references  |  References                                      |
      | plagiarism  |  Plagiarism check (iThenticate)                  |
      | authors     |  Authors                                         |
      | scope_match |  Most similar work in your/ other journals       |
      | trending    |  Topic similarity over the years in your journal |
      | originality |  Closest work by submission's authors            |

  @MD_EVISE
  Scenario Outline: Verify Show details button expand and hide using the show details/ hide details link
    Given the user opens the new model output page
     When the new manuscript dashboard page is loaded
      And show details for "<tiles>" is clicked for the new dashboard
     Then the respective details section is displayed for the "<tiles>"
      And clicking on the hide details for the "<tiles>" hides the details section
    Examples:
      |   tiles     |
      | references  |
      | authors     |
      | scope_match |
      | trending    |
      | originality |


  @TDS-532
  Scenario Outline: Verify Trending tile is displayed with the correct title
    Given the user opens the new model output page
    When the new manuscript dashboard page is loaded
    Then the "<tiles>" heading is correct
    Examples:
      |   tiles      |
      |   trending    |

#  Trending tile
  @TDS-532 @CI  @TDS-532
  Scenario: Verify trending tile displays the interactive graph
    Given the user opens the new model output page
    When the new manuscript dashboard page is loaded
    Then verify the trending graph is displayed for the collapsed view

#  Trending tile
  @TDS-532 @CI  @TDS-532
  Scenario Outline: Verify trending tile displays the topic similarity score with a value between 0 to 100 %, inclusive
    Given the user opens the new model output page
    When the new manuscript dashboard page is loaded
    Then verify the "<tiles>" is displayed with some value between 0 to 100 inclusive
    Examples:
      |   tiles      |
      |   trending   |
