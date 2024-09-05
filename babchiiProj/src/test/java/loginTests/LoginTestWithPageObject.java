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
        pageProvider.getHomePage().checkIsButtonCreatePostVisible();
        pageProvider.getHomePage().checkIsButtonMyProfileVisible();
        pageProvider.getHomePage().checkIsUserNameVisible();

//        Assert.assertTrue("Button Sign Out is not visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        Assert.assertFalse("Input Login is displayed", pageProvider.getLoginPage().getHeaderElement().isInputUserNameInLoginFormVisible());
        Assert.assertFalse("Input Password is displayed", pageProvider.getLoginPage().getHeaderElement().isInputPasswordInLoginFormVisible());
    }

    @Test
    public void TR002_invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button Sign Out is visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is not visible", pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("The alert message is not visible", pageProvider.getLoginPage().isInvalidLoginMessageVisible());
    }

    @Test
    public void TR003_checkSignOutButton(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderElement()
                .checkIsHeaderElementsAreVisible("yes")
                .getHeaderElement().clickOnButtonSignOut()
                .getHeaderElement().checkIsHeaderElementsAreVisible("no");
    }
}
