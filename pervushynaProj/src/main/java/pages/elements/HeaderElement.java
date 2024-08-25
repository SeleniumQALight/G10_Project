package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.LoginPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = "//a[@class='mr-2']")
    private WebElement myProfileButton;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-secondary']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement userName;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUsernameInLoginForm;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPasswordInLoggInForm;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
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

    public boolean isInputLoginDisplayed() {
        return isElementVisible(inputUsernameInLoginForm);
    }

    public boolean isInputPasswordDisplayed() {
        return isElementVisible(inputPasswordInLoggInForm);
    }
}
