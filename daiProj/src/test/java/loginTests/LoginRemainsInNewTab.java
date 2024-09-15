package loginTests;

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

        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
        logger.info("New tab is opened");

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        webDriver.switchTo().window(tabs.get(0));
        logger.info("Switched back to the main tab");
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

        webDriver.switchTo().window(tabs.get(1)).close();
        webDriver.switchTo().window(tabs.get(0));
        logger.info("Closed the new tab and switched back to the main tab");
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();

    }
}
