package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.elements.HeaderElement;

import java.time.Duration;
import java.util.List;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "// p[text()='Is this post unique? : yes']")
    private WebElement uniqueText;

    @FindBy(xpath = "//a[contains(@class, 'text-primary') and contains(@class, 'mr-2')]")
    private WebElement buttonEditPost;

    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successEditMessage;

    private String locatorForTextThisPostWasWritten = "//*[contains(text(), '%s')]";

    private String postTitleLocator = "//*[text()='%s']";



    private List<WebElement> postsListWithTitle(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }



    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }


    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        //TODO check same element
        return this;
    }


    /**
     * Method checks if success message is displayed
     * doesn't check text of message
     *
     * @return PostPage
     */
    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementVisible(successMessage, "Success message"));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text in message", expectedMessageText, actualText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }

    public PostPage checkTextThisPostWasWrittenIsVisible(String expectedText) {
        Assert.assertTrue(expectedText + "Text is not visible"
                , isElementVisible(String.format(locatorForTextThisPostWasWritten, expectedText)));
        return this;
    }

    public PostPage checkIsPostUniqueDisplayed() {
        Assert.assertTrue("Post is not unique", isElementVisible(uniqueText));
        return this;
    }

    public PostPage checkIsPostUniqueText(String expectedText) {
        String actualText = uniqueText.getText();
        Assert.assertEquals("Text", expectedText, actualText);
        return this;
    }

    public PostPage checkPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle, expectedNumberOfPosts, postsListWithTitle(postTitle).size());
        return this;
    }

    public PostPage clickOnEditPostButton() {
        clickOnElement(buttonEditPost);
        return this;
    }



    public PostPage enterTextIntoInputTitle(String postTitleEdited) {
        clearAndEnterTextIntoElement(inputTitle, postTitleEdited);
        return this;
    }

    public PostPage clickOnButtonSaveUpdates() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public PostPage checkIsRemainOnEditPostPage() {
        return this;
    }

    public PostPage checkIsEditSuccessMessageDisplayed(String s) {
        Assert.assertTrue("Success message is not displayed", isElementVisible(successEditMessage, "Edit Success message"));
        return this;


    }
    }




