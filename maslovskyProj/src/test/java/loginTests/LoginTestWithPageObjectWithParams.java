package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObjectWithParams extends BaseTest {
    protected String userName = "qaauto";
    protected String userPassword = "123456qwerty";

    @Test
    @Parameters(method = "invalidLoginParameters")
    public void HW6_parameterizedIncorrectLogin(String userName, String userPassword) {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLoginAndContinue(userName)
                .enterTextIntoInputPasswordAndContinue(userPassword)
                .clickOnButtonSighIn()
                .getLoginPage()
                .checkIsErrorMessageDisplayed();
    }

    private List<Object[]> invalidLoginParameters() {
        return Arrays.asList(new Object[][] {
                {userName, ""},
                {"", userPassword},
                {"", ""},
                {"invalidUser", "invalidPass"},
                {userName, "invalidPass"},
                {"invalidUser", userPassword}
        });
    }


}
