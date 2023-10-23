Feature: Product purchase E2E tests for US regional health store via mobile
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me

  @USHealthStore @USHS_E2E_Mobile @Mobile
  Scenario: US-HS Print Book purchase using the existing account checkout path
    Given I navigate to the "US" regional health store home page via mobile
    And I sign-in with username "test.elsevier.regional.ecom+us@gmail.com" and password "Spoon!23" via mobile
    And I empty the cart via mobile
    And I search for the product "The Geometrical Optics Workbook" via mobile
    When I add the product to the cart via mobile
    And I proceed to the checkout page
    And I confirm the shipping details via mobile
    And I navigate to payment page and enter the payment details via mobile
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order via mobile
    Then The order is displayed on the thank you page

  @USHealthStore @USHS_E2E_Mobile @Mobile
  Scenario: US-HS E-Book purchase using the existing account checkout path
    Given I navigate to the "US" regional health store home page via mobile
    And I sign-up as a new user via mobile
      | Given Name    | Family Name          | Password | Title | Profession | Speciality |
      | US Automation | Regional HealthStore | Spoon!23 | Dr.   | Physician  | Cardiology |
#    And I select "E-book" product in navigation bar via mobile
    And I search for the product "Record for the Physician’s Office" via mobile
    When I add the ebook product to the cart via mobile
    And I proceed to the checkout page
    And I navigate to payment page and enter the shipping details and payment details via mobile
      | Street Address | City          | Country       | State    | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | 23 Wall Street | New York City | United States | New York | 10001   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order via mobile
    Then The order is displayed on the thank you page


