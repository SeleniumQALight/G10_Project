package pages;

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
}
