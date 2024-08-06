package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class loginTestAllinOneFile {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5,  TimeUnit.SECONDS);
        logger.info("Browser started");


    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser stopped");

    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserNameInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserNameInLoginForm.clear();
        inputUserNameInLoginForm.sendKeys("qaauto");
        logger.info("Username entered");
        WebElement inputPasswordInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPasswordInLoginForm.clear();
        inputPasswordInLoginForm.sendKeys("123456qwerty");
        logger.info("Password entered");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Sign in was clicked");

        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());


    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info (state + "is element displayed");
            return state;
        }catch (Exception e){
            logger.info("Button sign out is not visible");
            return false;
        }
    }
}
