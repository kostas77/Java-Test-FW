Feature: Product purchase from EU store
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @EUHealthStore @EUHS_REGRESSION_PROD
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Periodontics" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHealthStore @EUHS_REGRESSION_PROD
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the journal "International Emergency Nursing" under product type "Journals"
    When I add the journal to the cart in EU HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHealthStore @EUHS_REGRESSION_PROD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "Decompressive Techniques" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHealthStore @EUHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path using Master card
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart in EU healthstore
      | Product Type | Product           |
      | Books        | Keratoconus       |
      | Journals     | Orthodontic Waves |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHealthStore @EUHS_REGRESSION_PROD
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "EU" regional health store home page
    And I search for the product "Keratoconus" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I login from the checkout page with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHealthStore @EUHS_REGRESSION_PROD @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "EU" regional health store home page
      And I search for different product under different product type and add them to cart in EU healthstore
          | Product Type | Product                        |
          | Books        | Mental Health Care             |
          | Flash Cards  | Veterinary Anatomy Flash Cards |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in EU Healthstore
          |Title | First Name    | Last Name            | Street Address | City   | ZipCode | Country | Phone Number |
          | Mr.  | EU Automation | Regional HealthStore | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHealthStore @EUHS_REGRESSION_PROD @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "EU" regional health store home page
      And I search for different product under different product type and add them to cart in EU healthstore
          | Product Type | Product                         |
          | Books        | Mental Health Care              |
          | Flash Cards  | Veterinary Anatomy Flash Cards  |
          | Journals     | International Emergency Nursing |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in EU Healthstore
          |Title | First Name    | Last Name            | Street Address | City   | ZipCode | Country | Phone Number |
          | Mr.  | EU Automation | Regional HealthStore | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details with different billing address in EU-HS
          |Title | First Name    | Last Name            | Street Address      | City      | ZipCode  | Country  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Mr.  | EU Automation | Regional HealthStore | Via de' Tornabuoni  | Paris     | 75000    |  France  | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHealthStore @EUHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "EU" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in EU healthstore
          | Product Type | Product                        |
          | Books        | Mental Health Care             |
          | Flash Cards  | Veterinary Anatomy Flash Cards |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in EU HealthStore
          | Street Address | City   | ZipCode | Country | Phone Number |
          | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page as a existing user and select different billing address and enter payment details in EU-HS
          |Title | First Name    | Last Name            | Street Address      | City      | Country  | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Mr.  | EU Automation | Regional HealthStore | Via de' Tornabuoni  | Paris     | France   |   75000 | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHealthStore @EUHS_REGRESSION_PROD
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "EU" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Periodontics" under product type "Books"
     Then I add the product to wishlist from product page
      And I add the product to cart from wishlist page
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in EU HealthStore
          | Street Address | City   | ZipCode | Country | Phone Number |
          | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHealthStore @EUHS_REGRESSION_PROD
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "EU" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
      And I empty the cart
     Then I navigate to my account page
      And I navigate to my orders page
     Then I reorder from existing purchase
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in EU HealthStore
          | Street Address | City   | ZipCode | Country | Phone Number |
          | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I navigate to payment page and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @EUHealthStore @etax
  Scenario: Etax validation between UAT & PROD (EU-HS Print Book purchase using the existing account checkout path)
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "9780323940399" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "9780323940399" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @EUHealthStore @etax
  Scenario: Etax validation between UAT & PROD (E-Book purchase using the existing account checkout path)
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "9780323594455" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "9780323594455" under product type "eBooks"
    When I add the E-book to the cart in Europe Health Store
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @EUHealthStore @EUHS_REGRESSION_PROD
  Scenario: Book can be purchased using the existing account checkout path from New Titles Menu
    Given I navigate to the "EU" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a Book product in "EU" from the New Titles Menu
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in EU HealthStore
      | Street Address | City   | ZipCode | Country | Phone Number |
      | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
