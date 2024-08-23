package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {

    // @FindBy(xpath = "//input[@id='post-title']")
    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@id='post-body']")
    private WebElement textAreaBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//select")
    private WebElement dropdownAccess;

    @FindBy(xpath = "//input [@type ='checkbox']")
    private WebElement checkBox;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkUrl();
        //TODO check same element
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

    public CreateNewPostPage selectTextInDropdownAccessByVisibleText(String textForSelect) {
        selectTextInDropdownByVisibleText(dropdownAccess, textForSelect);
        return this;
    }


    public CreateNewPostPage selectValueInDropdownAccess(String valueForSelect) {
        selectValueInDropdown(dropdownAccess, valueForSelect);
        return this;
    }


    public CreateNewPostPage setCheckBoxState(String state) {
        if ("check".equalsIgnoreCase(state)) {
            checkCheckBox(checkBox);
        } else if ("uncheck".equalsIgnoreCase(state)) {
            uncheckCheckBox(checkBox);
        }
        return this;

    }

}


