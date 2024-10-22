Feature: Login Feature

  @R001
  Scenario: R001 Valid Login
    Given I open login page
    When I login with valid cred
    Then I see avatar on Home page

  @R002
  Scenario Outline: R002 Login with invalid
    Given I open login page
    When I enter '<login>' into input Login in Login page
    And I enter '<password>' into input PassWord in Login page
    And I click on button SignIn in Login Page
    Then I see alert message with text 'Invalid username/password.'


    Examples:
      | login           | password           |
      | qaauto          | not_valid_password |
      | not_valid_login | 123456qwerty       |

  @R003
  Scenario Outline: R003 Check validation messages
    Given I open login page
    When I enter '<userName>' into input UserName in Registration form
    And I enter '<email>' into input Email in Registration form
    And I enter '<password>' into input PassWord in Registration form
    Then I see the validation messages '<expectedMessages>'

    Examples:
      | userName                        | email                        | password                                             | expectedMessages                                                                                                         |
      | R003raHand2024171700            | tr                           | tr                                                   | You must provide a valid email address.;Password must be at least 12 characters.                                         |
      | R003raHand2024171700            | R003raReilly202472108@gml.rr | tr                                                   | Password must be at least 12 characters.                                                                                 |
      | tt                              | tr                           | tr                                                   | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | tttttttttttttttttttttttttttttt1 | tr                           | tr                                                   | Username cannot exceed 30 characters.;You must provide a valid email address.;Password must be at least 12 characters.   |
      | tttttttttttttttttttttttttttttt  | tr                           | tttttt111111                                         | You must provide a valid email address.                                                                                  |
      | tttttttttttttttttttttttttttttt1 | tr                           | ttttttttttttttttttttttttt1111111111111111111111111   | Username cannot exceed 30 characters.;You must provide a valid email address.                                            |
      | tttttttttttttttttttttttttttttt1 | tr                           | ttttttttttttttttttttttttt111111111111111111111111122 | Username cannot exceed 30 characters.;You must provide a valid email address.;Password cannot exceed 50 characters.      |


