package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);// ініціалізує елементи описані FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element " + getElementName(webElement));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try{
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementVisible(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            if (state) {
               logger.info(getElementName(webElement) + " Element is displayed");
            } else {
                logger.info(getElementName(webElement) + "Element is not displayed");
            }
            return state;
        } catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected boolean isElementVisible(WebElement webElement, String elementName){
        try{
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(elementName + " Element is displayed");
            } else {
                logger.info(elementName + " Element is not displayed");
            }
            return state;
        } catch (Exception e){
            logger.info(elementName + " Element is not displayed");
            return false;
        }
    }

    protected boolean isElementVisible(String locator){
        try{
            return isElementVisible(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }


    protected void selectTextInDropdownByVisibleText(WebElement dropdown, String textForSelect) {
        try {
            Select optionsFromDropdown = new Select(dropdown);
            optionsFromDropdown.selectByVisibleText(textForSelect);
            logger.info(textForSelect + " was selected in dropdown " + getElementName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropdown(WebElement dropdown, String valueForSelect) {
        try {
            Select select = new Select(dropdown);
            select.selectByValue(valueForSelect);
            logger.info(valueForSelect + " was selected in dropdown " + getElementName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        try{
            return webElement.getAccessibleName();
        }catch (Exception e){
            return elementName;
        }

    }
}
