package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    @FindBy (xpath = "//input[@id='post-title']")
    private WebElement inputTitleForEdit;

    @FindBy(xpath = "//textarea[@id='post-body']")
    private WebElement textBodyForEdit;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdatePost;

    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement successUpdateMessage;

    @FindBy(xpath = "//a[@class='small font-weight-bold']")
    private WebElement buttonBackToPost;


    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        // TODO check some element
        return this;
    }

    public EditPostPage enterNewTextIntoInputTitle(String postTitle) {
        cleatAndEnterTextIntoElement(inputTitleForEdit, postTitle);
        return this;
    }

    public EditPostPage enterNewTextIntoInputBody(String postBody) {
        cleatAndEnterTextIntoElement(textBodyForEdit, postBody);
        return this;
    }

    public EditPostPage clickOnButtonSaveUpdatePost() {
        clickOnElement(buttonSaveUpdatePost);
        return this;
    }

    public EditPostPage checkIsSuccessUpdateMessageDisplayed() {
        isElementVisible(successUpdateMessage, "Success message");
        return this;
    }

    public EditPostPage checkTextInUpdateSuccessMessage(String expectedMessageText) {
        String actualText = successUpdateMessage.getText();
        Assert.assertEquals("Text in message", expectedMessageText, actualText);
        return this;
    }

    public PostPage clickOnButtonBackToPost() {
        clickOnElement(buttonBackToPost);
        return new PostPage(webDriver);
    }
}
