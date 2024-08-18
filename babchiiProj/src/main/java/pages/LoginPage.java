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

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement invalidLoginErrorMessage;

    Logger logger = Logger.getLogger(getClass());

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Login page was opened");
    }

    public void enterTextIntoInputLogin(String login) {
//        try{
////            WebElement inputUserNameInLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//            inputUserNameInLoginForm.clear();
//            inputUserNameInLoginForm.sendKeys(login);
//            logger.info(login + " was inputted into input Login");
//        }catch (Exception e){
//            logger.error("Can not work with element " + e);
//            Assert.fail("Can not work with element " + e);
//        }
        clearAndEnterTextIntoElement(inputUserNameInLoginForm, login);
    }

    public void enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPasswordInLoginForm, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isInvalidLoginMessageVisible() {return isElementVisible(invalidLoginErrorMessage);}

    public boolean isButtonSignInVisible() {return isElementVisible(buttonSignIn);}

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}
