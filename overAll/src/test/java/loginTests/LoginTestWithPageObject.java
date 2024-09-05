package loginTests;

import baseBase.BaseTest;

import org.junit.Test;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void TR001_validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

//        Assert.assertTrue("Button Sign Out is not Visible",
//                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible() );
        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSignOutVisible();
    }
    @Test
    public void TR001_validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderElement()
                .checkIsButtonSignOutVisible();
    }

}
