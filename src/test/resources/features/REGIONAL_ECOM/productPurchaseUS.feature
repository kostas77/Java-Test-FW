Feature: Product purchase E2E tests for US regional health store
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me


  @API_TEST
  Scenario: ORR API Test
    Then The order is displayed and validated in ORR API1

  @USHealthStore @USHS_E2E @ExistingUser @template
  Scenario: US-HS Print Book purchase using the existing account checkout path
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
#    And   I am an existing customer
#    When  I add the following product types in the cart:
#      | Product Type         |
#      | Printbook            |

    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"
    Then I verify the Order Confirmation mail "US"
#     And  The order is displayed in Magento Admin
#     And  I receive an vital source email
#     And  I receive an Elsevier new order confirmation email

  @USHealthStore @USHS_E2E @ExistingUser
  Scenario: US-HS Journal purchase using the existing account checkout path
    Given I navigate to the "US" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the journal "EXPLORE" under product type "Journals"
     When I add the journal to the cart
      And I proceed to the checkout page
      And I confirm the shipping details
      And I navigate to payment page and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E @ExistingUser @USHS_CICD
  Scenario: US-HS E-Book purchase using the existing account checkout path
    Given I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    Then I verify the Account Creation mail "US"
    And I search for the E-Book "Heart" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | Country       | State    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | United States | New York | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E @ExistingUser
  Scenario: US-HS Multi-product purchase using the existing account checkout path using Master card
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product |
      | Books        | Anatomy |
      | Journals     | Disease |
      | Flash Cards  | Medical |
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |
    And I place the order with Mastercard
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E @ExistingUser
  Scenario: US-HS Book purchase by signing into existing account at checkout stage
    Given I navigate to the "US" regional health store home page
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I login from the checkout page with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E @GuestUser @USHS_CICD
  Scenario: US-HS Multi-product purchase using the guest user checkout path
    Given I navigate to the "US" regional health store home page
    And I search for different product under different product type and add them to cart
      | Product Type | Product |
      | Books        | Anatomy |
      | Journals     | Disease |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user
      | Title | First Name    | Last Name            | Street Address | City          | State    | ZipCode | Phone Number |
      | Mr.   | US Automation | Regional HealthStore | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E @ExistingUser
  Scenario: US-HS Multi-product purchase using the existing account checkout path (different shipping/billing address)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product |
      | Books        | Anatomy |
      | Journals     | Disease |
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and select different billing address and enter payment details
      | Title | First Name    | Last Name            | Street Address | City        | Country       | State      | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr.   | US Automation | Regional HealthStore | Spruce Street  | Los Angeles | United States | California | 90001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E @GuestUser
  Scenario: US-HS Multi-product purchase using the guest user checkout path (different shipping/billing address)
    Given I navigate to the "US" regional health store home page
    And I search for different product under different product type and add them to cart
      | Product Type | Product |
      | Books        | Anatomy |
      | Journals     | Disease |
          #| DVD          | Anesthesia |
      | Flash Cards  | Mosby   |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user
      | Title | First Name    | Last Name            | Street Address | City          | State    | ZipCode | Phone Number |
      | Mr.   | US Automation | Regional HealthStore | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
    And I navigate to payment page enter different billing address and enter payment details
      | Title | First Name   | Last Name            | Street Address | City        | Country       | State      | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr.  | US Automation | Regional HealthStore | Spruce Street  | Los Angeles | United States | California | 90001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E @ExistingUser
  Scenario: US-HS Book purchase using the existing account checkout path (wishlist)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    Then I add the product to wishlist from product page
    And I add the product to cart from wishlist page
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E @ExistingUser
  Scenario: US-HS Product purchase using the existing account checkout path (reorder from past order)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    Then I navigate to my account page
    And I navigate to my orders page
    Then I reorder from existing purchase
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @ExistingUser @Paypal
  Scenario: US-HS Journal purchase using the existing account checkout with different payment(Paypal)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the journal "EXPLORE" under product type "Journals"
    When I add the journal to the cart
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and choose paypal payment
      | Paypal Username                                | Password |
      | test.elsevier.regional.ecom+paypalus@gmail.com | Spoon!23 |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E @ExistingUser @ignore #TODO : RSR-6256
  Scenario: US-HS Book purchase using the existing account checkout path with 60% discount coupon
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    Then I apply for a offer code "60%off" and validate price details in "US"
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E @ExistingUser @ignore #TODO : RSR-6256
  Scenario: US-HS Multi-product purchase using existing checkout path with fixed discount on whole cart
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product  |
      | Books        | Anatomy  |
      | Journals     | Clinical |
    Then I apply for a offer code "WholeCartFixedDiscount40" and validate price details in "US"
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"


  @USHealthStore @USHS_E2E @ExistingUser @ignore #TODO : RSR-6256
  Scenario: US-HS Multi-product purchase using the existing account checkout path with fixed discount
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product  |
      | Books        | Anatomy  |
      | Journals     | Clinical |
    Then I apply for a offer code "FixedDiscount25" and validate price details in "US"
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E @ExistingUser
  Scenario: Book can be purchased using the existing account checkout path from New Titles Menu
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a Book product in "US" from the New Titles Menu
    When I add the product to the cart
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"

    @USHealthStore @USHS_E2E @ExistingUser @USHS_CICD
  Scenario: US-HS E-Book purchase using the existing account checkout path
    Given I navigate to the "US" regional health store home page
      And I sign-up as a new user
         | Given Name    | Family Name          | Password |Title | Profession  | Speciality |
         | US Automation | Regional HealthStore | Spoon!23 |  Dr.  | Physician  | Cardiology |
     Then I verify the Account Creation mail "US"
      And I search for the E-Book "Heart" under product type "eBooks"
     When I add the E-book to the cart
      And I proceed to the checkout page
      And I navigate to payment page and enter the shipping details and payment details
          | Street Address    | City          |Country       | State   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          |23 Wall Street     | New York City |United States |New York | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I place the order
     Then The order is displayed on the thank you page
     Then The order is validated with ORR API request "US"

  @USHealthStore @USHS_E2E
  Scenario: US-HS Multi-product purchase by creating account in the checkout with a eBook in cart
    Given I navigate to the "US" regional health store home page
    And I search for different product under different product type and add them to cart
      | Product Type | Product |
      | Books        | Anatomy |
      | eBooks       | Heart   |
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

  @USHealthStore @USHS_E2E
  Scenario: US-HS Product purchase by creating account in the checkout flow
    Given I navigate to the "US" regional health store home page
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

  @USHealthStore @USHS_E2E
  Scenario: US-HS eBook can be purchased by creating account in the checkout flow
    Given I navigate to the "US" regional health store home page
    And I search for the E-Book "Heart" under product type "eBooks"
    Then I add the E-book to the cart
    And I proceed to the checkout page
    And I sign-up as a new user in the checkout
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | Country       | State    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | United States | New York | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the thank you page
    Then The order is validated with ORR API request "US"
