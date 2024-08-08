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
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

        WebElement inputUserNameInLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserNameInLoginForm.clear();
        inputUserNameInLoginForm.sendKeys("qaauto");
        logger.info("qaauto was inputted into input Login");

        WebElement inputPasswordInLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPasswordInLoginForm.clear();
        inputPasswordInLoginForm.sendKeys("123456qwerty");
        logger.info("Password was inputted into input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
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
    private void openLinkForTest(String url){
        webDriver.get(url);
        logger.info("Site was opened");
    }

    private void enterUserNameForLogin(String userName){
        WebElement inputUserNameLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserNameLoginForm.clear();
        inputUserNameLoginForm.sendKeys(userName);
        logger.info("Login + " + userName + " was inputted into Login input");
    }

    private void enterPasswordForLogin(String pass){
        WebElement inputPasswordInLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPasswordInLoginForm.clear();
        inputPasswordInLoginForm.sendKeys(pass);
        logger.info("Password was inputted into input Password");
    }

    private void clickSignInButton(){
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");
    }

    private boolean isButtonSignInIsVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            logger.info("Is element displayed - " + state + ". Sign In button is visible");
            return state;
        } catch (Exception e) {
            logger.info("Button Sign In is not visible");
            return false;
        }
    }

    private boolean isAlertMessageVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).isDisplayed();
            logger.info("Is element displayed - " + state + ". Alert message is visible");
            return state;
        } catch (Exception e) {
            logger.info("The alert message is not visible");
            return false;
        }
    }

    @Test
    public void invalidLogin() {
        openLinkForTest("https://aqa-complexapp.onrender.com/");
        enterUserNameForLogin("qaaauto");
        enterPasswordForLogin("123456qwerty");
        clickSignInButton();
        Assert.assertFalse("Button 'Sign Out' is visible", isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is not visible", isButtonSignInIsVisible());
        Assert.assertTrue("The alert message is not visible", isAlertMessageVisible());
    }
}
