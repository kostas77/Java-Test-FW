Feature: PDS Product purchase E2E tests for EU regional health store
  As a customer
  I want to be able to purchase different PDS product (ClinicalKey)

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @EUHS_PDS_REGRESSION_PROD @EUHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months)
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHS_PDS_REGRESSION_PROD @EUHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months) by applying a offer code
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    Then I apply for a offer code "30offtest" in "EU"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHS_PDS_REGRESSION_PROD @EUHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy multiple ClinicalKey subscription with different Speciality and get a 30% discount (12 month only)
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Neurology"
    Then I add subscription to cart "12 months"
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page with discount
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHS_PDS_REGRESSION_PROD @EUHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months) along with physical product
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHS_PDS_REGRESSION_PROD @EUHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months) along with e-book
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Dentistry"
    Then I add subscription to cart "1 month"
    And I search for the E-Book "Decompressive Techniques" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHS_PDS_REGRESSION_PROD @EUHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months) with a different country billing address
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "Try Essentials"
    Then I add subscription to cart "1 month"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHS_PDS_REGRESSION_PROD @EUHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months)
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "Buy Essentials"
    Then I add subscription to cart "15 days trial"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |