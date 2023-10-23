Feature: Product purchase from Middle East store
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Anatomy" under product type "Books"
     When I add the product to the cart
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page and enter the payment details in Middle East HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "MEA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+mea" and password "Spoon1@4"
    Then The order is validated in Magento Admin "MEA"
    Then I verify the Order Confirmation mail "MEA"
#    And   I receive an vital source email
#    And   I receive an Elsevier new order confirmation email

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the journal "Journal of Geriatric Oncology" under product type "Journals"
     When I add the journal to the cart in Middle East HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page and enter the payment details in Middle East HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E @MEHS_CICD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "Middle East" regional health store home page
      And I sign-up as a new user
        | Given Name     | Family Name          | Password | Title | Profession         | Speciality     |
        | MEA Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
     Then I verify the Account Creation mail "MEA"
      And I search for the E-Book "Anderson’s Pediatric Cardiology" under product type "eBooks"
     When I add the E-book to the cart in Middle East HealthStore
      And I proceed to the checkout page
      And I navigate to payment page and enter the billing address and payment details in MEA-HS
         | Street Address      | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
         | Al Markhiya Street  | Riyadh | 00000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path using Master card
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in Middle East HealthStore
          | Product Type | Product                       |
          | Books        | Anatomy                       |
          | Journals     | Journal of Geriatric Oncology |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
      And I place the order with Mastercard
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "Middle East" regional health store home page
      And I search for the product "Anatomy" under product type "Books"
     When I add the product to the cart
      And I proceed to the checkout page
      And I login from the checkout page with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E @GuestUserCheckout @MEHS_CICD
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "Middle East" regional health store home page
      And I search for different product under different product type and add them to cart in Middle East HealthStore
          | Product Type | Product                       |
          | Books        | Anatomy                       |
          | Journals     | Journal of Geriatric Oncology |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in Middle East HealthStore
          |Title | First Name     | Last Name            | Street Address     | City   | ZipCode | Country              | Phone Number |
          | Mr.  | MEA Automation | Regional HealthStore | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210    |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "Middle East" regional health store home page
      And I search for different product under different product type and add them to cart in Middle East HealthStore
          | Product Type | Product                       |
          | Books        | Anatomy                       |
          | Journals     | Journal of Geriatric Oncology |
#          | DVD          | Nursing                       |
          | Flash Cards  | Medical                       |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in Middle East HealthStore
          |Title | First Name     | Last Name            | Street Address     | City   | ZipCode | Country              | Phone Number |
          | Mr.  | MEA Automation | Regional HealthStore | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210    |
      And I navigate to payment page to select the payment method and enter the payment details with different billing address in MEA-HS
          |Title | First Name     | Last Name            | Street Address      | City     | ZipCode  | Country | Phone Number  | Card Number          | Expiry Date | CVV | Name on card    |
          | Mr.  | MEA Automation | Regional HealthStore | Al Markhiya Street  | Doha     | 00000    |Qatar    | 9876543210    | 4111 1111 4555 1142  | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in Middle East HealthStore
          | Product Type | Product                       |
          | Books        | Anatomy                       |
          | Journals     | Journal of Geriatric Oncology |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page as a existing user and select different billing address and enter payment details in MEA-HS
          |Title | First Name     | Last Name            | Street Address      | City     | Country  | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Mr.  | MEA Automation | Regional HealthStore | Al Markhiya Street  | Doha     | Qatar    | 00000    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Anatomy" under product type "Books"
     Then I add the product to wishlist from product page
      And I add the product to cart from wishlist page
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I empty the cart
     Then I navigate to my account page
      And I navigate to my orders page
     Then I reorder from existing purchase
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E @Paypal
  Scenario: Book can be purchased using the existing account checkout with different payment(Paypal)
    Given I navigate to the "Middle East" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Middle East HealthStore
      | Street Address     | City   | ZipCode |    Country           |Phone Number |
      | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
    And I navigate to payment page and choose paypal payment
      | Paypal Username                        | Password |
      | sb-43gxm226941181@personal.example.com | f+?m8UxQ |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: Book can be purchased using the existing account checkout path with 60% discount offer
    Given I navigate to the "Middle East" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    Then I apply for a offer code "60%off" and validate price details in "MEA"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Middle East HealthStore
      | Street Address    | City  | ZipCode | Country              | Phone Number |
      | The palm jumeirah | Dubai | 000007  | United Arab Emirates | 9876543210   |
    And I navigate to payment page and enter the payment details in Middle East HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "MEA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+mea" and password "Spoon1@4"
    Then The order is validated in Magento Admin "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path With fixed discount coupon
    Given I navigate to the "Middle East" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in Middle East HealthStore
      | Product Type | Product                       |
      | Books        | Anatomy                       |
      | Journals     | Journal of Geriatric Oncology |
    Then I apply for a offer code "FixedDiscount25" and validate price details in "MEA"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Middle East HealthStore
      | Street Address    | City  | ZipCode | Country              | Phone Number |
      | The palm jumeirah | Dubai | 000007  | United Arab Emirates | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
    And I place the order with Mastercard
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path With whole cart discount coupon
    Given I navigate to the "Middle East" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in Middle East HealthStore
      | Product Type | Product                       |
      | Books        | Anatomy                       |
      | Journals     | Journal of Geriatric Oncology |
    Then I apply for a offer code "WholeCartFixedDiscount40" and validate price details in "MEA"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Middle East HealthStore
      | Street Address    | City  | ZipCode | Country              | Phone Number |
      | The palm jumeirah | Dubai | 000007  | United Arab Emirates | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
    And I place the order with Mastercard
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: Book can be purchased using the existing account checkout path from New Titles Menu
    Given I navigate to the "Middle East" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a Book product in "MEA" from the New Titles Menu
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Middle East HealthStore
      | Street Address     | City   | ZipCode |    Country           |Phone Number |
      | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
    And I navigate to payment page and enter the payment details in Middle East HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: MEA-HS Multi-product purchase by creating account in the checkout with a eBook in cart
    Given I navigate to the "Middle East" regional health store home page
    And I search for different product under different product type and add them to cart
      | Product Type | Product                |
      | Books        | Periodontics           |
      | eBooks       | Travel Medicine E-Book |
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name     | Family Name          | Password | Title | Profession         | Speciality     |
      | MEA Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I navigate to shipping page and enter the shipping details in Middle East HealthStore
      | Street Address     | City   | ZipCode |    Country           |Phone Number |
      | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
    And I navigate to payment page and enter the payment details in Middle East HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: MEA-HS Product purchase by creating account in the checkout flow
    Given I navigate to the "Middle East" regional health store home page
    And I search for the product "Periodontics" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name     | Family Name          | Password | Title | Profession         | Speciality     |
      | MEA Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I navigate to shipping page and enter the shipping details in Middle East HealthStore
      | Street Address     | City   | ZipCode |    Country           |Phone Number |
      | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
    And I navigate to payment page and enter the payment details in Middle East HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "MEA"

  @MiddleEastHealthStore @MEHS_E2E
  Scenario: MEA-HS eBook can be purchased by creating account in the checkout flow
    Given I navigate to the "Middle East" regional health store home page
    And I search for the E-Book "Anderson’s Pediatric Cardiology" under product type "eBooks"
    When I add the E-book to the cart in Middle East HealthStore
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name     | Family Name          | Password | Title | Profession         | Speciality     |
      | MEA Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
    And I navigate to payment page and enter the billing address and payment details in MEA-HS
      | Street Address      | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Al Markhiya Street  | Riyadh | 00000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "MEA"