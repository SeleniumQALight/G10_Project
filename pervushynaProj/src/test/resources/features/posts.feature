Feature: Posts Feature

  Scenario Outline: R004 Check Number Of posts on UI
    Given I create <numberOfPosts> new posts via API for 'default' user and 'default' password
      | title  | Post by API  |
      | body   | body of post |
      | select | One Person   |

    Examples:
      | numberOfPosts |
      | 2             |
