package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {


    @Test
    public void TR001_validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed",
                pageProvider.getHomePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button Create Post is not displayed",
                pageProvider.getHomePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("My Profile is not displayed",
                pageProvider.getHomePage().getHeader().isMyProfileVisible());
        Assert.assertTrue("Username is not displayed",
                pageProvider.getHomePage().getHeader().isUsernameVisible(VALID_LOGIN_UI));
        Assert.assertFalse("Username login field should not be displayed",
                pageProvider.getLoginPage().isLoginFieldVisible());
        Assert.assertFalse("Password login field should not be displayed",
                pageProvider.getLoginPage().isPasswordFieldVisible());
    }

    @Test
    public void TR002_invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("invalidLogin");
        pageProvider.getLoginPage().enterTextIntoInputPassword("invalidPassword");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Alert about invalid login should be displayed",
                pageProvider.getLoginPage().isAlertInvalidLoginDisplayed());
        Assert.assertFalse("Button Sign Out should not be displayed",
                pageProvider.getHomePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button Sign In should be displayed",
                pageProvider.getLoginPage().isButtonSignInVisible());
    }


}
