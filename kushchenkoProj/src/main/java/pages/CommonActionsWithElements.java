package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані FinfBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered into element");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element is clicked");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementVisible(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        }catch (Exception e){
            logger.info("Element is not visible");
            return false;
        }
    }

    protected void setCheckbox(WebElement webElement) {
        if (!webElement.isSelected()) {
            clickOnElement(webElement);
            logger.info("Checkbox is set to true");
        } else{
            logger.info("Checkbox is already checked");
        }
    }

    protected void unsetCheckbox(WebElement webElement) {
        if (webElement.isSelected()) {
            clickOnElement(webElement);
            logger.info("Checkbox is set to false");
        } else {
            logger.info("Checkbox is already unchecked");
        }
    }

    protected void changeCheckBoxState(String state, WebElement webElement) {
        if (state.equalsIgnoreCase("check")) {
            this.setCheckbox(webElement);
        } else if (state.equalsIgnoreCase("uncheck")) {
            this.unsetCheckbox(webElement);
        } else {
            logger.error("State should be 'check' or 'uncheck'");
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }
}
