package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import static data.TestData.*;


public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void TR001_validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not visible",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
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
}
