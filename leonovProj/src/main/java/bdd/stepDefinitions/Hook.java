package bdd.stepDefinitions;

import api.ApiHelper;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigProvider;

import java.time.Duration;

public class Hook {

    WebDriverHelper webDriverHelper;

    private ApiHelper apiHelper = new ApiHelper();

    public Hook(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    }

    @Before (order = 10)
    public void setup() {
        webDriverHelper.getWebDriver().manage().window().maximize();
        webDriverHelper.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(
                ConfigProvider.configProperties.TIME_FOR_IMPLICIT_WAIT()));
    }

    @After (order = 15)
    public void tearDown() {
        webDriverHelper.quitDriver();
    }

    @Before(value = "@deletePostsForDefaultUser" , order = 50)
    @After(value = "@deletePostsForDefaultUser", order = 50)
    public void deletePostsForDefaultUser() {
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API,
                apiHelper.getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API));
    }
}
