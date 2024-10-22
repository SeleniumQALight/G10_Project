Feature: Login Feature


  @R001
  Scenario: R001 Valid Login
    Given I open Login page
    When I login with valid creds
    Then I see avatar on Home page

  @R002
  Scenario: R002 Invalid Login
    Given I open Login page
    When I enter '<login>' into input login in Login page
    And I enter '<password>' into input password in Login page
    And I click on button SignIn in Login page
    Then I see alert message with text 'Invalid username/password.'

    Examples:
      | login           | password           |
      | quaauto         | not_valid_password |
      | not_valid_login | 123456qwerty       |
