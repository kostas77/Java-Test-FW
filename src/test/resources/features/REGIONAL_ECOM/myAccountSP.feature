Feature: My Account in Spain HealthStore
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @SPHS_NON_E2E @SPHS_CICD @SPHS_REGRESSION_PROD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in UK Health store
    Given I navigate to the "Spain" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
     Then I navigate to my account page
      And I edit the profile information in SP-HS
          | Title   | Profession  | Speciality                  |
          |  Señor  |Fisioterapia | Fisioterapia del deporte    |
     Then I navigate to Address book page
      And I edit the default billing address in SP-HS
          |Title   | First Name    | Last Name            | Street Address    | City    | ZipCode  | Phone Number |
          | Señor  | SP Automation | Regional HealthStore | Preciados street  | Madrid  | 28001    | 9876543210   |
      And I edit the default shipping address in SP-HS
          |Title   | First Name    | Last Name            | Street Address    | City    | ZipCode  | Phone Number |
          | Señor  | SP Automation | Regional HealthStore | Preciados street  | Madrid  | 28001    | 9876543210   |
      And I add new address in SP-HS
          |Title   | First Name    | Last Name            | Street Address    | City    | ZipCode  | Phone Number |
          | Señor  | SP Automation | Regional HealthStore | Preciados street  | Madrid  | 28001    | 9876543210   |
      And I edit the newly added address in SP-HS
          |Title | First Name    | Last Name            | Street Address     | City         | ZipCode  | Phone Number |
          | Señor| SP Automation | Regional HealthStore | St Peter's Square  | Manchester   | M1 1AA   | 9876543210   |
      And I delete the newly added address in SP-HS

  @SPHS_NON_E2E @SPHS_REGRESSION_PROD
  Scenario: Existing user can add a product to wishlist from search page and product page
    Given I navigate to the "Spain" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+sp@gmail.com" and password "Spoon!23"
      And I search for the product "ojos"
     Then I add the product to wishlist from search page in SP-HS
      And I search for the E-Book "corazón" under product type "eBooks"
     Then I add the product to wishlist from product page in SP-HS
      And I remove the product from wishlist in SP-HS
