package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {
    protected Logger logger = Logger.getLogger(getClass());
    String environment = System.getProperty("env", "aqa").toLowerCase();
//    String baseUrl = "https://"+environment+"-complexapp.onrender.com";
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
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl());
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
    // https://aqa-complexapp.onrender.com/post/66cb2b5fdc6fdc00434fdd1d
    // regex for 66cb2b5fdc6fdc00434fdd1d
    // [a-zA-Z0-9]{24}
    // https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]

    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected \n" +
                "Expected URL: " + baseUrl + getRelativeUrl() +
                "\n Actual URL: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }
}
