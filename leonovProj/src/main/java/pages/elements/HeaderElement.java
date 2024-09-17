package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

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
    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    @Step
    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    @Step
    public boolean isButtonCreatePostVisible() {
        return isElementVisible(buttonCreatePost);
    }

    @Step
    public boolean isMyProfileVisible() {
        return isElementVisible(buttonMyProfile);
    }

    @Step
    public boolean isUsernameVisible(String validLoginUi) {
        return isElementVisible(String.format(username, validLoginUi));
    }
}
