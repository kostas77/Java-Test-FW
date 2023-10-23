Feature: Other important features in regional FR e-commerce
  As a customer I will be able give review and product to mini cart

  @FRHS_NON_E2E @Review @FRHS_REGRESSION_PROD
  Scenario: Existing user can add a review to the product
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I search for the product "Anatomie" under product type "Livres"
      And I add a review to a product in FR-HS
          | Nick Name            | Summary | Review |
          | FR Test Automation   |  Test   |  Test  |

  @FRHS_NON_E2E @FRHS_CICD @FRHS_REGRESSION_PROD
  Scenario: User can select category by product format from Navigation Bar
    Given I navigate to the "France" regional health store home page
      And I select books from product type
      And I add a book to cart in FR-HS

  @FRHS_NON_E2E @EmptyCart @FRHS_REGRESSION_PROD
  Scenario: User can clear the cart
    Given I navigate to the "France" regional health store home page
      And I search for the product "Anatomie" under product type "Livres"
     When I add the product to the cart in France HealthStore
      And I remove the product from cart in FR-HS
