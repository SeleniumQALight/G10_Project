package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;
    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;
    @FindBy(xpath = ".//p[contains(text(),'Is this post unique?')]")
    private WebElement IsPostUniqueInfoText;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO checkUrl
        //TODO check some element
        return this;
    }

    /**
     * Method checks if success message is displayed
     * does not check the text of the message
     * @return PostPage
     */
    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementVisible(successMessage));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Message text is not as expected", expectedMessageText, actualText);
        return this;
    }

    public PostPage checkIsPostUniqueValueAsExpected(String expectedState) {
        String infoText = IsPostUniqueInfoText.getText();
        String actualUniqueValue = infoText.substring(infoText.indexOf(":") + 2);
        Assert.assertEquals("IsPostUnique value is not as expected", expectedState, actualUniqueValue);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }
}
