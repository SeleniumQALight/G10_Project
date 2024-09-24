package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils.ConfigProvider;

import java.util.Locale;

abstract public class ParentPage extends CommonActionsWithElements {
    String environment = System.getProperty("env", "aqa").toLowerCase();
//    String baseUrl = "https://" + environment + "-complexapp.onrender.com";

    protected Logger logger = Logger.getLogger(getClass());

    String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    /**
     * Method for checking static URL
     */
    protected void checkUrl() {
        Assert.assertEquals("URL is not expected",
                baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/66c4df4adc6fdc00434fdcb0
    //regex for 66c4df4adc6fdc00434fdcb0
    //[a-zA-Z0-9]{24}
    //https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]

    protected void checkCurrentUrlWithPattern() {
        Assert.assertTrue("URL is not expected \n" + "Expected URL: " + baseUrl + getRelativeUrl()
                        + "\n" + "Actual URL: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }

    protected void switchTab(int tabNumber) {
        Object[] openedTabs = webDriver.getWindowHandles().toArray();
        webDriver.switchTo().window((String) openedTabs[tabNumber]);
        logger.info("Switched to tab #" + tabNumber);
    }

    protected void openPageInNewTab(String url) {
        String script = "window.open('" + url + "')";
        ((JavascriptExecutor) webDriver).executeScript(script);
        logger.info("Opened new tab with URL: " + url);
    }

    protected void closeCurrentTab() {
        webDriver.close();
        logger.info("Tab was closed");
    }

    protected void refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page was refreshed");
    }

    public void pressKeysUsingActions(Keys key) {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(key)
                    .perform();
            logger.info("Key " + key + " was pressed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void enterTextUsingActions(String text) {
        try {
            Actions actions = new Actions(webDriver);
            for (char c : text.toCharArray()) {
                actions.sendKeys(String.valueOf(c))
                        .perform();
            }
            logger.info("Text " + text + " was entered via keyboard");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

}
