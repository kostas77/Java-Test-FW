Feature: PDS Product purchase E2E tests for EU regional health store
  As a customer
  I want to be able to purchase different PDS product (ClinicalKey, ClinicalKey Pharmacology & ClinicalKey Student )

  @EUHS_PDS_E2E @EUHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months)
    Given I navigate to the "EU" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
      | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in EU-HS
      | Street Address            | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+eu" and password "Spoon1@3"
    Then The order is validated in Magento Admin "EU"

  @EUHS_PDS_E2E @EUHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey subscription by applying a offer code
    Given I navigate to the "EU" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
      | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    Then I apply for a offer code "FixedDiscount25" in "EU"
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in EU-HS
      | Street Address            | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"

  @EUHS_PDS_E2E @EUHealthStore @ClinicalKey
  Scenario: Existing user can buy multiple ClinicalKey subscription with different Speciality and get a 30% discount (12 month only)
    Given I navigate to the "EU" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
      | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Internal Medicine Extended"
    Then I add subscription to cart "12 months"
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page with discount
    And I navigate to payment page and enter the billing address and payment details in EU-HS
      | Street Address            | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"

  @EUHS_PDS_E2E @EUHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey subscription along with physical product
    Given I navigate to the "EU" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
      | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    And I search for the product "Anatomy" under product type "Books"
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

  @EUHS_PDS_E2E @EUHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey subscription along with e-book
    Given I navigate to the "EU" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
      | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    And I search for the E-Book "eyes" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in EU-HS
      | Street Address            | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"

  @EUHS_PDS_E2E @EUHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months) with a different country billing address
    Given I navigate to the "EU" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
      | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "Try Essentials"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in EU-HS
      | Street Address            | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"

  @EUHS_PDS_E2E @EUHealthStore @ClinicalKey
  Scenario: Existing user can buy free trial ClinicalKey subscription
    Given I navigate to the "EU" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession         | Speciality     |
      | EU Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "Buy Essentials"
    Then I add subscription to cart "15 days trial"
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in EU-HS
      | Street Address            | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "EU"

  @EUHS_PDS_E2E @EUHealthStore @ClinicalKey
  Scenario: Subscription product can be purchased by creating account in the checkout flow
    Given I navigate to the "EU" regional health store home page
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "12 months"
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

  @EUHS_PDS_E2E @EUHealthStore @ClinicalKey
  Scenario: Subscription product can be purchased by creating account in the checkout with a physical product in cart
    Given I navigate to the "EU" regional health store home page
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "12 months"
    And I search for the product "Anatomy" under product type "Books"
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
