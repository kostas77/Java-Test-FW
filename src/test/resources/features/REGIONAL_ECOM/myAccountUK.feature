Feature: My Account in UK HealthStore
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @UKHS_NON_E2E @UKHS_CICD @UKHS_REGRESSION_PROD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in UK Health store
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
     Then I navigate to my account page
      And I edit the profile information
          | Title | Profession        | Speciality |
          |  Dr.  | Medical Students  | Surgery    |
     Then I navigate to Address book page
      And I edit the default billing address in UK-HS
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I edit the default shipping address in UK-HS
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I add new address in UK-HS
          |Title | First Name    | Last Name            | Street Address | City    | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | Oxford Street  | London  | E1 6AN   | 9876543210   |
      And I edit the newly added address in UK-HS
          |Title | First Name    | Last Name            | Street Address     | City         | ZipCode  | Phone Number |
          | Mr.  | UK Automation | Regional HealthStore | St Peter's Square  | Manchester   | M1 1AA   | 9876543210   |
      And I delete the newly added address

  @UKHS_NON_E2E @UKHS_REGRESSION_PROD
  Scenario: Existing user can add a product to wishlist from search page and product page
    Given I navigate to the "UK" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+uk@gmail.com" and password "Spoon!23"
      And I search for the product "eyes"
     Then I add the product to wishlist from search page
      And I search for the E-Book "Heart" under product type "eBooks"
     Then I add the product to wishlist from product page
      And I remove the product from wishlist
