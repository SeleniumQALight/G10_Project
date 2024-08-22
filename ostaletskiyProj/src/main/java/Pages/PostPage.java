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


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }


    public PostPage checkIsRedirectToPostPage() {
        // TODO checkUrl
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

    public PostPage checkTextThisPostWasWrittenIsVissible(String expectedText) {
        Assert.assertTrue("Text is not visible",
                isElementDisplayed(String.format(locatorForTextThisPostWasWritten, expectedText)));
        return this;
    }

}
