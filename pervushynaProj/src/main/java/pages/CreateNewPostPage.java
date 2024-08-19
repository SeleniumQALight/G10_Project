package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage{

    //@FindBy(xpath = "//input[@id='post-title']")
    @FindBy (id = "post-title") //скорочений варіант
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@id='post-body']")
    private WebElement textBody;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement buttonSaveNewPost;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        // TODO checkUrl
        // TODO check some element
        return this;
    }

    public CreateNewPostPage enterTextIntoInputTitle(String postTitle) {
        cleatAndEnterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String postBody) {
        cleatAndEnterTextIntoElement(textBody, postBody);
        return this;
    }

    public PostPage clickOnSaveNewPostButton () {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }


}
