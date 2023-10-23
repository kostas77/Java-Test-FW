Feature: My Account in India HealthStore
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @INHS_NON_E2E @INHS_CICD @INHS_REGRESSION_PROD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in India Health store
    Given I navigate to the "India" regional health store home page
    And I sign-in with username "test.elsevier.regional.ecom+in@gmail.com" and password "Spoon!23"
    Then I navigate to my account page
    And I edit the profile information
      | Title | Profession | Speciality  |
      | Dr    | Physician  | Cardiology  |
    Then I navigate to Address book page
    And I edit the default billing address in IN-HS
      |Title      | First Name    | Last Name            | Street Address  | City    | ZipCode  | Phone Number |
      | Dr        | IN Automation | Regional HealthStore | Ascendas        | Chennai | 600113   | 9876543210   |
    And I edit the default shipping address in IN-HS
      |Title      | First Name    | Last Name            | Street Address  | City    | ZipCode  | Phone Number |
      | Dr        | IN Automation | Regional HealthStore | Ascendas        | Chennai | 600113   | 9876543210   |
    And I add new address in IN-HS
      |Title      | First Name    | Last Name            | Street Address  | City    | ZipCode  | Phone Number |
      | Dr        | IN Automation | Regional HealthStore | Ascendas        | Chennai | 600113   | 9876543210   |
    And I edit the newly added address in IN-HS
      |Title      | First Name    | Last Name            | Street Address  | City      | ZipCode  | Phone Number |
      | Dr        | IN Automation | Regional HealthStore | Koramangala     | Bangalore | 600113   | 9876543210   |