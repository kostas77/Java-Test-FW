Feature: Other important features in regional UK e-commerce
  As a customer I will be able give review and product to mini cart

  @UKHS_NON_E2E @UKHS_REGRESSION_PROD
  Scenario: Existing user can add a review to the product
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I search for the product "Anatomy" under product type "Books"
      And I add a review to a product
          | Nick Name             | Summary | Review |
          | UK Test Automation    |  Test   |  Test  |

  @UKHS_NON_E2E @UKHS_CICD @UKHS_REGRESSION_PROD
  Scenario: User can select category by product format from Navigation Bar
    Given I navigate to the "UK" regional health store home page
      And I select books from product type
      And I add a book to cart

  @UKHS_NON_E2E @UKHS_REGRESSION_PROD
  Scenario: User can clear the cart
    Given I navigate to the "UK" regional health store home page
      And I search for the product "Anatomy" under product type "Books"
     When I add the product to the cart
      And I remove the product from cart
