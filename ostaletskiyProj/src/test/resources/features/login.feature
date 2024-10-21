Feature: Login Feature

@R001
  Scenario: R001 Valid Login
    Given I open Login page
    When I login with valid cred
    Then I see avatar on Home page

  Scenario Outline: : R002 Login with invalid cred
    Given I open Login page
    When I enter '<login>' into input Login in Login Page
    And I enter '<password>' into input Password in Login Page
    And I click on button SignIn in Login Page
    Then I see alert message with text 'Invalid username/password.'

    Examples:
      | login            | password           |
      | qaauto           | not_valid_password |
      | not_valid _login | 123456qwerty       |