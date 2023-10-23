Feature: Product purchase E2E tests for French regional health store
  As a customer, I want to be able to purchase different product types (printbook, ebook, journal,...), so that they will be delivered to me

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @FranceHealthStore @FRHS_REGRESSION_PROD
  Scenario: Book can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Examens de laboratoire" under product type "Livres"
     When I add the product to the cart in France HealthStore
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in France HealthStore
          | Street Address            | City   | ZipCode | Phone Number |
          | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in FR-HS
        | Card Number         | Expiry Date | CVV | Name on card    |
        | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD
  Scenario: Journals can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the journal "Anesthésie & Réanimation" under product type "Revue"
     When I add the journal to the cart in France HealthStore
       | Format    | Status       | Country | Subscription Duration |
       | Paper     | Institution  | France  | 24 months             |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in France HealthStore
          | Street Address            | City   | ZipCode | Phone Number |
          | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in FR-HS
        | Card Number         | Expiry Date | CVV | Name on card    |
        | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD
  Scenario: E-Book can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the E-Book "thyroïde" under product type "eBooks"
     When I add the E-book to the cart in France HealthStore
      And I proceed to the checkout page
      And I navigate to payment page and enter the payment details in FR-HS
        | Card Number         | Expiry Date | CVV | Name on card    |
        | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in France healthstore
          | Product Type | Product                  |
          | Livres       | Examens de laboratoire   |
          | Revue        | Annales de paléontologie |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in France HealthStore
          | Street Address            | City   | ZipCode | Phone Number |
          | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in FR-HS
        | Card Number         | Expiry Date | CVV | Name on card    |
        | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD
  Scenario: Product can be purchased by signing into existing account in checkout path
    Given I navigate to the "France" regional health store home page
      And I search for the product "Examens de laboratoire" under product type "Livres"
     When I add the product to the cart in France HealthStore
      And I proceed to the checkout page
      And I login from the checkout page with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I navigate to shipping page and enter the shipping details in France HealthStore
          | Street Address            | City   | ZipCode | Phone Number |
          | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in FR-HS
        | Card Number         | Expiry Date | CVV | Name on card    |
        | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD @GuestUserCheckout
  Scenario: Product can be purchased using the guest user checkout path
    Given I navigate to the "France" regional health store home page
      And I search for different product under different product type and add them to cart in France healthstore
          | Product Type | Product                |
          | Livres       | Examens de laboratoire |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in France Healthstore
          |Title      | First Name | Last Name  | Street Address            | City    | ZipCode  | Phone Number |
          | MONSIEUR  | FR Test    | Automation | Avenue des Champs-Élysées | Paris   | 75000    | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in FR-HS
        | Card Number         | Expiry Date | CVV | Name on card    |
        | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD @GuestUserCheckout
  Scenario: Product can be purchased using the guest user checkout path with different billing address
    Given I navigate to the "France" regional health store home page
      And I search for different product under different product type and add them to cart in France healthstore
          | Product Type | Product                |
          | Livres       | Examens de laboratoire |
      And I proceed to the checkout page as a guest user
      And I enter shipping details as a guest user in France Healthstore
          |Title      | First Name | Last Name  | Street Address            | City    | ZipCode  | Phone Number |
          | MONSIEUR  | FR Test    | Automation | Avenue des Champs-Élysées | Paris   | 75000   | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details with different billing address in FR-HS
        | Title    | First Name | Last Name  | Street Address   | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
        | MONSIEUR | FR Test    | Automation | Rue du Panier  | Marseille | 13000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD
  Scenario: Multi-product can be purchased using the existing account checkout path with different billing address
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for different product under different product type and add them to cart in France healthstore
          | Product Type | Product                  |
          | Livres       | Examens de laboratoire   |
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in France HealthStore
          | Street Address            | City    | ZipCode  | Phone Number |
          | Avenue des Champs-Élysées | Paris   | 75000    | 9876543210   |
      And I navigate to payment page as a existing user and select different billing address and enter payment details in FR-HS
        | Title    | First Name | Last Name  | Street Address | City      | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
        | MONSIEUR | FR Test    | Automation | Rue du Panier  | Marseille | 13000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    
  @FranceHealthStore @FRHS_REGRESSION_PROD @wishlist
  Scenario: Existing user can make a purchase from wishlist
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I empty the cart
      And I search for the product "Examens de laboratoire" under product type "Livres"
      Then I add the product to wishlist from product page in FR-HS
      And I add the product to cart from wishlist page in FR-HS
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in France HealthStore
          | Street Address            | City   | ZipCode  | Phone Number |
          | Avenue des Champs-Élysées | Paris  | 75000    | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in FR-HS
        | Card Number         | Expiry Date | CVV | Name on card    |
        | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD @Reorder
  Scenario: Existing user can reorder a product from my account page
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I empty the cart
      Then I navigate to my account page
      And I navigate to my orders page
      Then I reorder from existing purchase
      And I proceed to the checkout page
      And I navigate to shipping page and enter the shipping details in France HealthStore
          | Street Address            | City   | ZipCode  | Phone Number |
          | Avenue des Champs-Élysées | Paris  | 75000    | 9876543210   |
      And I navigate to payment page to select the payment method and enter the payment details in FR-HS
        | Card Number         | Expiry Date | CVV | Name on card    |
        | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD
  Scenario: Book can be purchased using the existing account checkout path from New Titles Menu
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for a Book product in "FR" from the New Titles Menu
    When I add the product to the cart in France HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD
  Scenario: EMC can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select EMC from product type in navigation bar
    When I select a product "Vétérinaire" from the EMC Category
    When I add EMC to the cart in France HealthStore
      | Format                   | Status       | Country | Subscription Duration |
      | EMC - Offre Mobile Plus  | Particulier  | France  | 36 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD
  Scenario: EMC ClinicalKey can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select EMC from product type in navigation bar
    When I select a product "Savoirs et soins infirmiers" from the EMC Category
    When I add EMC to the cart in France HealthStore
      | Format                                | Status       | Country | Subscription Duration |
      | ClinicalKey Now & EMC - Web + Mobile  | Particulier  | France  | 36 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @FranceHealthStore @FRHS_REGRESSION_PROD
  Scenario: EMC (Internet offer) can be purchased using the existing account checkout path
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select EMC from product type in navigation bar
    When I select a product "Podologie" from the EMC Category
    When I add EMC to the cart in France HealthStore
      | Format               | Status       | Country | Subscription Duration |
      | EMC - Offre Internet | Particulier  | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City   | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris  | 75000   | 9876543210   |
    And I navigate to payment page to select the payment method and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
