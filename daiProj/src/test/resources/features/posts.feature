Feature: Posts Feature

  @R004
    @deletePostsForDefaultUser
  Scenario Outline: R004 Check Number of posts on UI
    Given I create <numberOfPosts> new posts via API for 'default' user and 'default' password
      | title  | Post by Api  |
      | body   | body of post |
      | select | One Person   |
    And I open Home page as 'default' user and 'default' password
    When I click on button MyProfile on Header Element
    Then I was redirected to MyProfile page
    And I see <numberOfPosts> posts on Posts list on MyProfile page



    Examples:
      | numberOfPosts |
      | 2             |