Feature: Product purchase from Spain store
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @SpainHealthStore @SPHS_REGRESSION_PROD
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Radiología básica" under product type "Libros"
    When I add the product to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address   | City   | ZipCode | Phone Number |
      | Preciados street | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @SpainHealthStore @SPHS_REGRESSION_PROD
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the journal "Acta Otorrinolaringológica" under product type "Revistas"
    When I add the journal to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address   | City   | ZipCode | Phone Number |
      | Preciados street | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @SpainHealthStore @SPHS_REGRESSION_PROD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "Cuidados perioperatorios" under product type "eBooks"
    When I add the E-book to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore for E-Books
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @SpainHealthStore @SPHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path using Master card
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in Spain healthstore
      | Product Type | Product               |
      | Revistas     | Acta Otorrinolaringológica |
      | Libros       | Radiología básica     |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address   | City   | ZipCode | Phone Number |
      | Preciados street | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |

  @SpainHealthStore @SPHS_REGRESSION_PROD
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "Spain" regional health store home page
    And I search for the product "corazón" under product type "Libros"
    When I add the product to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I login from the checkout page with username "test.elsevier.regional.ecom+sp1@gmail.com" and password "Spoon!23"
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address   | City   | ZipCode | Phone Number |
      | Preciados street | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @SpainHealthStore @SPHS_REGRESSION_PROD @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "Spain" regional health store home page
    And I search for different product under different product type and add them to cart in Spain healthstore
      | Product Type | Product               |
      | Libros       | Vacunas               |
      | Revistas     | Acta Otorrinolaringológica |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in Spain HealthStore
      | Title | First Name | Last Name  | Street Address   | City   | ZipCode | Phone Number |
      | Señor | SP Test    | Automation | Preciados street | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |


  @SpainHealthStore @SPHS_REGRESSION_PROD @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "Spain" regional health store home page
    And I search for different product under different product type and add them to cart in Spain healthstore
      | Product Type | Product               |
      | Revistas     | Acta Otorrinolaringológica |
      | Libros       | Vacunas               |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in Spain HealthStore
      | Title | First Name | Last Name  | Street Address   | City   | ZipCode | Phone Number |
      | Señor | SP Test    | Automation | Preciados street | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details with different billing address in SP-HS
      | Title | First Name | Last Name  | Street Address | City      | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Señor | SP Test    | Automation | La Rambla      | Barcelona | 08001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @SpainHealthStore @SPHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in Spain healthstore
      | Product Type | Product               |
      | Revistas     | Acta Otorrinolaringológica |
      | Libros       | Vacunas               |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address   | City   | ZipCode | Phone Number |
      | Preciados street | Madrid | 28001   | 9876543210   |
    And I navigate to payment page as a existing user and select different billing address and enter payment details in SP-HS
      | Title | First Name | Last Name  | Street Address | Country    | City      | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Señor | SP Test    | Automation | La Rambla      | Costa Rica | San Diego | 30302   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @SpainHealthStore @SPHS_REGRESSION_PROD @Wishlist
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "corazón" under product type "Libros"
    Then I add the product to wishlist from product page in SP-HS
    And I add the product to cart from wishlist page in SP-HS
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address   | City   | ZipCode | Phone Number |
      | Preciados street | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @SpainHealthStore @SPHS_REGRESSION_PROD @Reorder
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp1@gmail.com" and password "Spoon!23"
    And I empty the cart
    Then I navigate to my account page
    And I navigate to my orders page
    Then I reorder from existing purchase
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address   | City   | ZipCode | Phone Number |
      | Preciados street | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @SPHealthStore @etax
  Scenario: Book Tax Verification in UAT and PROD for Spain Health Store
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "9788491136651" under product type "Libros"
    When I add the product to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Preciados street  | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "9788491136651" under product type "Libros"
    When I add the product to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Preciados street  | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @SPHealthStore @etax
  Scenario: E-Book Tax Verification in UAT and PROD for Spain Health Store
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "9788491132660" under product type "eBooks"
    When I add the E-book to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore for E-Books
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "Spain" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password |Title    | Profession  | Speciality                |
      | SP Automation | Regional HealthStore | Spoon!23 |  Señor  |Fisioterapia | Fisioterapia del deporte  |
    And I search for the E-Book "9788491132660" under product type "eBooks"
    When I add the E-book to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in SP-HS
      | Street Address    | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Preciados street  | Madrid | 28001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @SPHealthStore @etax
  Scenario: Journal Tax Verification in UAT and PROD for Spain Health Store
    Given I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the journal "Piel. Formación" under product type "Revistas"
    When I add the journal to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Preciados street  | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "Spain" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the journal "Piel. Formación" under product type "Revistas"
    When I add the journal to the cart in Spain HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Spain HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Preciados street  | Madrid | 28001   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in Spain HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD
