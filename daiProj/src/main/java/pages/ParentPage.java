package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.ConfigProvider;

import java.util.ArrayList;
import java.util.Locale;


abstract public class ParentPage extends CommonActionsWithElements {

    protected Logger logger = Logger.getLogger(getClass());
    static String environment = System.getProperty("env", "aqa").toLowerCase();
//    String baseUrl = "https://"+environment+"-complexapp.onrender.com";
public static String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }


    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("URL is not expected"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }


    //метод по перевірці чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/64d21e8490364000341c338
    // regex for 64d21e8490364000341c338
    // [a-zA-Z0-9]{24}
    //https://aqa-complexapp.onrender.com/post/[a-zA-z0-9]

    protected void checkUrlWithPattern(){
        Assert.assertTrue("URL is not expected \n" +
                "Expected url: " + baseUrl + getRelativeUrl() +
                "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }


    public void openNewTab(){
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab is opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


public void switchToTab(int tabNumber) {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabNumber));
        logger.info("Switched to tab" + tabNumber);
    }

    public void closeTab ( int tabNumber){
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabNumber)).close();
        logger.info("Tab number " + tabNumber + " is closed");
    }

}

