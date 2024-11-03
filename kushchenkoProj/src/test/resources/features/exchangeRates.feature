Feature: Privatbank Exchange Rates Feature

  @R008
  Scenario Outline: Check currency exchange rates from Privatbank
    Given I get currency exchange rates '<currency>' from Privatbank by API
    When I open Privatbank Home page
    And I get currency exchange rates '<currency>' from Privatbank Exchange Rate popup
    Then I compare currency exchange rates from API and from UI to be equal

    Examples:
        | currency |
        | USD      |
        | EUR      |


