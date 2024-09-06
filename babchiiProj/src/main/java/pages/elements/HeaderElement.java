package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {
    // myProfileButton
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement myProfileButton;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[@href='/create-post']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = ".//span[@class='text-white mr-2']")
    private WebElement userName;
    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserNameInLoginForm;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPasswordInLoginForm;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }
    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }
    public WebElement buttonCreatePost() {
        return buttonCreatePost;
    }
    public boolean isButtonCreatePostVisible() {
        return isElementVisible(buttonCreatePost);
    }
    public boolean isMyProfileButtonVisible() {
        return isElementVisible(myProfileButton);
    }
    public String getUserNameText() {
        return userName.getText();
    }
    public WebElement getInputUserNameInLoginForm() {
        return inputUserNameInLoginForm;
    }
    public WebElement getInputPasswordInLoginForm() {
        return inputPasswordInLoginForm;
    }
    public boolean isInputUserNameInLoginFormVisible() {
        return isElementVisible(inputUserNameInLoginForm);
    }
    public boolean isInputPasswordInLoginFormVisible() {
        return isElementVisible(inputPasswordInLoginForm);
    }
    public HeaderElement checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }
}
