Feature: Manuscript Dashboard accessibility tests

  @tardis_a11y
  Scenario: Verify that the Manuscript Dashboard standalone page complies with A11y requirements
    Given the user visits the Manuscript Dashboard page: default
    Then the current page is tested for A11y

  @tardis_a11y
  Scenario Outline: Verify that the Manuscript Dashboard standalone page  complies with A11y requirements
    Given the user visits the Manuscript Dashboard page: default
     Then the user clicks on the Show Details button for the <Tile Name> tile
     Then the current page is tested for A11y
  Examples:
    | Tile Name   |
    | References  |
    | Authors     |
    | Scope Match |
    | Trending    |
    | Originality |
