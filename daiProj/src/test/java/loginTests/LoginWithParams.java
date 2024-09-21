package loginTests;

import baseBase.BaseTest;
import data.TestData;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.PageProvider;


@RunWith(JUnitParamsRunner.class)
public class LoginWithParams extends BaseTest {
    final String invalidValue = "invalid";
    final String emptyValue = "";


    @Test
    @Parameters(method = "parametersForInvalidLoginTest")
    public void TC008_invalidLoginTestWithParams(String userName, String password){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(userName);
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().isAlertMessageVisible();

    }

    public Object[][] parametersForInvalidLoginTest() {
        return new Object[][]{
                {emptyValue, emptyValue},
                {TestData.VALID_LOGIN_UI, invalidValue},
                {invalidValue, TestData.VALID_PASSWORD_UI},
                {emptyValue, TestData.VALID_PASSWORD_UI},
                {TestData.VALID_LOGIN_UI, emptyValue}

        };
    }
}
