Feature: Author Hub
  All different Author Hub sections are visible and accessible to a user

  @authorhub_a11y
  Scenario: Verify that the Author Hub page complies with A11y requirements
    Given a user logs in to the Author Hub
     Then the current page is tested for A11y
