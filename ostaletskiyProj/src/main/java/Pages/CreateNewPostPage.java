package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {

    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@id=\"post-body\"]")
    private WebElement textAreaBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreateNewPostPage checkIsRedirectToCreatePostPage() {
        // TODO checkUrl
        // TODO check some element
        return this;
    }

    public CreateNewPostPage enterTextIntoInputTitle(String postTitle) {
        clearAndEnterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String postBody) {
        clearAndEnterTextIntoElement(textAreaBody, postBody);
        return this;
    }

     public PostPage clickOnButtonSaveNewPost() {
         clickOnElement(buttonSaveNewPost);
         return new PostPage(webDriver);

     }
}
