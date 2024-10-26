Feature: Compare currency rates UI and API

  @HW003
  Scenario Outline: HW003 compare currency rates from API with rates displayed on UI
    Given I retrieve '<currency>' exchange rates via API
    And I retrieve '<currency>' exchange rates from UI
    Then I compare currency exchange rates from API with rate displayed on UI

    Examples:
      | currency |
      | USD      |
      | EUR      |

