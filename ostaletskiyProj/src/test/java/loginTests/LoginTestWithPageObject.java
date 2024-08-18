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

        Assert.assertTrue("Button Sign Out is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
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
}