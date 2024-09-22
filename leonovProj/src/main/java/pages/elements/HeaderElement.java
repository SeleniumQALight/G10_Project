package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.LoginPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//a[@class='text-white mr-2 header-search-icon']")
    private WebElement iconSearch;

    @FindBy(xpath = "//span[@class='text-white mr-2 header-chat-icon']")
    private WebElement iconChat;

    @FindBy(xpath = "//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    private String username = "//span[text()=' %s']";


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public HeaderElement checkIsIconSearchVisible() {
        checkIsElementVisible(iconSearch);
        return this;
    }

    @Step
    public HeaderElement checkIsIconSearchNotVisible() {
        checkIsElementNotVisible(iconSearch);
        return this;
    }

    @Step
    public HeaderElement checkIsIconChatVisible() {
        checkIsElementVisible(iconChat);
        return this;
    }

    @Step
    public HeaderElement checkIsIconChatNotVisible() {
        checkIsElementNotVisible(iconChat);
        return this;
    }

    @Step
    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    @Step
    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut);
        return this;
    }

    @Step
    public boolean isButtonCreatePostVisible() {
        return isElementVisible(buttonCreatePost);
    }

    @Step
    public HeaderElement checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    @Step
    public HeaderElement checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost);
        return this;
    }

    @Step
    public boolean isMyProfileVisible() {
        return isElementVisible(buttonMyProfile);
    }

    @Step
    public HeaderElement checkIsMyProfileVisible() {
        checkIsElementVisible(buttonMyProfile);
        return this;
    }

    @Step
    public HeaderElement checkIsMyProfileNotVisible() {
        checkIsElementNotVisible(buttonMyProfile);
        return this;
    }

    @Step
    public boolean isUsernameVisible(String validLoginUi) {
        return isElementVisible(String.format(username, validLoginUi));
    }


    public void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
    }
}
