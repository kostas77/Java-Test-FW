Feature: My Account in B2B EU EOP B2B Store
  As a customer I will be able to maintain the account details like personal information, Address book, Wishlist etc

  @EUEOP_NON_E2E @USEOP_CICD
  Scenario: My Account details like personal details, Address book can be updated or edited as a existing customer in US B2B Health store
    Given I navigate to the "EU EOP" B2B store home page
      And I sign-into B2B store with username "v.mariappan+eu@elsevier.com" and password "magento123"
     Then I navigate to my account page in B2B health store
     Then I navigate to Address book page in B2B health store
      And I edit the default billing address in B2B EU store
          | First Name | Last Name  | Street Address | City   | State    | ZipCode | Country | Phone Number |
          | Vishal     | EOP        | Via del Corso  | Rome   | Livorno  |00100    |  Italy  | 9876543210   |

  @EUEOP_NON_E2E
  Scenario: Existing user can add a product to wishlist from search page and product page
    Given I navigate to the "EU EOP" B2B store home page
      And I sign-into B2B store with username "v.mariappan+eu@elsevier.com" and password "magento123"
      And I search for the required product "the emergency department technician handbook"
     Then I add the product to wishlist from search page
      And I remove the product from wishlist