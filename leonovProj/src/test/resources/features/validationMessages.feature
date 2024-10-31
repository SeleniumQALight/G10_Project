Feature: Validation Messages Feature

  @R005
    @validationMessages
  Scenario Outline: R005 Check validation messages on UI
    Given I open Login page
    When I enter '<username>' into Username field
    And I enter '<email>' into Email field
    And I enter '<password>' into Password field
    Then I see validation message '<validationMessages>' for fields

    Examples:
      | username | email                | password | validationMessages                                                                                                       |
      | tr       | tr                   | tr       | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | DL13     | tr                   | tr       | You must provide a valid email address.;Password must be at least 12 characters.                                         |
      | DL13     | skydark912@gmail.com | tr       | Password must be at least 12 characters.                                                                                 |