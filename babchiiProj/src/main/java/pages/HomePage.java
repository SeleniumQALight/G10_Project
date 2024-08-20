package pages;

import data.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("It is not Home page", getHeaderElement().isButtonSignOutVisible());
        //TODO checkUrl
        return this;
    }
    public HomePage checkIsButtonCreatePostVisible() {
        Assert.assertTrue("Button Create Post is not displayed", getHeaderElement().isButtonCreatePostVisible());
        return this;
    }
    public HomePage checkIsButtonMyProfileVisible() {
        Assert.assertTrue("Button My Profile is not displayed", getHeaderElement().isMyProfileButtonVisible());
        return this;
    }
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
}
