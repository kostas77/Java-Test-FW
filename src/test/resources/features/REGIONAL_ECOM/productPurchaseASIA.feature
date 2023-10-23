Feature: Product purchase E2E tests for ASIA regional health store
  As a customer, I want to be able to purchase different product types (printbook, ebook, journal,...), so that they will be delivered to me

  @ASIAHealthStore @ASIAHS_E2E
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City          | ZipCode | Phone Number |
      | Rizal Avenue   | Caloocan City | 6024    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "ASIA" regional health store home page
    And I sign-up as a new user
      | Given Name      | Family Name          | Password | Title | Profession | Speciality  |
      | ASIA Automation | Regional HealthStore | Spoon!23 | Dr.   | Dentist    | Endodontics |
    Then I verify the Account Creation mail "ASIA"
    And I search for the E-Book "Orthobiologics, An Issue of Physical Medicine" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in ASIA-HS
      | Street Address            | City  | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E @ExistingUser
  Scenario: ASIA-HS Multi-product(book + ebook) purchase using the existing account checkout path
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product                                       |
      | Books        | Atlas of Liver Pathology                      |
      | eBooks       | Orthobiologics, An Issue of Physical Medicine |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City          | ZipCode | Phone Number |
      | Rizal Avenue   | Caloocan City | 6024    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E @GuestUserCheckout
  Scenario: ASIA-HS Multi-product(book + Flash cards) can be purchased using the guest user checkout path
    Given I navigate to the "ASIA" regional health store home page
    And I search for different product under different product type and add them to cart in ASIA healthstore
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in ASIA Healthstore
      | Title | First Name      | Last Name            | Street Address | City          | ZipCode | Phone Number |
      | Mr.   | ASIA Automation | Regional HealthStore | Rizal Avenue   | Caloocan City | 6024    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E
  Scenario: ASIA-HS Multi-product (Book + Flash Cards) can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in ASIA healthstore
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City          | ZipCode | Phone Number |
      | Rizal Avenue   | Caloocan City | 6024    | 9876543210   |
    And I navigate to payment page as a existing user and select different billing address and enter payment details in ASIA-HS
      | Title | First Name      | Last Name            | Street Address | City      | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr.   | ASIA Automation | Regional HealthStore | Lambton Quay   | Minato-ku | 6030    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E @ExistingUser
  Scenario: ASIA-HS Multi-product purchase (book + flash cards) using the existing account checkout path using Master card
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City          | ZipCode | Phone Number |
      | Rizal Avenue   | Caloocan City | 6024    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
    And I place the order with Mastercard
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E @GuestUserCheckout
  Scenario: ASIA-HS Multi-product(Book + Flash cards) can be purchased using the guest user checkout path
    Given I navigate to the "ASIA" regional health store home page
    And I search for different product under different product type and add them to cart in ASIA healthstore
      | Product Type | Product         |
      | Books        | Nursing         |
     # | eBooks       | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in ASIA Healthstore
      | Title | First Name      | Last Name            | Street Address | City      | ZipCode | Phone Number |
      | Mr.   | ASIA Automation | Regional HealthStore | Rizal Avenue   | Minato-ku | 6024    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@3"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E @GuestUserCheckout
  Scenario: ASIA-HS Multi-product (Book + ebook + Flash Cards) can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "ASIA" regional health store home page
    And I search for different product under different product type and add them to cart in ASIA healthstore
      | Product Type | Product         |
      | Books        | Nursing         |
      #| eBooks       | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in ASIA Healthstore
      | Title | First Name      | Last Name            | Street Address | City       | ZipCode | Phone Number |
      | Mr.   | ASIA Automation | Regional HealthStore | Ayala Avenue   | Pasay City | 6024    | 9876543210   |
    And I navigate to payment page as a existing user and select different billing address and enter payment details in ASIA-HS
      | Title | First Name      | Last Name            | Street Address | City      | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr.   | ASIA Automation | Regional HealthStore | Lambton Quay   | Minato-ku | 6030    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E @Wishlist
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Nursing" under product type "Books"
    Then I add the product to wishlist from product page
    And I add the product to cart from wishlist page
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Rizal Avenue   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E @Reorder
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I empty the cart
    Then I navigate to my account page
    And I navigate to my orders page
    Then I reorder from existing purchase
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Rizal Avenue   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E
  Scenario: Book can be purchased using the existing account checkout path with percentage discount
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Nursing" under product type "Books"
    When I add the product to the cart
    Then I apply for a offer code "60%off" and validate price details in "ASIA"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City          | ZipCode | Phone Number |
      | Rizal Avenue   | Caloocan City | 6024    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E @ExistingUser
  Scenario: ASIA-HS Multi-product purchase (book + flash cards) using the existing account checkout path with whole cart discount
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    Then I apply for a offer code "WholeCartFixedDiscount40" and validate price details in "ASIA"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City          | ZipCode | Phone Number |
      | Rizal Avenue   | Caloocan City | 6024    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 1111 1111 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E @ExistingUser
  Scenario: ASIA-HS Multi-product purchase (book + flash cards) using the existing account checkout path with Fixed discount
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    Then I apply for a offer code "FixedDiscount25" and validate price details in "ASIA"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City          | ZipCode | Phone Number |
      | Rizal Avenue   | Caloocan City | 6024    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 1111 1111 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"

  @ASIAHealthStore @ASIAHS_E2E @ExistingUser
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "ASIA" regional health store home page
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I login from the checkout page with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City          | ZipCode | Phone Number |
      | Rizal Avenue   | Caloocan City | 6024    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 1111 1111 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"

  @ASIAHealthStore @ASIAHS_E2E
  Scenario: ASIA-HS Multi-product purchase by creating account in the checkout with a eBook in cart
    Given I navigate to the "ASIA" regional health store home page
    And I search for different product under different product type and add them to cart
      | Product Type | Product                                       |
      | Books        | Atlas of Liver Pathology                      |
      | eBooks       | Orthobiologics, An Issue of Physical Medicine |
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name      | Family Name          | Password | Title | Profession | Speciality  |
      | ASIA Automation | Regional HealthStore | Spoon!23 | Dr.   | Dentist    | Endodontics |
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City          | ZipCode | Phone Number |
      | Rizal Avenue   | Caloocan City | 6024    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 1111 1111 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"

  @ASIAHealthStore @ASIAHS_E2E
  Scenario: ASIA-HS Product purchase by creating account in the checkout flow
    Given I navigate to the "ASIA" regional health store home page
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name      | Family Name          | Password | Title | Profession | Speciality  |
      | ASIA Automation | Regional HealthStore | Spoon!23 | Dr.   | Dentist    | Endodontics |
    And I navigate to shipping page and enter the shipping details in ASIA HealthStore
      | Street Address | City          | ZipCode | Phone Number |
      | Rizal Avenue   | Caloocan City | 6024    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in ASIA HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 1111 1111 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"

  @ASIAHealthStore @ASIAHS_E2E
  Scenario: ASIA-HS eBook can be purchased by creating account in the checkout flow
    Given I navigate to the "ASIA" regional health store home page
    And I search for the E-Book "Orthobiologics, An Issue of Physical Medicine" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name      | Family Name          | Password | Title | Profession | Speciality  |
      | ASIA Automation | Regional HealthStore | Spoon!23 | Dr.   | Dentist    | Endodontics |
    And I navigate to payment page and enter the billing address and payment details in ASIA-HS
      | Street Address            | City  | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "ASIA"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+asia" and password "Spoon1@4"
    Then The order is validated in Magento Admin "ASIA"
