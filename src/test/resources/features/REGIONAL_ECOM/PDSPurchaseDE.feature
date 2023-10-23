Feature: PDS Product purchase E2E tests for German regional health store
  As a customer
  I want to be able to purchase PDS product

  @DEHS_PDS_E2E @GermanyHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license
    Given I navigate to the "Germany" regional health store home page
      And I sign-up as a new user
          | Given Name    | Family Name          | Password | Title | Profession | Speciality |
          | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
      And I select ClinicalKey now from product type in navigation bar
     Then I add ClinicalKey now private license for "1 month" to cart
      And I proceed to the checkout page
      And I navigate to payment page and enter the billing address and payment details in DE-HS
         | Street Address   | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
         | Schwedter Straße | Berlin | 10435   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order in German Health Store
     Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+de+pds" and password "Spoon1@4"
    Then The order is validated in Magento Admin "DE"

  @DEHS_PDS_E2E @GermanyHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license by applying a offer code
    Given I navigate to the "Germany" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
    Then I apply for a offer code "Test" in "DE"
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in DE-HS
      | Street Address   | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Schwedter Straße | Berlin | 10435   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order in German Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"

  @DEHS_PDS_E2E @GermanyHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license along with physical product
    Given I navigate to the "Germany" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
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

  @DEHS_PDS_E2E @GermanyHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license with a different country billing address
    Given I navigate to the "Germany" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
    And I search for the product "Heilmittel" under product type "Buch"
    When I add the product to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Germany HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Schwedter Straße  | Berlin | 10435   | 9876543210   |
    And I navigate to payment page as a existing user and select different billing address and enter payment details in DE-HS
      |Title  | First Name | Last Name  | Street Address            | City    |  Country     | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Herr  | Carl       | Johnson    | Avenue des Champs-Élysées | Paris   |  Frankreich  |75000     | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order in German Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"

  @DEHS_PDS_E2E @GermanyHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license Free trial
    Given I navigate to the "Germany" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "15 days trial" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and proceed for free trial in DE-HS
      | Street Address   | City   | ZipCode | Phone Number |
      | Schwedter Straße | Berlin | 10435   | 9876543210   |
    #And I navigate to payment page and enter the billing address and payment details in DE-HS
    #  | Street Address   | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
    #  | Schwedter Straße | Berlin | 10435   | 9876543210   | 4111 1111 4555 1142 | 0330 | 737 | Test Automation |
    #And I place the order in German Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "DE"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+de+pds" and password "Spoon1@4"
    Then The order is validated in Magento Admin "DE"

  @DEHS_PDS_E2E @GermanyHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Now private license free trial along with physical product
    Given I navigate to the "Germany" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | DE Automation | Regional HealthStore | Spoon!23 | Herr  | Medizin    | Neurologie |
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "15 days trial" to cart
    And I search for the product "Krankenpflegeexamen" under product type "Buch"
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

  @DEHS_PDS_E2E @GermanyHealthStore @ClinicalKey
  Scenario: Subscription product can be purchased by creating account in the checkout flow
    Given I navigate to the "Germany" regional health store home page
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
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

  @DEHS_PDS_E2E @GermanyHealthStore @ClinicalKey
  Scenario: Subscription product can be purchased by creating account in the checkout with a physical product in cart
    Given I navigate to the "Germany" regional health store home page
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
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