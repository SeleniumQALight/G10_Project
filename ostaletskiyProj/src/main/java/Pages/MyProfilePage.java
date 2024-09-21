package Pages;

import Pages.elements.HeaderElement;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    private final String postTitleLocator = "//*[text()='%s']"; // locators with parameter

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    private List<WebElement> PostsListWithTitle(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }


    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        // TODO check some element
        return this;
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                expectedNumberOfPosts, PostsListWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsLists = PostsListWithTitle(postTitle);
        final int MAX_POST_COUNT = 100; // postList.size()
        int counter = 0;
        while (!postsLists.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsLists.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessMessageDisplayed();
            logger.info("Post with title " + postTitle + " was deleted");
            postsLists = PostsListWithTitle(postTitle);
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            Assert.fail("There are more than " + MAX_POST_COUNT + " posts with title " + postTitle);
        }
        return this;
    }

    private MyProfilePage checkIsSuccessMessageDisplayed() {
        // check message is displayed
        Assert.assertTrue("Success message is not displayed"
                , isElementDisplayed(successMessageDelete, "Success message about delete"));
        return this;
    }

    public EditPostPage clickOnPostWithTitle(String postTitle) {
        clickOnElement(PostsListWithTitle(postTitle).get(0));
        return new EditPostPage(webDriver);
    }
}
