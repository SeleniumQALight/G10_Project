package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    protected String userName = "qaauto";
    protected String userPassword = "123456qwerty";

    @Test
    public void TR001_validLogin() {

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(userName);
        pageProvider.getLoginPage().enterTextIntoInputPassword(userPassword);
        pageProvider.getLoginPage().clickOnButtonSighIn();

//        Assert.assertTrue("Button Sign Out is not visible",
//                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutVisible();

//        Assert.assertTrue("Button Create Post is not visible",
//                pageProvider.getHomePage().getHeaderElement().isButtonCreatePostVisible());
//
//        Assert.assertTrue("Button MyProfile is not visible",
//                pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());
//
////        Assert.assertEquals("User name is not equal to Login userName", userName,
////                pageProvider.getHomePage().getHeaderElement()
////                        .getUserName());
//
//        Assert.assertTrue("Element UserName is not visible",
//                pageProvider.getHomePage().getHeaderElement()
//                        .isUserNameVisible(userName));
//
//        Assert.assertFalse("input UserName is visible",
//                pageProvider.getLoginPage().isUsernameInputFieldVisible());
//
//        Assert.assertFalse("input Password is visible",
//                pageProvider.getLoginPage().isPasswordInputFieldVisible());
    }

    // зробити тест на невалідний логін
    @Test
    public void TR002_invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
//        pageProvider.getLoginPage().enterTextIntoInputLogin(userName);
//        pageProvider.getLoginPage().enterTextIntoInputPassword(userPassword);
        pageProvider.getLoginPage().clickOnButtonSighIn();
        Assert.assertTrue("Button Sign In is not visible",
                pageProvider.getLoginPage().isButtonSignInVisible());

        Assert.assertTrue("Alert text is not displayed",
                pageProvider.getLoginPage().isInvalidCredentialsTextDisplayed());

        Assert.assertFalse("Button Sign Out is visible",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
    }


    public void validLoginPrecondition() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderElement().checkIsButtonSighOutVisible();
    }

    @Test
    public void HW6_validLoginVisibleInNewTab() {
        validLoginPrecondition();
        pageProvider.getHomePage()
                .openNewBrowserTab()
                .switchToNewBrowserTab()
                .openLoginPage()
                .getHeaderElement().checkIsButtonSighOutVisible()
                .getHomePage()
                .returnToFirstBrowserTab()
                .getHeaderElement().checkIsButtonSighOutVisible()
                .getHomePage()
                .closeNewBrowserTab()
                .returnToFirstBrowserTab()
                .getHeaderElement().checkIsButtonSighOutVisible()
        ;

    }

    @Test
    public void HW6_dataFromLoginAndPasswordFieldsDisappearsAfterPageRefreshed() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLoginAndContinue(userName)
                .enterTextIntoInputPasswordAndContinue(userPassword)
                .refreshPage()
                .clickOnButtonSighIn()
                .checkIsButtonSighOutNotVisible()
        ;

    }




}
