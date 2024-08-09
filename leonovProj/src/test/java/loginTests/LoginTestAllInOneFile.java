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

        Assert.assertTrue("Button Sign Out is not visible",
                isElementVisible("//button[text()='Sign Out]"));
    }


    // HW2
    // hw написати тест кейс на невалідний логін в цьому ж класі, просто створити окремий тест на невалідний логін
    // впевнитись що кнопки сайн аут нема
    // впевнитись що кнопка сайн ін присутня
    // впевнитись що банер про невалідний логін присутній


    private boolean isElementVisible(String locator) {
        try {
            boolean state = webDriver.findElement(By.xpath(locator)).isDisplayed();
            return state;
        } catch (Exception e) {
            logger.info("Element not found");
            return false;
        }
    }

    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("site opened");

        WebElement userNameLoginInput = webDriver.findElement(By.
                xpath("//input[@placeholder='Username']"));

        WebElement passwordLoginInput = webDriver.findElement(By.
                xpath("//input[@placeholder='Password']"));

        WebElement loginButton = webDriver.findElement(By.xpath("//button[text()='Sign In']"));

        String signInButton = "//button[text()='Sign In']";
        String incorrectCredentialsBanner = "//div[text()='Invalid username/password.']";
        String signOutButton = "//button[text()='Sign Out]";

        userNameLoginInput.clear();
        userNameLoginInput.sendKeys("qqaauto");
        logger.info("invalid login entered");

        passwordLoginInput.clear();
        passwordLoginInput.sendKeys("123456qwerty");
        logger.info("password entered");

        loginButton.click();
        logger.info("login button clicked");

        Assert.assertFalse("signOut button displayed", isElementVisible(signOutButton));
        Assert.assertTrue("SignIn button isn't present", isElementVisible(signInButton));
        Assert.assertTrue("Incorrect credentials banner isn't displayed",
                isElementVisible(incorrectCredentialsBanner));

    }
}
