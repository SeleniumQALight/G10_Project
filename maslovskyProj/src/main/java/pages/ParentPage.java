package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

abstract class ParentPage extends CommonActionsWithElements {
    protected Logger logger = Logger.getLogger(getClass());
    String baseUrl = "https://aqa-complexapp.onrender.com";
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
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())   );
    }

}
