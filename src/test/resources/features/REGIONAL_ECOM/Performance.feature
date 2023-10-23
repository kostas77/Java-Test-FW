Feature: Regional stores Performance testing

  @perf @lighthouse
  Scenario: Run the LightHouse plugin on the USHS home page
    Given I navigate to the "US" regional health store home page
     Then a LightHouse report is generated for that page

