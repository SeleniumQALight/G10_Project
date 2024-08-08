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
        logger.info("Browser is opened");
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser is closed");
    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site is opened");

        WebElement inputUserNameInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserNameInLoginForm.clear();
        inputUserNameInLoginForm.sendKeys("qaauto");
        logger.info("Username is entered");

        WebElement inputPasswordInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPasswordInLoginForm.clear();
        inputPasswordInLoginForm.sendKeys("123456qwerty");
        logger.info("Password is entered");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Login button is clicked");

        Assert.assertTrue("Sign Out button is not visible", isButtonSignOutVisible());
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + " is element displayed");
            return state;
        } catch (Exception e) {
            logger.info("Sign Out button is not present on page");
            return false;
        }
    }



    @Test
    public void invalidLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("The Login page is opened");

        WebElement inputUserNameInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserNameInLoginForm.clear();
        inputUserNameInLoginForm.sendKeys("1qaauto");
        logger.info("Incorrect username is entered");

        WebElement inputPasswordInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPasswordInLoginForm.clear();
        inputPasswordInLoginForm.sendKeys("123456qwerty");
        logger.info("Password is entered");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Login button is clicked");

        Assert.assertTrue("Sign In button is not visible", isButtonSignInVisible());
        Assert.assertFalse("Sign Out button is visible", isButtonSignOutVisible());
        Assert.assertTrue("Alert message is not displayed", isAlertMessageIsVisible());
    }

    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            logger.info(state + " Sign In button is displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element is not present on page");
            return false;
        }
    }
    private boolean isAlertMessageIsVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
            logger.info(state + " alert message is dispalayed");
            return  state;
        } catch (Exception e) {
            logger.info("Alert message is not present on page");
            return false;
        }
    }
}



