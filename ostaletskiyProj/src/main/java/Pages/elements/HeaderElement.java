package Pages.elements;

import Pages.CommonActionsWithElements;
import Pages.HomePage;
import Pages.MyProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderElement extends CommonActionsWithElements {

    // my profile button
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[@class='text-white mr-2 header-search-icon']")
    private WebElement iconSearch;

    @FindBy(xpath = "//span[@class='text-white mr-2 header-chat-icon']")
    private WebElement chatButton;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public boolean isButtonMyProfileVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed (buttonCreatePost);
    }

    public boolean isSearchIconVisible() {
        return isElementDisplayed (iconSearch);
    }


    public HomePage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new HomePage(webDriver);
    }

    public boolean isChatButtonVisible() {
        return isElementDisplayed(chatButton);
    }
}
