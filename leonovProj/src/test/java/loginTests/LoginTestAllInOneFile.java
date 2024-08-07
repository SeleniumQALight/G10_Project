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
        logger.info("Browser opened");
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("browser closed");

    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("site opened");

        WebElement inputUserNameInLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserNameInLoginForm.clear();
        inputUserNameInLoginForm.sendKeys("qaauto");
        logger.info("qaauto entered into the input Login");

        WebElement inputPasswordInLoginForm =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPasswordInLoginForm.clear();
        inputPasswordInLoginForm.sendKeys("123456qwerty");
        logger.info("password entered into the input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("button Sign In clicked");

        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + " is element displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element is not present on the page");
            return false;
        }
    }

    // hw написати тест кейс на невалідний логін в цьому ж класі, просто створити окремий тест на невалідний логін
    // впевнитись що кнопки сайн аут нема
    // впевнитись що кнопка сайн ін присутня
    // впевнитись що банер про невалідний логін присутній
}
