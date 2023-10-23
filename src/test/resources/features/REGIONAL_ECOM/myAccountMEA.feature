Feature: My Account in MEA HealthStore
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @MEHS_NON_E2E @addressBook @MEHS_CICD @MEHS_REGRESSION_PROD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in US Health store
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
     Then I navigate to my account page
      And I edit the profile information
          | Title | Profession         | Speciality     |
          |  Dr.  | Health Professions | Manual Therapy |
     Then I navigate to Address book page
      And I edit the default billing address in MEA-HS
         |Title | First Name     | Last Name            | Street Address     | City       | ZipCode | Country      | Phone Number |
         | Mr.  | MEA Automation | Regional HealthStore | Long Street        | Cape Town  | 6665    | South Africa |9876543210    |
      And I edit the default shipping address in MEA-HS
         |Title | First Name     | Last Name            | Street Address     | City   | ZipCode | Country          | Phone Number |
         | Mr.  | MEA Automation | Regional HealthStore | Long Street        | Cape Town  | 6665    | South Africa |9876543210    |
      And I add new address in MEA-HS
         |Title | First Name     | Last Name            | Street Address     | City       | ZipCode | Country      | Phone Number |
         | Mr.  | MEA Automation | Regional HealthStore | Long Street        | Cape Town  | 6665    | South Africa |9876543210    |
      And I edit the newly added address in MEA-HS
         |Title | First Name     | Last Name            | Street Address     | City       | ZipCode  | Country      | Phone Number |
         | Mr.  | MEA Automation | Regional HealthStore | Long Street        | Cape Town  | 6665     | South Africa |9876543210    |
      And I delete the newly added address

  @MEHS_NON_E2E @Wishlist @MEHS_REGRESSION_PROD
  Scenario: Existing user can add a product to wishlist from search page and product page
    Given I navigate to the "Middle East" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23"
      And I search for the product "eyes"
     Then I add the product to wishlist from search page
      And I search for the product "Heart" under product type "Books"
     Then I add the product to wishlist from product page
      And I remove the product from wishlist
