Feature: Product purchase E2E tests for German regional health store
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me

  @GermanyHealthStore @DEHS_E2E
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de3@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Heilmittel" under product type "Buch"
     When I add the product to the cart in Germany HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order in German Health Store
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "DE"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+de" and password "Spoon1@4"
    Then The order is validated in Magento Admin "DE"
    Then I verify the Order Confirmation mail "DE"
#    And   I receive an vital source email
#    And   I receive an Elsevier new order confirmation email

  @GermanyHealthStore @DEHS_E2E
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de3@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the journal "Lyme-Borreliose" under product type "Loseblatt"
     When I add the journal to the cart in Germany HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order in German Health Store
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E @DEHS_CICD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "Germany" regional health store home page
      And I sign-up as a new user
         | Given Name    | Family Name          | Password | Title | Profession | Speciality |
         | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
     Then I verify the Account Creation mail "DE"
      And I search for the E-Book "Facharztwissen Hämatologie Onkologie" under product type "eBooks"
     When I add the E-book to the cart in Germany HealthStore
      And I proceed to the checkout page
      And I navigate to payment page and enter the billing address and payment details in DE-HS
         | Street Address   | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
         | Schwedter Straße | Berlin | 10435   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order in German Health Store
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de3@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in Germany HealthStore
        | Product Type | Product                                        |
        | Buch         | Schmerzmedizin                                 |
        | Loseblatt    | Harnwegsinfektionen                            |
        | DVD          | Heilpraktiker-Prüfungstraining interaktiv DVD  |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order in German Health Store
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "Germany" regional health store home page
      And I search for the product "Heilmittel" under product type "Buch"
     When I add the product to the cart in Germany HealthStore
      And I proceed to the checkout page
      And I login from the checkout page with username "test.elsevier.regional.ecom+de3@gmail.com" and password "Spoon!23"
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order in German Health Store
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de3@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in Germany HealthStore
        | Product Type | Product                                        |
        | Buch         | Schmerzmedizin                                 |
        | Loseblatt    | Harnwegsinfektionen                            |
        | DVD          | Heilpraktiker-Prüfungstraining interaktiv DVD  |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page as a existing user and select different billing address and enter payment details in DE-HS
          |Title  | First Name | Last Name  | Street Address  | City        | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Herr  | DE Test    | Automation | Neue Kräme      | frankfurt   | 60306    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order in German Health Store
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de3@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Heilmittel" under product type "Buch"
     Then I add the product to wishlist from product page in DE-HS
      And I add the product to cart from wishlist page in DE-HS
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order in German Health Store
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de3@gmail.com" and password "Spoon!23"
      And I empty the cart
     Then I navigate to my account page
      And I navigate to my orders page
     Then I reorder from existing purchase
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order in German Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E @DEHS_CICD
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "Germany" regional health store home page
      And I search for different product under different product type and add them to cart in Germany HealthStore
          | Product Type | Product         |
          | Buch         | Heilmittel      |
          | Loseblatt    | Lyme-Borreliose |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in Germany Healthstore
          |Title  | First Name | Last Name  | Street Address    | Country     | City    | ZipCode  | Phone Number |
          | Herr  | DE Test    | Automation | Schwedter Straße  | Deutschland | Berlin  | 10435    | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order in German Health Store
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E
  Scenario: Multi-product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "Germany" regional health store home page
      And I search for different product under different product type and add them to cart in Germany HealthStore
          | Product Type | Product                                        |
          | Buch         | Schmerzmedizin                                 |
          | Loseblatt    | Harnwegsinfektionen                            |
          | DVD          | Heilpraktiker-Prüfungstraining interaktiv DVD  |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in Germany Healthstore
          |Title | First Name | Last Name  | Street Address    |  Country     |City     | ZipCode   | Phone Number |
          | Herr | DE Test    | Automation | Schwedter Straße  | Deutschland  | Berlin  | 10435     | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details with different billing address in DE-HS
          |Title  | First Name | Last Name  | Street Address     | City        | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Herr  | DE Test    | Automation | Neue Kräme         | frankfurt   | 60306    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order in German Health Store
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E
  Scenario: Multi product can be purchased using the existing account checkout path with fixed discount coupon
    Given I navigate to the "Germany" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
    # And I empty the cart
   # And I search for the product "Anatomie" under product type "Buch"
    And I search for different product under different product type and add them to cart in Germany HealthStore
      | Product Type | Product         |
      | Buch         | Heilmittel      |
      | Loseblatt    | Lyme-Borreliose |
      | DVD          | Heilpraktiker-Prüfungstraining interaktiv DVD   |
    Then I apply for a offer code "FixedDiscount25" and validate price details in "DE"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Germany HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Schwedter Straße  | Berlin | 10435   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order in German Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E
  Scenario: Multi products can be purchased using the existing account checkout path with fixed discount coupon whole cart
    Given I navigate to the "Germany" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
    # And I empty the cart
   # And I search for the product "Anatomie" under product type "Buch"
    And I search for different product under different product type and add them to cart in Germany HealthStore
      | Product Type | Product         |
      | Buch         | Heilmittel      |
      | Loseblatt    | Lyme-Borreliose |
    Then I apply for a offer code "WholeCartFixedDiscount40" and validate price details in "DE"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Germany HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Schwedter Straße  | Berlin | 10435   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order in German Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E @DEHS_CICD
  Scenario: E-Book can be purchased using the new account checkout with 60% discount coupon
    Given I navigate to the "Germany" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
    And I search for the E-Book "BASICS Chirurgie" under product type "eBooks"
    When I add the E-book to the cart in Germany HealthStore
    Then I apply for a offer code "60%off" and validate price details in "DE"
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in DE-HS
      | Street Address   | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Schwedter Straße | Berlin | 10435   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order in German Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E @Paypal
  Scenario: Book can be purchased using the existing account checkout with different payment(Paypal)
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de3@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Heilmittel" under product type "Buch"
    When I add the product to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Germany HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Schwedter Straße  | Berlin | 10435   | 9876543210   |
    And I navigate to payment page and choose paypal payment in DE
      | Paypal Username                       | Password |
      | sb-rw3mi26940724@personal.example.com | w%1)<OKE |
    And I place the order
    Then The order is displayed on the thank you page

  @GermanyHealthStore @DEHS_E2E
  Scenario: Book can be purchased using the existing account checkout path from New Titles Menu
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de3@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a Book product in "DE" from the New Titles Menu
    When I add the product to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Germany HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Schwedter Straße  | Berlin | 10435   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order in German Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E
  Scenario: Book can be purchased using the existing account checkout path from New Titles Menu
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de3@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a Book product in "DE" from the New Titles Menu
    When I add the product to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Germany HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Schwedter Straße  | Berlin | 10435   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order in German Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E
  Scenario: DE-HS Product purchase by creating account in the checkout flow
    Given I navigate to the "Germany" regional health store home page
    And I search for the product "Heilmittel" under product type "Buch"
    When I add the product to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
    And I navigate to shipping page and enter the shipping details in Germany HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Schwedter Straße  | Berlin | 10435   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order in German Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"

  @GermanyHealthStore @DEHS_E2E
  Scenario: DE-HS eBook can be purchased by creating account in the checkout flow
    Given I navigate to the "Germany" regional health store home page
    And I search for the E-Book "Facharztwissen Hämatologie Onkologie" under product type "eBooks"
    When I add the E-book to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
    And I navigate to payment page and enter the billing address and payment details in DE-HS
      | Street Address   | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Schwedter Straße | Berlin | 10435   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order in German Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"