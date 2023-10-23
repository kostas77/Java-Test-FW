Feature: PDS Product purchase E2E tests for US regional health store
  As a customer
  I want to be able to purchase different PDS product (ClinicalKey, ClinicalKey Pharmacology & ClinicalKey Student )

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+us+pds" and password "Spoon1@3"
    Then The order is validated in Magento Admin "US"
    Then I verify the Order Confirmation mail for Clinical Key Products "US"


  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Pharmacology subscription (15 days trial, 1 month)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "Clinical Pharmacology" from product type in navigation bar
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"


  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Student subscription (15 days trial, 1 month, 3 months & 6 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey Student" from product type in navigation bar
    Then I select a Q-Bank "Buy Step 2 Q-Bank"
    And I add CK Student subscription to cart "6 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey @ignore #TODO :RSR-6256
  Scenario: Existing user can buy ClinicalKey subscription by applying a offer code
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    Then I apply for a offer code "30offtest" in "US"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy multiple ClinicalKey subscription with different Speciality and get a 30% discount (12 month only)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Internal Medicine Extended"
    Then I add subscription to cart "12 months"
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page with discount
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey subscription along with physical product
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details
      | Street Address | City          | State    | ZipCode | Phone Number |
      | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey subscription along with e-book
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    And I search for the E-Book "Heart" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @ClinicalKey @ignore
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months) with a different country billing address
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City   | Country        | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Oxford Street  | London | United Kingdom | E1 6AN  | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @ClinicalKey @ignore
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "Buy Essentials"
    Then I add subscription to cart "1 month"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
#    Then The order is validated with ORR API request "US"
    And  I navigate to the "US" regional health store home page
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    And I proceed to the checkout page

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey subscription 15 days trial
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Anesthesiology"
    Then I add subscription to cart "15 days trial"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    And I login into Magento Admin with username "test.elsevier.regional.ecom+us+pds" and password "Spoon1@3"
    Then The order is validated in Magento Admin "US"
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey subscription 15 days trial along with physical product
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Anesthesiology"
    Then I add subscription to cart "15 days trial"
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details
      | Street Address | City          | State    | ZipCode | Phone Number |
      | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: user can buy Clinical Pharmacology subscription (15 days trial)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "Clinical Pharmacology" from product type in navigation bar
    Then I add subscription to cart "15 days trial"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: user can buy Clinical Pharmacology subscription (15 days trial) along with physical product
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "Clinical Pharmacology" from product type in navigation bar
    Then I add subscription to cart "15 days trial"
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details
      | Street Address | City          | State    | ZipCode | Phone Number |
      | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: user can buy ClinicalKey Student subscription (15 days trial)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey Student" from product type in navigation bar
    Then I add subscription to cart "15 days trial"
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details
      | Street Address | City          | State    | ZipCode | Phone Number |
      | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy STATdx subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "STATdx"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy PATHPrimer subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "PATHPrimer"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy RADPrimer subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "RADPrimer"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy ImmunoQuery subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "ImmunoQuery"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy ExpertPath subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "ExpertPath"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"


  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy ExpertPath subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "ExpertPath"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy ImmunoQuery (Buy Bundle pack) subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "ImmunoQuery"
    Then I add subscription to cart "Buy Bundle pack"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy ExpertPath subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "ExpertPath"
    Then I add subscription to cart "Buy Bundle pack"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy ImmunoQuery (Buy Bundle pack Trial) subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "ImmunoQuery"
    Then I add subscription to cart "Buy Bundle pack Trial"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy Buy Bundle pack Trial subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "ExpertPath"
    Then I add subscription to cart "Buy Bundle pack Trial"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy ExpertPath subscription with physical product (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "ExpertPath"
    Then I add subscription to cart "12 months"
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details
      | Street Address | City          | State    | ZipCode | Phone Number |
      | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @PSSI
  Scenario: Existing user can buy RADPrimer subscription with eBook (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I search for the PSSi product "RADPrimer"
    Then I add subscription to cart "12 months"
    And I search for the E-Book "Heart" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Subscription product can be purchased by creating account in the checkout flow
    Given I navigate to the "US" regional health store home page
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Subscription product can be purchased by creating account in the checkout with a physical product in cart
    Given I navigate to the "US" regional health store home page
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "12 months"
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I navigate to shipping page and enter the shipping details
      | Street Address | City          | State    | ZipCode | Phone Number |
      | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Residents subscription (12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password |Title | Profession  | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 |  Dr.  | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select ClinicalKey Residents
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Psychiatry Residents"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address    | City          | State    |Country        | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      |23 Wall Street     | New York City | New York |United States  | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+us+pds" and password "Spoon1@3"
    Then The order is validated in Magento Admin "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Residents subscription (1 month)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password |Title | Profession  | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 |  Dr.  | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select ClinicalKey Residents
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Emergency Medicine Residents"
    Then I add subscription to cart "1 month"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address    | City          | State    |Country        | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      |23 Wall Street     | New York City | New York |United States  | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy ClinicalKey Residents subscription (Free Trial)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password |Title | Profession  | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 |  Dr.  | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select ClinicalKey Residents
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Obstetrics & Gynecology Residents"
    Then I add subscription to cart "15 days trial"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address    | City          | State    |Country        | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      |23 Wall Street     | New York City | New York |United States  | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHS_PDS_E2E @USHealthStore @ClinicalKey
  Scenario: Existing user can buy Multiple ClinicalKey Residents subscription and get a 30% discount (12 month only)
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password |Title | Profession  | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 |  Dr.  | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select ClinicalKey Residents
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Psychiatry Residents"
    Then I add subscription to cart "12 months"
    And I select "ClinicalKey" from product type in navigation bar
    And I select ClinicalKey Residents
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Emergency Medicine Residents"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page with discount
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address    | City          | State    |Country        | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      |23 Wall Street     | New York City | New York |United States  | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+us+pds" and password "Spoon1@3"
    Then The order is validated in Magento Admin "US"