package pages;

import data.TestData;
import data.UserForRegistration;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.HeaderElement;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserNameInLoginForm;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPasswordInLoginForm;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSighIn;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSighUp;

    @FindBy(xpath = "//*[contains (text(), 'Invalid username/password.')]")
    private WebElement invalidCredentialsText;

    private Logger logger = Logger.getLogger(getClass());

    @FindBy(id = "username-register") // xpath = ".//*[@id='username-register']"
    private WebElement inputUserNameInRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;

    final static String listOfMessagesLocator =
            "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = listOfMessagesLocator)
    private List<WebElement> listOfMessages;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement alertInCenter;

    @FindBy(xpath = "//input[@id='username-register']/../div")
    private WebElement registrationUsername;

    @FindBy(xpath = "//input[@id='email-register']/../div")
    private WebElement registrationEmail;

    @FindBy(xpath = "//input[@id='password-register']/../div")
    private WebElement registrationPassword;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened " + baseUrl);
        return this;
    }

    Actions actions = new Actions(webDriver);

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    @Step
    public void enterTextIntoInputLogin(String login) {
        clearAndEnterTextIntoElement(inputUserNameInLoginForm, login);
    }

    public LoginPage enterTextIntoInputLoginAndContinue(String login) {
        clearAndEnterTextIntoElement(inputUserNameInLoginForm, login);
        return this;
    }

    @Step
    public void enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPasswordInLoginForm, password);
    }

    public LoginPage enterTextIntoInputPasswordAndContinue(String password) {
        clearAndEnterTextIntoElement(inputPasswordInLoginForm, password);
        return this;
    }

    @Step
    public HeaderElement clickOnButtonSighIn() {
        clickOnElement(buttonSighIn);
        return new HeaderElement(webDriver);
    }

    @Step
    public boolean isInvalidCredentialsTextDisplayed() {
        return isElementVisible(invalidCredentialsText);
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSighIn();
        return new HomePage(webDriver);
    }

    @Step
    public boolean isButtonSignInVisible() {
        return isElementVisible(buttonSighIn);
    }

    public boolean isUsernameInputFieldVisible() {
        return isElementVisible(inputUserNameInLoginForm);
    }

    public boolean isPasswordInputFieldVisible() {
        return isElementVisible(inputPasswordInLoginForm);
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
                .numberOfElementsToBe(By.xpath(listOfMessagesLocator), messagesArray.length));

        Utils.waitABit(1); // wait for all messages to appear

        Assert.assertEquals("Number of messages", messagesArray.length, listOfMessages.size());

        ArrayList<String> textFromMessagesActual = new ArrayList<>();
        for (WebElement element : listOfMessages) {
            textFromMessagesActual.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < messagesArray.length; i++) {
            softAssertions
                    .assertThat(textFromMessagesActual.get(i))
                    .as("Message number " + i)
                    .isIn(messagesArray);
        }
        softAssertions.assertAll(); // check all soft assertions
        return this;
    }

    @Step
    public LoginPage checkIsErrorMessageDisplayed() {
        Assert.assertTrue(isElementVisible(invalidCredentialsText, "Invalid username/password message"));
        return this;
    }

    @Step
    public LoginPage enterText(String text) {
        actions.sendKeys(text).perform();
        return this;
    }

    @Step
    public HomePage pressEnterButton() {
        actions.sendKeys(Keys.ENTER).perform();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage reloadPageContent() {
        refreshPage();
        return this;
    }

    @Step
    public LoginPage goToNewBrowserTab() {
        switchToNewBrowserTab();
        return this;
    }


    @Step
    public LoginPage tabPressing(int count) {
        for (int i = 0; i < count; i++) {
            actions.sendKeys(Keys.TAB).perform();
        }
        return this;
    }

    public LoginPage enterRegistrationIfNotNull(UserForRegistration user) {
        if (user.getUserName() != null) {
            enterTextIntoRegistrationUserNameField(user.getUserName());
        }
        if (user.getEmail()!= null) {
            enterTextIntoRegistrationEmailField(user.getEmail());
        }
        if (user.getPassword() != null) {
            enterTextIntoRegistrationPasswordField(user.getPassword());
        }
        return this;
    }

    public void clickOnSignUpButton() {
        clickOnElement(buttonSighUp);
    }

    public LoginPage checkTextInAlertMessageInCenter(String expectedMessage) {
        checkTextInElement(alertInCenter, expectedMessage);
        return this;
    }


}
