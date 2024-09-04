package Pages;

import data.TestData;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserNameInLoginForm;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPasswordInLoginForm;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[contains(text(), 'Invalid username/password.')]")
    private WebElement notificationAlert;


    private Logger logger = Logger.getLogger(getClass());

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        webDriver.get(baseUrl);
        logger.info( "Login page was opened_" + baseUrl);
    }

    public void enterTextIntoInputLogin(String login) {
        clearAndEnterTextIntoElement(inputUserNameInLoginForm, login);
    }

    public void enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPasswordInLoginForm, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isNotificationVisible() {
        return isElementDisplayed(notificationAlert, "Notification alert");
    }

    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn, "Sign In button");
    }

    public boolean isInputLoginVisible() {
        return isElementDisplayed(inputUserNameInLoginForm, "Login input");
    }

    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPasswordInLoginForm, "Password input");
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCredentials() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}

