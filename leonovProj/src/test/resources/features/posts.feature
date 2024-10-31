Feature: Posts Feature

  @R004
  @deletePostsForDefaultUser
  Scenario Outline: R004 Check number of posts on UI
    Given I create <numberOfPosts> new posts via API for 'default' user and 'default' password
      | title  | Post by API  |
      | body   | body of post |
      | select | One Person   |
    And I open home page as 'default' user and 'default' password
    When I click on button MyProfile on Header Element
    Then I was redirected to MyProfile page
    And I see <numberOfPosts> posts in Posts List on MyProfile page

    Examples:
      | numberOfPosts |
      | 2             |
