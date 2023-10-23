Feature: Product purchase E2E tests for UK regional health store
  As a customer, I want to be able to purchase different product types (printbook, ebook, journal,...), so that they will be delivered to me

  @UKHealthStore @UKHS_E2E
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Dental Secrets" under product type "Books"
     When I add the product to the cart
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
           | Street Address | City   | ZipCode | Phone Number |
           | Oxford Street  | London | E1 6AN  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
            | Card Number         | Expiry Date | CVV | Name on card    |
            | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "UK"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+uk" and password "Spoon1@4"
    Then The order is validated in Magento Admin "UK"
    Then I verify the Order Confirmation mail "UK"
#    And   I receive an vital source email
#    And   I receive an Elsevier new order confirmation email

  @UKHealthStore @UKHS_E2E
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the journal "Maturitas" under product type "Journals"
     When I add the journal to the cart in UK HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
            | Street Address | City   | ZipCode | Phone Number |
            | Oxford Street  | London | E1 6AN  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
            | Card Number         | Expiry Date | CVV | Name on card    |
            | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E @UKHS_CICD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "UK" regional health store home page
      And I sign-up as a new user
        | Given Name    | Family Name          | Password | Title | Profession        | Speciality |
        | UK Automation | Regional HealthStore | Spoon!23 |  Dr.  | Medical Students  | Surgery    |
     Then I verify the Account Creation mail "UK"
      And I search for the E-Book "Anderson’s Pediatric Cardiology" under product type "eBooks"
     When I add the E-book to the cart in UK HealthStore
      And I proceed to the checkout page
      And I navigate to payment page and enter the billing address and payment details in UK-HS
         | Street Address | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
         | Oxford Street  | London | E1 6AN  | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path using Master card
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in UK healthstore
        | Product Type | Product                      |
        | Books        | Anatomy                      |
        | Journals     | Maturitas                    |
        | Flash Cards  | Cell Biology Playing Cards   |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
           | Street Address | City   | ZipCode | Phone Number |
           | Oxford Street  | London | E1 6AN  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
           | Card Number         | Expiry Date | CVV | Name on card    |
           | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
      And I place the order with Mastercard
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "UK" regional health store home page
      And I search for the product "Anatomy" under product type "Books"
     When I add the product to the cart
      And I proceed to the checkout page
      And I login from the checkout page with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I navigate to shipping page and enter the shipping details in UK HealthStore
          | Street Address | City   | ZipCode | Phone Number |
          | Oxford Street  | London | E1 6AN  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E @GuestUserCheckout @UKHS_CICD
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "UK" regional health store home page
      And I search for different product under different product type and add them to cart in UK healthstore
          | Product Type | Product   |
          | Books        | Anatomy   |
          | Journals     | Maturitas |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in UK Healthstore
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "UK" regional health store home page
      And I search for different product under different product type and add them to cart in UK healthstore
          | Product Type | Product   |
          | Books        | Anatomy   |
          | Journals     | Maturitas |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in UK Healthstore
          |Title | First Name    | Last Name  | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details with different billing address in UK-HS
          |Title | First Name    | Last Name            | Street Address     | City         | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Mr.  | UK Automation | Regional HealthStore | St Peter's Square  | Manchester   | M1 1AA   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in UK healthstore
          | Product Type | Product                      |
          | Books        | Anatomy                      |
          | Journals     | Maturitas                    |
          | Flash Cards  | Cell Biology Playing Cards   |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I navigate to payment page as a existing user and select different billing address and enter payment details in UK-HS
          |Title | First Name    | Last Name            | Street Address     | City         | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Mr.  | UK Automation | Regional HealthStore | St Peter's Square  | Manchester   | M1 1AA   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E @Wishlist @ignore #TODO: RSR-5646
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Anatomy" under product type "Books"
     Then I add the product to wishlist from product page
      And I add the product to cart from wishlist page
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E @Reorder
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
    And I empty the cart
     Then I navigate to my account page
      And I navigate to my orders page
     Then I reorder from existing purchase
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E @Paypal
  Scenario: Book can be purchased using the existing account checkout with different payment(Paypal)
    Given I navigate to the "UK" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in UK HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Oxford Street  | London | E1 6AN  | 9876543210   |
    And I navigate to payment page and choose paypal payment
      | Paypal Username                                | Password |
      | test.elsevier.regional.ecom+paypaluk@gmail.com | Spoon!23 |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E
  Scenario: Book can be purchased using the existing account checkout path with 60% discount coupon
    Given I navigate to the "UK" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    Then I apply for a offer code "60%off" and validate price details in "UK"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in UK HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Oxford Street  | London | E1 6AN  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "UK"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+uk" and password "Spoon1@4"
    Then The order is validated in Magento Admin "UK"

  @UKHealthStore @UKHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path with fixed discount coupon
    Given I navigate to the "UK" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in UK healthstore
      | Product Type | Product   |
      | Books        | Anatomy   |
      | Journals     | Maturitas |
      | Flash Cards  | Netter's Anatomy Flash Cards   |
    Then I apply for a offer code "FixedDiscount25" and validate price details in "UK"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in UK HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Oxford Street  | London | E1 6AN  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
    And I place the order with Mastercard
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path with Whole cart discount coupon
    Given I navigate to the "UK" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in UK healthstore
      | Product Type | Product   |
      | Books        | Anatomy   |
      | Journals     | Maturitas |
      | Flash Cards  | Netter's Anatomy Flash Cards   |
    Then I apply for a offer code "WholeCartFixedDiscount40" and validate price details in "UK"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in UK HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Oxford Street  | London | E1 6AN  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
    And I place the order with Mastercard
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E
  Scenario: Book can be purchased using the existing account checkout path from New Titles Menu
    Given I navigate to the "UK" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a Book product in "UK" from the New Titles Menu
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in UK HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Oxford Street  | London | E1 6AN  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E
  Scenario: UK-HS Multi-product purchase by creating account in the checkout with a eBook in cart
    Given I navigate to the "UK" regional health store home page
    And I search for different product under different product type and add them to cart in UK healthstore
      | Product Type | Product                         |
      | Books        | Anatomy                         |
      | eBooks       | Anderson’s Pediatric Cardiology |
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession        | Speciality |
      | UK Automation | Regional HealthStore | Spoon!23 |  Dr.  | Medical Students  | Surgery    |
    And I navigate to shipping page and enter the shipping details in UK HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Oxford Street  | London | E1 6AN  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E
  Scenario: UK-HS Product purchase by creating account in the checkout flow
    Given I navigate to the "UK" regional health store home page
    And I search for the product "Dental Secrets" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession        | Speciality |
      | UK Automation | Regional HealthStore | Spoon!23 |  Dr.  | Medical Students  | Surgery    |
    And I navigate to shipping page and enter the shipping details in UK HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Oxford Street  | London | E1 6AN  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "UK"

  @UKHealthStore @UKHS_E2E
  Scenario: UK-HS eBook can be purchased by creating account in the checkout flow
    Given I navigate to the "UK" regional health store home page
    And I search for the E-Book "Anderson’s Pediatric Cardiology" under product type "eBooks"
    When I add the E-book to the cart in UK HealthStore
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession        | Speciality |
      | UK Automation | Regional HealthStore | Spoon!23 |  Dr.  | Medical Students  | Surgery    |
    And I navigate to payment page and enter the billing address and payment details in UK-HS
      | Street Address | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Oxford Street  | London | E1 6AN  | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "UK"