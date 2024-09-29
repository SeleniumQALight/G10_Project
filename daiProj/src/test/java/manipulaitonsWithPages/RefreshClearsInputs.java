package manipulaitonsWithPages;

import baseBase.BaseTest;
import data.TestData;
import org.junit.Assert;
import org.junit.Test;

public class RefreshClearsInputs extends BaseTest {

    @Test
    public void TR007_refreshClearsInputs() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        Assert.assertFalse("Button Sign Out is not visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());

    }
}
