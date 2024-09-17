package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import data.TestData;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import utils.ConfigProperties;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    public void TR001_validLogin(){

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

//        Assert.assertTrue("Button Sign Out is not visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
//        Assert.assertTrue("Create Post button is not visible", pageProvider.getHomePage().isButtonCreatePostVisible());
//        Assert.assertTrue("My Profile button is not visible", pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());
//        Assert.assertTrue("User name is not displayed", pageProvider.getHomePage().getHeaderElement().isUserNameDisplayed());
//        Assert.assertFalse("Input Login is displayed", pageProvider.getLoginPage().isInputLoginDisplayed());
//        Assert.assertFalse("Input Password is displayed", pageProvider.getLoginPage().isInputPasswordDisplayed());

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

    }

    @Test
    public void TR001_validLoginWithExcel() throws IOException {

        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }


    @Test
    public void TR002_invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("123456");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button Sign Out is visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Button Sign In is not visible", pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("Text is not display", pageProvider.getLoginPage().textIsDisplay());
    }


}
