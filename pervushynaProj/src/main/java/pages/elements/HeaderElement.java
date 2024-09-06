package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreateNewPostPage;
import pages.LoginPage;
import pages.LoginPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {

    //myProfile button
    @FindBy(xpath = "//a[@class='mr-2']")
    private WebElement myProfileButton;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-secondary']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement ImgAvatar;

    @FindBy(xpath = "//*[@class='text-white mr-2 header-chat-icon']")
    private WebElement chatButton;

    @FindBy(xpath = "//a[@href='#']")
    private WebElement searchButton;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;


    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement userName;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }


    public boolean isButtonSignOutVisible() {
        return  isElementVisible(buttonSignOut);

    }

    public boolean isButtonMyProfileVisible() {
        return isElementVisible(myProfileButton);
    }

    public boolean isUserNameDisplayed() {
        return isElementVisible(userName);
    }

    public boolean isImgAvatarVisible() {
        return isElementVisible(ImgAvatar);
    }

    public boolean isChatButtonVisible() {
        return isElementVisible(chatButton);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public boolean isSearchButtonVisible() {
        return isElementVisible(searchButton);
    }

    public boolean isButtonCreatePostVisible() {
        return isElementVisible(buttonCreatePost);
    }
}
