Feature: Product purchase E2E tests for UK regional health store
  As a customer, I want to be able to purchase different product types (printbook, ebook, journal,...), so that they will be delivered to me

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @UKHealthStore @UKHS_REGRESSION_PROD
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Anatomy" under product type "Books"
     When I add the product to the cart
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
            | Street Address | City   | ZipCode | Phone Number |
            | Oxford Street  | London | E1 6AN  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
            | Card Number         | Expiry Date | CVV | Name on card    |
            | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @UKHealthStore @UKHS_REGRESSION_PROD
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the journal "Scandinavian Journal of Pain" under product type "Journals"
     When I add the journal to the cart in UK HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
            | Street Address | City   | ZipCode | Phone Number |
            | Oxford Street  | London | E1 6AN  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
            | Card Number         | Expiry Date | CVV | Name on card    |
            | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
      And I empty the cart

  @UKHealthStore @UKHS_REGRESSION_PROD @ignore #TODO: RSR-5561
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the E-Book "Heart" under product type "eBooks"
     When I add the E-book to the cart
      And I proceed to the checkout page
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @UKHealthStore @UKHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path using Master card
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in UK healthstore
           | Product Type | Product                         |
           | Books        | Anatomy                         |
           | Journals     | Scandinavian Journal of Pain    |
           | eBooks       | Anderson’s Pediatric Cardiology |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
           | Street Address | City   | ZipCode | Phone Number |
           | Oxford Street  | London | E1 6AN  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
           | Card Number         | Expiry Date | CVV | Name on card    |
           | 2222 4000 1000 0008 | 0330        | 737 | Test Automation |

  @UKHealthStore @UKHS_REGRESSION_PROD
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "UK" regional health store home page
      And I search for the product "Anatomy" under product type "Books"
     When I add the product to the cart
      And I proceed to the checkout page
      And I login from the checkout page with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I navigate to shipping page and enter the shipping details in UK HealthStore
          | Street Address | City   | ZipCode | Phone Number |
          | Oxford Street  | London | E1 6AN  | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @UKHealthStore @UKHS_REGRESSION_PROD @GuestUserCheckout
  Scenario: Product can be purchased using the guest user checkout path
    Given I navigate to the "UK" regional health store home page
      And I search for different product under different product type and add them to cart in UK healthstore
          | Product Type | Product                         |
          | Books        | Anatomy                         |
          | Journals     | Scandinavian Journal of Pain    |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in UK Healthstore
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @UKHealthStore @UKHS_REGRESSION_PROD @GuestUserCheckout
  Scenario: Product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "UK" regional health store home page
      And I search for different product under different product type and add them to cart in UK healthstore
          | Product Type | Product                       |
          | Books        | Anatomy                       |
          | Journals     | Scandinavian Journal of Pain  |
#          | DVD          | Nursing                       |
          | Flash Cards  | Netter’s Histology Cut-Out    |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in UK Healthstore
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details with different billing address in UK-HS
          |Title | First Name    | Last Name            | Street Address     | City         | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Mr.  | UK Automation | Regional HealthStore | St Peter's Square  | Manchester   | M1 1AA   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @UKHealthStore @UKHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in UK healthstore
          | Product Type | Product                          |
          | Books        | Anatomy                          |
          | Journals     | Scandinavian Journal of Pain     |
          | eBooks       | Anderson’s Pediatric Cardiology  |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I navigate to payment page as a existing user and select different billing address and enter payment details in UK-HS
          |Title | First Name | Last Name  | Street Address     | City         | ZipCode  | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
          | Mr.  | UK Test    | Automation | St Peter's Square  | Manchester   | M1 1AA   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @UKHealthStore @UKHS_REGRESSION_PROD @Wishlist
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Anatomy" under product type "Books"
     Then I add the product to wishlist from product page
      And I add the product to cart from wishlist page
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @UKHealthStore @UKHS_REGRESSION_PROD @Reorder
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I empty the cart
     Then I navigate to my account page
      And I navigate to my orders page
     Then I reorder from existing purchase
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in UK HealthStore
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details
          | Card Number         | Expiry Date | CVV | Name on card    |
          | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @UKHealthStore @UKHS_REGRESSION_PROD
  Scenario: Book can be purchased using the existing account checkout path from New Titles Menu
    Given I navigate to the "UK" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a Book product in "UK" from the New Titles Menu
    When I add the product to the cart
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in UK HealthStore
      | Street Address | City   | ZipCode | Phone Number |
      | Oxford Street  | London | E1 6AN  | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
