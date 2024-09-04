package pages;

import data.TestData;
import org.apache.hc.core5.http.HeaderElement;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUsernameInLoginForm;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPasswordInLoggInForm;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement textInvalidUsernamePasswordIsDisplay;

    private Logger logger = Logger.getLogger(getClass());
    private HeaderElement headerElement;

    @FindBy(id = "username-register")
    private WebElement inputUserNameInRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;

    final static String listOfMessagesLocator
            = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listOfMessagesLocator)
    private List<WebElement> listOfMessages;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public pages.elements.HeaderElement getHeaderElement() {
        return new pages.elements.HeaderElement(webDriver);
    }

    public void openLoginPage() {
        webDriver.get(baseURL);
        logger.info("Login page was opened");
    }

    public void enterTextIntoInputLogin(String login) {
//        try {
////            WebElement inputUsernameInLoginForm =
////                    webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//            inputUsernameInLoginForm.clear();
//            inputUsernameInLoginForm.sendKeys(login);
//            logger.info(login + " was inputted into input Login");
//        } catch (Exception e) {
//            logger.error("Can not work with element " + e);
//            Assert.fail("Can not work with element " + e);
//        }
        cleatAndEnterTextIntoElement(inputUsernameInLoginForm, login);
    }

    public void enterTextIntoInputPassword(String password) {
        cleatAndEnterTextIntoElement(inputPasswordInLoggInForm, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public HomePage openLoginPageAndFillingFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public boolean isButtonSignInVisible() {
        return isElementVisible(buttonSignIn);
    }

    public boolean textIsDisplay() {
        return isElementVisible(textInvalidUsernamePasswordIsDisplay);
    }

    public LoginPage checkIsRedirectToLoginPage() {
        Assert.assertTrue("It is not Login page", isButtonSignInVisible());
        Assert.assertTrue("Input Username is not visible", isElementVisible(inputUsernameInLoginForm));
        Assert.assertTrue("Input Password is not visible", isElementVisible(inputPasswordInLoggInForm));
        Assert.assertFalse("Button Sign Out is visible", getHeaderElement().isButtonSignOutVisible());
        Assert.assertFalse("Button Search is visible", getHeaderElement().isSearchButtonVisible());
        Assert.assertFalse("Button Chat is visible", getHeaderElement().isChatButtonVisible());
        Assert.assertFalse("Img Avatar is visible", getHeaderElement().isImgAvatarVisible());
        Assert.assertFalse("Button Create Post is visible", getHeaderElement().isButtonCreatePostVisible());
        checkUrlWithPattern();
        return this;
    }


    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        cleatAndEnterTextIntoElement(inputUserNameInRegistrationForm, userName);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        cleatAndEnterTextIntoElement(inputEmailInRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        cleatAndEnterTextIntoElement(inputPasswordInRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorsMessage(String expectedMessages) {
        //error1;error2;error3 -> [error1, error2, error3]
        String[] messagesArray = expectedMessages.split(";");

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfMessagesLocator), messagesArray.length));

        Utils.waitABit(1);

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

        softAssertions.assertAll();
        return this;
    }




}
