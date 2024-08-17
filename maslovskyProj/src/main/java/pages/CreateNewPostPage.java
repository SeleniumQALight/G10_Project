package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;


public class CreateNewPostPage extends ParentPage {

//    @FindBy(xpath = "//input[@name='title']")
    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@id='post-body']")
    private WebElement textAreaBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkBox;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreateNewPostPage checkIsRedirectOnCreateNewPostPage() {
        //TODO: check URL
        //TODO: check some element
        return this;
    }

    public CreateNewPostPage enterTextIntoInputTitle(String postTitle) {
        clearAndEnterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public CreateNewPostPage enterTextIntoTextAreaBody(String postBody) {
        clearAndEnterTextIntoElement(textAreaBody, postBody);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }


    public boolean isCheckBoxSelected() {
        return checkBox.isSelected();
    }

    public CreateNewPostPage setCheckBoxOn() {
        if (!isCheckBoxSelected()) {
            clickOnElement(checkBox);
            logger.info("checkbox set to 'check' status");
        } else {
            logger.info("The checkbox is already in 'check' status");
        }
        return this;
    }

    public CreateNewPostPage setCheckBoxOff() {
        if (isCheckBoxSelected()) {
            clickOnElement(checkBox);
            logger.info("checkbox set to 'uncheck' status");
        } else {
            logger.info("The checkbox is already in 'uncheck' status");
        }
        return this;
    }

    public CreateNewPostPage setCheckBoxStatus(String status) {
        switch (status) {
            case "check":
                setCheckBoxOn();
            break;
            case "uncheck":
                setCheckBoxOff();
            break;
            default:
                logger.info("The status '" + status + "' is not valid, test is interrupted");
                Assert.assertTrue("The status '" + status + "' is not valid", false);
                break;
        }
        return this;
    }
}
