package Pages;

import Pages.elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {

    @FindBy(xpath = "//div[@class=\"alert alert-success text-center\"]")
    private WebElement successMessage;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    private String locatorForTextThisPostWasWritten = "//*[contains(text(), '%s')]";

    @FindBy(xpath = "// p[text()='Is this post unique? : yes']")
    private WebElement uniqueText;


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
     * Method to check if success message is displayed
     * doesn't check the text of the message
     *
     * @return
     */

    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed",
                isElementDisplayed(successMessage));
        return this;
    }

    public PostPage checkTextInSuccesMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text in message",
                expectedMessageText, actualText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }

    public PostPage checkIsPostUniqueDisplayed() {
        Assert.assertTrue("Post is not unique", isElementDisplayed(uniqueText));
        return this;
    }

    public PostPage checkIsPostUniqueText(String expectedText) {
        String actualText = uniqueText.getText();
        Assert.assertEquals("Text", expectedText, actualText);
        return this;
    }

    public PostPage checkTextThisPostWasWrittenIsVisible(String expectedText) {
        Assert.assertTrue("Text is not visible",
                isElementDisplayed(String.format(locatorForTextThisPostWasWritten, expectedText)));
        return this;
    }

}
