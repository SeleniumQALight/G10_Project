package Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
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



   private Logger logger = Logger.getLogger(getClass());

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPage() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Login page was opened");
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
}

