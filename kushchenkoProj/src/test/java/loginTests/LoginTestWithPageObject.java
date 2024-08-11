package loginTests;
import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;


public class LoginTestWithPageObject  extends BaseTest {
    @Test
    public void TR001_validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not visible",
                pageProvider.getHomePage().isButtonSignOutVisible());
    }
}
