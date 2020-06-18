Feature: Tardis team manuscript dashboard standalone tests - Scope Match

  Scenario: Verify scope match list is displayed in the Scope Match tile
    Given the user sign in to Evise as an Editor
    And the user opens the new model output page for "<tiles>"
    When the new  manuscript dashboard page is loaded
    Then the "<tiles>" would display the closest papers from this journal

  Scenario: Verify the list of papers displayed in the Scope match tile have hyperlinks to their Scopus pages
    Given the user sign in to Evise as an Editor
    And the user opens the new model output page for "<tiles>"
    When the new  manuscript dashboard page is loaded
    And click on first closest papers in your journal in new dashboard for the "<tiles>"
    Then the paper displayed in "<tiles>" tile with hyperlink should open the correct Scopus page

  Scenario: Verify scope match score is displayed in the Scope Match tile
    Given the user sign in to Evise as an Editor
    And the user opens the new model output page for "<tiles>"
    When the new  manuscript dashboard page is loaded
    Then the "<tiles>" would display the score

  Scenario: Verify when there is no data the tile should display no data message
    Given the user sign in to Evise as an Editor
    And the user opens the new model output page for "plagiarism"
    When the new  manuscript dashboard page is loaded
    Then the "<tiles>" would display "<no data message>"

############### Expanded scope match ####################
  @TDS-533 @CI @FullRegression
  Scenario: Detail tile contains the correct heading
    Given the user opens the new model output page for "<tiles>"
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    Then the "<tiles>" heading is correct in the expanded view

  @TDS-533 @newDashRegression
  Scenario: Paper link still links to its Scopus page
    Given the user opens the new model output page for "<tiles>"
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    And click on first closest papers in your journal in new dashboard for the "<tiles>" in the expanded view
    Then the paper displayed in "<tiles>" tile with hyperlink should open the correct Scopus page

  Scenario: Papers for this journal are displayed in the expanded view
    Given the user sign in to Evise as an Editor
    And the user opens the new model output page for "<tiles>"
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    Then the papers for this journal displayed in "<tiles>" tile are correct in the expanded view

  Scenario: Papers for other journals are displayed in the expanded view
    Given the user sign in to Evise as an Editor
    And the user opens the new model output page for "<tiles>"
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    Then the papers for other journals displayed in "<tiles>" tile are correct in the expanded view

  Scenario: Hovering over the information button display the popup about scope match calculation
    Given the user sign in to Evise as an Editor
    And the user opens the new model output page for "<tiles>"
    When the new  manuscript dashboard page is loaded
    And show details for "<tiles>" is clicked for the new dashboard
    And the user hovers over the information icon in "<tiles>"
    Then the information popup is displayed for "<tiles>"

