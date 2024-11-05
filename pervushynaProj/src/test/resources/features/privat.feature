Feature: Privat Feature

  @R005
    @checkPrivatCurrencies
  Scenario Outline: R005 Comparison of exchange rates via API and UI
    Given I send request to Privat Bank API to receive exchange rates
    And I save the API buy and sale rates for "<currency>"
    And I open Privat Bank page
    When I click on button "Курси валют" on Header
    Then I save exchange rates "<currency>" from UI
    And I compare exchange rates from API and UI

    Examples:
      | currency |
      | usd      |
      | eur      |

