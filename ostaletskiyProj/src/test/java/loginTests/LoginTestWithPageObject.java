package loginTests;

import baseBase.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void TR001_validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
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
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty123");
        pageProvider.getLoginPage().clickOnButtonSignIn();


        Assert.assertFalse("Button Sign Out is displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Notification is not displayed",
                pageProvider.getLoginPage().isNotificationVisible());
        Assert.assertTrue("Button Sign In is not displayed",
              pageProvider.getLoginPage().isButtonSignInVisible());

    }
    @Test
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
}