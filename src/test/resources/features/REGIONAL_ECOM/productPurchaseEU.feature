Feature: Product purchase from EU store
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me

  @EUHealthStore @EUHS_E2E
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "EU" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Periodontics" under product type "Books"
     When I add the product to the cart
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in EU HealthStore
          | Street Address | City   | ZipCode | Country | Phone Number |
          | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "EU"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+eu" and password "Spoon1@3"
    Then The order is validated in Magento Admin "EU"
    Then I verify the Order Confirmation mail "EU"
#    And   I receive an vital source email
#    And   I receive an Elsevier new order confirmation email

  @EUHealthStore @EUHS_E2E
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "EU" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the journal "Maturitas" under product type "Journals"
     When I add the journal to the cart in EU HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in EU HealthStore
          | Street Address | City   | ZipCode | Country | Phone Number |
          | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "EU"


  @EUHealthStore @EUHS_E2E @EUHS_CICD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "EU" regional health store home page
      And I sign-up as a new user
          | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
          | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
     Then I verify the Account Creation mail "EU"
      And I search for the E-Book "e-book" under product type "eBooks"
     When I add the E-book to the cart in Europe Health Store
      And I proceed to the checkout page
      And I navigate to payment page and enter the billing address and payment details in EU-HS
        | Street Address            | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
        | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path using Master card
    Given I navigate to the "EU" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in EU healthstore
          | Product Type | Product       |
          | Books        | Periodontics  |
          | Journals     | Maturitas     |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in EU HealthStore
          | Street Address | City   | ZipCode | Country | Phone Number |
          | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
        | Card Number         | Expiry Date | CVV | Name on card    |
        | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
      And I place the order with Mastercard
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "EU" regional health store home page
      And I search for the product "Periodontics" under product type "Books"
     When I add the product to the cart
      And I proceed to the checkout page
      And I login from the checkout page with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
      And I navigate to shipping page and enter the shipping details in EU HealthStore
          | Street Address | City   | ZipCode | Country | Phone Number |
          | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E @GuestUserCheckout @EUHS_CICD
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "EU" regional health store home page
      And I search for different product under different product type and add them to cart in EU healthstore
          | Product Type | Product                         |
          | Books        | Periodontics                    |
          | Journals     | International Emergency Nursing |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in EU Healthstore
          |Title | First Name        | Last Name             | Street Address | City   | ZipCode | Country | Phone Number |
          | Mr.  | EU Automation     | Regional HealthStore  | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "EU" regional health store home page
      And I search for different product under different product type and add them to cart in EU healthstore
          | Product Type | Product                         |
          | Books        | Periodontics                    |
          | Journals     | International Emergency Nursing |
#          | DVD          | Nursing                         |
          | Flash Cards  | Pharmacology Flash Cards        |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in EU Healthstore
          |Title | First Name    | Last Name            | Street Address  | City   | ZipCode | Country | Phone Number |
          | Mr.  | EU Automation | Regional HealthStore |  Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details with different billing address in EU-HS
          |Title | First Name        | Last Name              | Street Address       | City         | ZipCode  | Country | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Mr.  | EU Automation     | Regional HealthStore   |  Via de' Tornabuoni  | Florence     | 50100    |  Italy  | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "EU" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in EU healthstore
          | Product Type | Product        |
          | Books        | Periodontics   |
          | Journals     | Maturitas      |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in EU HealthStore
          | Street Address | City   | ZipCode | Country | Phone Number |
          | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page as a existing user and select different billing address and enter payment details in EU-HS
          |Title | First Name        | Last Name             | Street Address      | City         |Country    | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Mr.  | EU Automation     | Regional HealthStore  |  Via de' Tornabuoni  | Florence     | Italy     |50100     | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "EU" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Periodontics" under product type "Books"
     Then I add the product to wishlist from product page
      And I add the product to cart from wishlist page in EU-HS
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in EU HealthStore
          | Street Address | City   | ZipCode | Country | Phone Number |
          | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "EU" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
      And I empty the cart
     Then I navigate to my account page
      And I navigate to my orders page
     Then I reorder from existing purchase
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in EU HealthStore
          | Street Address | City   | ZipCode | Country | Phone Number |
          | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E @ignore #TODO : RSR-6256
  Scenario: E-Book can be purchased using the new account checkout with 60% discount
    Given I navigate to the "EU" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
      | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I search for the E-Book "Anderson’s Pediatric Cardiology" under product type "eBooks"
    When I add the E-book to the cart in Europe Health Store
    Then I apply for a offer code "60%off" and validate price details in "EU"
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in EU-HS
      | Street Address            | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E @ignore #TODO : RSR-6256
  Scenario: Multi-product can be purchased using the existing account checkout path with fixed discount
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in EU healthstore
      | Product Type | Product       |
      | Books        | Periodontics  |
      | Journals     | Maturitas     |
    Then I apply for a offer code "FixedDiscount25" and validate price details in "EU"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E @ignore #TODO : RSR-6256
  Scenario: Multi products can be purchased using the existing account checkout path with fixed discount whole cart
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in EU healthstore
      | Product Type | Product      |
      | Books        | Periodontics |
      | Journals     | Maturitas    |
    Then I apply for a offer code "WholeCartFixedDiscount40" and validate price details in "EU"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E @Paypal
  Scenario: Book can be purchased using the existing account checkout with different payment(Paypal)
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Periodontics" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page and choose paypal payment
      | Paypal Username                                | Password |
      | test.elsevier.regional.ecom+paypaleu@gmail.com | Spoon!23 |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E
  Scenario: Book can be purchased using the existing account checkout path from New Titles Menu
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a Book product in "EU" from the New Titles Menu
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+eu" and password "Spoon1@3"
    Then The order is validated in Magento Admin "EU"

  @EUHealthStore @EUHS_E2E
  Scenario: EU-HS Multi-product purchase by creating account in the checkout with a eBook in cart
    Given I navigate to the "EU" regional health store home page
    And I search for different product under different product type and add them to cart
      | Product Type | Product                |
      | Books        | Periodontics           |
      | eBooks       | Travel Medicine E-Book |
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
      | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E
  Scenario: EU-HS Product purchase by creating account in the checkout flow
    Given I navigate to the "EU" regional health store home page
    And I search for the product "Periodontics" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
      | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"

  @EUHealthStore @EUHS_E2E
  Scenario: EU-HS eBook can be purchased by creating account in the checkout flow
    Given I navigate to the "EU" regional health store home page
    And I search for the E-Book "e-book" under product type "eBooks"
    When I add the E-book to the cart in Europe Health Store
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
      | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I navigate to payment page and enter the billing address and payment details in EU-HS
      | Street Address            | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"