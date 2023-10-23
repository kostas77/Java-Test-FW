Feature: Other important features in regional ASIA e-commerce
  As a customer I will be able give review and product to mini cart

  @ASIAHS_NON_E2E @Review @ASIAHS_REGRESSION_PROD @ignore #TODO: RSR-6347
  Scenario: Existing user can add a review to the product
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I search for the product "Anatomy" under product type "Book"
    And I add a review to a product
      | Nick Name            | Summary | Review |
      | ASIA Test Automation | Test    | Test   |

  @ASIAHS_NON_E2E @ASIAHS_CICD @ASIAHS_REGRESSION_PROD
  Scenario: User can select category by product format from Navigation Bar
    Given I navigate to the "ASIA" regional health store home page
    And I select books from product type
    And I add the product to the cart

  @ASIAHS_NON_E2E @ASIAHS_REGRESSION_PROD
  Scenario: User can clear the cart
    Given I navigate to the "ASIA" regional health store home page
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I remove the product from cart