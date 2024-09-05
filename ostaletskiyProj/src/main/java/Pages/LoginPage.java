package Pages;

import data.TestData;
import org.apache.commons.logging.Log;
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

    @FindBy(xpath = "//div[contains(text(), 'Invalid username/password.')]")
    private WebElement notificationAlert;

    @FindBy(id = "username-register")
    private WebElement inputUserNameInRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    final static String listErrorMessages
            = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listErrorMessages)
    private List<WebElement> listOfMessages;


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

    public LoginPage enterTextRegistrationNameField(String userName) {
        clearAndEnterTextIntoElement(inputUserNameInRegistrationForm, userName);
        return this;
    }

    public LoginPage enterTextRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailInRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordInRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorMessageForRegistrationForm(String exeptedMessage) {
        String[] messagesArray = exeptedMessage.split(";");

        webDriverWait_10.until(ExpectedConditions
                .numberOfElementsToBe(By.xpath(listErrorMessages), messagesArray.length));


        Utils.waitABit(1); // wait for all messages to appear

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
        }

        softAssertions.assertAll(); // check all assertions
        return this;
    }







}

