package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {

    private String postTitleLocator = "//*[text()='%s']"; // locator with parameter

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successDeleteMessage;

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

    public MyProfilePage checkIsRedirectToProfilePage() {
        checkUrlWithPattern();
        //TODO check some element
        return this;
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle, expectedNumberOfPosts, postListWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {

        List<WebElement> postsList = postListWithTitle(postTitle);
        final int MAX_POST_COUNT = 100; // postList.size()
        int counter = 0;
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToProfilePage()
                    .checkIsMessageSuccessDeletePostPresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = postListWithTitle(postTitle);
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            logger.info("There are more than " + MAX_POST_COUNT + " posts with title " + postTitle);
        }
        return this;
    }

    private MyProfilePage checkIsMessageSuccessDeletePostPresent() {
        Assert.assertTrue("Success message is not displayed", isElementVisible(successDeleteMessage));
        return this;
    }
}
