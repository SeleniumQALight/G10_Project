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

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    @FindBy(xpath = "//textarea[@id='post-body']")
    WebElement textAreaBody;

    @FindBy(id = "post-title")
    WebElement inputTitle;

    @FindBy(xpath = "//button[text()= 'Save New Post']")
    WebElement buttonSaveNewPost;

    @FindBy(xpath = "//button[text()= 'Save Updates']")
    WebElement buttonUpdatePost;



    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement checkbox;

    public CreateNewPostPage enterTextIntoInputTitle(String postTitle) {
        clearAndEnterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public PostPage createTestPost(String postTitle, String postBody) {
        enterTextIntoInputTitle(postTitle);
        enterTextIntoInputBody(postBody);
        clickOnButtonSaveNewPost();
        return new PostPage(webDriver);
    }

    public CreateNewPostPage enterTextIntoInputBody(String postBody) {
        clearAndEnterTextIntoElement(textAreaBody, postBody);
        return this;
    }


    public CreateNewPostPage checkIsRedirectOnCreateNewPostPage() {
        checkUrl();
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

    public CreateNewPostPage checkCheckboxOnPostPage() {
        checkCheckbox(checkbox);
        return this;
    }

    public CreateNewPostPage uncheckCheckboxOnPostPage() {
        uncheckCheckbox(checkbox);
        return this;
    }

    public CreateNewPostPage checkboxStateOnPostPage(String state) {
        checkboxState(checkbox, state);
        return this;
    }


    public PostPage editPostTitle(String newPostTitle) {
        clearAndEnterTextIntoElement(inputTitle, newPostTitle);
        clickOnElement(buttonUpdatePost);
        return new PostPage(webDriver);
    }
}
