package loginTests;

import baseBase.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Before
    public void login() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCredentials();

        Assert.assertTrue("Button Sign Out is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Button My Profile is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());
        Assert.assertTrue("Button Create Post is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonCreatePostVisible());
        Assert.assertTrue("Chat button is not displayed",
                pageProvider.getHomePage().getHeaderElement().isChatButtonVisible());
        Assert.assertTrue("Search icon is not displayed",
                pageProvider.getHomePage().getHeaderElement().isSearchIconVisible());
        Assert.assertFalse("Button Sign In is displayed",
                pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertFalse("Input for password is visible",
                pageProvider.getLoginPage().isInputPasswordVisible());
        Assert.assertFalse("Input for login is visible",
                pageProvider.getLoginPage().isInputLoginVisible());
    }
    @Test
    public void TR004_signOut() {
        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();

        Assert.assertFalse("Button Sign Out is displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertFalse("Button My Profile is displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());
        Assert.assertFalse("Button Create Post is displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonCreatePostVisible());
        Assert.assertFalse("Search icon is displayed",
                pageProvider.getHomePage().getHeaderElement().isSearchIconVisible());
        Assert.assertFalse("Chat button is displayed",
                pageProvider.getHomePage().getHeaderElement().isChatButtonVisible());
        Assert.assertTrue("Button Sign In is not displayed",
                pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("Input for password is not visible",
                pageProvider.getLoginPage().isInputPasswordVisible());
        Assert.assertTrue("Input for login is not visible",
                pageProvider.getLoginPage().isInputLoginVisible());
    }

}
