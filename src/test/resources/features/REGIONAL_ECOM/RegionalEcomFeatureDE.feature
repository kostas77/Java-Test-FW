Feature: Other important features in regional Germany e-commerce
  As a customer I will be able give review and product to mini cart

  @DEHS_NON_E2E @Review @DEHS_REGRESSION_PROD
  Scenario: Existing user can add a review to the product
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de@gmail.com" and password "Spoon!23"
      And I search for the product "Anatomie" under product type "Buch"
      And I add a review to a product in DE-HS
          | Nick Name            | Summary | Review |
          | DE Test Automation   |  Test   |  Test  |

  @DEHS_NON_E2E @DEHS_CICD @DEHS_REGRESSION_PROD
  Scenario: User can select category by product format from Navigation Bar
    Given I navigate to the "Germany" regional health store home page
      And I select books from product type
      And I add a book to cart in DE-HS

  @DEHS_NON_E2E @DEHS_REGRESSION_PROD
  Scenario: User can clear the cart
    Given I navigate to the "Germany" regional health store home page
      And I search for the product "Anatomie" under product type "Buch"
     When I add the product to the cart in Germany HealthStore
      And I remove the product from cart in DE-HS
