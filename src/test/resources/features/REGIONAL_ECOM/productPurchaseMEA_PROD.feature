Feature: Product purchase from Middle East store
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @MiddleEastHealthStore @MEHS_REGRESSION_PROD
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Keratoconus" under product type "Books"
     When I add the product to the cart
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | the palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page and enter the payment details in Middle East HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @MiddleEastHealthStore @MEHS_REGRESSION_PROD
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the journal "Journal of Geriatric Oncology" under product type "Journals"
     When I add the journal to the cart in Middle East HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | the palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page and enter the payment details in Middle East HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @MiddleEastHealthStore @MEHS_REGRESSION_PROD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the E-Book "Learning Disabilities E-Book" under product type "eBooks"
     When I add the E-book to the cart
      And I proceed to the checkout page
      And I navigate to payment page and enter the payment details in Middle East HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @MiddleEastHealthStore @MEHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path using Master card
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in Middle East HealthStore
          | Product Type | Product                        |
          | Books        | Keratoconus                    |
          | Flash Cards  | Crash Cards: Medicine          |
          | eBooks       | Learning Disabilities E-Book   |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | the palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page and enter the payment details in Middle East HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |

  @MiddleEastHealthStore @MEHS_REGRESSION_PROD
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "Middle East" regional health store home page
      And I search for the product "Keratoconus" under product type "Books"
     When I add the product to the cart
      And I proceed to the checkout page
      And I login from the checkout page with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | the palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page and enter the payment details in Middle East HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @MiddleEastHealthStore @MEHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "Middle East" regional health store home page
      And I search for different product under different product type and add them to cart in Middle East HealthStore
          | Product Type | Product                        |
          | Books        | Keratoconus                    |
          | Flash Cards  | Crash Cards: Medicine          |
          | Journals     | Journal of Geriatric Oncology  |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in Middle East HealthStore
          |Title | First Name     | Last Name            | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | Mr.  | MEA Automation | Regional HealthStore | the palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page and enter the payment details in Middle East HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @MiddleEastHealthStore @MEHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "Middle East" regional health store home page
      And I search for different product under different product type and add them to cart in Middle East HealthStore
         | Product Type     | Product                        |
         | Books            | Keratoconus                    |
         | Flash Cards      | Crash Cards: Medicine          |
         | Online Resource  | The Developing Human Elsevier  |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in Middle East HealthStore
         |Title | First Name     | Last Name            | Street Address     | City   | ZipCode | Country              | Phone Number |
         | Mr.  | MEA Automation | Regional HealthStore | The palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210    |
     And I navigate to payment page to select the payment method and enter the payment details with different billing address in MEA-HS
         |Title | First Name     | Last Name            | Street Address      | City     | ZipCode  | Country | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
         | Mr.  | MEA Automation | Regional HealthStore | Al Markhiya Street  | Doha     | 00000    |  Qatar  | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @MiddleEastHealthStore @MEHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I search for different product under different product type and add them to cart in Middle East HealthStore
        | Product Type     | Product                        |
        | Books            | Keratoconus                    |
        | Flash Cards      | Crash Cards: Medicine          |
        | Online Resource  | The Developing Human Elsevier  |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
          |Title | First Name     | Last Name  | Street Address     | City   | ZipCode |    Country           |Phone Number |
          | Mr.  | MEA Automation | Regional HealthStore | the palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page as a existing user and select different billing address and enter payment details in MEA-HS
          |Title | First Name     | Last Name            | Street Address      | City     | ZipCode  | Country | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Mr.  | MEA Automation | Regional HealthStore | Al Markhiya Street  | Doha     | 00000    |  Qatar  | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @MiddleEastHealthStore @MEHS_REGRESSION_PROD
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Periodontics" under product type "Books"
     Then I add the product to wishlist from product page
      And I add the product to cart from wishlist page
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in Middle East HealthStore
         | Street Address     | City   | ZipCode |    Country           |Phone Number |
         | the palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page and enter the payment details in Middle East HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @MiddleEastHealthStore @MEHS_REGRESSION_PROD
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
          | the palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
      And I navigate to payment page and enter the payment details in Middle East HealthStore
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @MiddleEastHealthStore @etax
  Scenario: Etax validation between UAT & PROD (MEA-HS Print Book purchase using the existing account checkout path)
    Given I navigate to the "Middle East" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "9780323594455" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details in Middle East HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "Middle East" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "9780323594455" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details in Middle East HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @MiddleEastHealthStore @MEHS_REGRESSION_PROD
  Scenario: Book can be purchased using the existing account checkout path from New Titles Menu
    Given I navigate to the "Middle East" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a Book product in "MEA" from the New Titles Menu
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in Middle East HealthStore
      | Street Address     | City   | ZipCode |    Country           |Phone Number |
      | the palm jumeirah  | Dubai  | 000000  | United Arab Emirates |9876543210   |
    And I navigate to payment page and enter the payment details in Middle East HealthStore
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |