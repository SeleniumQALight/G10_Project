package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigProvider;

import java.time.Duration;

public class Hook {
    WebDriverHelper webDriverHelper;

    public Hook (WebDriverHelper webDriverHelper){
        this.webDriverHelper = webDriverHelper;
    }

    @Before
    public void setUp() {
//        webDriverHelper = new WebDriverHelper();
        webDriverHelper.getWebDriver().manage().window().maximize();
        webDriverHelper.getWebDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
    }

    @After
    public void tearDown() {
        webDriverHelper.quiteWebDriver();
    }
}
