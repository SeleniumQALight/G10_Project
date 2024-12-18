package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        PageFactory.initElements(webDriver, this);// ініціалізує елементи описані в FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_DEFAULT_WAIT()));

    }

    protected void cleatAndEnterTextIntoElement(WebElement webElement, String text) {
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element " + getElementsName(webElement));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementsName(webElement);
             webElement.click();
             logger.info(elementName + " Element was clicker");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void setCheckBoxToNeededState(WebElement webElement, String neededState){
        if (neededState.equals("check")){
            setCheckBoxToChecked(webElement);
        }else if (neededState.equals("uncheck")){
            setCheckBoxToUnChecked(webElement);
        }else {
            logger.error("State should be only 'check' or 'uncheck'");
            Assert.fail("State should be only 'check' or 'uncheck'");
        }
    }

    protected void setCheckBoxToChecked(WebElement webElement){
        if (!webElement.isSelected()){
            webElement.click();
            logger.info("CheckBox is checked");
        }else {
            logger.info("CheckBox is already checked");
        }
    }

    protected void setCheckBoxToUnChecked(WebElement webElement){
        if (webElement.isSelected()){
            webElement.click();
            logger.info("CheckBox is unchecked");
        }else {
            logger.info("CheckBox is already unchecked");
        }
    }


    protected boolean isElementVisible(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementsName(webElement) + " Element is displayed");
            }else {
                logger.info(getElementsName(webElement) + " Element is not displayed");
            }
            return state;
        }catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected boolean isElementVisible(WebElement webElement, String elementName){
        try{
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(elementName + " Element is displayed");
            }else {
                logger.info(elementName + " Element is not displayed");
            }
            return state;
        }catch (Exception e){
            logger.info(elementName + " Element is not displayed");
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
            logger.info(textForSelect + " was selected in dropdown " + getElementsName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropdown(WebElement dropdown, String valueForSelect) {
        try {
            Select select = new Select(dropdown);
            select.selectByValue(valueForSelect);
            logger.info(valueForSelect + " was selected in dropdown " + getElementsName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

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
            logger.info("Scrolled to element " + getElementsName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //press Enter key using Actions class
    protected void pressEnterKey() {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER)
                    .build().perform();
            logger.info("Enter key was pressed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }

    public void checkIsElementVisible(WebElement webElement, String elementName) {
        Assert.assertTrue(elementName + " is not visible", isElementVisible(webElement, elementName));
    }

    protected void checkTextInElement(WebElement webElement, String expectedMessage) {
        Assert.assertTrue("Element is not displayed", isElementVisible(webElement));
        String textFromElement = webElement.getText();
        Assert.assertEquals("Text in element is not matched", expectedMessage, textFromElement);
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }


    private String getElementsName (WebElement webElement){
        String elementName = "";
        try {
            return webElement.getAccessibleName();
        }catch (Exception e){
            return elementName;
        }
    }
}



