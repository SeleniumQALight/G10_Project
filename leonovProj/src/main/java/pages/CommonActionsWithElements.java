package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.ConfigProvider.configProperties;


public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує елемент описані FindBy анотаціями
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_DEFAULT_WAIT()));
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

    //accept alert
    protected void acceptAlert() {
        try {
            webDriverWait10.until(ExpectedConditions.alertIsPresent());
            webDriver.switchTo().alert().accept();
            logger.info("Alert was accepted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //scroll to element
    protected void scrollToElement(WebElement webElement) {
        try {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement)
                    .perform();
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //press Enter using Actions class
    protected void pressEnterUsingActions(WebElement webElement) {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(webElement, Keys.ENTER)
                    .perform();
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //execute JS script - open a new tab
    protected void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void checkCheckbox(WebElement checkbox) {
        if (checkbox.isSelected()) {
            logger.info("Checkbox is already checked");
        } else {
            clickOnElement(checkbox);
            logger.info("Checkbox was unchecked, but now is checked");
        }
    }

    protected void uncheckCheckbox(WebElement checkbox) {
        if (checkbox.isSelected()) {
            clickOnElement(checkbox);
            logger.info("Checkbox was checked, but now is unchecked");
        } else {
            logger.info("Checkbox is already unchecked");
        }
    }

    protected void checkboxState(WebElement checkbox, String state) {
        if (state.equals("check")) {
            checkCheckbox(checkbox);
        } else if (state.equals("uncheck")) {
            uncheckCheckbox(checkbox);
        } else {
            logger.error("State for checkbox should be 'check' or 'uncheck'");
            Assert.fail("State for checkbox should be 'check' or 'uncheck'");
        }
    }

    public void checkIsElementVisible(WebElement element) {
        Assert.assertTrue("Element should be visible", isElementVisible(element));
    }

    public void checkIsElementVisible(WebElement webElement, String elementName) {
        Assert.assertTrue(elementName + "Element should be visible"
                , isElementVisible(webElement, elementName));
    }


}
