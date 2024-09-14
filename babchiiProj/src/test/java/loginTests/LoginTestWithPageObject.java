package loginTests;

import baseTest.BaseTest;
import data.TestData;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void TR001_validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
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

    @Test
    public void TR001_validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
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
    public void TR004_validLoginAndCheckLoggedUserInNewTab(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .openNewTabAfterValidLoginAndCheckIsUserLoggedIn()
                .checkIsUserLoggedInMainTab()
                .closeNewTabAndCheckIsUserLoggedInOnMainTab();
    }

    @Test
    public void TR005_checkInputsClearAfterRefreshPage(){
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                .enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI)
                .refreshLoginPage()
                .clickOnButtonSignIn()
                .getHeaderElement().checkIsHeaderElementsAreVisible("no");
    }

    @Test
    @Parameters(method = "parametersForInvalidLogin")
    public void TR006_checkInputsClearAfterRefreshPage(String userName, String password){
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(userName)
                .enterTextIntoInputPassword(password)
                .clickOnButtonSignIn();

        Assert.assertFalse("Button Sign Out is visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is not visible", pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("The alert message is not visible", pageProvider.getLoginPage().isInvalidLoginMessageVisible());
    }

    public Object[][] parametersForInvalidLogin(){
        return new Object[][]{
                {TestData.VALID_LOGIN_UI, "1"},
                {"1", TestData.VALID_PASSWORD_UI},
                {"1", "1"}
        };
    }
}
