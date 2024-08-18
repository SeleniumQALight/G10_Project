package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreateNewPostPage;
import pages.MyProfilePage;

import static data.TestData.VALID_LOGIN_UI;

public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;
//
    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement userName;

//    @FindBy(xpath = "//span[contains(text(), '" + VALID_LOGIN_UI + "')]")
//    private WebElement userName;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    public boolean isButtonCreatePostVisible() {
        return isElementVisible(buttonCreatePost);
    }

    public boolean isButtonMyProfileVisible() {
        return isElementVisible(buttonMyProfile);
    }

    public String getUserName() {
        return returnTextFromElementByLocator(userName);
    }

}
