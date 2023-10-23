Feature: My Account in LATAM HealthStore
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @LATAMHS_NON_E2E @addressBook @LATAMHS_CICD @LATAMHS_REGRESSION_PROD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in US Health store
    Given I navigate to the "LATAM" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+latam@gmail.com" and password "Spoon!23"
    Then I navigate to my account page
     And I edit the profile information in LATAM-HS
         | Title    | Profession    | Speciality                                |
         | Señor.   |Enfermería     | Enfermería comunitaria y salud pública    |
    Then I navigate to Address book page
     And I edit the default billing address in LATAM-HS
         | Title   | First Name       | Last Name            | Street Address  | City          | ZipCode | Country  | Phone Number |
         | Señor.  | LATAM Automation | Regional HealthStore | Madero Street   | Mexico City   | 01000   |  México  | 9876543210   |
     And I add new address in LATAM-HS
       | Title   | First Name       | Last Name            | Street Address  | City          | ZipCode | Country  | Phone Number |
       | Señor.  | LATAM Automation | Regional HealthStore | Madero Street   | Mexico City   | 01000   |  México  | 9876543210   |
     And I edit the newly added address in LATAM-HS
         | Title  | First Name       | Last Name            | Street Address     | City                | ZipCode  | Country  | Phone Number |
         | Señor. | LATAM Automation | Regional HealthStore | Avenida Mem de Sá  | Rio de janeiro      | 20000    |  Brasil  | 9876543210   |
     And I delete the newly added address

  @LATAMHS_NON_E2E @Wishlist @LATAMHS_REGRESSION_PROD
  Scenario: Existing user can add a product to wishlist from search page and product page
    Given I navigate to the "LATAM" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+latam@gmail.com" and password "Spoon!23"
      And I search for the product "piel"
     Then I add the product to wishlist from search page
      And I search for the E-Book related to "Clínica"
     Then I add the product to wishlist from product page in LATAM-HS
      And I remove the product from wishlist in LATAM-HS
