package signOutTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import loginTests.LoginTestWithPageObject;


public class SignOutTest extends BaseTest {

    @Before
    public void ValidLogin() {
//        commonLoginActions(userName, userPassword);
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred();
    }

    @Test
    public void TR004_signout() {
        pageProvider.getHomePage()
                .getHeaderElement()
                .clickOnSignOutButton();

        Assert.assertTrue("Button Sign In is not visible",
                pageProvider.getLoginPage().isButtonSignInVisible());

        Assert.assertTrue("Username login input field is not visible",
                pageProvider.getLoginPage().isUsernameInputFieldVisible());

        Assert.assertTrue("Password login input field is not visible",
                pageProvider.getLoginPage().isPasswordInputFieldVisible());
    }

}