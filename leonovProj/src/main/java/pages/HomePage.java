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



    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }



    public HomePage checkIsRedirectOnHomePage() {
        Assert.assertTrue("Home page is not opened", getHeader().isButtonSignOutVisible());
        checkUrl();

        return this;
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);

        return new CreateNewPostPage(webDriver);
    }

    public HomePage openHomepageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (getHeader().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectOnHomePage();
            logger.info("User successfully logged in");
        }

        return this;
    }

    public HomePage switchTabToHomePage(int tabNumber) {
        switchTab(tabNumber);
        return this;
    }

    public void closeCurrentHomePageTab() {
        closeCurrentTab();
    }
}
