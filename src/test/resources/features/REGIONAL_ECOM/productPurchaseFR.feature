Feature: Product purchase E2E tests for French regional health store
  As a customer, I want to be able to purchase different product types (printbook, ebook, journal,...), so that they will be delivered to me

  @FranceHealthStore @FRHS_E2E
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Anatomie" under product type "Livres"
     When I add the product to the cart in France HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in France HealthStore
          | Street Address            | City   | ZipCode | Phone Number |
          | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in FR-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "FR"
     Then I verify the Order Confirmation mail "FR"
#    And   The order is displayed in Magento Admin
#    And   I receive an vital source email
#    And   I receive an Elsevier new order confirmation email

  @FranceHealthStore @FRHS_E2E
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the journal "Anesthésie & Réanimation" under product type "Revues"
    When I add the journal to the cart in France HealthStore
      | Format    | Status       | Country | Subscription Duration |
      | Paper     | Institution  | France  | 36 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E @FRHS_CICD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
      And I empty the cart
      And I sign-up as a new user
         | Given Name    | Family Name          | Password |Title     | Profession  | Speciality |
         | FR Automation | Regional HealthStore | Spoon!23 |MONSIEUR  | Etudiant    | Biologie   |
     Then I verify the Account Creation mail "FR"
      And I search for the E-Book "Dermatologie" under product type "eBooks"
     When I add the E-book to the cart in France HealthStore
      And I proceed to the checkout page
      And I navigate to payment page and enter the billing address and payment details in FR-HS
         | Street Address   | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
         | Schwedter Straße | Berlin | 10435   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path using Master card
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in France healthstore
      | Product Type | Product            |
      | Livres       | Anatomie           |
      | Revues       | La Presse Médicale |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
    And I place the order with Mastercard
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "France" regional health store home page
    And I search for the product "Anatomie" under product type "Livres"
    When I add the product to the cart in France HealthStore
    And I proceed to the checkout page
    And I login from the checkout page with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E @GuestUserCheckout @FRHS_CICD
  Scenario: Product can be purchased using the guest user checkout path
    Given I navigate to the "France" regional health store home page
    And I search for different product under different product type and add them to cart in France healthstore
      | Product Type | Product  |
      | Livres       | Anatomie |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in France Healthstore
      |Title      | First Name    | Last Name            | Street Address            | City    | ZipCode  | Phone Number |
      | MONSIEUR  | FR Automation | Regional HealthStore | Avenue des Champs-Élysées | Paris   | 75000    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E @GuestUserCheckout
  Scenario: Product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "France" regional health store home page
    And I search for different product under different product type and add them to cart in France healthstore
      | Product Type | Product  |
      | Livres       | Anatomie |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in France Healthstore
      |Title      | First Name    | Last Name            | Street Address            | City    | ZipCode  | Phone Number |
      | MONSIEUR  | FR Automation | Regional HealthStore | Avenue des Champs-Élysées | Paris   | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details with different billing address in FR-HS
      |Title      | First Name    | Last Name            | Street Address     | City         | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | MONSIEUR  | FR Automation | Regional HealthStore | Rue du Panier      | Marseille    | 13000    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in France healthstore
      | Product Type | Product                   |
      | Revues       | Anesthésie & Réanimation  |
      | Livres       | Anatomie                  |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City    | ZipCode  | Phone Number |
      | Avenue des Champs-Élysées | Paris   | 75000    | 9876543210   |
    And I navigate to payment page as a existing user and select different billing address and enter payment details in FR-HS
      |Title      | First Name    | Last Name            | Street Address  | City         | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      |MONSIEUR   | FR Automation | Regional HealthStore | Rue du Panier   | Marseille    | 13000    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E @wishlist
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomie" under product type "Livres"
    Then I add the product to wishlist from product page in FR-HS
    And I add the product to cart from wishlist page in FR-HS
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode  | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E @Reorder
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I empty the cart
     Then I navigate to my account page
     And I navigate to my orders page
    Then I reorder from existing purchase
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode  | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E
  Scenario: Multi product can be purchased using the existing account checkout path with fixed discount coupon
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in France healthstore
      | Product Type | Product        |
      | Livres       | Les Addictions |
      | Livres       | Anatomie       |
    Then I apply for a offer code "FixedDiscount25" and validate price details in "FR"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City    | ZipCode  | Phone Number |
      | Avenue des Champs-Élysées | Paris   | 75000    | 9876543210   |
    And I navigate to payment page as a existing user and select different billing address and enter payment details in FR-HS
      |Title      | First Name    | Last Name            | Street Address  | City         | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      |MONSIEUR   | FR Automation | Regional HealthStore | Rue du Panier   | Marseille    | 13000    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E @FRHS_CICD
  Scenario: E-Book can be purchased using the new account checkout path with 60% offer coupon
    Given I navigate to the "France" regional health store home page
    And I empty the cart
    And I sign-up as a new user
      | Given Name    | Family Name          | Password |Title     | Profession  | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 |MONSIEUR  | Etudiant    | Biologie   |
    And I search for the E-Book "Chirurgie palpébrale" under product type "eBooks"
    When I add the E-book to the cart in France HealthStore
    Then I apply for a offer code "60%off" and validate price details in "FR"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path with fixed discount on whole cart price
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in France healthstore
      | Product Type | Product              |
      | eBooks       | Chirurgie palpébrale |
      | Livres       | Anatomie             |
    Then I apply for a offer code "WholeCartFixedDiscount40" and validate price details in "FR"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City    | ZipCode  | Phone Number |
      | Avenue des Champs-Élysées | Paris   | 75000    | 9876543210   |
    And I navigate to payment page as a existing user and select different billing address and enter payment details in FR-HS
      |Title     | First Name    | Last Name            | Street Address  | City         | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      |MONSIEUR  | FR Automation | Regional HealthStore | Rue du Panier   | Marseille    | 13000    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E @Paypal
  Scenario: Book can be purchased using the existing account checkout with different payment(Paypal)
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomie" under product type "Livres"
    When I add the product to the cart in France HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page and choose paypalExpress payment
      | Paypal Username                                | Password |
      | test.elsevier.regional.ecom+paypalfr@gmail.com | Spoon!23 |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E
  Scenario: EMC can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select EMC from product type in navigation bar
    When I select a product "Vétérinaire" from the EMC Category
    When I add EMC to the cart in France HealthStore
      | Format                   | Status       | Country | Subscription Duration |
      | EMC - Offre Mobile Plus  | Particulier  | France  | 36 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E
  Scenario: EMC ClinicalKey can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select EMC from product type in navigation bar
    When I select a product "Savoirs et soins infirmiers" from the EMC Category
    When I add EMC to the cart in France HealthStore
      | Format                                | Status       | Country | Subscription Duration |
      | ClinicalKey Now & EMC - Web + Mobile  | Particulier  | France  | 36 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @FRHS_E2E
  Scenario: EMC (Internet offer) can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select EMC from product type in navigation bar
    When I select a product "Podologie" from the EMC Category
    When I add EMC to the cart in France HealthStore
      | Format               | Status       | Country | Subscription Duration |
      | EMC - Offre Internet | Particulier  | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHealthStore @FRHS_E2E
  Scenario: FR-HS Multi-product purchase by creating account in the checkout with a eBook in cart
    Given I navigate to the "France" regional health store home page
    And I search for different product under different product type and add them to cart in France healthstore
      | Product Type | Product              |
      | Livres       | Anatomie             |
      | eBooks       | Chirurgie palpébrale |
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password |Title     | Profession  | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 |MONSIEUR  | Etudiant    | Biologie   |
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHealthStore @FRHS_E2E
  Scenario: FR-HS Product purchase by creating account in the checkout flow
    Given I navigate to the "France" regional health store home page
    And I search for the product "Anatomie" under product type "Livres"
    When I add the product to the cart in France HealthStore
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password |Title     | Profession  | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 |MONSIEUR  | Etudiant    | Biologie   |
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHealthStore @FRHS_E2E
  Scenario: FR-HS eBook can be purchased by creating account in the checkout flow
    Given I navigate to the "France" regional health store home page
    And I search for the E-Book "Dermatologie" under product type "eBooks"
    When I add the E-book to the cart in France HealthStore
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password |Title     | Profession  | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 |MONSIEUR  | Etudiant    | Biologie   |
    And I navigate to payment page and enter the billing address and payment details in FR-HS
      | Street Address   | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Schwedter Straße | Berlin | 10435   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"