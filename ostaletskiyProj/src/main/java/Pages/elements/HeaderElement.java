package Pages.elements;

import Pages.CommonActionsWithElements;
import Pages.CreateNewPostPage;
import Pages.HomePage;
import Pages.MyProfilePage;
import io.qameta.allure.Step;
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
    @Step
    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut, "Sign Out button");
    }
@Step
    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }
    @Step
    public boolean isButtonMyProfileVisible() {
        return isElementDisplayed(buttonMyProfile , "My Profile button");
    }
    @Step
    public boolean isButtonCreatePostVisible() {
        return isElementDisplayed (buttonCreatePost , "Create Post button");
    }
    @Step
    public boolean isSearchIconVisible() {
        return isElementDisplayed (iconSearch);
    }

    @Step
    public HomePage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new HomePage(webDriver);
    }
    @Step
    public boolean isChatButtonVisible() {
        return isElementDisplayed(chatButton);
    }
    @Step
    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

}
