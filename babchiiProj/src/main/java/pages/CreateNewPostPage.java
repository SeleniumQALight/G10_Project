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
    @FindBy(xpath = "//select")
    private WebElement dropdownAccess;
    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement isPostUniqueCheckbox;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkUrl();
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
        setCheckBoxToNeededState(isPostUniqueCheckbox, neededState);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreateNewPostPage selectTextInDropDownAccessByVisibleText(String textForSelect) {
        selectTextInDropDownByVisibleText(dropdownAccess, textForSelect);
        return this;
    }

    public CreateNewPostPage selectValueInDropDownAccess(String valueFromSelect) {
        selectValueInDropDown(dropdownAccess, valueFromSelect);
        return this;
    }
}
