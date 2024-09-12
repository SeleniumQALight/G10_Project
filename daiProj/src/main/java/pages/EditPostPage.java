package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;


public class EditPostPage extends ParentPage {

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successEditMessage;

    public EditPostPage enterNewTextIntoInputTitle(String postTitleEdited) {
        clearAndEnterTextIntoElement(inputTitle, postTitleEdited);
        return this;
    }

    public EditPostPage clickOnButtonSaveUpdates() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }



    public EditPostPage checkIsEditSuccessMessageDisplayed(String s) {
        Assert.assertTrue("Success message is not displayed", isElementVisible(successEditMessage, "Edit Success message"));
        return this;

    }

    public EditPostPage checkIsRemainOnEditPostPage() {
        return this;
    }
}
