package Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProvider;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import static utils.ConfigProvider.configProperties;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait  webDriverWait_10, webDriverWait_15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // Ініціалізуємо елементи сторінки FindBy
        webDriverWait_10 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait_15 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_DEFAULT_WAIT()));
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

    public void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // check element is visible on page
    public void checkIsElementVisible(WebElement webElement) {
            Assert.assertTrue("Element is not visible", webElement.isDisplayed());
    }

    public void checkIsElementVisible (WebElement webElement, String elementName) {
        Assert.assertTrue(elementName + " is not visible",  isElementDisplayed(webElement, elementName));
    }


    private String getElementName(WebElement webElement) {
        String elementName = "";
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return elementName;
        }


    }
    public void switchToTab(String tabName, int tabIndex) {
        try {
            Set<String> allWindows = webDriver.getWindowHandles();
            ArrayList<String> tabList = new ArrayList<>(allWindows);
            webDriver.switchTo().window(tabList.get(tabIndex));
            logger.info("Switched to " + tabName);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void closeTab(String tabName, int tabIndex) {
        try {
            this.switchToTab(tabName, tabIndex);
            webDriver.close();
            logger.info(tabName + " is closed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page is refreshed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    protected String getTextFromElement(WebElement webElement) {
        try {
            String textFromElement = webElement.getText();
            logger.info("Text from element " + getElementName(webElement) + " was got : " + textFromElement);
            return textFromElement;
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return "";
        }
    }
}

