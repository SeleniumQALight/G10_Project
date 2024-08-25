package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void TR001_validLogin(){

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Create Post button is not visible", pageProvider.getHomePage().isButtonCreatePostVisible());
        Assert.assertTrue("My Profile button is not visible", pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());
        Assert.assertTrue("User name is not displayed", pageProvider.getHomePage().getHeaderElement().isUserNameDisplayed());
        Assert.assertFalse("Input Login is displayed", pageProvider.getHomePage().getHeaderElement().isInputLoginDisplayed());
        Assert.assertFalse("Input Password is displayed", pageProvider.getHomePage().getHeaderElement().isInputPasswordDisplayed());
    }

    @Test
    public void TR002_invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("123456");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button Sign Out is visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Button Sign In is not visible", pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("Text is not display", pageProvider.getLoginPage().textIsDisplay());
    }
}
