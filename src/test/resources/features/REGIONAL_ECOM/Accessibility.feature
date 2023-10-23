Feature: Regional stores Accessibility testing

  @A11y
  Scenario: Multiple products can be purchased using the existing account checkout path
    Given I navigate to the "US" regional health store home page
     Then the current page is tested for A11y
