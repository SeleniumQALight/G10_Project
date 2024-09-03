package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {

    private String postTitleLocator = ".//*[text()='%s']";   //locator with parameters
    @FindBy(xpath = ".//div[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    private List<WebElement> postListWithTitle(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        //TODO check some element
        return this;
    }

    public MyProfilePage checkIsPostWithTitlePresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle, expectedNumberOfPosts, postListWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsWithTitlePresent(String postTitle) {
        List<WebElement> postsList = postListWithTitle(postTitle);
        final int MAX_POST_COUNT = 100;
        int counter = 0;
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeleteMessageDisplayed();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = postListWithTitle(postTitle);
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            logger.error("Number of posts with title " + postTitle + " is more than " + MAX_POST_COUNT);
        }
        return this;
    }
    public MyProfilePage checkIsSuccessDeleteMessageDisplayed() {
        //check message is present
        Assert.assertTrue("Message is not displayed", isElementVisible(successMessageDelete));
        return this;
    }

    public PostPage clickOnPostWithTitle(String postTitle) {
        clickOnElement(postListWithTitle(postTitle).get(0));
        return new PostPage(webDriver);
    }
}
