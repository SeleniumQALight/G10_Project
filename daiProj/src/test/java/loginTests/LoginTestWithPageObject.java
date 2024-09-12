package loginTests;

import baseBase.BaseTest;
import categories.SmokeTestFilter;
import data.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)
    public void TR001_validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        // Assert.assertTrue("Button Sign Out is not visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        Assert.assertTrue("Button Create Post is visible", pageProvider.getHomePage().getHeaderElement().isButtonCreatePostVisible());
        Assert.assertTrue("Button My Profile is visible", pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());
        Assert.assertFalse("Input for login is visible", pageProvider.getLoginPage().isInputLoginVisible());
        Assert.assertFalse("Input for password is visible", pageProvider.getLoginPage().isInputPasswordVisible());

    }

    @Test
    public void TR002_invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("invalid");
        pageProvider.getLoginPage().enterTextIntoInputPassword("12345678");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign In is visible", pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertFalse("Button Sign Out is not visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Alert message is visible", pageProvider.getLoginPage().isAlertMessageVisible());

    }


    @Test
    public void TR001_validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");


        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        Assert.assertTrue("Button Create Post is visible", pageProvider.getHomePage().getHeaderElement().isButtonCreatePostVisible());
        Assert.assertTrue("Button My Profile is visible", pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());
        Assert.assertFalse("Input for login is visible", pageProvider.getLoginPage().isInputLoginVisible());
        Assert.assertFalse("Input for password is visible", pageProvider.getLoginPage().isInputPasswordVisible());
    }
}
