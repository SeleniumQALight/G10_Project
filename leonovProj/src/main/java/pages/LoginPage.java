package pages;

import data.TestData;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserNameInLoginForm;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPasswordInLoginForm;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement alertInvalidLogin;

    @FindBy(id = "username-register")
    private WebElement inputUserNameInRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;

    //ListOfMessages
    //стрінгу прописуємо САМЕ З МОДИФІКАТОРОМ СТАТІК!!!
    final static String LIST_OF_ERROR_MESSAGES =
            "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = LIST_OF_ERROR_MESSAGES)
    private List<WebElement> listOfErrorMessages;
    // подивитись як виглядатиме список

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
    public void openLoginPageInNewTab() {
        openPageInNewTab(baseUrl + getRelativeUrl());
    }

    @Step
    public void enterTextIntoInputLogin(String login) {
//        try {
////            WebElement inputUserNameInLoginForm =
////                    webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//            inputUserNameInLoginForm.clear();
//            inputUserNameInLoginForm.sendKeys(login);
//            logger.info(login + " entered into the input Login");
//
//        } catch (Exception e) {
//            logger.error("Cannot work with element" + e);
//            Assert.fail("Cannot work with element" + e);
        clearAndEnterTextIntoElement(inputUserNameInLoginForm, login);


    }

    @Step
    public void enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPasswordInLoginForm, password);
    }

    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public boolean isAlertInvalidLoginDisplayed() {
        return isElementVisible(alertInvalidLogin);
    }

    @Step
    public boolean isButtonSignInVisible() {
        return isElementVisible(buttonSignIn);
    }

    @Step
    public boolean isLoginFieldVisible() {
        return isElementVisible(inputUserNameInLoginForm);
    }

    @Step
    public LoginPage checkIsLoginFieldVisible() {
        checkIsElementVisible(inputUserNameInLoginForm);
        return this;
    }

    @Step
    public LoginPage checkIsPasswordFieldVisible() {
        checkIsElementVisible(inputPasswordInLoginForm);
        return this;
    }

    @Step
    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
        return this;
    }

    @Step
    public boolean isPasswordFieldVisible() {
        return isElementVisible(inputPasswordInLoginForm);
    }

    @Step
    public LoginPage enterTextIntoRegistrationNameField(String username) {
        clearAndEnterTextIntoElement(inputUserNameInRegistrationForm, username);

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
        //error1;error2;error3 -> [error1, error2, error3]
        String[] messagesArray = expectedMessages.split(";");
        System.out.println(listOfErrorMessages + "  ========= invalid ");

        webDriverWait10.until(ExpectedConditions
                .numberOfElementsToBe(By.xpath(LIST_OF_ERROR_MESSAGES), messagesArray.length));

        Utils.waitABit(1);

        Assert.assertEquals("Number of messages", messagesArray.length, listOfErrorMessages.size());

        ArrayList<String> textFromMessagesActual = new ArrayList<>();
        for (WebElement element : listOfErrorMessages) {
            textFromMessagesActual.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < messagesArray.length; i++) {
            softAssertions
                    .assertThat(textFromMessagesActual.get(i))
                    .as("Message number " + i)
                    .isIn(messagesArray);
        }

        softAssertions.assertAll();
        return this;
    }

    public LoginPage refreshLoginPage() {
        refreshPage();
        return this;
    }

    public LoginPage clickTabKeyViaActions() {
        pressKeysUsingActions(Keys.TAB);
        return this;
    }

    public LoginPage clickEnterKeyViaActions() {
        pressKeysUsingActions(Keys.ENTER);
        return this;
    }

    public LoginPage enterTextViaKeyboard(String text) {
        enterTextUsingActions(text);
        return this;
    }

}
