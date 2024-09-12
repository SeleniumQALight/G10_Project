package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Keys;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

//@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    protected String userName = "qaauto";
    protected String userPassword = "123456qwerty";

    @Test
    @Category(SmokeTestFilter.class)
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

    @Test
    public void TR001_validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(),"validLogOn");

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSighIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSighOutVisible();
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
                .createNewBrowserTab()
                .goToNewBrowserTab()
                .openLoginPage()
                .getHeaderElement().checkIsButtonSighOutVisible()
                .getHomePage()
                .getBackToFirstBrowserTab()
                .getHeaderElement().checkIsButtonSighOutVisible()
                .getHomePage()
                .closeNewBrowserTab()
                .getBackToFirstBrowserTab()
                .getHeaderElement().checkIsButtonSighOutVisible();
    }

    @Test
    public void HW6_dataFromLoginAndPasswordFieldsDisappearsAfterPageRefreshed() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLoginAndContinue(userName)
                .enterTextIntoInputPasswordAndContinue(userPassword)
                .reloadPageContent()
                .clickOnButtonSighIn()
                .checkIsButtonSighOutNotVisible();
    }

//    @Test
//    @Parameters(method = "invalidLoginParameters")
//    public void HW6_parameterizedIncorrectLogin(String userName, String userPassword) {
//        pageProvider.getLoginPage().openLoginPage()
//                .enterTextIntoInputLoginAndContinue(userName)
//                .enterTextIntoInputPasswordAndContinue(userPassword)
//                .clickOnButtonSighIn()
//                .getLoginPage()
//                .checkIsErrorMessageDisplayed();
//    }
//
//    private List<Object[]> invalidLoginParameters() {
//        return Arrays.asList(new Object[][] {
//                {userName, ""},
//                {"", userPassword},
//                {"", ""},
//                {"invalidUser", "invalidPass"},
//                {userName, "invalidPass"},
//                {"invalidUser", userPassword}
//        });
//    }

    @Test
    public void HW6_validLoginUsingKeyboardKeys() {
        pageProvider.getLoginPage().openLoginPage()
                .tabPressing(2)
                .enterText(userName)
                .tabPressing(1)
                .enterText(userPassword)
                .pressEnterButton()
                .getHeaderElement().checkIsButtonSighOutVisible();
    }

}
