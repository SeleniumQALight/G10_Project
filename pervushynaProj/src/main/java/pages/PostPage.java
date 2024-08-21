package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    private String locatorForTextThisPostWasWritten = "//*[contains(text(), '%s')]";

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
        // TODO check some element
        return this;
    }

    /**
     * Method checks if success message is displayed
     * doesn't check text of message
     * @return PostPage
     */
    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementVisible(successMessage));
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
        Assert.assertTrue(expectedText + "Text is not visible", isElementVisible(String.format(locatorForTextThisPostWasWritten, expectedText)));
        return this;
    }
}
