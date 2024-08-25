package pages;

import data.TestData;
import org.apache.hc.core5.http.Header;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{
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

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("It is not Home page", getHeaderElement().isButtonSignOutVisible());
        Assert.assertTrue("Button Chat is not visible", getHeaderElement().isChatButtonVisible());
        Assert.assertTrue("Img Avatar is not visible", getHeaderElement().isImgAvatarVisible());
        Assert.assertTrue("Button Create Post is not visible", isElementVisible(buttonCreatePost));
        checkUrlWithPattern();
        return this;
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (getHeaderElement().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        }else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was not logged in");
        }
        return this;
    }

//    public HomePage checkIsButtonCreatePostVisible() {
//        Assert.assertTrue("Button Create Post is not visible", isElementVisible(buttonCreatePost));
//        return this;
//    }
//
//    public HomePage checkIsImgAvatarVisible() {
//        HeaderElement headerElement = new HeaderElement(webDriver);
//        Assert.assertTrue("Img Avatar is not visible", headerElement.isImgAvatarVisible());
//        return this;
//    }

//    public HomePage checkIsChatButtonVisible() {
//        HeaderElement headerElement = new HeaderElement(webDriver);
//        Assert.assertTrue("Chat button is not visible", headerElement.isChatButtonVisible());
//        return this;
//    }
}
