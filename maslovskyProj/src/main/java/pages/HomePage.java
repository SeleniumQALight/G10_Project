package pages;

import data.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

import static data.TestData.initialNumberOpenedTabs;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage checkIsRedirectOnHomePage() {
        Assert.assertTrue("It is not Home page", getHeaderElement().isButtonSignOutVisible());
        checkUrl();
        return this;
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (getHeaderElement().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSighIn();
            logger.info("User was logged in");
        }
        return this;
    }

    public HomePage openLoginPage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        return this;
    }

    public HomePage createNewBrowserTab() {
        openNewBrowserTab();
        return this;
    }

    public HomePage gotoToNewBrowserTab() {
        switchToNewBrowserTab();
        return this;
    }
//
//    public HomePage openLoginPage() {
//        LoginPage loginPage = new LoginPage(webDriver);
//        loginPage.openLoginPage();
//        return this;
//    }
//
    public HomePage getBackToFirstBrowserTab() {
        returnToFirstBrowserTab();
        return this;
    }

    public HomePage closeNewBrowserTab() {
        closeBrowserTab(initialNumberOpenedTabs-1);
        return this;
    }

}
