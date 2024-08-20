package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {

    @FindBy(xpath = "//select")
    private WebElement dropDownAccess;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//textarea[@id='post-body']")
    WebElement textAreaBody;

    @FindBy(id = "post-title")
    WebElement inputTitle;

    @FindBy(xpath = "//button[text()= 'Save New Post']")
    WebElement buttonSaveNewPost;

    public CreateNewPostPage enterTextIntoInputTitle(String postTitle) {
        clearAndEnterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String postBody) {
        clearAndEnterTextIntoElement(textAreaBody, postBody);
        return this;
    }


    public CreateNewPostPage checkIsRedirectOnCreateNewPostPage() {
        //TODO checkUrl
        //TODO check some element
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }


    public CreateNewPostPage selectTextInDropDownAccessByVisibleText(String textForSelect) {

        selectTextInDropDownByVisibleText(dropDownAccess, textForSelect);
        return this;
    }


    public CreateNewPostPage selectInDropDownAccessByValue(String valueForSelect) {
        selectValueInDropDown(dropDownAccess, valueForSelect);
        return this;
    }


}
