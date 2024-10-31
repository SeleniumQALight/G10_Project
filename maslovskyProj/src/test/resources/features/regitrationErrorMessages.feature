Feature: Registration Error Messages Feature

  @HW001
  Scenario Outline: HW001 Incorrect Registration error messages
    Given I open Registration page
    When I enter '<Username>' into input field Pick a username in Registration page
    And I enter '<Email>' into input field Email in Registration page
    And I enter '<Password>' into input field Password in Registration page
    Then I see registration error messages with text '<ErrorMessageTexts>'

    Examples:
      | Username       | Email      | Password           | ErrorMessageTexts                                    |
      | validName      | shortEmail | shortPassword      | errorEmail;errorPassword                             |
      | validName      |            | shortPassword      | errorPassword                                        |
      | shortName      | shortEmail | shortPassword      | errorUsername;errorEmail;errorPassword               |
      | overLengthName | shortEmail | shortPassword      | errorMaxSizeUsername;errorEmail;errorPassword        |
      | maxLengthName  | shortEmail | minLengthPassword  | errorEmail                                           |
      | overLengthName | shortEmail | maxLengthPassword  | errorMaxSizeUsername;errorEmail                      |
      | overLengthName | shortEmail | overLengthPassword | errorMaxSizeUsername;errorEmail;errorMaxSizePassword |

