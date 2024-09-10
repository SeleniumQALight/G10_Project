package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import utils.ConfigProvider;

import java.util.ArrayList;

import static data.TestData.initialNumberOpenedTabs;

abstract class ParentPage extends CommonActionsWithElements {
    protected Logger logger = Logger.getLogger(getClass());
    String environment = System.getProperty("env", "aqa");
//    String baseUrl = "https://" + environment.toLowerCase() + "-complexapp.onrender.com";
    String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract String getRelativeUrl();

    /**
     * Method for checking static URL
     */
    protected void checkUrl() {
        Assert.assertEquals("URL is not expected",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected \n" +
                "Expected URL " + baseUrl + getRelativeUrl() +
                        "\n Actual URL " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
        logger.info("Current URL equals to expected URL");
    }

    public void openNewBrowserTab() {
        initialNumberOpenedTabs = webDriver.getWindowHandles().size();
        ((JavascriptExecutor) webDriver).executeScript("window.open();");

        Assert.assertTrue("New browser TAB is not opened",
                webDriver.getWindowHandles().size() > initialNumberOpenedTabs);
        logger.info("New browser TAB is opened");
    }

    public ParentPage returnToFirstBrowserTab() {
        switchingToTab(initialNumberOpenedTabs-1);
        logger.info("Returning to first browser TAB");
        return this;
    }

    public void switchToNewBrowserTab() {
        switchingToTab(initialNumberOpenedTabs);
        logger.info("Switching to new TAB succeed");
    }

    public ParentPage closeBrowserTab(int tabNumber) {
        switchingToTab(tabNumber);
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
        return this;
    }

    private ParentPage switchingToTab(int tabNumber) {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabNumber));
        Assert.assertEquals("Switching to TAB failed", tabs.get(tabNumber), webDriver.getWindowHandle());
        return this;
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page is refreshed");
    }


}
