Feature: My Account in ANZ HealthStore
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @ANZHS_NON_E2E @ANZHS_CICD @ANZHS_REGRESSION_PROD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in ANZ Health store
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    Then I navigate to my account page
    And I edit the profile information
      | Title | Profession | Speciality  |
      |  Dr   | Dentist    | Endodontics |
    Then I navigate to Address book page
    And I edit the default billing address in ANZ-HS
      | Title | First Name     | Last Name            | Street Address | City   | ZipCode | Country   | Phone Number |
      | Mr    | ANZ Automation | Regional HealthStore | Oxford Street  | Sydney | 1000    | Australia | 9876543210   |
    And I edit the default shipping address in ANZ-HS
      | Title | First Name     | Last Name            | Street Address | City   | ZipCode | Country   | Phone Number |
      | Mr    | ANZ Automation | Regional HealthStore | Oxford Street  | Sydney | 1000    | Australia | 9876543215   |
    And I add new address in ANZ-HS
      | Title | First Name     | Last Name            | Street Address | City   | ZipCode | Country | Phone Number |
      | Mr    | ANZ Automation | Regional HealthStore | Oxford Street  | Labasa | 1000    | Fiji    | 9876543215   |
    And I edit the newly added address in ANZ-HS
      | Title | First Name     | Last Name            | Street Address | City      | ZipCode | Country    | Phone Number |
      | Mr    | ANZ Automation | Regional HealthStore | Crown Street  | Melbourne | 2000    | Fiji    | 9876543215   |
    And I delete the newly added address

  @ANZHS_NON_E2E @ANZHS_REGRESSION_PROD
  Scenario: Existing user can add a product to wishlist from search page and product page
    Given I navigate to the "ANZ" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+anz@gmail.com" and password "Spoon!23"
    And I search for the product "eyes"
    Then I add the product to wishlist from search page
    And I search for the E-Book "Heart" under product type "eBooks"
    Then I add the product to wishlist from product page
    And I remove the product from wishlist