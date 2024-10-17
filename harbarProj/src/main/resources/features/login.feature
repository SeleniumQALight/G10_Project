Feature: Login Feature

  Scenario: R001 Valid Login
    Given I open login page
    When I login with valid cred
    Then I see avatar on Home page