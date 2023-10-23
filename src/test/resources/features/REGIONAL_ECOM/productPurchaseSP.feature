Feature: Product purchase from Spain store
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me

  @SpainHealthStore @SPHS_E2E
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "Spain" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Anatomía" under product type "Libros"
     When I add the product to the cart in Spain HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Spain HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Preciados street  | Madrid | 28001   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "SP"
      And I login into Magento Admin with username "test.elsevier.regional.ecom+sp" and password "Spoon1@4"
     Then The order is validated in Magento Admin "SP"
    Then I verify the Order Confirmation mail "SP"
#    And   I receive an vital source email
#    And   I receive an Elsevier new order confirmation email

  @SpainHealthStore @SPHS_E2E
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "Spain" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the journal "Acta Otorrinolaringológica Española" under product type "Revistas"
     When I add the journal to the cart in Spain HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Spain HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Preciados street  | Madrid | 28001   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E @SPHS_CICD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "Spain" regional health store home page
      And I sign-up as a new user
         | Given Name    | Family Name          | Password |Title    | Profession  | Speciality                |
         | SP Automation | Regional HealthStore | Spoon!23 |  Señor  |Fisioterapia | Fisioterapia del deporte  |
     Then I verify the Account Creation mail "SP"
      And I search for the E-Book "corazón" under product type "eBooks"
     When I add the E-book to the cart in Spain HealthStore
      And I proceed to the checkout page
      And I navigate to payment page and enter the billing address and payment details in SP-HS
         | Street Address    | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
         | Preciados street  | Madrid | 28001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path using Master card
    Given I navigate to the "Spain" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in Spain healthstore
          | Product Type | Product                   |
          | Revistas     | Acta Otorrinolaringológica Española  |
          | Libros       | Anatomía                  |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Spain HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Preciados street  | Madrid | 28001   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
      And I place the order with Mastercard
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "Spain" regional health store home page
      And I search for the product "Anatomía" under product type "Libros"
     When I add the product to the cart in Spain HealthStore
      And I proceed to the checkout page
      And I login from the checkout page with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
      And I navigate to shipping page and enter the shipping details in Spain HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Preciados street  | Madrid | 28001   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E @GuestUserCheckout @SPHS_CICD
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "Spain" regional health store home page
      And I search for different product under different product type and add them to cart in Spain healthstore
          | Product Type | Product                   |
          | Revistas     | Acta Otorrinolaringológica Española  |
          | Libros       | Anatomía                  |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in Spain HealthStore
          |Title   | First Name    | Last Name            | Street Address    | City    | ZipCode  | Phone Number |
          | Señor  | SP Automation | Regional HealthStore | Preciados street  | Madrid  | 28001    | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "Spain" regional health store home page
      And I search for different product under different product type and add them to cart in Spain healthstore
          | Product Type | Product                   |
          | Revistas     | Acta Otorrinolaringológica Española  |
          | Libros       | Anatomía                  |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in Spain HealthStore
          |Title   | First Name    | Last Name  | Street Address    | City    | ZipCode  | Phone Number |
          | Señor  | SP Automation | Regional HealthStore | Preciados street  | Madrid  | 28001    | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details with different billing address in SP-HS
          |Title   | First Name     | Last Name            | Street Address   | City         | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Señor  | SP Automation  | Regional HealthStore | La Rambla        | Barcelona    | 08001    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "Spain" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in Spain healthstore
          | Product Type | Product                             |
          | Revistas     | Acta Otorrinolaringológica Española |
          | Libros       | Anatomía                            |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Spain HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Preciados street  | Madrid | 28001   | 9876543210   |
      And I navigate to payment page as a existing user and select different billing address and enter payment details in SP-HS
          |Title   | First Name    | Last Name            | Street Address  | Country    | City      |ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Señor  | SP Automation | Regional HealthStore | La Rambla       | Costa Rica | San Diego |30302    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E @Wishlist
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "Spain" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "corazón" under product type "Libros"
     Then I add the product to wishlist from product page in SP-HS
      And I add the product to cart from wishlist page in SP-HS
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Spain HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Preciados street  | Madrid | 28001   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E @Reorder
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "Spain" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
      And I empty the cart
     Then I navigate to my account page
      And I navigate to my orders page
     Then I reorder from existing purchase
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Spain HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Preciados street  | Madrid | 28001   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E @Paypal
  Scenario: Book can be purchased using the existing account checkout with different payment(PaypalExpress)
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomía" under product type "Libros"
    When I add the product to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Preciados street  | Madrid | 28001   | 9876543210   |
    And I navigate to payment page and choose paypalExpress payment
      | Paypal Username                       | Password |
      | sb-dvefb26658075@personal.example.com | 1C&/&<j. |
    And I place the order
    Then The order is displayed on the thank you page

  @SpainHealthStore @SPHS_E2E @ignore #TODO: RSR-5692
  Scenario: Book can be purchased using the existing account checkout path with 60% discount offer
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomía" under product type "Libros"
    When I add the product to the cart in Spain HealthStore
    Then I apply for a offer code "60%off" and validate price details in "SP"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Preciados street  | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "SP"
    And I login into Magento Admin with username "test.elsevier.regional.ecom+sp" and password "Spoon1@4"
    Then The order is validated in Magento Admin "SP"

  @SpainHealthStore @SPHS_E2E @ignore #TODO: RSR-5692
  Scenario: Multi-product can be purchased using the existing account checkout path with Fixed discount coupon
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in Spain healthstore
      | Product Type | Product                   |
      | Revistas     | Acta Otorrinolaringológica Española  |
      | Libros       | Anatomía                  |
    Then I apply for a offer code "FixedDiscount25" and validate price details in "SP"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Preciados street  | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
    And I place the order with Mastercard
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E @ignore #TODO: RSR-5692
  Scenario: Multi-product can be purchased using the existing account checkout path with Whole cart discount coupon
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in Spain healthstore
      | Product Type | Product                   |
      | Revistas     | Acta Otorrinolaringológica Española  |
      | Libros       | Anatomía                  |
    Then I apply for a offer code "WholeCartFixedDiscount40" and validate price details in "SP"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Preciados street  | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
    And I place the order with Mastercard
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E
  Scenario: SP-HS Multi-product purchase by creating account in the checkout with a eBook in cart
    Given I navigate to the "Spain" regional health store home page
    And I search for different product under different product type and add them to cart in Spain healthstore
      | Product Type | Product    |
      | Libros       | Anatomía   |
      | eBooks       |  corazón   |
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password |Title    | Profession  | Speciality                |
      | SP Automation | Regional HealthStore | Spoon!23 |  Señor  |Fisioterapia | Fisioterapia del deporte  |
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Preciados street  | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E
  Scenario: SP-HS Product purchase by creating account in the checkout flow
    Given I navigate to the "Spain" regional health store home page
    And I search for the product "Anatomía" under product type "Libros"
    When I add the product to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password |Title    | Profession  | Speciality                |
      | SP Automation | Regional HealthStore | Spoon!23 |  Señor  |Fisioterapia | Fisioterapia del deporte  |
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Preciados street  | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "SP"

  @SpainHealthStore @SPHS_E2E
  Scenario: SP-HS eBook can be purchased by creating account in the checkout flow
    Given I navigate to the "Spain" regional health store home page
    And I search for the E-Book "corazón" under product type "eBooks"
    When I add the E-book to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password |Title    | Profession  | Speciality                |
      | SP Automation | Regional HealthStore | Spoon!23 |  Señor  |Fisioterapia | Fisioterapia del deporte  |
    And I navigate to payment page and enter the billing address and payment details in SP-HS
      | Street Address    | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Preciados street  | Madrid | 28001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "SP"