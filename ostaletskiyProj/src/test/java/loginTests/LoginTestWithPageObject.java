package loginTests;

import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends baseBase.baseTest {

    @Test
    public void TR001_validLogin() {
        pageProvider.getLoginPage().openPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed",
                pageProvider.getHomePage().isButtonSignOutVisible());

}
}