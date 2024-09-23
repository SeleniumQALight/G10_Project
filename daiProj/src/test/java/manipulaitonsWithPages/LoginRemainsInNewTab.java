package manipulaitonsWithPages;

import baseBase.BaseTest;
import data.TestData;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;


public class LoginRemainsInNewTab extends BaseTest {
    @Test
    public void TR006_loginRemainsInNewTab() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();


        pageProvider.getParentPage().openNewTab();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        pageProvider.getParentPage().switchToTab(0);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        pageProvider.getParentPage().closeTab(1);
        pageProvider.getParentPage().switchToTab(0);
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

    }
}
