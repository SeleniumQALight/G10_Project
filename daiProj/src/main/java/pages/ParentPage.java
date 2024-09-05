package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Locale;


abstract public class ParentPage extends CommonActionsWithElements {

    protected Logger logger = Logger.getLogger(getClass());
    String environment = System.getProperty("env", "aqa").toLowerCase();
    String baseUrl = "https://"+environment+"-complexapp.onrender.com";

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
}

