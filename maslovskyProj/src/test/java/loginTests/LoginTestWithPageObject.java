package loginTests;

import baseTest.BaseTest;
import data.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    protected String userName = "qaauto";
    protected String userPassword = "123456qwerty";

    @Test
    public void TR001_validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
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
}
