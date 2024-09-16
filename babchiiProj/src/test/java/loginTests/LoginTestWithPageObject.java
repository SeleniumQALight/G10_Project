package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import data.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)
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
}
