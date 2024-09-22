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
import pages.elements.HeaderElement;

public class LoginPage extends ParentPage {
    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement invalidLoginErrorMessage;

    Logger logger = Logger.getLogger(getClass());
    @FindBy(id = "username-register") //xpath = "//*[@id='username-register']"
    private WebElement inputUserNameInRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    final static String listMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listMessagesLocator)
    private List<WebElement> listOfMessages;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSignUp;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened " + baseUrl);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputLogin(String login) {
//        try{
////            WebElement inputUserNameInLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//            inputUserNameInLoginForm.clear();
//            inputUserNameInLoginForm.sendKeys(login);
//            logger.info(login + " was inputted into input Login");
//        }catch (Exception e){
//            logger.error("Can not work with element " + e);
//            Assert.fail("Can not work with element " + e);
//        }
        clearAndEnterTextIntoElement(getHeaderElement().getInputUserNameInLoginForm(), login);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(getHeaderElement().getInputPasswordInLoginForm(), password);
        return this;
    }

    @Step
    public HomePage clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
        return new HomePage(webDriver);
    }

    @Step
    public boolean isInvalidLoginMessageVisible() {return isElementVisible(invalidLoginErrorMessage);}

    @Step
    public boolean isButtonSignInVisible() {return isElementVisible(buttonSignIn);}

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
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
    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordInRegistrationForm, password);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailInRegistrationForm, email);
        return this;
    }

    @Step
    public LoginPage checkErrorsMessages(String expectedMessages) {
        // error1; error2; error3 -> [error1, error2, error3]
        String[] messagesArray = expectedMessages.split(";");

        webDriverWait_10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listMessagesLocator), messagesArray.length));

        Utils.waitABit(1); //wait all messages to appear

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

        softAssertions.assertAll(); //check all soft assertions
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

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }
}
