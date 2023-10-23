Feature: PDS Product purchase E2E tests for US regional health store
  As a customer
  I want to be able to purchase different PDS product (ClinicalKey, ClinicalKey Pharmacology & ClinicalKey Student )

  Background: Run at the start of each test cases
    Given Switch Test Environment to "PROD"

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Dermatology and Clinical Key Pharmacology subscription (15 days trial, 1 month & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Dermatology"
    Then I add subscription to cart "1 month"
    And I select "Clinical Pharmacology" from product type in navigation bar
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Student subscription (15 days trial, 1 month, 3 months & 6 months)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey Student" from product type in navigation bar
    Then I select a Q-Bank "Buy Step 2 Q-Bank"
    And I add CK Student subscription to cart "6 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Surgery subscription (1 month) by applying a offer code
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    Then I apply for a offer code "30offtest" in "US"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy multiple ClinicalKey subscription with different Speciality and get a 30% discount (12 month only)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Neurology"
    Then I add subscription to cart "12 months"
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Pediatrics"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page with discount
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey and ExpertPath subscription (1 month & 12 months) along with physical product
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Family Medicine"
    Then I add subscription to cart "1 month"
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I search for the PSSi product "ExpertPath"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey and RAD Primer subscription (1 month & 12 months) along with ebook
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Pathology"
    Then I add subscription to cart "1 month"
    And I search for the PSSi product "RADPrimer"
    Then I add subscription to cart "12 months"
    And I search for the E-Book "Muscle and Bone" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey subscription (1 month) with a different country billing address
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHealthStore @ClinicalKey_PROD @etax
  Scenario: Existing user can buy ClinicalKey subscription (15 days trial, 1 month & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then Switch Test Environment to "UAT"
    Then I navigate to the "US" regional health store home page
    And I sign-up as a new user
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "1 month"
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details
      | Street Address | City          | State    | Country       | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | New York | United States | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    Then ETax percentage is compared between UAT and PROD

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Surgery and ClinicalKey Pharmacology subscription free trial
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Surgery"
    Then I add subscription to cart "15 days trial"
    And I select "Clinical Pharmacology" from product type in navigation bar
    Then I add subscription to cart "15 days trial"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Student subscription 15 days trial
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds1@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey Student" from product type in navigation bar
    Then I add subscription to cart "15 days trial"
    And I proceed to the checkout page
  #Free Free trial is activated for Clinicalkey student - ignored because without card details the order will be confirmed
    #And I navigate to payment page and enter the payment details
    #  | Card Number         | Expiry Date | CVV | Name on card    |
    #  | 4111 1111 4555 1142 | 0330 | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy STATdx and PATHPrimer subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the PSSi product "STATdx"
    Then I add subscription to cart "12 months"
    And I search for the PSSi product "PATHPrimer"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy RADPrimer and ImmunoQuery subscription (15 days trial & 12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the PSSi product "RADPrimer"
    Then I add subscription to cart "12 months"
    And I search for the PSSi product "ImmunoQuery"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy ImmunoQuery (Buy Bundle pack) subscription (12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the PSSi product "ImmunoQuery"
    Then I add subscription to cart "Buy Bundle pack"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy ExpertPath (Buy Bundle pack) subscription (12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the PSSi product "ExpertPath"
    Then I add subscription to cart "Buy Bundle pack"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy ImmunoQuery (Buy Bundle pack Trial) subscription (15 days trial)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the PSSi product "ImmunoQuery"
    Then I add subscription to cart "Buy Bundle pack Trial"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy ExpertPath (Buy Bundle pack Trial) subscription (15 days trial)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the PSSi product "ExpertPath"
    Then I add subscription to cart "Buy Bundle pack Trial"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy PATHPrimer and ExpertPath subscription with different countries (Asia)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the PSSi product "ExpertPath"
    Then I add subscription to cart "12 months"
    And I search for the PSSi product "PATHPrimer"
    And I verify the "PATHPrimer" PSSI product location restriction text in PDP
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details
      |Title | First Name | Last Name  | Street Address | City      | Country     | State    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr   | Carl       | Johnson    | Ascendas       | Chennai   | India       | N/A      | 600113  | 9876543210   | 4111 1111 4555 1142 | 0330       | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy RADPrimer subscription with different countries (Asia)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the PSSi product "RADPrimer"
    And I verify the "RADPrimer" PSSI product location restriction text in PDP
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details
      |Title | First Name | Last Name  | Street Address           | City         | Country     | State    | ZipCode   | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr   | Carl       | Johnson    | Ombetsucho Kinashibetsu  | Kushiro-shi  | Japan       | N/A      | 085-0062  | 9876543210   | 4111 1111 4555 1142 | 0330       | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy ExpertPath subscription with physical product (different countries)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the PSSi product "ExpertPath"
    And I verify the "ExpertPath" PSSI product location restriction text in PDP
    Then I add subscription to cart "12 months"
    And I search for the product "Anatomy" under product type "Books"
    When I add the product to the cart
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter different billing address and enter payment details
      |Title | First Name | Last Name  | Street Address           | City         | Country     | State    | ZipCode   | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr   | Carl       | Johnson    | Ombetsucho Kinashibetsu  | Kushiro-shi  | Japan       | N/A      | 085-0062  | 9876543210   | 4111 1111 4555 1142 | 0330       | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy STATdx subscription with Flashcard product (different countries)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the product "OSCEs and History" under product type "Flash Cards"
    When I add the product to the cart
    And I search for the PSSi product "STATdx"
    And I verify the "STATdx" PSSI product location restriction text in PDP
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I confirm the shipping details
    And I navigate to payment page and enter different billing address and enter payment details
      |Title | First Name | Last Name  | Street Address           | City         | Country     | State    | ZipCode   | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr   | Carl       | Johnson    | Ombetsucho Kinashibetsu  | Kushiro-shi  | Japan       | N/A      | 085-0062  | 9876543210   | 4111 1111 4555 1142 | 0330       | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy ClinicalKey Pharmacology and STATdx subscription with different countries (Asia)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "Clinical Pharmacology" from product type in navigation bar
    Then I add subscription to cart "12 months"
    And I search for the PSSi product "STATdx"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details
      |Title | First Name | Last Name  | Street Address | City      | Country     | State    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr   | Carl       | Johnson    | Ascendas       | Chennai   | India       | N/A      | 600113  | 9876543210   | 4111 1111 4555 1142 | 0330       | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy RADPrimer subscription with eBook ( different countries )
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the PSSi product "ImmunoQuery"
    And I verify the "ImmunoQuery" PSSI product location restriction text in PDP
    Then I add subscription to cart "12 months"
    And I search for the E-Book "Orofacial Structures" under product type "eBooks"
    When I add the E-book to the cart
    And I proceed to the checkout page
    And I navigate to payment page and verify different countries not displayed for PSSI Products

  @USHS_PDS_REGRESSION_PROD @USHealthStore @PSSI
  Scenario: Existing user can buy ExpertPath subscription with No Tax Countries (Asia)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I search for the PSSi product "ExpertPath"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter different billing address and enter payment details
      |Title | First Name | Last Name  | Street Address     | City       | Country     | State    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Mr   | Carl       | Johnson    | 25 Lor 22 Geylang  | Singapore  | Singapore   | N/A      | 398682  | 9876543210   | 4111 1111 4555 1142 | 0330       | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Residents subscription (12 months)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select ClinicalKey Residents
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Essentials - Residents"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Residents subscription (1 month)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select ClinicalKey Residents
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Emergency Medicine - Residents"
    Then I add subscription to cart "1 month"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy ClinicalKey Residents subscription (Free Trial)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select ClinicalKey Residents
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Hematology, Oncology, Palliative Medicine - Residents"
    Then I add subscription to cart "15 days trial"
    And I proceed to the checkout page
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |

  @USHS_PDS_REGRESSION_PROD @USHealthStore @ClinicalKey_PROD
  Scenario: Existing user can buy multiple ClinicalKey subscription with different Speciality and get a 30% discount (12 month only)
    Given I navigate to the "US" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+us+pds@gmail.com" and password "Spoon!23"
    And I empty the cart
    And I select "ClinicalKey" from product type in navigation bar
    And I select ClinicalKey Residents
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Physical Medicine & Rehabilitation - Residents"
    Then I add subscription to cart "12 months"
    And I select "ClinicalKey" from product type in navigation bar
    And I select ClinicalKey Residents
    And I select a product based on the ClinicalKey Specialties "ClinicalKey Anesthesiology - Residents"
    Then I add subscription to cart "12 months"
    And I proceed to the checkout page with discount
    And I navigate to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |