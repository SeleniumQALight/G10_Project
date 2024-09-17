package loginTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static data.TestData.*;


@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObjectWithParams extends BaseTest {
    final String ALERT_MESSAGE = "Invalid username/password.";


    @Test
    @Parameters(method = "parametersForInvalidLoginTest")
    public void TR010_invalidLoginWithParameters(String login, String password, String alertMessage) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().checkIsAlertMessageDisplayed(alertMessage);
    }

    public Object[][] parametersForInvalidLoginTest() {
        return new Object[][]{
                {INVALID_LOGIN_UI, INVALID_PASSWORD_UI, ALERT_MESSAGE},
                {VALID_LOGIN_UI, INVALID_PASSWORD_UI, ALERT_MESSAGE},
                {INVALID_LOGIN_UI, VALID_PASSWORD_UI, ALERT_MESSAGE}
        };
    }
}
