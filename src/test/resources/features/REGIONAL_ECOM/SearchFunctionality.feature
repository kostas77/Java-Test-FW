Feature: Search Functionality in regional ecom website

  @regional
  Scenario Outline: When a customer searches for a product, all the products related to the input given should be displayed
    Given Customer is on the main page of website
    When Customer searches for a product "<product>"
    Then search page should display the list of searched product "<product>"
    Examples:
      | product |
      | Heart   |
