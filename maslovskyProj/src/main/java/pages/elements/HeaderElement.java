package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;

import static data.TestData.VALID_LOGIN_UI;

public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement userName;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement chatButton;

//    @FindBy(xpath = "//span[contains(text(), '" + VALID_LOGIN_UI + "')]")
//    private WebElement userName;

    String userNameLocator = "//header//*[contains (text(), '%s')]";

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    @Step
    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut, "Sign Out button");
    }

    public boolean isButtonCreatePostVisible() {
        return isElementVisible(buttonCreatePost, "Create Post button");
    }

    public boolean isButtonMyProfileVisible() {
        return isElementVisible(buttonMyProfile, "My Profile button");
    }

    public String getUserName() {
        return returnTextFromElementByLocator(userName);
    }

    public boolean isUserNameVisible(String userName) {
        return isElementVisible(String.format(userNameLocator, userName));
    }

    @Step
    public HomePage clickOnSignOutButton() {
        clickOnElement(buttonSignOut);
        return new HomePage(webDriver);
    }

    public boolean isSearchButtonVisible() {
        return isElementVisible(searchButton, "Search button");
    }

    public boolean isChatButtonVisible() {
        return isElementVisible(chatButton, "Chat button");
    }

    @Step
    public HeaderElement checkIsButtonSighOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSighOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut, "Sign Out");
        return this;
    }

    @Step
    public HomePage getHomePage() {
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

}
