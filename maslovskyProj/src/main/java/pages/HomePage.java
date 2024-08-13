package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//*[contains (text(), 'Invalid username/password.')]")
//  as a variant  @FindBy(xpath = "//*[contains (@class, 'alert-danger') and not (contains(@class, 'liveValidateMessage'))]")
    private WebElement invalidCredentialsText;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    public boolean isButtonSignInVisible() {
        return isElementVisible(buttonSignIn);
    }

    public boolean isInvalidCredentialsTextDisplayed() {
        return isElementVisible(invalidCredentialsText);
    }

    public HomePage checkIsRedirectOnHomePage() {
        Assert.assertTrue("It is not Home page", isButtonSignOutVisible());
        // TODO check URL
        return this;
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }
}
