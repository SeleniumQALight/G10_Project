package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {

    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@id='post-body']")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSavePost;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkBox;

    @FindBy(xpath = "//select")
    private WebElement dropDownAccess;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        //TODO checkUrl
        //TODO check some element
        return this;
    }

    public CreateNewPostPage enterTitleInToInputTitle(String titleOfNewPost) {
        clearAndEnterTextIntoElement(inputTitle, titleOfNewPost);
        return this;
    }

    public CreateNewPostPage enterTextInToInputBody(String textInBody) {
        clearAndEnterTextIntoElement(inputBody, textInBody);
        return this;
    }

    public PostPage clickOnButtonSavePost() {
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);
    }

    public CreateNewPostPage setCheckBoxPostUniqueTrue(String state) {
        this.changeCheckBoxState(state, checkBox);
        return this;
    }

    public CreateNewPostPage selectTextInDropDownAccessByVisibleText(String textForSelect) {
        selectTextInDropdownByVisibleText(dropDownAccess, textForSelect);
        return this;
    }


    public CreateNewPostPage selectVaueInDropDownAccess(String valueForSelect) {
        selectValueInDropdown(dropDownAccess, valueForSelect);
        return this;
    }


}
