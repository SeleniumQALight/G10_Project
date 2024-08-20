package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successDeleteMessage;

    private String postTitleLocator = "//*[text()='%s']"; // locator with parameter

    private List<WebElement> postsListWithTitle(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkIsRedirectOnProfilePage() {
        checkCurrentUrlWithPattern();
        //TODO check some element

        return this;
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                expectedNumberOfPosts, postsListWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostTillPresent(String postTitle) {
        List<WebElement> postList = postsListWithTitle(postTitle);
        final int MAX_POST_COUNT = 100;  //postList.size()
        int counter = 0;
        while (!postList.isEmpty() && counter < MAX_POST_COUNT) {
            clickOnElement(postList.get(0));
            new PostPage(webDriver).checkIsRedirectOnPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectOnProfilePage()
                    .checkIsSuccessDeleteMessageDisplayed();
            logger.info("Post with title " + postTitle + " was deleted");
            postList = postsListWithTitle(postTitle);
            counter++;
        }

        if (counter >= MAX_POST_COUNT) {
            logger.info("Number of posts with title " + postTitle + " is more than " + MAX_POST_COUNT);
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeleteMessageDisplayed() {

        Assert.assertTrue("Success delete message is not displayed",
                isElementVisible(successDeleteMessage));

        return this;
    }
}
