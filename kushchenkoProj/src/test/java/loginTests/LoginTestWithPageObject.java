package loginTests;

import baseTest.BaseTest;
import data.TestData;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

import static data.TestData.*;


@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    final String ALERT_MESSAGE = "Invalid username/password.";

    @Test
    public void TR001_validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutIsVisible();

        Assert.assertTrue("Button Create Post is not visible",
                pageProvider.getHeaderElement().isButtonCreatePostVisible());
        Assert.assertTrue("Button My Profile is not visible",
                pageProvider.getHeaderElement().isButtonMyProfileVisible());
        Assert.assertTrue("Username is not visible",
                pageProvider.getHeaderElement().isUserNameVisible(VALID_LOGIN_UI));
        Assert.assertFalse("Input Username is visible",
                pageProvider.getLoginPage().isInputUserNameVisible());
        Assert.assertFalse("Input Password is visible",
                pageProvider.getLoginPage().isInputPasswordVisible());
    }

    @Test
    public void TR002_invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("1qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Sign Out button is visible",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Sign In button is not visible",
                pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("Alert message is not displayed",
                pageProvider.getLoginPage().isAlertMessageVisible());
    }

    @Test
    public void TR003_logout() {
        pageProvider.getLoginPage().openLoginPageAndLoginWithValidCreds();
        Assert.assertTrue("Button Sign Out is not visible",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Button Create Post is not visible",
                pageProvider.getHeaderElement().isButtonCreatePostVisible());
        Assert.assertTrue("Button My Profile is not visible",
                pageProvider.getHeaderElement().isButtonMyProfileVisible());
        Assert.assertTrue("Search icon is not visible",
                pageProvider.getHeaderElement().isSearchIconVisible());
        Assert.assertTrue("Chat icon is not visible",
                pageProvider.getHeaderElement().isChatIconVisible());

        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();

        Assert.assertTrue("Button Sign In is not visible",
                pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("Input Username is not visible",
                pageProvider.getLoginPage().isInputUserNameVisible());
        Assert.assertTrue("Input Password is not visible",
                pageProvider.getLoginPage().isInputPasswordVisible());
        Assert.assertFalse("Button Sign Out is visible",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertFalse("Button Create Post is visible",
                pageProvider.getHeaderElement().isButtonCreatePostVisible());
        Assert.assertFalse("Button My Profile is visible",
                pageProvider.getHeaderElement().isButtonMyProfileVisible());
        Assert.assertFalse("Search icon is visible",
                pageProvider.getHeaderElement().isSearchIconVisible());
        Assert.assertFalse("Chat icon is visible",
                pageProvider.getHeaderElement().isChatIconVisible());
    }

    @Test
    public void TR001_validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutIsVisible();
    }

    @Test
    public void TR007_SessionPersistenceAcrossTabs() {
        pageProvider.getLoginPage().openLoginPageAndLoginWithValidCreds();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutIsVisible();
        pageProvider.getCommonActionsWithElements().openNewTab();
        pageProvider.getCommonActionsWithElements().switchToTab("new tab");
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHeaderElement().checkIsButtonSignOutIsVisible();
        pageProvider.getCommonActionsWithElements().switchToTab("main tab");
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutIsVisible();
        pageProvider.getCommonActionsWithElements().closeNewTabAndSwitchToMainTab();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutIsVisible();

    }

    @Test
    public void TR009_inputsAreClearedAfterRefresh() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getCommonActionsWithElements().refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().checkIsButtonSignInVisible();
    }

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
