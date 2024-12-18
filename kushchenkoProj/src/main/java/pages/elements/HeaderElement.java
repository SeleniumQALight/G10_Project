package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.LoginPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    public WebElement buttonCreatePost;

    public String userNameLocator = "//span[text()=' %s']";

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement searchIcon;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement chatIcon;

    private WebElement getUserNameElement(String login) {
        return webDriver.findElement(By.xpath(String.format(userNameLocator, login)));
    }

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut, "Sign Out button");
    }

    @Step
    public boolean isButtonCreatePostVisible() {
        return isElementVisible(buttonCreatePost, "Create Post button");
    }

    @Step
    public boolean isButtonMyProfileVisible() {
        return isElementVisible(buttonMyProfile, "My Profile button");
    }

    @Step
    public boolean isUserNameVisible(String login) {
        return isElementVisible(getUserNameElement(login));
    }

    @Step
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    @Step
    public boolean isSearchIconVisible() {
        return isElementVisible(searchIcon, "Search icon");
    }

    @Step
    public boolean isChatIconVisible() {
        return isElementVisible(chatIcon, "Chat icon");
    }

    @Step
    public  HeaderElement checkIsButtonSignOutIsVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public HeaderElement checkIsMyProfileButtonVisible() {
        checkIsElementVisible(buttonMyProfile);
        return this;
    }
}
