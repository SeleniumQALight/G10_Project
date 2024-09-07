package Pages.elements;

import Pages.CommonActionsWithElements;
import Pages.CreateNewPostPage;
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
        return isElementDisplayed(buttonSignOut, "Sign Out button");
    }

    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public boolean isButtonMyProfileVisible() {
        return isElementDisplayed(buttonMyProfile , "My Profile button");
    }

    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed (buttonCreatePost , "Create Post button");
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

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

}
