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

public class LoginTestsAllInOneFile {
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
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site was opened");

        WebElement inputUsernameInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Username']"));

        inputUsernameInLoginForm.clear();
        inputUsernameInLoginForm.sendKeys("qaauto");
        logger.info("qaauto was inputted into input Login");

        WebElement inputPasswordInLoggInForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Password']"));

        inputPasswordInLoggInForm.clear();
        inputPasswordInLoggInForm.sendKeys("123456qwerty");
        logger.info("Password was inputted into input Password");

        webDriver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm']")).click();
        logger.info("Button Sign in was clicked");

        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());

    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[@class='btn btn-sm btn-secondary']")).isDisplayed();
            logger.info(state + " is element displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element is not present on page");
            return false;
        }

    }
}
