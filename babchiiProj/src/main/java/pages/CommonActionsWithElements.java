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
    protected WebDriverWait webDriverWait_10, webDriverWait_15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані FindBy
        webDriverWait_10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait_15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
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

    protected boolean isElementVisible(WebElement webElement) {
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

    protected void checkElementIsVisible(WebElement webElement) {
        webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    protected void checkElementIsNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementVisible(webElement));
    }

    protected boolean isElementVisible(String locator) {
        try {
//            boolean state = webDriver.findElement(By.xpath(locator)).isDisplayed();
//            if (state) {
//                logger.info("Element is displayed");
//            } else {
//                logger.info("Element is not displayed");
//            }
//            return state;
            return isElementVisible(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.info("Element is not displayed");
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

    protected void selectValueInDropDown(WebElement dropdown, String valueFromSelect) {
        try {
            Select select = new Select(dropdown);
            select.selectByValue(valueFromSelect);
            logger.info(valueFromSelect + " was selected in DropDown " + getElementName(dropdown));
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

    protected void pressEnterKey() {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER)
                    .build()
                    .perform();
            logger.info("Enter key was pressed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void scrollToElement(WebElement webElement) {
        try {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement)
                    .build()
                    .perform();
            logger.info("Scrolled to element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //executes JavaScript code - open new tab
    protected void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
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

    private void makeCheckboxChecked(WebElement webElement) {
        try {
            if (!webElement.isSelected()) {
                webElement.click();
                logger.info("Checkbox was checked");
            } else {
                logger.info("Checkbox is already checked");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    private void makeCheckboxUnchecked(WebElement webElement) {
        try {
            if (webElement.isSelected()) {
                webElement.click();
                logger.info("Checkbox was unchecked");
            } else {
                logger.info("Checkbox is already unchecked");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    protected void setCheckBoxToNeededState(WebElement webElement, String neededState) {
        if (neededState.equals("yes")) {
            makeCheckboxChecked(webElement);
        } else if (neededState.equals("no")) {
            makeCheckboxUnchecked(webElement);
        } else {
            logger.error("State should be only 'Checked' or 'Unchecked'");
            Assert.fail("State should be only 'Checked' or 'Unchecked'");
        }
    }
    public void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    public void checkIsElementVisible(WebElement webElement, String elementName) {
        Assert.assertTrue(elementName + " Element is not visible", isElementVisible(webElement, elementName));
    }


}
