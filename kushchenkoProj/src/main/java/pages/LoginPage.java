package pages;

import data.TestData;
import data.UserForRegistration;
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

    @FindBy(xpath = "//div[text()='Invalid username/password.']")
    private WebElement alertMessage;

    @FindBy(id = "username-register") // xpath = ".//*[@id='username-register']"
    private WebElement inputUserNameInRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSignUp;

    final static String listErrorMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = listErrorMessagesLocator)
    private List<WebElement> listOfMessages;

    private Logger logger = Logger.getLogger(getClass());

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
        logger.info("Login page is opened " + baseUrl);
    }

    @Step
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

    @Step
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
        return isElementVisible(alertMessage, "Alert message");
    }

    @Step
    public boolean isInputUserNameVisible() {
        return isElementVisible(inputUserNameInLoginForm);
    }

    @Step
    public boolean isInputPasswordVisible() {
        return isElementVisible(inputPasswordInLoginForm);
    }

    @Step
    public HomePage openLoginPageAndLoginWithValidCreds() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
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

    @Step
    public LoginPage checkErrorsMessages(String expectedMessages) {
        // error1;error2;error3 -> [error1, error2, error3]
        String[] messagesArray = expectedMessages.split(";");
        webDriverWait10.until(ExpectedConditions
                .numberOfElementsToBe(By.xpath(listErrorMessagesLocator), messagesArray.length));

        Utils.waitABit(1);

        Assert.assertEquals("Number of messages", messagesArray.length, listOfMessages.size());

        ArrayList<String> actualTextFromMessages = new ArrayList<>();
        for (WebElement element : listOfMessages) {
            actualTextFromMessages.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < messagesArray.length; i++) {
            softAssertions
                    .assertThat(actualTextFromMessages.get(i))
                    .as("Message number " + i)
                    .isIn(messagesArray);

            softAssertions.assertAll(); // check all soft assertions
            return this;
        }
        return this;
    }

    @Step
    public LoginPage checkIsButtonSignInVisible() {
        Assert.assertTrue("Button Sign In is not visible", isButtonSignInVisible());
        return this;
    }

    @Step
    public LoginPage checkIsAlertMessageDisplayed(String alertMessage) {
        Assert.assertTrue("Alert message is not displayed", isAlertMessageVisible());
        return this;
    }

    public LoginPage enterRegistrationDataIfNotNull(UserForRegistration user) {
        if (user.getUserName() != null) {
            enterTextIntoRegistrationUserNameField(user.getUserName());
        }
        if (user.getEmail() != null) {
            enterTextIntoRegistrationEmailField(user.getEmail());
        }
        if (user.getPassword() != null) {
            enterTextIntoRegistrationPasswordField(user.getPassword());
        }
        return this;
    }

    public void clickOnSignUpButton() {
        clickOnElement(buttonSignUp);
    }
}
