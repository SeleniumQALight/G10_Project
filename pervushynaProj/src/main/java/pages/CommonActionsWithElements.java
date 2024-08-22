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
        PageFactory.initElements(webDriver, this); // ініціалізує елементи описані в FindBy
    }

    protected void cleatAndEnterTextIntoElement(WebElement webElement, String text) {
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element ");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try {
             webElement.click();
             logger.info("Element was clicker");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementVisible(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            }else {
                logger.info("Element is not jdisplayed");
            }
            return state;
        }catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected boolean isElementVisible(String locator){
        try{
//            boolean state = webDriver.findElement(By.xpath(locator)).isDisplayed();
//            if (state) {
//                logger.info("Element is displayed");
//            }else {
//                logger.info("Element is not displayed");
//            }
//            return state;
            return isElementVisible(webDriver.findElement(By.xpath(locator)));
        }catch (Exception e){
            logger.info("Element is not displayed");
            return false;
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
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

}
