Feature: My Account in ASIA HealthStore
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @ASIAHS_NON_E2E @ASIAHS_CICD @ASIAHS_REGRESSION_PROD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in ASIA Health store
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    Then I navigate to my account page
    And I edit the profile information
      | Title | Profession | Speciality  |
      |  Dr   | Dentist    | Endodontics |
    Then I navigate to Address book page
    And I edit the default billing address in ASIA-HS
      | Title | First Name      | Last Name            | Street Address | City         | ZipCode | Country     | Phone Number |
      | Mr    | ASIA Automation | Regional HealthStore | Gyeonggi-do    | Namyangju-si | 11124   | South Korea | 9876543210   |
    And I edit the default shipping address in ASIA-HS
      | Title | First Name      | Last Name            | Street Address | City         | ZipCode | Country   | Phone Number |
      | Mr    | ASIA Automation | Regional HealthStore | Okcheon-gun    | Namyangju-si | 11124    | South Korea | 9876543215   |
    And I add new address in ASIA-HS
      | Title | First Name      | Last Name            | Street Address | City  | ZipCode | Country  | Phone Number |
      | Mr    | ASIA Automation | Regional HealthStore | Kemaman Cukai  | Cukai | 11126   | Malaysia | 9876543215   |
    And I edit the newly added address in ASIA-HS
      | Title | First Name      | Last Name            | Street Address | City    | ZipCode | Country | Phone Number |
      | Mr    | ASIA Automation | Regional HealthStore | Kemaman Cukai  | Kuching | 1112444 | Japan   | 9876543215   |
    And I delete the newly added address

  @ASIAHS_NON_E2E @ASIAHS_REGRESSION_PROD
  Scenario: Existing user can add a product to wishlist from search page and product page
    Given I navigate to the "ASIA" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+asia@gmail.com" and password "Spoon!23"
    And I search for the product "eyes"
    Then I add the product to wishlist from search page
    And I search for the E-Book "Orthobiologics, An Issue of Physical Medicine" under product type "eBooks"
    Then I add the product to wishlist from product page
    And I remove the product from wishlist
