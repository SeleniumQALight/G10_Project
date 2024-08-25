package pages;

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

    @FindBy(xpath = "//div[text()='Invalid username/password.']")
    private WebElement alertMessage;

    private Logger logger = Logger.getLogger(getClass());

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Login page is opened");
    }

    public void enterTextIntoInputLogin(String login) {
//        try {
////            WebElement inputUserNameInLoginForm =
////                    webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//            inputUserNameInLoginForm.clear();
//            inputUserNameInLoginForm.sendKeys(login);
//            logger.info(login + " was entered into input Login");
//        } catch (Exception e) {
//            logger.error("Can not work with element " + e);
//            Assert.fail("Can not work with element " + e);
//        }
        clearAndEnterTextIntoElement(inputUserNameInLoginForm, login);
    }

    public void enterTextIntoInputPassword(String password) {
//        try {
////            WebElement inputPasswordInLoginForm =
////                    webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//            inputPasswordInLoginForm.clear();
//            inputPasswordInLoginForm.sendKeys(password);
//            logger.info(password + " was entered into input Password");
//
//        } catch (Exception e) {
//            logger.error("Can not work with element " + e);
//            Assert.fail("Can not work with element " + e);
//        }
        clearAndEnterTextIntoElement(inputPasswordInLoginForm, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInVisible() {
        return isElementVisible(buttonSignIn);
    }

    public boolean isAlertMessageVisible() {
        return isElementVisible(alertMessage);
    }

    public boolean isInputUserNameVisible() {
        return isElementVisible(inputUserNameInLoginForm);
    }

    public boolean isInputPasswordVisible() {
        return isElementVisible(inputPasswordInLoginForm);
    }

    public HomePage openLoginPageAndLoginWithValidCreds() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}
