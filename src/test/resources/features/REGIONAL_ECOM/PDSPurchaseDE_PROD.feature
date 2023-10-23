Feature: PDS Product purchase E2E tests for German regional health store
  As a customer
  I want to be able to purchase PDS product

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @DEHS_PDS_REGRESSION_PROD @GermanyHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de+pds@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I select ClinicalKey now from product type in navigation bar
     Then I add ClinicalKey now private license for "1 month" to cart
      And I proceed to the checkout page
      And I navigate to payment page and enter the payment details in DE-HS
         | Card Number         | Expiry Date | CVV | Name on card    |
         | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @DEHS_PDS_REGRESSION_PROD @GermanyHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license by applying a offer code
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de+pds@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I select ClinicalKey now from product type in navigation bar
     Then I add ClinicalKey now private license for "1 month" to cart
     Then I apply for a offer code "Test" in "DE"
      And I proceed to the checkout page
      And I navigate to payment page and enter the payment details in DE-HS
         | Card Number         | Expiry Date | CVV | Name on card    |
         | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |


  @DEHS_PDS_REGRESSION_PROD @GermanyHealthStore @ClinicalKey_PROD @ignore #TODO: RSR-6508
  Scenario: Existing user can buy ClinicalKey Now private license along with physical product
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de+pds@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I select ClinicalKey now from product type in navigation bar
     Then I add ClinicalKey now private license for "1 month" to cart
      And I search for the product "BASICS" under product type "Buch"
     When I add the product to the cart in Germany HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
         | Street Address    | City   | ZipCode | Phone Number |
         | Schwedter Straße  | Berlin | 10435   | 9876543210   |
     And I navigate to payment page and enter the payment details in DE-HS
        | Card Number         | Expiry Date | CVV | Name on card    |
        | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @GermanyHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license with a different country billing address
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @DEHealthStore @etax
  Scenario: Clinical Key Now Tax Verification in UAT and PROD for Austria Address in German Health Store
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de+pds1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details in DE-HS
      | Street Address          | City   | Country     |ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Gumpendorfer Strasse 42 | Tyrol  | Österreich  |6522    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom.de3@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details in DE-HS
      | Street Address          | City   | Country     |ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Gumpendorfer Strasse 42 | Tyrol  | Österreich  |6522    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @DEHealthStore @etax
  Scenario: Clinical Key Now Tax Verification in UAT and PROD for Switzerland Address in German Health Store
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details in DE-HS
      | Street Address  | City    | Country   |ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Rosenweg 120    | Lauerz  | Schweiz   |2540    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom.de3@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details in DE-HS
      | Street Address  | City    | Country   |ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Rosenweg 120    | Lauerz  | Schweiz   |2540    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

 @DEHealthStore @etax
  Scenario: Clinical Key Now Tax Verification in UAT and PROD for German Health Store
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom.de3@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "1 month" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @DEHS_PDS_REGRESSION_PROD @GermanyHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license free trial
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "15 days trial" to cart
    And I proceed to the checkout page
  #In DE-HS free trial PDS don't navigates to Payment page.
    #And I navigate to payment page and enter the payment details in DE-HS
    #  | Card Number         | Expiry Date | CVV | Name on card    |
    #  | 4111 1111 4555 1142 | 0330 | 737 | Test Automation |

  @DEHS_PDS_REGRESSION_PROD @GermanyHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license free trial along with physical product
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I add ClinicalKey now private license for "15 days trial" to cart
    And I search for the product "BASICS" under product type "Buch"
    When I add the product to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Germany HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Schwedter Straße  | Berlin | 10435   | 9876543210   |
    And I navigate to payment page and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |