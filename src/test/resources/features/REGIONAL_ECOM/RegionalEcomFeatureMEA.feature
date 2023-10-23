Feature: Other important features in regional MEA e-commerce
  As a customer I will be able give review and product to mini cart

  @MEHS_NON_E2E @MEHS_REGRESSION_PROD
  Scenario: Existing user can add a review to the product
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I search for the product "Anatomy" under product type "Books"
      And I add a review to a product
          | Nick Name            | Summary | Review |
          | EU Test Automation   |  Test   |  Test  |

  @MEHS_NON_E2E @MEHS_CICD @MEHS_REGRESSION_PROD
  Scenario: User can select category by product format from Navigation Bar
    Given I navigate to the "Middle East" regional health store home page
      And I select books from product type
      And I add a book to cart

  @MEHS_NON_E2E @MEHS_REGRESSION_PROD
  Scenario: User can clear the cart
    Given I navigate to the "Middle East" regional health store home page
      And I search for the product "Anatomy" under product type "Books"
     When I add the product to the cart
      And I remove the product from cart
