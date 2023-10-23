Feature: PDS Product purchase E2E tests for French regional health store
  As a customer
  I want to be able to purchase PDS product

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license (See all products)
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And  I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+fr+pds" and password "Spoon1@4"
    Then The order is validated in Magento Admin "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license (Select your specialty)
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "Hématologie"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license (Select your specialty) by web format
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "Hématologie"
    And I add ClinicalKey subscription to cart
      | Format | country | Subscription duration |
      | Web    | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address details in FR-HS
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license (Select your specialty) by Web + Paper + Mobile format
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "Imagerie médicale"
    And I add ClinicalKey subscription to cart
      | Format    | country | Subscription duration |
      | Web + App | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address details in FR-HS
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy two different ClinicalKey Now private license
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "Hématologie"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I search for the cknow "Angéiologie" under product type "ClinicalKey Now"
    And I add ClinicalKey subscription to cart
      | Format    | country | Subscription duration |
      | Web + App | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license by applying a offer code
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    Then I apply for a offer code "Test" in "FR"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license along with physical product
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I search for the product "Anatomie" under product type "Livres"
    When I add the product to the cart in France HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license along with e-book
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I search for the E-Book "L'infirmier(e) en gériatrie" under product type "eBooks"
    When I add the E-book to the cart in France HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license (See all products) with a different country billing address
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and select different billing address in France HealthStore
      | Street Address | City      | ZipCode | Phone Number | Country |
      | Rue du Panier  | Marseille | 13000   | 9876543210   | France  |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: User can buy ClinicalKey Now product with free trial subscription
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | Trial                 |
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address details in FR-HS
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And  I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: user can buy ClinicalKey Now private license Free trial along with physical product
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | Trial                 |
    And I search for the product "Anatomie" under product type "Livres"
    When I add the product to the cart in France HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license free trial along with e-book
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | Trial                 |
    And I search for the E-Book "L'infirmier(e) en gériatrie" under product type "eBooks"
    When I add the E-book to the cart in France HealthStore
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address details in FR-HS
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license along with EMC
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I select EMC from product type in navigation bar
    When I select a product "Savoirs et soins infirmiers" from the EMC Category
    When I add EMC to the cart in France HealthStore
      | Format                               | Status      | Country | Subscription Duration |
      | ClinicalKey Now & EMC - Web + Mobile | Particulier | France  | 36 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license free trial along with EMC
    Given I navigate to the "France" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now "All" and select the Product "Dermatologie"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | Trial                 |
    And I select EMC from product type in navigation bar
    When I select a product "Podologie" from the EMC Category
    When I add EMC to the cart in France HealthStore
      | Format               | Status      | Country | Subscription Duration |
      | EMC - Offre Internet | Particulier | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Subscription product can be purchased by creating account in the checkout flow
    Given I navigate to the "France" regional health store home page
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "Hématologie"
    And I add ClinicalKey subscription to cart
      | Format | country | Subscription duration |
      | Web    | France  | 12 months             |
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I navigate to payment page and enter the billing address details in FR-HS
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"

  @FRHS_PDS_E2E @FranceHealthStore @ClinicalKey
  Scenario: Subscription product can be purchased by creating account in the checkout with a physical product in cart
    Given I navigate to the "France" regional health store home page
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "Hématologie"
    And I add ClinicalKey subscription to cart
      | Format | country | Subscription duration |
      | Web    | France  | 12 months             |
    And I search for the product "Anatomie" under product type "Livres"
    When I add the product to the cart in France HealthStore
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title    | Profession | Speciality |
      | FR Automation | Regional HealthStore | Spoon!23 | MONSIEUR | Etudiant   | Biologie   |
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "FR"