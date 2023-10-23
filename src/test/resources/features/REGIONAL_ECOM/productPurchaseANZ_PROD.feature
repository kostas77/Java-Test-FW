Feature: Product purchase E2E tests for ANZ regional health store
  As a customer, I want to be able to purchase different product types (printbook, ebook, journal,...), so that they will be delivered to me

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @ANZHealthStore @ANZHS_REGRESSION_PROD
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

  @ANZHealthStore @ANZHS_REGRESSION_PROD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "Palliative Radiation Oncology" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    
  @ANZHealthStore @ANZHS_REGRESSION_PROD
  Scenario: ANZ-HS Multi-product purchase using the existing account checkout path
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product                       |
      | Books        | Anatomy                       |
      | eBooks       | Palliative Radiation Oncology |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @ANZHealthStore @ANZHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the guest user checkout path
    Given I navigate to the "ANZ" regional health store home page
    And I search for different product under different product type and add them to cart in ANZ healthstore
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in ANZ Healthstore
      |Title | First Name     | Last Name            | Street Address | City    | ZipCode  | Phone Number |
      | Mr   | ANZ Automation | Regional HealthStore | Crown Street   | Sydney   | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @ANZHealthStore @ANZHS_REGRESSION_PROD @GuestUserCheckout
  Scenario: Multi-product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "ANZ" regional health store home page
    And I search for different product under different product type and add them to cart in ANZ healthstore
      | Product Type | Product         |
      | Books        | Anatomy         |
      | Flash Cards  | Mosby's Anatomy |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user in ANZ Healthstore
      |Title | First Name     | Last Name            | Street Address | City    | ZipCode  | Phone Number |
      | Mr   | ANZ Automation | Regional HealthStore | Crown Street   | Sydney   | 1000    | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details with different billing address in ANZ-HS
      | Title | First Name     | Last Name            | Street Address | City       | ZipCode | Country     | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr    | ANZ Automation | Regional HealthStore | Lambton Quay   | Wellington | 2000    | New Zealand | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @ANZHealthStore @ANZHS_REGRESSION_PROD
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

  @ANZHealthStore @ANZHS_REGRESSION_PROD @Wishlist
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

  @eTax
  Scenario: Etax validation between UAT & PROD (ANZ-HS Print Book purchase using the existing account checkout path)
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
    Then Switch Test Environment to "UAT"
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
    Then ETax percentage is compared between UAT and PROD

  @eTax
  Scenario: Etax validation of eBook product between UAT & PROD (ANZ-HS Print Book purchase using the existing account checkout path)
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "Orthobiologics, An Issue of Physical Medicine" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "Orthobiologics, An Issue of Physical Medicine" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @ANZHealthStore @ANZHS_REGRESSION_PROD
  Scenario: Book can be purchased from BestSellers and Australian Titles Menu
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a product from the Best Sellers Menu
    And I add the product to the cart
    And I search for a product from the Australian Titles Menu
    And I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @ANZHealthStore @ANZHS_REGRESSION_PROD
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "ANZ" regional health store home page
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I login from the checkout page with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I navigate to shipping page and enter the shipping details in ANZ HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Crown Street   | Sydney | 1000    | 9876543210   |
    And I navigate to payment page and enter payment details in ANZ-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |