Feature: Validation Messages Feature

  @R005
    @validationMessages
  Scenario Outline: R005 Check validation messages
    Given I open Login page
    When I enter '<username>' into input Username in Login page
    And I enter '<email>' into input Email in Login page
    And I enter '<password>' into input Password in Login page
    And I click on button SignUp in Login page
    Then I see validation message with text '<validationMessages>'



    Examples:
      | username | email                | password     | validationMessages                                                                                                       |
      | mda1     | maria16dai@gmail.com | tr           | Password must be at least 12 characters.                                                                                 |
      | tr       | tr                   | tr           | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | tr       | tr                   | 123456qwerty | Username must be at least 3 characters.;You must provide a valid email address.                                          |
      | mdai1    | tr                   | 123456qwerty | You must provide a valid email address.                                                                                  |