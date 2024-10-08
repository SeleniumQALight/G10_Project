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

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkBoxUniquePost;

    @FindBy(xpath = "//select")
    private WebElement dropdownAccess;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkUrlWithPattern();
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


    public CreateNewPostPage setCheckBoxUniquePost() {
        setCheckBoxToNeededState(checkBoxUniquePost, "check");
        return this;
    }

    public CreateNewPostPage selectTextInDropdownAccessByVisibleText(String textForSelect) {
        selectTextInDropdownByVisibleText(dropdownAccess, textForSelect);
        return this;
    }


    public CreateNewPostPage selectValueInDropdownAccess(String valueForSelect) {
        selectValueInDropdown(dropdownAccess, valueForSelect);
        return this;
    }
}


