Feature: Other important features in regional LATAM e-commerce
  As a customer I will be able give review and product to mini cart

  @LATAMHS_NON_E2E @LATAMHS_REGRESSION_PROD
  Scenario: Existing user can add a review to the product
    Given I navigate to the "LATAM" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+latam@gmail.com" and password "Spoon!23"
      And I search for the E-Book related to "corazón"
      And I add a review to a product in LATAM-HS
          | Nick Name               | Summary | Review |
          | Latam Test Automation   |  Test   |  Test  |

  @LATAMHS_NON_E2E @LATAMHS_REGRESSION_PROD
  Scenario: User can clear the cart
    Given I navigate to the "LATAM" regional health store home page
      And I search for the E-Book related to "corazón"
     When I add the E-book to the cart in LATAM health store
      And I remove the product from cart in LATAM-HS
