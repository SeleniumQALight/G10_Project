package Pages;

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
    protected WebDriverWait webDriverWait_10, webDriverWait_15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // Ініціалізуємо елементи сторінки FindBy
        webDriverWait_10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered into input" + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element is displayed");
            } else {
                logger.info(getElementName(webElement) + " Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected boolean isElementDisplayed(WebElement webElement, String elementName) {
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

    // isElementDisplayed (String locator)
    protected boolean isElementDisplayed(String locator) {
        try {
            boolean state = webDriver.findElement(By.xpath(locator)).isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    public boolean checkCheckBox(WebElement checkBox) {
        if (!checkBox.isSelected()) {
            checkBox.click();
            logger.info("CheckBox is checked");
            return true;
        } else {
            logger.info("CheckBox is already checked");
            return false;
        }
    }

    public boolean uncheckCheckBox(WebElement checkBox) {
        if (checkBox.isSelected()) {
            checkBox.click();
            logger.info("CheckBox is unchecked");
            return true;
        } else {
            logger.info("CheckBox is already unchecked");
            return false;
        }
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

    protected void selectValueInDropDown(WebElement dropdown, String value) {
        try {
            Select optionsFromDropdown = new Select(dropdown);
            optionsFromDropdown.selectByValue(value);
            logger.info(value + " was selected in DropDown" + getElementName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void acceptAlert() {
        try {
            webDriverWait_10.until(ExpectedConditions.alertIsPresent());
            webDriver.switchTo().alert().accept();
            logger.info("Alert was accepted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

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

    protected void pressEnterKey() {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER)
                    .build().perform();
            logger.info(" Enter key was pressed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // execute javascript code - open new tab

    protected void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
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
}

