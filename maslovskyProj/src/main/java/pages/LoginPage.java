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
    private WebElement buttonSighIn;

    @FindBy(xpath = "//*[contains (text(), 'Invalid username/password.')]")
    private WebElement invalidCredentialsText;

    private Logger logger = Logger.getLogger(getClass());

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Login page was opened");
    }

    public void enterTextIntoInputLogin(String login) {
//        try{
//            WebElement inputUserNameInLoginForm =
//                    webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//            inputUserNameInLoginForm.clear();
//            inputUserNameInLoginForm.sendKeys(login);
//            logger.info(login + " was inputted into input Login");
//        } catch (Exception e) {
//            logger.error("Can not work with element " + e);
//            Assert.fail("Can not work with element " + e);
//        }
        clearAndEnterTextIntoElement(inputUserNameInLoginForm, login);
    }

    public void enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPasswordInLoginForm, password);
    }

    public void clickOnButtonSighIn() {
        clickOnElement(buttonSighIn);
    }

    public boolean isInvalidCredentialsTextDisplayed() {
        return isElementVisible(invalidCredentialsText);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSighIn();
        return new HomePage(webDriver);
    }

    public boolean isButtonSignInVisible() {
        return isElementVisible(buttonSighIn);
    }

    public boolean isUsernameInputFieldVisible() {
        return isElementVisible(inputUserNameInLoginForm);
    }

    public boolean isPasswordInputFieldVisible() {
        return isElementVisible(inputPasswordInLoginForm);
    }
}
