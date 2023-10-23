Feature: Other important features in regional Spain e-commerce
  As a customer I will be able give review and product to mini cart

  @SPHS_NON_E2E @SPHS_REGRESSION_PROD
  Scenario: Existing user can add a review to the product
    Given I navigate to the "Spain" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
      And I search for the product "Anatomía" under product type "Libros"
      And I add a review to a product in SP-HS
          | Nick Name               | Summary | Review |
          | Spain Test Automation   |  Test   |  Test  |

  @SPHS_NON_E2E @SPHS_CICD @SPHS_REGRESSION_PROD
  Scenario: User can select category by product format from Navigation Bar
    Given I navigate to the "Spain" regional health store home page
      And I select books from product type
      And I add a book to cart in SP-HS

  @SPHS_NON_E2E @SPHS_REGRESSION_PROD
  Scenario: User can clear the cart
    Given I navigate to the "Spain" regional health store home page
      And I search for the product "Anatomía" under product type "Libros"
     When I add the product to the cart in Spain HealthStore
      And I remove the product from cart in SP-HS
