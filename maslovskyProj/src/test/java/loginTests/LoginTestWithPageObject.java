package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void TR001_validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSighIn();

        Assert.assertTrue("Button Sign Out is not visible",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
    }

    // зробити тест на невалідний логін
    @Test
    public void TR002_invalidLoginWithNotValidName() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("invalidName");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSighIn();

        Assert.assertTrue("Button Sign In is not visible",
                pageProvider.getHomePage().isButtonSignInVisible());

        Assert.assertTrue("Alert text is not displayed",
                pageProvider.getHomePage().isInvalidCredentialsTextDisplayed());
    }

    @Test
    public void TR003_invalidLoginWithNotValidPassword() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("invalidPassword");
        pageProvider.getLoginPage().clickOnButtonSighIn();

        Assert.assertTrue("Button Sign In is not visible",
                pageProvider.getHomePage().isButtonSignInVisible());

        Assert.assertTrue("Alert text is not displayed",
                pageProvider.getHomePage().isInvalidCredentialsTextDisplayed());
    }

    @Test
    public void TR004_invalidLoginWithBlankName() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSighIn();

        Assert.assertTrue("Button Sign In is not visible",
                pageProvider.getHomePage().isButtonSignInVisible());

        Assert.assertTrue("Alert text is not displayed",
                pageProvider.getHomePage().isInvalidCredentialsTextDisplayed());
    }

    @Test
    public void TR005_invalidLoginWithBlankPassword() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().clickOnButtonSighIn();

        Assert.assertTrue("Button Sign In is not visible",
                pageProvider.getHomePage().isButtonSignInVisible());

        Assert.assertTrue("Alert text is not displayed",
                pageProvider.getHomePage().isInvalidCredentialsTextDisplayed());
    }

    @Test
    public void TR006_invalidLoginWithBlankNameAndPassword() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().clickOnButtonSighIn();

        Assert.assertTrue("Button Sign In is not visible",
                pageProvider.getHomePage().isButtonSignInVisible());

        Assert.assertTrue("Alert text is not displayed",
                pageProvider.getHomePage().isInvalidCredentialsTextDisplayed());
    }
}
