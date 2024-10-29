Feature: Posts Feature

  @R004
    @deletePostsForDefaultUser
  Scenario Outline: ROO4 Check number of posts on UI
    Given I create <numberOfPosts> new posts via API for 'default' and 'default' password
      | title  | Post by API  |
      | body   | body of post |
      | select | One Person   |
    And I open Home page as 'default' user and 'default' password
    When I click on button MyProfile on Header Element
    Then I was redirected to MyProfile page
    And I see <numberOfPosts> in Posts list on MyProfile Page

    Examples:
      | numberOfPosts |
      | 2             |
