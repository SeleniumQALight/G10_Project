package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPostPage extends ParentPage {

    @FindBy(id = "post-title")
    private WebElement inputTitleEditPage;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement successUpdatedMessage;

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public EditPostPage enterNewTitleInToInputTitle(String updatedTitleOfPost) {
        clearAndEnterTextIntoElement(inputTitleEditPage, updatedTitleOfPost);
        return this;
    }

    public EditPostPage clickOnButtonSaveUpdates() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public EditPostPage checkIsSuccessUpdateMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed"
                , isElementVisible(successUpdatedMessage, "Success message"));
        return this;
    }

    public EditPostPage checkTextInSuccessUpdateMessage(String expectedMessageText) {
        String actualText = successUpdatedMessage.getText();
        Assert.assertEquals("Text in message", expectedMessageText, actualText);
    return this;
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }
}

