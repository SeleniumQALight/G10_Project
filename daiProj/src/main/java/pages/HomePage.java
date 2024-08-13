package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;;

public class HomePage extends ParentPage{

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    private Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    // public boolean isButtonSignOutVisible() {
    // try {
    //     boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
    //   logger.info(state + " is element displayed");
    // return state;
    // }catch (Exception e) {
    //   logger.info("Element is not present on page");
    //  return false;
    // }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
}
}