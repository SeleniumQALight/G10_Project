package loginTests;

import baseBase.BaseTest;
import data.TestData;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class LoginWithParams extends BaseTest {
    final String EMPTY_USERNAME_MESSAGE = "Invalid username/password.";
    final String EMPTY_PASSWORD_MESSAGE = "Invalid username/password.";
    final String INVALID_USERNAME_MESSAGE = "Invalid username/password.";
    final String INVALID_PASSWORD_MESSAGE = "Invalid username/password.";
    final String SEMICOLON = ";";
    final String emptyValue = "";
    final String invalidValue = "invalid";

    @Test
    @Parameters(method = "parametersForInvalidLoginTest")
    public void TC008_invalidLoginTestWithParams(String userName, String password, String expectedMessage) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(userName);
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        Assert.assertTrue("Alert message is visible", pageProvider.getLoginPage().isAlertMessageVisible());

    }

    public Object[][] parametersForInvalidLoginTest() {
        return new Object[][]{
                {emptyValue, emptyValue, EMPTY_USERNAME_MESSAGE + SEMICOLON + EMPTY_PASSWORD_MESSAGE},
                {invalidValue, invalidValue, INVALID_USERNAME_MESSAGE + SEMICOLON + INVALID_PASSWORD_MESSAGE},
                {emptyValue, TestData.VALID_PASSWORD_UI, EMPTY_USERNAME_MESSAGE},
                {TestData.VALID_LOGIN_UI, emptyValue, EMPTY_PASSWORD_MESSAGE},
        };
    }
}
