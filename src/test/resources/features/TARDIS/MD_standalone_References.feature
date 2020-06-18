Feature: Tardis team manuscript dashboard standalone tests - References Tile

  @MD_references
  Scenario: The References tile preview display valid numbers of References and all the relevant data
    Given the user visits the Manuscript Dashboard page: alternate
     Then the "Number of references" and "References older than 5 years" are displaying expected values on the References tile preview

  @MD_references
  Scenario: The References tile details display valid numbers of References and all the relevant columns
    Given the user visits the Manuscript Dashboard page: alternate
     When the user clicks on the Show Details button for the references tile
     Then all the references are displayed in three columns with titles "Reference", "Journal" and "Year"
      And the "Number of references" and the "References older than 5 years" are displaying expected values on the References tile details

  @MD_references
  Scenario: Sorting references works as expected in the References tile details
    Given the user visits the Manuscript Dashboard page: references
     When the user clicks on the Show Details button for the references tile
     Then sorting using the Newest option works as expected
      And sorting using the References option works as expected

  @MD_references
  Scenario: References have valid PubMed and CrossRef links in the References tile details
    Given the user visits the Manuscript Dashboard page: references
     When the user clicks on the Show Details button for the references tile
     Then clicking on the pubMed link for the top reference opens the correct pubMed page
#     Then clicking on the crossRef link for the top reference opens the correct crossRef page
