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

  @R004
  Scenario Outline: R003 validation on Registration form
    Given I open Login page
    When I enter '<username>' into input username in Registration form
    And I enter '<email>' into input email in Registration form
    And I enter '<password>' into input password in Registration form
    Then I see alert messages '<expectedMessages>'

    Examples:
      | username                        | email              | password                                                          | expectedMessages                                                                                                                |
      | test_user                       | tt                 | trr                                                               | Username can only contain letters and numbers.;You must provide a valid email address.;Password must be at least 12 characters. |
      | trt                             | tt                 | rtr                                                               | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.        |
      | testauto                        | tt                 | qwerty123356                                                      | You must provide a valid email address.                                                                                         |
      | tr11111111111111111111111111111 | tt                 | qwerty123356                                                      | Username cannot exceed 30 characters.;You must provide a valid email address.                                                   |
      | trt                             | testauto@gmail.com | qwerty12335111111111111111111111122222222222222222222222222222222 | Username must be at least 3 characters.;Password cannot exceed 50 characters.                                                   |