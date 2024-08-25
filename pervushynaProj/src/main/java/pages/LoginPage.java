package pages;

import com.beust.ah.A;
import data.TestData;
import org.apache.hc.core5.http.HeaderElement;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
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
        return this;
    }


}
