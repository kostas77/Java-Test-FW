Feature: My Account in France HealthStore
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @FRHS_NON_E2E @FRHS_CICD @FRHS_REGRESSION_PROD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in French Health store
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
     Then I navigate to my account page
      And I edit the profile information in FR-HS
          | Title     | Profession  | Speciality |
          | MONSIEUR  | Etudiant    | Biologie   |
     Then I navigate to Address book page
      And I edit the default billing address in FR-HS
          |Title      | First Name    | Last Name            | Street Address            | City    | ZipCode  | Phone Number |
          | MONSIEUR  | FR Automation | Regional HealthStore | Avenue des Champs-Élysées | Paris   | 75000    | 9876543210   |
      And I edit the default shipping address in FR-HS
          |Title      | First Name    | Last Name            | Street Address            | City    | ZipCode  | Phone Number |
          | MONSIEUR  | FR Automation | Regional HealthStore | Avenue des Champs-Élysées | Paris   | 75000    | 9876543210   |
      And I add new address in FR-HS
          |Title      | First Name    | Last Name            | Street Address            | City    | ZipCode  | Phone Number |
          | MONSIEUR  | FR Automation | Regional HealthStore | Avenue des Champs-Élysées | Paris   | 75000    | 9876543210   |
       And I edit the newly added address in FR-HS
           |Title      | First Name    | Last Name            | Street Address  | City         | ZipCode  | Phone Number |
           | MONSIEUR  | FR Automation | Regional HealthStore | Rue du Panier   | Marseille    | 13000    | 9876543210   |
       And I delete the newly added address in FR-HS

  @FRHS_NON_E2E @Wishlist @FRHS_REGRESSION_PROD
  Scenario: Existing user can add a product to wishlist from search page and product page
    Given I navigate to the "France" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+fr@gmail.com" and password "Spoon!23"
      And I search for the product "Anatomie"
     Then I add the product to wishlist from search page in FR-HS
      And I search for the E-Book "Chirurgie" under product type "eBooks"
     Then I add the product to wishlist from product page in FR-HS
      And I remove the product from wishlist in FR-HS
