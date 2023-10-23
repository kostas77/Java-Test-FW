Feature: Product purchase from LATAM store
  As a customer
  I want to be able to purchase E-books
  So that they will be delivered to me

  @LATAMHealthStore @LATAMHS_E2E @LATAMHS_CICD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "LATAM" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+latam@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the E-Book related to "Anatomía"
     When I add the E-book to the cart in LATAM health store
      And I proceed to the checkout page
      And I navigate to payment page to select the payment method and enter the payment details in LATAM Health store
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "LATAM"
      And I login into Magento Admin with username "test.elsevier.regional.ecom+latam" and password "Spoon1@4"
     Then The order is validated in Magento Admin "LATAM"
     Then I verify the Order Confirmation mail "LATAM"

  @LATAMHealthStore @LATAMHS_E2E @LATAMHS_CICD
  Scenario: E-Book can be purchased by signing into existing account in checkout path
    Given I navigate to the "LATAM" regional health store home page
      And I search for the E-Book related to "Anatomía"
     When I add the E-book to the cart in LATAM health store
      And I proceed to the checkout page
      And I login from the checkout page with username "test.elsevier.regional.ecom+latam@gmail.com" and password "Spoon!23" in LATAM Health store
      And I navigate to payment page to select the payment method and enter the payment details in LATAM Health store
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "LATAM"

  @LATAMHealthStore @LATAMHS_E2E @ExistingUser
  Scenario: Existing user can make a purchase from wishlist using Master card
    Given I navigate to the "LATAM" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+latam@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book related to "corazón"
    Then I add the product to wishlist from product page
    And I add the product to cart from wishlist page in LATAM-HS
    And I proceed to the checkout page
    And I navigate to payment page to select the payment method and enter the payment details in LATAM Health store
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
    And I place the order with Mastercard
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "LATAM"

  @LATAMHealthStore @LATAMHS_E2E @ExistingUser
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
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "LATAM"

  @LATAMHealthStore @LATAMHS_E2E @ExistingUser
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
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "LATAM"

  @LATAMHealthStore @LATAMHS_E2E @ExistingUser
  Scenario: Product purchase by creating account in the checkout flow
    Given I navigate to the "LATAM" regional health store home page
    And I search for the E-Book related to "Anatomía"
    When I add the E-book to the cart in LATAM health store
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name       | Family Name          | Password | Title | Profession         | Speciality     |
      | LATAM Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I navigate to payment page and enter the billing address and payment details in LATAM Health store
      | Street Address  | City          | ZipCode | Country  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Madero Street   | Mexico City   | 01000   |  México  | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "LATAM"