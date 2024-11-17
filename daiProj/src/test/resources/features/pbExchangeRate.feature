Feature: PrivatBank Exchange Rate Feature

  @R006
  Scenario Outline: Get exchange rate from PrivatBank
    Given I send GET request to PrivatBankApi and I save rates for '<currency>' from response
    When I open the PrivatBank main page in browser
    And I click on currency rates button and I save rates for '<currency>' from UI
    Then I compare rates from API with UI


    Examples:
      | currency |
      | USD      |
      | EUR      |




