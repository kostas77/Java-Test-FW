Feature: Tardis team manuscript dashboard standalone tests - Authors Tile

  @MD_authors
  Scenario: Verify the list of authors is displayed in the Authors tile preview and it has valid links to Scopus Author profiles
    Given the user visits the Manuscript Dashboard page: default
     Then the Authors tile preview displays the author names
      And the author names displayed in the Authors tile preview with hyperlink should open the correct Scopus page

  @MD_authors
  Scenario: Verify the list of authors is displayed in the Authors tile details and it has valid links to Scopus Author profiles
    Given the user visits the Manuscript Dashboard page: default
     When the user clicks on the Show Details button for the authors tile
     Then the Authors tile details display a list of authors
      And the author names displayed in the Authors tile details with hyperlink should open the correct Scopus page
