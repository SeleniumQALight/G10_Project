package pages;

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

    private Logger logger = Logger.getLogger(getClass());

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Login page was opened");
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
}

