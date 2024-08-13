package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

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


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

}
