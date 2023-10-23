Feature: PDS Product purchase E2E tests for IN regional health store
  As a customer
  I want to be able to purchase different PDS product

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @INHS_PDS_REGRESSION_PROD @INHealthStore @ClinicalKey
  Scenario: Existing user can buy 1 Month ClinicalKey subscription
    Given I navigate to the "India" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+in+pds1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "1 Month" to cart
    And I proceed to the checkout page
    And I navigate to payment page as a existing user and select a billing address and enter payment details IN-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4591 5000 0000 0055 | 0229        | 123 | Test Automation |

  @INHS_PDS_REGRESSION_PROD @INHealthStore @ClinicalKey
  Scenario: Existing user can buy 3 Months ClinicalKey subscription
    Given I navigate to the "India" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+in+pds1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "3 Months" to cart
    And I proceed to the checkout page
    And I navigate to payment page as a existing user and select a billing address and enter payment details IN-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4591 5000 0000 0055 | 0229        | 123 | Test Automation |

  @INHS_PDS_REGRESSION_PROD @INHealthStore @ClinicalKey
  Scenario: Existing user can buy 12 Months ClinicalKey subscription
    Given I navigate to the "India" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+in+pds1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "12 Months" to cart
    And I proceed to the checkout page
    And I navigate to payment page as a existing user and select a billing address and enter payment details IN-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4591 5000 0000 0055 | 0229        | 123 | Test Automation |

  @INHS_PDS_REGRESSION_PROD @INHealthStore @ClinicalKey
  Scenario: Existing user can buy 15 Days Free Trial ClinicalKey subscription
    Given I navigate to the "India" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+in+pds1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "15 Days trial" to cart
    And I proceed to the checkout page
    And I navigate to payment page as a existing user and select a billing address and enter payment details IN-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4591 5000 0000 0055 | 0229        | 123 | Test Automation |

  @INHS_PDS_REGRESSION_PROD @INHealthStore @ClinicalKey @etax
  Scenario: Existing user can buy 1 Month ClinicalKey subscription
    Given I navigate to the "India" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+in+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "1 Month" to cart
    And I proceed to the checkout page
    And I navigate to payment page as a existing user and select a billing address and enter payment details IN-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4591 5000 0000 0055 | 0229        | 123 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "India" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | IN Automation | Regional HealthStore | Spoon!23 | Dr   | Physician  | Cardiology  |
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "1 Month" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in IN-HS
      | Payment Method | Street Address  | City    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |OTP   |
      | Credit Card    | Ascendas        | Chennai | 600113  | 9876543210   | 4591 5000 0000 0055 | 0229        | 123 | Test Automation |123456|
    Then ETax percentage is compared between UAT and PROD
