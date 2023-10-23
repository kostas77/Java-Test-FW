Feature: Product purchase E2E tests for JP EOP B2B health store
  As a customer
  I want to be able to purchase different product types (ebook, ejournal,...)

  @JPB2BEOPStore @JPEOP_E2E @B2BE2E
  Scenario: eBook purchase using the existing account checkout path
    Given I navigate to the "JP EOP" B2B store home page
    And I sign-into B2B store with username "v.mariappan+jp@elsevier.com" and password "magento123"
    And I empty the B2B store cart
    And I search for the required product "Annals of Emergency Medicine"
    When I add the product to the cart BtoB site
    And I proceed to the checkout pages
    And I proceed further to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the order thank you page
    Then The B2B order is validated with ORR API request "JP EOP"

  @JPB2BEOPStore @JPEOP_E2E @B2BE2E
  Scenario: eJournal purchase using the existing account checkout path
    Given I navigate to the "JP EOP" B2B store home page
    And I sign-into B2B store with username "v.mariappan+jp@elsevier.com" and password "magento123"
    And I empty the B2B store cart
    And I search for the required product "Journal of Thrombosis and Haemostasis"
    When I add the product to the cart BtoB site
    And I proceed to the checkout pages
    And I proceed further to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the order thank you page
    Then The B2B order is validated with ORR API request "JP EOP"

  @JPB2BEOPStore @JPEOP_E2E @B2BE2E
  Scenario: Mixed cart purchase using the existing account checkout path
    Given I navigate to the "JP EOP" B2B store home page
    And I sign-into B2B store with username "v.mariappan+jp@elsevier.com" and password "magento123"
    And I empty the B2B store cart
    And I search for different product and add them to cart
      | Journal of Thrombosis and Haemostasis        |
      | the emergency department technician handbook |
    And I proceed to the checkout pages
    And I proceed further to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the order thank you page
    Then The B2B order is validated with ORR API request "JP EOP"

  @JPB2BEOPStore @JPEOP_E2E @B2BE2E
  Scenario: Product can be purchased from wishlist
    Given I navigate to the "JP EOP" B2B store home page
    And I sign-into B2B store with username "v.mariappan+jp@elsevier.com" and password "magento123"
    And I empty the B2B store cart
    And I search for the product "the emergency department technician handbook" to add to wishlist
    Then I add the product to wishlist from search page
    And I add the product to cart from B2B wishlist page
    And I proceed to the checkout pages
    And I proceed further to payment page and enter the payment details
      | Card Number         | Expiry Date | CVV | Name on card    |
      | 4111 1111 4555 1142 | 0330        | 737 | Test Automation |
    And I place the order
    Then The order is displayed on the order thank you page
    Then The B2B order is validated with ORR API request "JP EOP"