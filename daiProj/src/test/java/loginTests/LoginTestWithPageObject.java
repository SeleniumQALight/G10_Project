package loginTests;

import baseBase.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void TR001_validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
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
}
