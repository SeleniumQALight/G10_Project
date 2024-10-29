Feature: Login Feature


  @R001
  Scenario: R001 Valid Login
    Given I open Login page
    When I login with valid creds
    Then I see avatar on Home page

  @R002
  Scenario Outline: R002 Invalid Login
    Given I open Login page
    When I enter '<login>' into input login in Login page
    And I enter '<password>' into input password in Login page
    And I click on button SignIn in Login page
    Then I see alert message with text 'Invalid username/password.'

    Examples:
      | login           | password           |
      | quaauto         | not_valid_password |
      | not_valid_login | 123456qwerty       |

  @R003
  Scenario Outline: R003 validation on Registration form
    Given I open Login page
    When I enter '<username>' into input username in Registration form
    And I enter '<email>' into input email in Registration form
    And I enter '<password>' into input password in Registration form
    Then I see alert messages '<expectedMessages>'

    Examples:
      | username                        | email            | password                                                         | expectedMessages                                                                                                                |
      | valid_user                      | tr               | tr                                                               | Username can only contain letters and numbers.;You must provide a valid email address.;Password must be at least 12 characters. |
      | tr                              | tr               | tr                                                               | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.        |
      | quaauto                         | tr               | qwerty123456                                                     | You must provide a valid email address.                                                                                         |
      | tr11111111111111111111111111111 | tr               | qwerty123456                                                     | Username cannot exceed 30 characters.;You must provide a valid email address.                                                   |
      | tr                              | qaauto@gmail.com | qwerty1234111111111111111111111122222222222222222222222222222222 | Username must be at least 3 characters.;Password cannot exceed 50 characters.                                                   |
