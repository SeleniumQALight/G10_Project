package pages;

import data.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;
import utils.Utils;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    Utils utils = new Utils(webDriver, logger);

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

    public HomePage openNewBrowserTab() {
        utils.openNewBrowserTab();
        return this;
    }

    public HomePage switchToNewBrowserTab() {
        utils.switchToNewBrowserTab();
        return this;
    }

    public HomePage openLoginPage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        return this;
    }

    public HomePage returnToFirstBrowserTab() {
        utils.returnToFirstBrowserTab();
        return this;
    }

    public HomePage closeNewBrowserTab() {
        utils.closeNewBrowserTab();
        return this;
    }

}
