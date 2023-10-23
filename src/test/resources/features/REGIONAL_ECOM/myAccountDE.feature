Feature: My Account in Germany HealthStore
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @DEHS_NON_E2E @DEHS_CICD @DEHS_REGRESSION_PROD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in German Health store
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de@gmail.com" and password "Spoon!23"
      And I sign-in with username "test.elsevier.regional.ecom+de@gmail.com" and password "Spoon!23"
     Then I navigate to my account page
      And I edit the profile information in DE-HS
          | Title | Profession  | Speciality |
          | Herr  | Medizin     | Neurologie |
     Then I navigate to Address book page
      And I edit the default billing address in DE-HS
         |Title  | First Name | Last Name  | Street Address    | City    | ZipCode  | Phone Number |
         | Herr  | DE Test    | Automation | Schwedter Straße  | Berlin  | 10435    | 9876543210   |
      And I edit the default shipping address in DE-HS
         |Title  | First Name | Last Name  | Street Address    | City    | ZipCode  | Phone Number |
         | Herr  | DE Test    | Automation | Schwedter Straße  | Berlin  | 10435    | 9876543210   |
      And I add new address in DE-HS
         |Title  | First Name | Last Name  | Street Address    | City    | ZipCode  | Phone Number |
         | Herr  | DE Test    | Automation | Schwedter Straße  | Berlin  | 10435    | 9876543210   |
      And I edit the newly added address in DE-HS
         |Title  | First Name | Last Name  | Street Address | City        | ZipCode  | Phone Number |
         | Herr  | DE Test    | Automation | Neue Kräme     | frankfurt   | 60306    | 9876543210   |
      And I delete the newly added address in DE-HS

  @DEHS_NON_E2E @Wishlist @DEHS_REGRESSION_PROD
  Scenario: Existing user can add a product to wishlist from search page and product page
    Given I navigate to the "Germany" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+de@gmail.com" and password "Spoon!23"
      And I search for the product "Augen"
     Then I add the product to wishlist from search page in DE-HS
      And I search for the E-Book "Herz" under product type "eBooks"
     Then I add the product to wishlist from product page in DE-HS
      And I remove the product from wishlist in DE-HS
