Feature: Currency exchange rates

  @R010
  Scenario Outline: R010 Verification of currency exchange rates between API and UI
    Given Given I request the exchange rate for <currency> from the PrivatBank API
    When I get the exchange rate for <currency> from the PrivatBank homepage
    Then The exchange rate from the API match the rate from the UI


    Examples:
      | currency |
      | USD      |
      | EUR      |
