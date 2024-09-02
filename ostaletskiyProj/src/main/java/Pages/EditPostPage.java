package Pages;

import Pages.elements.HeaderElement;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {
    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement buttonEditPost;

    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@id='post-body']")
    private WebElement textAreaBody;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveSaveUpdates;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//a[contains(text(), 'Back to post permalink')]")
    private WebElement getBack;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage enterTextIntoInputTitle(String postTitle) {
        clearAndEnterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public EditPostPage enterTextIntoTextAreaBody(String postBody) {
        clearAndEnterTextIntoElement(textAreaBody, postBody);
        return this;
    }

    public EditPostPage clickOnButtonSaveUpdates() {
        clickOnElement(buttonSaveSaveUpdates);
        return new EditPostPage(webDriver);
    }

    public EditPostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementDisplayed(successMessage, "Success update message"));
        return this;
    }

    public EditPostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Message text is not as expected", expectedMessageText, actualText);
        return this;
    }


    public EditPostPage checkTextInPostTitle(String newPostTitle) {
        String actualText = inputTitle.getAttribute("value");
        Assert.assertEquals("Message title is not as expected", newPostTitle, actualText);
        return this;
    }

    public EditPostPage checkTextInPostBody(String newPostBody) {
        String actualText = textAreaBody.getAttribute("value");
        Assert.assertEquals("Message body is not as expected", newPostBody, actualText);
        return this;
    }


    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }
}

