package signOutTests;

import baseBase.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void TR004_signOut() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage();
        Assert.assertTrue("Search button is not visible", pageProvider.getHomePage().getHeaderElement().isButtonSearchVisible());
        Assert.assertTrue("Chat button is not visible", pageProvider.getHomePage().getHeaderElement().isButtonChatVisible());
        Assert.assertTrue("Avatar is not visible", pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());
        Assert.assertTrue("Create post button is not visible", pageProvider.getHomePage().getHeaderElement().isButtonCreatePostVisible());
        Assert.assertTrue("Sign out button is not visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());

        pageProvider.getHomePage().clickOnButtonSignOut()
                .checkIsRedirectToLoginPage();
        Assert.assertFalse("Search button is visible", pageProvider.getHomePage().getHeaderElement().isButtonSearchVisible());
        Assert.assertFalse("Chat button is visible", pageProvider.getHomePage().getHeaderElement().isButtonChatVisible());
        Assert.assertFalse("Avatar is visible", pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());
        Assert.assertFalse("Create post button is visible", pageProvider.getHomePage().getHeaderElement().isButtonCreatePostVisible());
        Assert.assertFalse("Sign out button is visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Input for login is visible", pageProvider.getLoginPage().isInputLoginVisible());
        Assert.assertTrue("Input for password is visible", pageProvider.getLoginPage().isInputPasswordVisible());
        Assert.assertTrue("Button Sign In is not visible", pageProvider.getLoginPage().isButtonSignInVisible());

    }


}
