package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage{

    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@id='post-body']")
    private WebElement textAreaBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement IsPostUniqueCheckbox;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        //TODO checkUrl
        //TODO check some element
        return this;
    }

    public CreateNewPostPage enterTitleInToInputTitle(String postTitle) {
        clearAndEnterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public CreateNewPostPage enterTextInToTextAreaBody(String postBody) {
        clearAndEnterTextIntoElement(textAreaBody, postBody);
        return this;
    }

    public CreateNewPostPage setStateToIsPostUniqueCheckbox(String neededState){
        setCheckBoxToNeededState(IsPostUniqueCheckbox, neededState);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

}
