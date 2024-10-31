package bdd.stepDefinitions;

import api.dto.ApiHelper;
import bdd.helpers.WebDriverHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigProvider;

import java.time.Duration;

import static data.TestData.VALID_LOGIN_API;
import static data.TestData.VALID_PASSWORD_API;

public class Hook {
    WebDriverHelper webDriverHelper;
    private ApiHelper apiHelper = new ApiHelper();

    public Hook(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    }

    @Before (order = 10)
    public void setUp() {
        webDriverHelper.getWebDriver().manage().window().maximize();
        webDriverHelper.getWebDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_IMPLICIT_WAIT()));
    }

    @After (order = 15)
    public void tearDown() {
        webDriverHelper.quitDriver();
    }

    @Before(value = "@deletePostsForDefaultUser", order = 50)
    @After(value = "@deletePostsForDefaultUser", order = 50)
    public void deletePostsForDefaultUser(){
        apiHelper.deleteAllPostsTillPresent(VALID_LOGIN_API,
                apiHelper.getToken(VALID_LOGIN_API, VALID_PASSWORD_API));
    }
}
