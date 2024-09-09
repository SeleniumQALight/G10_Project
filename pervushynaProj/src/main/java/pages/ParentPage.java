package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.ConfigProvider;

abstract class ParentPage extends CommonActionsWithElements{

    protected Logger logger = Logger.getLogger(getClass());
    String environment = System.getProperty("env", "aqa").toLowerCase();
    //String baseURL = "https://"+environment+"-complexapp.onrender.com";

    String baseURL = ConfigProvider.configProperties.base_url().replace("[env]", environment);
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();


    /**
     * Method for checking static URL
     */
    protected void checkUrl() {
        Assert.assertEquals("URL is not expected", baseURL + getRelativeUrl(), webDriver.getCurrentUrl());
    }

// метод по перевірці чи відкрита потрібна сторінка патерну
    //https://aqa-complexapp.onrender.com/post/5f7e3b3b9f3f4b0017f3b3b3
    //regex for 5f7e3b3b9f3f4b0017f3b3b3
    // [a-Za-ZO-9]{24]
    //https://aqa-complexapp.onrender.com/post/[a-Za-ZO-9]{24}
    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected\n" +
                "Expected URL: " + baseURL + getRelativeUrl() + "\n" +
                "Actual URL: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseURL + getRelativeUrl()));
    }


}
