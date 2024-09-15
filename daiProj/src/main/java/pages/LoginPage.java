package pages;

import data.TestData;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

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

    @FindBy(id = "username-register") // xpath = "//*[@id='username-register']"
    private WebElement inputUserNameInRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;


    final static String listErrorMessagesLocator
            = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listErrorMessagesLocator)
    private List<WebElement> listOfMessages;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened " + baseUrl);
    }
    @Step
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
    @Step
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
@Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }
    @Step
    public boolean isButtonSignInVisible() {
        return isElementVisible(buttonSignIn);
    }
    @Step
    public boolean isAlertMessageVisible() {
        return isElementVisible(alertMessage);
    }
    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);

    }

    public boolean isInputLoginVisible() {
        return isElementVisible(inputUserNameInLoginForm);
    }

    public boolean isInputPasswordVisible() {
        return isElementVisible(inputPasswordInLoginForm);
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextIntoElement(inputUserNameInRegistrationForm, userName);
        return this;
    }
    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailInRegistrationForm, email);
        return this;
    }
    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordInRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedMessages) {
        // error1;error2;error3 -> [error1, error2, error3]
        String[] messagesArray = expectedMessages.split(";");

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorMessagesLocator), messagesArray.length));

        Utils.waitABit(1); //wait for all messages to appear

        Assert.assertEquals("Number of messages", messagesArray.length, listOfMessages.size());
        ArrayList<String> textFromMessagesActual = new ArrayList<>();
        for (WebElement element : listOfMessages) {
            textFromMessagesActual.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < messagesArray.length; i++) {
            softAssertions.assertThat(textFromMessagesActual.get(i))
                    .as("Message " + i)
                    .isIn(messagesArray);
        }
        softAssertions.assertAll(); //check all soft assertions
        return this;
    }
    public LoginPage checkIsRedirectToLoginPage() {
        Assert.assertTrue("It is not Login page", isButtonSignInVisible());
        checkUrl();
        return this;
    }
}


