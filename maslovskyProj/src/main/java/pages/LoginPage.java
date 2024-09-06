package pages;

import data.TestData;
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

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    Utils utils = new Utils(webDriver, logger);

    Actions actions = new Actions(webDriver);

    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened " + baseUrl);
        return this;
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public void enterTextIntoInputLogin(String login) {
        clearAndEnterTextIntoElement(inputUserNameInLoginForm, login);
    }

    public LoginPage enterTextIntoInputLoginAndContinue(String login) {
        clearAndEnterTextIntoElement(inputUserNameInLoginForm, login);
        return this;
    }

    public void enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPasswordInLoginForm, password);
    }

    public LoginPage enterTextIntoInputPasswordAndContinue(String password) {
        clearAndEnterTextIntoElement(inputPasswordInLoginForm, password);
        return this;
    }

    public HeaderElement clickOnButtonSighIn() {
        clickOnElement(buttonSighIn);
        return new HeaderElement(webDriver);
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

    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextIntoElement(inputUserNameInRegistrationForm, userName);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailInRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordInRegistrationForm, password);
        return this;
    }

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

    public LoginPage refreshPage() {
        utils.refreshPage();
        return this;
    }

    public LoginPage checkIsErrorMessageDisplayed() {
        Assert.assertTrue(isElementVisible(invalidCredentialsText, "Invalid username/password message"));
        return this;
    }

    public LoginPage navigateToUsernameField() {
        tabPressing(2);
        return this;
    }

    public LoginPage enterText(String text) {
        actions.sendKeys(text).perform();
        return this;
    }

    public LoginPage navigateToPasswordField() {
        tabPressing(1);
        return this;
    }

    private void tabPressing(int count) {
        for (int i = 0; i < count; i++) {
            actions.sendKeys(Keys.TAB).perform();
        }
    }

    public LoginPage navigateToSignInButton() {
        tabPressing(1);
        return this;
    }

    public HomePage clickOnButton() {
        actions.sendKeys(Keys.ENTER).perform();
        return new HomePage(webDriver);
    }
}
