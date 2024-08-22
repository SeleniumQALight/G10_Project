package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
        PageFactory.initElements(webDriver, this); // ініціалізує елемент описані FindBy анотаціями
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element" + e);
        Assert.fail("Cannot work with element" + e);
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element is displayed");
            } else {
                logger.info(getElementName(webElement) + " Element is not visible");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not present on the page");
            return false;
        }
    }

    protected boolean isElementVisible(WebElement webElement, String elementName) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(elementName + " Element is displayed");
            } else {
                logger.info(elementName + " Element is not visible");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not present on the page");
            return false;
        }
    }

    //isElementVisible(String locator)
    protected boolean isElementVisible(String locator) {
        try {
//            boolean state = webDriver.findElement(By.xpath(locator)).isDisplayed();
//            if (state) {
//                logger.info("Element is displayed");
//            } else {
//                logger.info("Element is not visible");
//            }
            return isElementVisible(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.info("Element is not present on the page");
            return false;
        }
    }

    protected void selectTextInDropDownByVisibleText(WebElement dropDown, String textForSelect) {
        try {
            Select optionsFromDropDown = new Select(dropDown);
            optionsFromDropDown.selectByVisibleText(textForSelect);
            logger.info(textForSelect + " was selected in dropDown " + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String valueForSelect) {

        try {
            Select optionsFromDropDown = new Select(dropDown);
            optionsFromDropDown.selectByValue(valueForSelect);
            logger.info(valueForSelect + " was selected in dropDown " + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);

        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";

        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return elementName;
        }
    }
}
