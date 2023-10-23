Feature: PDS Product purchase E2E tests for IN regional health store
  As a customer
  I want to be able to purchase different PDS product

  @INHS_PDS_E2E @INHealthStore @ClinicalKey
  Scenario: Existing user can buy 1 Month ClinicalKey subscription
    Given I navigate to the "India" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | IN Automation | Regional HealthStore | Spoon!23 | Dr   | Physician  | Cardiology  |
    Then I verify the Account Creation mail "IN"
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "1 Month" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in IN-HS
      | Payment Method | Street Address  | City    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |OTP   |
      | Credit Card    | Ascendas        | Chennai | 600113  | 9876543210   | 4591 5000 0000 0055 | 0229        | 123 | Test Automation |123456|
    And I place the order in Indian Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "IN"

  @INHS_PDS_E2E @INHealthStore @ClinicalKey
  Scenario: Existing user can buy 3 Months ClinicalKey subscription
    Given I navigate to the "India" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | IN Automation | Regional HealthStore | Spoon!23 | Dr   | Physician  | Cardiology  |
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "3 Months" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in IN-HS
      | Payment Method | Street Address  | City    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |OTP   |
      | Credit Card    | Ascendas        | Chennai | 600113  | 9876543210   | 4591 5000 0000 0055 | 0229        | 123 | Test Automation |123456|
    And I place the order in Indian Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "IN"

  @INHS_PDS_E2E @INHealthStore @ClinicalKey
  Scenario: Existing user can buy 12 Months ClinicalKey subscription
    Given I navigate to the "India" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | IN Automation | Regional HealthStore | Spoon!23 | Dr   | Physician  | Cardiology  |
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "12 Months" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in IN-HS
      | Payment Method | Street Address  | City    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |OTP   |
      | Credit Card    | Ascendas        | Chennai | 600113  | 9876543210   | 4591 5000 0000 0055 | 0229        | 123 | Test Automation |123456|
    And I place the order in Indian Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "IN"

  @INHS_PDS_E2E @INHealthStore @ClinicalKey
  Scenario: Existing user can buy 15 Days Free Trial ClinicalKey subscription
    Given I navigate to the "India" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | IN Automation | Regional HealthStore | Spoon!23 | Dr   | Physician  | Cardiology  |
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "15 Days trial" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in IN-HS
      | Payment Method | Street Address  | City    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |OTP   |
      | Credit Card    | Ascendas        | Chennai | 600113  | 9876543210   | 4591 5000 0000 0055 | 0229        | 123 | Test Automation |123456|
    And I place the order in Indian Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "IN"

  @INHS_PDS_E2E @INHealthStore @ClinicalKey
  Scenario: Existing user can buy 3 Months ClinicalKey subscription
    Given I navigate to the "India" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | IN Automation | Regional HealthStore | Spoon!23 | Dr   | Physician  | Cardiology  |
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "3 Months" to cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in IN-HS
      | Payment Method | Street Address  | City    | ZipCode | Phone Number | UPI ID        |
      | UPI            | Ascendas        | Chennai | 600113  | 9876543210   | testvpa@icici |
   # And I place the order in Indian Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "IN"

  @INHS_PDS_E2E @INHealthStore @ClinicalKey
  Scenario: Subscription product can be purchased by creating account in the checkout flow
    Given I navigate to the "India" regional health store home page
    And I navigate to Clinical Key Now Page in India Health Store
    Then I add ClinicalKey now license for "3 Months" to cart
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | IN Automation | Regional HealthStore | Spoon!23 | Dr   | Physician  | Cardiology  |
    And I navigate to payment page and enter the billing address and payment details in IN-HS
      | Payment Method | Street Address  | City    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |OTP   |
      | Credit Card    | Ascendas        | Chennai | 600113  | 9876543210   | 4591 5000 0000 0055 | 0229        | 123 | Test Automation |123456|
    And I place the order in Indian Health Store
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "IN"