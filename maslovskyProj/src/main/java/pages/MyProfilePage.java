package pages;

import com.google.common.io.ByteSource;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {

//    private String postTitleLocator = "//*[text()='%s']";  // locator with parameter
    private String postTitleLocator = "//*[contains(text(), '%s')]";  // locator with parameter

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    private List<WebElement> postListWithTitle(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        //TODO: check some element
        return this;
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                expectedNumberOfPosts, postListWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = postListWithTitle(postTitle);
        final int MAX_POST_COUNT = 100; // postList.size()
        int counter = 0;
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsList.get(0), "Post with title " + postTitle);
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = postListWithTitle(postTitle);
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            logger.info("Number of posts with title " + postTitle + " is more than " + MAX_POST_COUNT);
        }
            return this;
    }

    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        // check message is present
        Assert.assertTrue("Message is not present"
                , isElementVisible(successMessageDelete, "Message about successful delete"));
        return this;
    }

    public EditPostPage clickOnPostWithTitle(String postTitle) {
        clickOnElement(postTitleLocator, postTitle);
        return new EditPostPage(webDriver);
    }

    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts ", numberOfPosts, postsList.size());
        return this;
    }
}
