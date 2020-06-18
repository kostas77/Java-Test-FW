Feature: Manuscript Dashboard standalone tests

  @tardis_smoke
  Scenario: Verify that all the Tiles are present and display the expected headings
    Given the user visits the Manuscript Dashboard page: default
     Then each Tile displays the expected heading
      | Tile Name   | Tile Heading                                        |
      | references  | References                                          |
      | plagiarism  | Plagiarism check (iThenticate)                      |
      | authors     | Authors                                             |
      | scope_match | Closest published papers in your and other journals |
      | trending    | Trending of this topic in your journal              |
      | originality | Closest published papers by submission's authors    |

  @tardis_smoke
  Scenario Outline: Verify that the Show/Hide Details buttons behave as expected for all the Tiles
    Given the user visits the Manuscript Dashboard page: default
    Then the user clicks on the Show Details button for the <Tile Name> tile
     Then the respective details section is displayed for the <Tile Name> tile
      And the <Tile Name> tile details heading is correct
      And the <Tile Name> tile details definition is as expected
      And clicking on the hide details for the <Tile Name> tile hides the details section
  Examples:
    |   tile     |
    | references  |
    | authors     |
    | scope_match |
    | trending    |
    | originality |

#  Scenario: Verify that the rating functionality works as expected
