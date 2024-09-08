package Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.ConfigHiddenProperties;
import utils.ConfigProvider;

abstract class ParentPage extends CommonActionsWithElements {
    protected Logger logger = Logger.getLogger(getClass());
    String environment = System.getProperty("env", "aqa");
  //  String baseUrl = "https://"+environment+"-complexapp.onrender.com";
    String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    /**
     * Method for checking static URL
     */
    protected void checkUrl() {
        Assert.assertEquals("URL is not expected"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected" +
                        "Expected URL: " + baseUrl + getRelativeUrl() +
                        "Actual URL: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }


}

