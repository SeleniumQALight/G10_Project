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

    @FindBy(xpath = "//input [@type ='checkbox']")
    private WebElement checkBox;

    @FindBy(xpath = "//select")
    private WebElement dropdownAccess;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreateNewPostPage checkIsRedirectToCreatePostPage() {
        checkUrl();
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

    public CreateNewPostPage selectTextInDropDownAccessByVisibleText(String textForSelect) {
        selectTextInDropDownByVisibleText(dropdownAccess, textForSelect);
        return this;
    }

    public CreateNewPostPage selectValueInDropDownAccess(String value) {
        selectValueInDropDown(dropdownAccess, value);
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



