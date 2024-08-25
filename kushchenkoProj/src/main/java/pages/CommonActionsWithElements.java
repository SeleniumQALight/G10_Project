package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані FinfBy
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

    protected boolean isElementVisible(String locator){
        try{
            return isElementVisible(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e){
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
}
