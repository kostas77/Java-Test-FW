Feature: My Account in EU HealthStore
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @EUHS_NON_E2E @EUHS_CICD @EUHS_REGRESSION_PROD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in US Health store
    Given I navigate to the "EU" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
     Then I navigate to my account page
      And I edit the profile information
          | Title | Profession | Speciality |
          |  Dr.  | Health Professions | Manual Therapy |
     Then I navigate to Address book page
      And I edit the default billing address in EU-HS
          |Title | First Name    | Last Name            | Street Address | City   | ZipCode | Country | Phone Number |
          | Dr.  | EU Automation | Regional HealthStore | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I edit the default shipping address in EU-HS
          |Title | First Name    | Last Name            | Street Address | City   | ZipCode | Country | Phone Number |
          | Dr.  | EU Automation | Regional HealthStore | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I add new address in EU-HS
          |Title | First Name    | Last Name            | Street Address | City   | ZipCode | Country | Phone Number |
          | Dr.  | EU Automation | Regional HealthStore | Via del Corso  | Rome   | 00100   |  Italy  | 9876543210   |
      And I edit the newly added address in EU-HS
          |Title | First Name    | Last Name            | Street Address      | City         | ZipCode  | Country | Phone Number |
          | Mr.  | EU Automation | Regional HealthStore | Via de' Tornabuoni  | Florence     | 50100    |  Italy  | 9876543210   |
      And I delete the newly added address

  @EUHS_NON_E2E @EUHS_REGRESSION_PROD
  Scenario: Existing user can add a product to wishlist from search page and product page
    Given I navigate to the "EU" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+eu@gmail.com" and password "Spoon!23"
      And I search for the product "eyes"
     Then I add the product to wishlist from search page
      And I search for the product "Heart" under product type "Books"
     Then I add the product to wishlist from product page
      And I remove the product from wishlist
