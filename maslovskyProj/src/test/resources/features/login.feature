Feature: Login Feature

  Scenario: ROO1 Valid Login
    Given I open Login page
    When I login with valid cred
    Then I see avatar on Home page