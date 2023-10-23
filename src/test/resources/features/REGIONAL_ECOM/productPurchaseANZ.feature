Feature: Product purchase E2E tests for ANZ regional health store
  As a customer, I want to be able to purchase different product types (printbook, ebook, journal,...), so that they will be delivered to me

  @ANZHealthStore @ANZHS_E2E
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"
    Then I verify the Order Confirmation mail "ANZ"

  @ANZHealthStore @ANZHS_E2E
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "ANZ" regional health store home page
    And I sign-up as a new user
      | Given Name     | Family Name          | Password | Title | Profession | Speciality |
      | ANZ Automation | Regional HealthStore | Spoon!23 |  Dr.  | Dentist    | Endodontics |
    Then I verify the Account Creation mail "ANZ"
    And I search for the E-Book "Orthobiologics, An Issue of Physical Medicine" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in ANZ-HS
      | Street Address | City   | ZipCode | Phone Number | Card Number         | Expiry Date  | CVV | Name on card    |
      | Oxford Street  | Sydney | 1000    | 9876543210   | 4111 1111 4555 1142 | 0330         | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @ExistingUser
  Scenario: ANZ-HS Multi-product purchase using the existing account checkout path
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product                                       |
      | Books        | Atlas of Liver Pathology                      |
      | eBooks       | Orthobiologics, An Issue of Physical Medicine |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "ANZ" regional health store home page
    And I search for different product under different product type and add them to cart in ANZ healthstore
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in ANZ Healthstore
      |Title | First Name     | Last Name            | Street Address | City    | ZipCode  | Phone Number |
      | Mr.  | ANZ Automation | Regional HealthStore | Crown Street   | Sydney   | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "ANZ" regional health store home page
    And I search for different product under different product type and add them to cart in ANZ healthstore
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in ANZ Healthstore
      |Title | First Name     | Last Name            | Street Address | City    | ZipCode  | Phone Number |
      | Mr.  | ANZ Automation | Regional HealthStore | Crown Street   | Sydney   | 1000    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details with different billing address in ANZ-HS
      | Title | First Name     | Last Name            | Street Address | City       | ZipCode | Country     | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr.   | ANZ Automation | Regional HealthStore | Lambton Quay   | Wellington | 2000    | New Zealand | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in ANZ healthstore
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page as a existing user and select different billing address and enter payment details in ANZ-HS
      | Title | First Name     | Last Name            | Street Address | City       | ZipCode | Country     | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr    | ANZ Automation | Regional HealthStore | Lambton Quay   | Wellington | 2000    | New Zealand | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @ExistingUser
  Scenario: ANZ-HS Multi-product purchase using the existing account checkout path
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "ANZ" regional health store home page
    And I search for different product under different product type and add them to cart in ANZ healthstore
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in ANZ Healthstore
      |Title | First Name     | Last Name            | Street Address | City    | ZipCode  | Phone Number |
      | Mr.  | ANZ Automation | Regional HealthStore | Crown Street   | Sydney   | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @ExistingUser
  Scenario: ANZ-HS Multi-product purchase using the existing account checkout path using Master card
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
    And I place the order with Mastercard
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in ANZ healthstore
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page as a existing user and select different billing address and enter payment details in ANZ-HS
      |Title | First Name     | Last Name            | Street Address | City         | ZipCode  | Country       | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr.  | ANZ Automation | Regional HealthStore | Lambton Quay   | Wellington   | 2000     |  New Zealand  | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @Wishlist
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    Then I add the product to wishlist from product page
    And I add the product to cart from wishlist page
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @Reorder
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    Then I navigate to my account page
    And I navigate to my orders page
    Then I reorder from existing purchase
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @Paypal
  Scenario: Book can be purchased using the existing account checkout with different payment(PaypalExpress)
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and choose paypalExpress payment
      | Paypal Username                       | Password |
      | sb-ej0nq26913938@personal.example.com | 4}O>ecXp |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E
  Scenario: Book can be purchased using the existing account checkout path with 60% discount coupon
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    Then I apply for a offer code "60%off" and validate price details in "ANZ"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @ExistingUser
  Scenario: ANZ-HS Multi-product purchase using the existing account checkout path with fixed discount
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product                                       |
      | Books        | Atlas of Liver Pathology                      |
      | eBooks       | Orthobiologics, An Issue of Physical Medicine |
    Then I apply for a offer code "FixedDiscount25" and validate price details in "ANZ"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @ExistingUser
  Scenario: ANZ-HS Multi-product purchase using the existing account checkout path with whole cart discount
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product                                       |
      | Books        | Atlas of Liver Pathology                      |
      | eBooks       | Orthobiologics, An Issue of Physical Medicine |
    Then I apply for a offer code "WholeCartFixedDiscount40" and validate price details in "ANZ"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @ignore #TODO: RSR-6145
  Scenario: Book can be purchased using the existing account checkout path using Reward points(100% price discount using Reward points)
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I use reward points to purchase in ANZ
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @Reward @ignore #TODO: RSR-6476
  Scenario: Book can be purchased using the existing account checkout path using Reward points
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I use reward points to purchase in ANZ
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @Reward @ignore #TODO: RSR-6476
  Scenario: Multi product can be purchased using the existing account checkout path using Reward points
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product                                       |
      | Books        | Atlas of Liver Pathology                      |
      | eBooks       | Orthobiologics, An Issue of Physical Medicine |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I use reward points to purchase in ANZ
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "ANZ" regional health store home page
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I login from the checkout page with username "test.elsevier.regional.ecom+anz1@gmail.com" and password "Spoon!23"
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"
    Then I verify the Order Confirmation mail "ANZ"

  @ANZHealthStore @ANZHS_E2E @ExistingUser
  Scenario: ANZ-HS Multi-product purchase by creating account in the checkout with a eBook in cart
    Given I navigate to the "ANZ" regional health store home page
    And I search for different product under different product type and add them to cart
      | Product Type | Product                                       |
      | Books        | Atlas of Liver Pathology                      |
      | eBooks       | Orthobiologics, An Issue of Physical Medicine |
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name     | Family Name          | Password | Title | Profession | Speciality |
      | ANZ Automation | Regional HealthStore | Spoon!23 |  Dr.  | Dentist    | Endodontics |
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E @ExistingUser
  Scenario: ANZ-HS product purchase by creating account in the checkout flow
    Given I navigate to the "ANZ" regional health store home page
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name     | Family Name          | Password | Title | Profession | Speciality |
      | ANZ Automation | Regional HealthStore | Spoon!23 |  Dr.  | Dentist    | Endodontics |
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"

  @ANZHealthStore @ANZHS_E2E
  Scenario: ANZ-HS eBook can be purchased by creating account in the checkout flow
    Given I navigate to the "ANZ" regional health store home page
    And I search for the E-Book "Orthobiologics, An Issue of Physical Medicine" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name     | Family Name          | Password | Title | Profession | Speciality  |
      | ANZ Automation | Regional HealthStore | Spoon!23 |  Dr.  | Dentist    | Endodontics |
    And I navigate to payment page and enter the billing address and payment details in ANZ-HS
      | Street Address | City   | ZipCode | Phone Number | Card Number         | Expiry Date  | CVV | Name on card    |
      | Oxford Street  | Sydney | 1000    | 9876543210   | 4111 1111 4555 1142 | 0330         | 737 | Test Automation |
    And I place the order for ANZ-HS
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ANZ"