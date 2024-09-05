package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract class ParentPage extends CommonActionsWithElements{
    protected Logger logger = Logger.getLogger(getClass());
    String environment = System.getProperty("env", "aqa").toLowerCase();
    String baseUrl = "https://"+ environment +"-complexapp.onrender.com";
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract  protected String getRelativeUrl();

    /**
     * Method for checking static URL
     */
    protected void checkUrl() {
        Assert.assertEquals("URL is not expected", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/5f3e3b3b9f3e4b0017f2b3b3
    // regex for 5f3e3b3b9f3e4b0017f2b3b3
    // [a-zA-z0-9]{24}
    //https://aqa-complexapp.onrender.com/post/[a-zA-z0-9]{24}
    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected \n" + "Expected URL: " + baseUrl + getRelativeUrl() + "\nActual URL: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }
}
