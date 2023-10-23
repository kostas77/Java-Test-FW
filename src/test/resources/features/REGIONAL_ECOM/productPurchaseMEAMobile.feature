Feature: Product purchase E2E tests for MEA regional health store via mobile
  As a customer
  I want to be able to purchase different product types (printbook, ebook, journal,...)
  So that they will be delivered to me

  @MEAHealthStore @MEAHS_E2E_Mobile @Mobile
  Scenario: MEA-HS Print Book purchase using the existing account checkout path
    Given I navigate to the "Middle East" regional health store home page via mobile
    And I sign-in with username "test.elsevier.regional.ecom+mea@gmail.com" and password "Spoon!23" via mobile
    And I empty the cart via mobile
    And I search for the product "Pain, 1st Edition" via mobile
    When I add the product to the cart via mobile
    And I proceed to the checkout page
    And I confirm the shipping details via mobile
    And I navigate to payment page and enter the payment details via mobile
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order via mobile
    Then The order is displayed on the thank you page

  @MEAHealthStore @MEAHS_E2E_Mobile @Mobile
  Scenario: MEA-HS E-Book purchase using the existing account checkout path
    Given I navigate to the "Middle East" regional health store home page via mobile
    And I sign-up as a new user via mobile
      | Given Name     | Family Name          | Password | Title | Profession         | Speciality     |
      | MEA Automation | Regional HealthStore | Spoon!23 |  Dr.  | Health Professions | Manual Therapy |
#    And I select "E-book" product in navigation bar via mobile
    And I search for the product "Crash Course Gastrointestinal System Updated Print + eBook edition, 4th Edition" via mobile
    When I add the ebook product to the cart via mobile
    And I proceed to the checkout page
    And I navigate to payment page and enter the billing address and payment details in EU-HS via mobile
      | Street Address      | City   | ZipCode | Phone Number | Card Number         | Expiry Date | CVV | Name on card    |
      | Al Markhiya Street  | Riyadh | 00000   | 9876543210   | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order via mobile
    Then The order is displayed on the thank you page
