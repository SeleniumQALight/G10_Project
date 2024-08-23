package pages;

import data.TestData;
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

    @FindBy(xpath = "//div[contains(@class, 'alert-danger') and contains(@class, 'text-center')]")
    private WebElement alertMessage;


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
        logger.info("Login page was opened " + baseUrl);
    }

    public void enterTextIntoInputLogin(String login) {
        // try{
        //  WebElement inputUserNameInLoginForm =
        //          webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        // inputUserNameInLoginForm.clear();
        // inputUserNameInLoginForm.sendKeys(login);
        // logger.info(login + " was inputted into input Login");

        // }catch (Exception e){
        //   logger.error("Can not work with element " + e);
        //  Assert.fail("Can not work with element " + e);
        //   }
        clearAndEnterTextIntoElement(inputUserNameInLoginForm, login);

    }

    public void enterTextIntoInputPassword(String password) {
        // try {
        //   WebElement inputPasswordInLoginForm =
        //           webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        //   inputPasswordInLoginForm.clear();
        //   inputPasswordInLoginForm.sendKeys(password);
        //   logger.info(password + " was inputted into input Password");

//        } catch (Exception e) {
        //          logger.error("Can not work with element " + e);
        //        Assert.fail("Can not work with element " + e);
        //  }
        clearAndEnterTextIntoElement(inputPasswordInLoginForm, password);
    }

    // public void clickOnButtonSignIn(){
    //     try {
    // webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();

    //    buttonSignIn.click();
    //   logger.info("Button was clicked");

//        }catch (Exception e){
    //          logger.error("Can not work with element " + e);
    //        Assert.fail("Can not work with element " + e);
    //    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInVisible() {
        return isElementVisible(buttonSignIn);
    }

    public boolean isAlertMessageVisible() {
        return isElementVisible(alertMessage);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);

    }

    public boolean isInputLoginVisible() { return isElementVisible(inputUserNameInLoginForm);
    }

    public boolean isInputPasswordVisible() { return isElementVisible(inputPasswordInLoginForm);
    }
}

