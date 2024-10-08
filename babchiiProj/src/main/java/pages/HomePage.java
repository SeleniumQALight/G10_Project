package pages;

import data.TestData;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("It is not Home page", getHeaderElement().isButtonSignOutVisible());
        checkUrl();
        return this;
    }
    @Step
    public HomePage checkIsButtonCreatePostVisible() {
        Assert.assertTrue("Button Create Post is not displayed", getHeaderElement().isButtonCreatePostVisible());
        return this;
    }
    @Step
    public HomePage checkIsButtonMyProfileVisible() {
        Assert.assertTrue("Button My Profile is not displayed", getHeaderElement().isMyProfileButtonVisible());
        return this;
    }
    @Step
    public HomePage checkIsUserNameVisible() {
        Assert.assertTrue("User name is not displayed", getHeaderElement().getUserNameText().equals(TestData.VALID_LOGIN_UI));
        return this;
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(getHeaderElement().buttonCreatePost());
        return new CreateNewPostPage(webDriver);
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (getHeaderElement().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;

    }

    public HomePage openNewTabAfterValidLoginAndCheckIsUserLoggedIn(){
        openNewTab(baseUrl);
        switchToTabByIndex(1);
        checkIsRedirectToHomePage();
        getHeaderElement().checkIsButtonSignOutVisible();
        return this;
    }

    public HomePage checkIsUserLoggedInMainTab(){
        switchToMainTab();
        getHeaderElement().checkIsButtonSignOutVisible();
        return this;
    }

    public HomePage closeNewTabAndCheckIsUserLoggedInOnMainTab(int tabIndex){
        switchToTabByIndex(tabIndex);
        closeCurrentTab();
        switchToMainTab();
        getHeaderElement().checkIsButtonSignOutVisible();
        return this;
    }
}
