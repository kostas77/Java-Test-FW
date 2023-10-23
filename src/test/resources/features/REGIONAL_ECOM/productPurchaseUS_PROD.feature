Feature: Product purchase E2E tests for US regional health store
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @USHS_REGRESSION_PROD
  Scenario: US-HS Print Book purchase using the existing account checkout path
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_REGRESSION_PROD
  Scenario: US-HS Journal purchase using the existing account checkout path
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the journal "Veterinary Medicine" under product type "Journals"
    When I add the journal to the cart
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_REGRESSION_PROD
  Scenario: US-HS E-Book purchase using the existing account checkout path
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the E-Book "Muscle and Bone" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_REGRESSION_PROD @ExistingUser
  Scenario: US-HS Multi-product purchase using the existing account checkout path
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product            |
      | Books        | Anatomy            |
      | Journals     | Clinical Nutrition |
      | eBooks       | Muscle and Bone    |
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |

  @USHS_REGRESSION_PROD @ExistingUser
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

  @USHS_REGRESSION_PROD @GuestUser
  Scenario: US-HS Multi-product purchase using the guest user checkout path
    Given I navigate to the "US" regional health store home page
    And I search for different product under different product type and add them to cart
      | Product Type | Product            |
      | Books        | Anatomy            |
      | Journals     | Clinical Nutrition |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user
      | Title | First Name    | Last Name            | Street Address | City          | State    | ZipCode | Phone Number |
      | Mr    | US Automation | Regional HealthStore | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_REGRESSION_PROD @ExistingUser
  Scenario: US-HS Multi-product purchase using the existing account checkout path (different shipping/billing address)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for different product under different product type and add them to cart
      | Product Type | Product            |
      | Books        | Anatomy            |
      | Journals     | Clinical Nutrition |
      | eBooks       | Muscle and Bone    |
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and select different billing address and enter payment details
      | Title | First Name    | Last Name            | Street Address | City        | Country       | State      | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr    | US Automation | Regional HealthStore | Spruce Street  | Los Angeles | United States | California | 90001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_REGRESSION_PROD @GuestUser
  Scenario: US-HS Multi-product purchase using the guest user checkout path (different shipping/billing address)
    Given I navigate to the "US" regional health store home page
    And I search for different product under different product type and add them to cart
      | Product Type | Product            |
      | Books        | Anatomy            |
      | Journals     | Clinical Nutrition |
#          | DVD          | Anesthesia         |
      | Flash Cards  | Mosby's Medical    |
    And I proceed to the checkout page as a guest user
    And I enter shipping details as a guest user
      | Title | First Name    | Last Name            | Street Address | City          | State    | ZipCode | Phone Number |
      | Mr    | US Automation | Regional HealthStore | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
    And I navigate to payment page enter different billing address and enter payment details
      | Title | First Name    | Last Name            | Street Address | City        | Country       | State      | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr    | US Automation | Regional HealthStore | Spruce Street  | Los Angeles | United States | California | 90001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_REGRESSION_PROD @ExistingUser
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

  @USHS_REGRESSION_PROD @ExistingUser
  Scenario: US-HS Product purchase using the existing account checkout path (reorder from past order)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    Then I navigate to my account page
    And I navigate to my orders page
    Then I reorder from existing purchase
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @eTax
  Scenario: Etax validation between UAT & PROD (US-HS Print Book purchase using the existing account checkout path)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "9780323758000" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "9780323758000" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @ExistingUser @ignore @etax
  Scenario: US-HS Multi-product purchase using the existing account checkout path (different shipping/billing address)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "9780323758000" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and select different billing address and enter payment details
      | Title | First Name | Last Name  | Street Address | City        | Country       | State      | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr    | US Test    | Automation | Spruce Street  | Los Angeles | United States | California | 90001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "9780323758000" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and select different billing address and enter payment details
      | Title | First Name    | Last Name            | Street Address | City        | Country       | State      | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr.   | US Automation | Regional HealthStore | Spruce Street  | Los Angeles | United States | California | 90001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @USHS_REGRESSION_PROD
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
