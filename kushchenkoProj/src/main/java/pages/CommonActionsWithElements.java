package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.support.ui.Select;

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

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered into element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " element is clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " element is displayed");
            } else {
                logger.info(getElementName(webElement) + " element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not visible");
            return false;
        }
    }

    protected boolean isElementVisible(String locator){
        try{
            return isElementVisible(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e){
            logger.info("Element is not visible");
            return false;
        }
    }

    protected boolean isElementVisible(WebElement webElement, String elementName) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(elementName + " element is displayed");
            } else {
                logger.info(elementName + " element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info(elementName + " element is not visible");
            return false;
        }
    }

    protected void setCheckbox(WebElement webElement) {
        if (!webElement.isSelected()) {
            clickOnElement(webElement);
            logger.info("Checkbox is set to true");
        } else {
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

    protected void selectTextInDropdownByVisibleText(WebElement dropdown, String textForSelect) {
        try {
        Select optionsFromDropdown = new Select(dropdown);
        optionsFromDropdown.selectByVisibleText(textForSelect);
        logger.info(textForSelect + " was selected in dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropdown(WebElement dropdown, String valueForSelect) {
        try {
            Select select = new Select(dropdown);
            select.selectByValue(valueForSelect);
            logger.info(valueForSelect + " was selected in dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }

    public void acceptAlert() {
        try {
            webDriver.switchTo().alert().accept();
            logger.info("Alert is accepted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void scrollToElement(WebElement webElement) {
        try {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement)
                    .build().perform();
            logger.info("Scrolled to element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void pressEnterKey() {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER)
                    .build().perform();
            logger.info("Enter key is pressed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab is opened");
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
