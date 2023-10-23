Feature: Product purchase from LATAM store
  As a customer
  I want to be able to purchase E-books
  So that they will be delivered to me

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @LATAMHealthStore @LATAMHS_REGRESSION_PROD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "LATAM" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+latam@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the E-Book related to "Heart"
     When I add the E-book to the cart in LATAM health store
      And I proceed to the checkout page
      And I navigate to payment page to select the payment method and enter the payment details in LATAM Health store
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @LATAMHealthStore @LATAMHS_REGRESSION_PROD
  Scenario: E-Book can be purchased by signing into existing account in checkout path
    Given I navigate to the "LATAM" regional health store home page
      And I search for the E-Book related to "Heart"
     When I add the E-book to the cart in LATAM health store
      And I proceed to the checkout page
      And I login from the checkout page with username "test.elsevier.regional.ecom+latam@gmail.com" and password "Spoon!23" in LATAM Health store
      And I navigate to payment page to select the payment method and enter the payment details in LATAM Health store
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @LATAMHealthStore @LATAMHS_REGRESSION_PROD @ExistingUser
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "LATAM" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+latam@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the E-Book related to "Heart"
     Then I add the product to wishlist from product page
      And I add the product to cart from wishlist page in LATAM-HS
      And I proceed to the checkout page
      And I navigate to payment page to select the payment method and enter the payment details in LATAM Health store
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @LATAMHealthStore @LATAMHS_REGRESSION_PROD @ExistingUser
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "LATAM" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+latam@gmail.com" and password "Spoon!23"
      And I empty the cart
     Then I navigate to my account page
      And I navigate to my orders page
     Then I reorder from existing purchase
      And I proceed to the checkout page
      And I navigate to payment page to select the payment method and enter the payment details in LATAM Health store
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
