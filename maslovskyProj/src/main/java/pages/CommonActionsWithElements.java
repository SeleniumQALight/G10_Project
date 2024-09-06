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

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "'" + " was inputted into element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
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

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xPath, String elementName) {
        try {
            clickOnElement(webDriver.findElement(By.xpath(String.format(xPath, elementName))), elementName);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element is visible");
            } else {
                logger.info(getElementName(webElement) + " Element is not visible");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected boolean isElementVisible(String xPath) {
        try {
            return isElementVisible(webDriver.findElement(By.xpath(xPath)));
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected boolean isElementVisible(WebElement webElement, String elementName) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(elementName + " Element is displayed");
            } else {
                logger.info(elementName + " Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info(elementName + " Element is not displayed");
            return false;
        }
    }

    protected String returnTextFromElementByLocator(WebElement webElement) {
        try {
            String userName = webElement.getText().trim().toLowerCase();
            logger.info("Element is displayed");
            return userName;
        } catch (Exception e) {
            logger.error("Element is not displayed");
            Assert.fail("Element is not displayed " + e);
            return "";
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected void selectTextInDropDownByVisibleText(WebElement dropdown, String textForSelect) {
        try {
            Select optionsFromDropdown = new Select(dropdown);
            optionsFromDropdown.selectByVisibleText(textForSelect);
            logger.info(textForSelect + " was selected in DropDown " + getElementName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropdown(WebElement dropdown, String valueForSelect) {
        try {
            Select select = new Select(dropdown);
            select.selectByValue(valueForSelect);
            logger.info(valueForSelect + " was selected in DropDown " + getElementName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
            }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
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
                    .build().perform();
            logger.info("Scrolled to element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //press Enter key using Actions class
    protected void pressEnterKeyUsingActions() {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER)
                    .build().perform();
            logger.info("Enter key was pressed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //execute JS script - open new tab
    protected void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    public void checkIsElementNotVisible(WebElement webElement, String elementName) {
        Assert.assertFalse("Element is visible", isElementVisible(webElement, elementName));
    }

    public void checkIsElementVisible(WebElement webElement, String elementName) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement, elementName));
    }

}
