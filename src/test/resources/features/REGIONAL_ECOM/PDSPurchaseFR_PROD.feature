Feature: PDS Product purchase E2E tests for French regional health store
  As a customer
  I want to be able to purchase PDS product

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD @ignore #TODO: RSR-6535
  Scenario: Existing user can buy ClinicalKey Now private license (See all products)
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license (Select your specialty)
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "Hématologie"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license (Select your specialty) by web format
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "Hématologie"
    And I add ClinicalKey subscription to cart
      | Format | country | Subscription duration |
      | Web    | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license (Select your specialty) by Web + Paper + Mobile format
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "Imagerie médicale"
    And I add ClinicalKey subscription to cart
      | Format    | country | Subscription duration |
      | Web + App | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |


  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD @ignore #TODO: RSR-6535
  Scenario: Existing user can buy two different ClinicalKey Now private license
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "Hématologie"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    #Then I select ClinicalKey now from product type in navigation bar
    #Then I select a product based on the ClinicalKey Now Specialties "Imagerie médicale"
    And I search for the product "Angéiologie" under product type "ClinicalKey Now"
    And I add ClinicalKey subscription to cart
      | Format    | country | Subscription duration |
      | Web + App | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license by applying a offer code
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    Then I apply for a offer code "Test" in "FR"
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license along with physical product
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I search for the product "Anatomie" under product type "Livres"
    When I add the product to the cart in France HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD @ignore #TODO: RSR-6535
  Scenario: Existing user can buy ClinicalKey Now private license along with e-book
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I search for the E-Book "Remédiation cognitive" under product type "eBooks"
    When I add the E-book to the cart in France HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FranceHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license (See all products) with a different country billing address
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+de@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format    | country | Subscription duration |
      | Web + App | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license Free trial
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | Trial                 |
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD @ignore #TODO: RSR-6535
  Scenario: Existing user can buy ClinicalKey Now private license free trial along with e-book
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | Trial             |
    And I search for the E-Book "cœur" under product type "eBooks"
    When I add the E-book to the cart in France HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license free trial along with physical product
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | Trial                 |
    And I search for the product "Anatomie" under product type "Livres"
    When I add the product to the cart in France HealthStore
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license along with EMC
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now Specialties "All"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | 12 months             |
    And I select EMC from product type in navigation bar
    When I select a product "Savoirs et soins infirmiers" from the EMC Category
    When I add EMC to the cart in France HealthStore
      | Format                                | Status       | Country | Subscription Duration |
      | ClinicalKey Now & EMC - Web + Mobile  | Particulier  | France  | 36 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |

  @FRHS_PDS_REGRESSION_PROD @FranceHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Now private license free trial along with EMC
    Given I navigate to the "France" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+fr+pds2@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select ClinicalKey now from product type in navigation bar
    Then I select a product based on the ClinicalKey Now "All" and select the Product "Dermatologie"
    And I add ClinicalKey subscription to cart
      | Format      | country | Subscription duration |
      | Web + Paper | France  | Trial                 |
    And I select EMC from product type in navigation bar
    When I select a product "Podologie" from the EMC Category
    When I add EMC to the cart in France HealthStore
      | Format               | Status       | Country | Subscription Duration |
      | EMC - Offre Internet | Particulier  | France  | 12 months             |
    And I proceed to the checkout page
    And I navigate to shipping page and enter the shipping details in France HealthStore
      | Street Address            | City  | ZipCode | Phone Number |
      | Avenue des Champs-Élysées | Paris | 75000   | 9876543210   |
    And I navigate to payment page and enter the payment details in FR-HS
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 0000 0100 0000 0005 | 0330        | 737 | Test Automation |