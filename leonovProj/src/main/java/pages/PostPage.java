package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement buttonEditPost;


    private String locatorIsPostUnique = "//p[text()='Is this post unique? : %s']";

    private String locatorForTextThisPostWasWritten = "//*[contains(text(), '%s')]";  //параметризований локатор

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

    public PostPage checkIsRedirectOnPostPage() {
        checkCurrentUrlWithPattern();
        //TODO check some element

        return this;
    }

    /**
     * Method checks if success message is displayed
     * doesn't check the text of the message
     *
     * @return PostPage
     */
    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementVisible(successMessage
                , "Success message"));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Message text is not as expected", expectedMessageText, actualText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {

        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);

    }

    public PostPage checkTextThisPostWasWrittenIsVisible(String expectedText) {
        Assert.assertTrue(expectedText + " Text is not visible"
                , isElementVisible(String.format(locatorForTextThisPostWasWritten, expectedText)));

        return this;
    }

    public PostPage checkIsCreatedPostUnique(String state) {
        Assert.assertTrue("Incorrect post unique state"
                , isElementVisible(String.format(locatorIsPostUnique, state)));

        return this;
    }

    public CreateNewPostPage clickOnEditButton() {
        clickOnElement(buttonEditPost);
        return new CreateNewPostPage(webDriver);
    }

}
