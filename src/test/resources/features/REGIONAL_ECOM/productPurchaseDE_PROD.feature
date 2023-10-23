Feature: Product purchase E2E tests for German regional health store
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @GermanyHealthStore @DEHS_REGRESSION_PROD
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Diagnostische" under product type "Buch"
     When I add the product to the cart in Germany HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @GermanyHealthStore @DEHS_REGRESSION_PROD
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the journal "Krankenhaushygiene" under product type "Loseblatt"
     When I add the journal to the cart in Germany HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @GermanyHealthStore @DEHS_REGRESSION_PROD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the E-Book "Anästhesie" under product type "eBooks"
     When I add the E-book to the cart in Germany HealthStore
      And I proceed to the checkout page
      And I navigate to payment page and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @GermanyHealthStore @DEHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in Germany HealthStore
          | Product Type | Product             |
          | Buch         | Diagnostische       |
          | Loseblatt    | Krankenhaushygiene  |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @GermanyHealthStore @DEHS_REGRESSION_PROD
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "Germany" regional health store home page
      And I search for the product "Diagnostische" under product type "Buch"
     When I add the product to the cart in Germany HealthStore
      And I proceed to the checkout page
      And I login from the checkout page with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @GermanyHealthStore @DEHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in Germany HealthStore
          | Product Type | Product             |
          | Buch         | Diagnostische       |
          | Loseblatt    | Krankenhaushygiene  |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page as a existing user and select different billing address and enter payment details in DE-HS
          |Title  | First Name | Last Name  | Street Address  | City        | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Herr  | DE Test    | Automation | Neue Kräme      | frankfurt   | 60306    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @GermanyHealthStore @DEHS_REGRESSION_PROD
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Diagnostische" under product type "Buch"
     Then I add the product to wishlist from product page in DE-HS
      And I add the product to cart from wishlist page in DE-HS
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @GermanyHealthStore @DEHS_REGRESSION_PROD
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
      And I empty the cart
     Then I navigate to my account page
      And I navigate to my orders page
     Then I reorder from existing purchase
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Germany HealthStore
          | Street Address    | City   | ZipCode | Phone Number |
          | Schwedter Straße  | Berlin | 10435   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @GermanyHealthStore @DEHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "Germany" regional health store home page
      And I search for different product under different product type and add them to cart in Germany HealthStore
          | Product Type | Product             |
          | Buch         | Diagnostische       |
          | Loseblatt    | Krankenhaushygiene  |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in Germany Healthstore
          |Title  | First Name | Last Name  | Street Address    | Country     | City    | ZipCode  | Phone Number |
          | Herr  | DE Test    | Automation | Schwedter Straße  | Deutschland | Berlin  | 10435    | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in DE-HS
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @GermanyHealthStore @DEHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "Germany" regional health store home page
      And I search for different product under different product type and add them to cart in Germany HealthStore
          | Product Type | Product             |
          | Buch         | Diagnostische       |
          | Loseblatt    | Krankenhaushygiene  |
          | DVD          | DVD                 |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in Germany Healthstore
          |Title | First Name | Last Name  | Street Address    |  Country     |City    | ZipCode  | Phone Number |
          | Herr | DE Test    | Automation | Schwedter Straße  | Deutschland  | Berlin  | 10435    | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details with different billing address in DE-HS
          |Title  | First Name | Last Name  | Street Address     | City         | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Herr  | DE Test    | Automation | Neue Kräme         | frankfurt    | 60306    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

 @DEHealthStore @etax
  Scenario: E-Book Tax Verification in UAT and PROD for German Health Store
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "9783437060496" under product type "eBooks"
    When I add the E-book to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom.de2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "9783437060496" under product type "eBooks"
    When I add the E-book to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

   @DEHealthStore @etax
  Scenario: E-Book Tax Verification in UAT and PROD for Austria Address in German Health Store
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "9783437060496" under product type "eBooks"
    When I add the E-book to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details in DE-HS
      | Street Address          | City   | Country     |ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Gumpendorfer Strasse 42 | Tyrol  | Österreich  |6522    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom.de2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "9783437060496" under product type "eBooks"
    When I add the E-book to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details in DE-HS
      | Street Address          | City   | Country     |ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Gumpendorfer Strasse 42 | Tyrol  | Österreich  |6522    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

 @DEHealthStore @etax
  Scenario: E-Book Tax Verification in UAT and PROD for Switzerland Address in German Health Store
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "9783437060496" under product type "eBooks"
    When I add the E-book to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details in DE-HS
      | Street Address  | City    | Country   |ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Rosenweg 120    | Lauerz  | Schweiz   |2540    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom.de2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "9783437060496" under product type "eBooks"
    When I add the E-book to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details in DE-HS
      | Street Address  | City    | Country   |ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Rosenweg 120    | Lauerz  | Schweiz   |2540    | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @GermanyHealthStore @DEHS_REGRESSION_PROD
  Scenario: Book can be purchased using the existing account checkout path from New Titles Menu
    Given I navigate to the "Germany" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a Book product in "DE" from the New Titles Menu
    When I add the product to the cart in Germany HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Germany HealthStore
      | Street Address    | City   | ZipCode | Phone Number |
      | Schwedter Straße  | Berlin | 10435   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in DE-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
