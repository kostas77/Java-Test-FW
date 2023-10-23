Feature: My Account in US HealthStore
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @USHS_NON_E2E @USHS_CICD @USHS_REGRESSION_PROD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in US Health store
    Given I navigate to the "US" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
     Then I navigate to my account page
      And I edit the profile information
          | Title  | Profession | Speciality |
          |  Dr    | Physician  | Cardiology |
     Then I navigate to Address book page
      And I edit the default billing address
          |Title  | First Name    | Last Name            | Street Address | City          | State    | ZipCode | Phone Number |
          | Dr    | US Automation | Regional HealthStore | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
      And I edit the default shipping address
          |Title  | First Name    | Last Name            | Street Address | City          | State    | ZipCode | Phone Number |
          | Dr    | US Automation | Regional HealthStore | 23 Wall Street | New York City | New York | 10001   | 9876543210   |
      And I add new address
          |Title  | First Name    | Last Name            | Street Address | City          | State    | ZipCode | Phone Number |
          | Mr    | US Automation | Regional HealthStore |23 Wall Street  | New York City | New York | 10001   | 9876543210   |
      And I edit the newly added address
          |Title  | First Name    | Last Name            | Street Address | City          | State      | ZipCode | Phone Number |
          | Mr    | US Automation | Regional HealthStore |Spruce Street   | Los Angeles   |California  | 90001   | 9876543210   |
      And I delete the newly added address

  @USHS_NON_E2E @USHS_REGRESSION_PROD
  Scenario: Existing user can add a product to wishlist from search page and product page
    Given I navigate to the "US" regional health store home page
      And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23"
      And I search for the product "eyes"
     Then I add the product to wishlist from search page
      And I search for the product "Heart" under product type "Books"
     Then I add the product to wishlist from product page
      And I remove the product from wishlist




