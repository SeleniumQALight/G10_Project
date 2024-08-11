package Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {
   private Logger logger = Logger.getLogger(getClass());

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPage() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Login page was opened");
    }

    public void enterTextIntoInputLogin(String login) {
        try {
            WebElement inputUserNameInLoginForm =
                    webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
            inputUserNameInLoginForm.clear();
            inputUserNameInLoginForm.sendKeys(login);
            logger.info(login + " was entered into input Login");
        } catch (Exception e) {
            logger.error("Cannot work with element " + e);
            Assert.fail("Cannot work with element " + e);
        }
    }

    public void enterTextIntoInputPassword(String password) {
        try {
            WebElement inputPasswordInLoginForm =
                    webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
            inputPasswordInLoginForm.clear();
            inputPasswordInLoginForm.sendKeys(password);
            logger.info(password + " was entered into input Password");
        } catch (Exception e) {
            logger.error("Cannot work with element " + e);
            Assert.fail("Cannot work with element " + e);
        }
    }

    public void clickOnButtonSignIn() {
        try {
            webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
            logger.info("Button Sign In was clicked");
        } catch (Exception e) {
            logger.error("Cannot work with element " + e);
            Assert.fail("Cannot work with element " + e);
        }
    }
}

