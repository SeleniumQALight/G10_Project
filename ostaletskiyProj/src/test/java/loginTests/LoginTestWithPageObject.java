package loginTests;

import baseBase.BaseTest;
import categories.SmokeTestFilter;
import data.TestData;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

import static data.TestData.*;

@Epic("Allure examples")
@Feature("Junit 4 support")
@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    final String ALERT_MESSAGE = "Invalid username/password.";


    @Test
    @Category(SmokeTestFilter.class)
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    public void TR001_validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();

      //  Assert.assertTrue("Button Sign Out is not displayed",
      //          pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        Assert.assertTrue("Button My Profile is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());
        Assert.assertTrue("Button Create Post is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonCreatePostVisible());
        Assert.assertFalse("Button Sign In is displayed",
                pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertFalse("Input for password is visible",
                pageProvider.getLoginPage().isInputPasswordVisible());
        Assert.assertFalse("Input for login is visible",
                pageProvider.getLoginPage().isInputLoginVisible());
}

    @Test
    public void TR002_invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(INVALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(INVALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();


        Assert.assertFalse("Button Sign Out is displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Notification is not displayed",
                pageProvider.getLoginPage().isNotificationVisible());
        Assert.assertTrue("Button Sign In is not displayed",
              pageProvider.getLoginPage().isButtonSignInVisible());

    }
    @Test
    @Ignore
    public void TR001_validLoginWithExcel() throws IOException {
        Map<String , String> dataValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSignIn();

        //  Assert.assertTrue("Button Sign Out is not displayed",
        //          pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        Assert.assertTrue("Button My Profile is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());
        Assert.assertTrue("Button Create Post is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonCreatePostVisible());
        Assert.assertFalse("Button Sign In is displayed",
                pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertFalse("Input for password is visible",
                pageProvider.getLoginPage().isInputPasswordVisible());
        Assert.assertFalse("Input for login is visible",
                pageProvider.getLoginPage().isInputLoginVisible());
    }

    @Test
    @Parameters(method = "ParametersForInvalidLoginTest")
    public void TR010_InvalidLoginWithParameters(String login, String password, String alertMessage) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().isNotificationVisible();
    }

    public Object[][] ParametersForInvalidLoginTest() {
        return new Object[][]{
                {INVALID_LOGIN_UI, INVALID_PASSWORD_UI, ALERT_MESSAGE},
                {VALID_LOGIN_UI, INVALID_PASSWORD_UI, ALERT_MESSAGE},
                {INVALID_LOGIN_UI, VALID_PASSWORD_UI, ALERT_MESSAGE}
        };
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
    public void TR007_SessionPersistenceAcrossTabs() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCredentials();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getCommonActionsWithElements().openNewTab();
        pageProvider.getCommonActionsWithElements().switchToTab("new tab", 1);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getCommonActionsWithElements().switchToTab("main tab", 0);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getCommonActionsWithElements().closeTab("new tab",1);
        pageProvider.getCommonActionsWithElements().switchToTab("main tab", 0);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }
}