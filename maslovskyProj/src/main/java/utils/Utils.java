package utils;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utils {
//    private Utils() {
//    }

    private WebDriver webDriver;
    private Logger logger;

    public Utils(WebDriver webDriver, Logger logger) {
        this.webDriver = webDriver;
        this.logger = logger;
    }

    public static void waitABit(Integer second){
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returned SystemDateAndTime In Format yyyy-MM-dd_HH-mm-ss
     */
    public static String getDateAndTimeFormatted(){
        return getDateAndTime("yyyy-MM-dd_HH-mm-ss");
    }

    /**
     * Method returned SystemDateAndTime In Format yyyyMMddHHmmss
     */
    public static String getDateAndTimeFormattedOnlyNumbers(){
        return getDateAndTime("yyyyMMddHHmmss");
    }

    /**
     * Method returned SystemDateAndTime
     */
    public static String getDateAndTime(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void openNewBrowserTab() {
        ((JavascriptExecutor) webDriver).executeScript("window.open();");

        Assert.assertTrue("New browser TAB is not opened",
                webDriver.getWindowHandles().size() > 1);
        logger.info("New browser TAB is opened");
    }

    public void returnToFirstBrowserTab() {
        switchingToTab(0);
        logger.info("Returning to first browser TAB");
    }

    public void switchToNewBrowserTab() {
        switchingToTab(1);
        logger.info("Switching to new TAB succeed");
    }

    public void closeNewBrowserTab() {
        switchingToTab(1);
        String newTabHandle = webDriver.getWindowHandle();
        webDriver.close();

        boolean newTabTitleExists = false;
        for (String handle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(handle);
            if (webDriver.getTitle().equals(newTabHandle)) {
                newTabTitleExists = true;
                break;
            }
        }

        Assert.assertFalse("New TAB is not closed", newTabTitleExists);
        logger.info("New TAB is closed");
    }

    private void switchingToTab(int tabNumber) {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabNumber));
        Assert.assertEquals("Switching to TAB failed", tabs.get(tabNumber), webDriver.getWindowHandle());
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page is refreshed");
    }

}
