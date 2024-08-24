package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(xpath = ".//*[text()='Is this post unique? : yes']")
    private WebElement uniqueText;

    @FindBy(xpath = "//h2")
    private WebElement titleText;

    @FindBy(xpath = "(//div[@class='body-content'])[2]")
    private WebElement bodyText;

    private String postTitleLocator = "//*[text()='%s']";  // locator with parameter


    private String locatorForTextThisPostWasWrittenIsVisible = "//*[contains(text(), '%s')]";

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        //TODO: check some element
        return this;
    }

    /**
     * Method checks if success message is displayed
     * doesn't check the text of the message
     * @return PostPage
     */
    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementVisible(successMessage, "Success message"));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Message text is not as expected", expectedMessageText, actualText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "Delete post button");
        return new MyProfilePage(webDriver);
    }

    public PostPage checkIsUniqueTextInPostDisplayed() {
        Assert.assertTrue("'Unique' text is not displayed", isElementVisible(uniqueText));
        return this;
    }

    public PostPage checkTextThisPostWasWrittenIsVisible(String expectedText) {
        Assert.assertTrue(expectedText + " Text is not visible",
                isElementVisible(String.format(locatorForTextThisPostWasWrittenIsVisible, expectedText)));
        return this;
    }

    public PostPage checkTextInPostTitleOfPostPage(String newPostTitle) {
        Assert.assertEquals("Title text is not as expected", titleText.getText(), newPostTitle);
        return this;
    }

    public PostPage checkTextInPostBodyOfPostPage(String newPostBody) {
        Assert.assertEquals("Body text is not as expected", bodyText.getText(), newPostBody);
        return this;
    }
}
