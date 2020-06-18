Feature: LoginB
  This feature is responsible for testing all the scenarios for Login of application

  @login @regression
  Scenario: Check Login with correct username and password (part3)
    And I ensure application opened
    Then I click login link
    When I enter UserName and Password
      | UserName | Password |
      | admin    | password |
    Then I click login button
    Then I should see the username with hello

  @login @regression
  Scenario: Check Login with correct username and password (part4)
    And I ensure application opened
    Then I click login link
    When I enter UserName and Password
      | UserName | Password |
      | admin    | password |
    Then I click login button
    Then I should see the username with hello
