package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

public class LoginTestAllInOneFile {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserNameInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserNameInLoginForm.clear();
        inputUserNameInLoginForm.sendKeys("qaauto");
        logger.info("qaauto was inputted into input Login");

        WebElement inputPasswordInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPasswordInLoginForm.clear();
        inputPasswordInLoginForm.sendKeys("123456qwerty");
        logger.info("Password was inputted into input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());

    }

    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");
        logger.info("nothing was inputted into input Login");
        logger.info("nothing was inputted into input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertFalse("Button Sign Out is visible", isButtonSignOutVisible());
        Assert.assertTrue("Button Sign In is not visible", isButtonSignInVisible());
        Assert.assertTrue("Alert text is not displayed", isInvalidCredentialsTextDisplayed());

    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + " is element displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element is not present on page");
            return false;
        }
    }

    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            logger.info(state + " is element displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element is not present on page");
            return false;
        }
    }

    private boolean isInvalidCredentialsTextDisplayed() {
        try {
            boolean state = webDriver.findElement
                    (By.xpath("//*[contains (text(), 'Invalid username/password.')]")).isDisplayed();
            logger.info(state + " is element displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element is not present on page");
            return false;
        }
    }

}
