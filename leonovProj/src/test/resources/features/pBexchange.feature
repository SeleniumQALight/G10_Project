Feature: PrivatBank Exchange Feature

@R009
  Scenario Outline: Get exchange rate from PrivatBank
    Given I send a GET request to PrivatApi and I save rates for '<currency>' from response
    When I open the Privat main page in the browser
    And I click currency rates button and save rates for '<currency>' from UI
    Then I compare rates from API and UI to be equal


    Examples:
    | currency |
    | USD      |
    | EUR      |


