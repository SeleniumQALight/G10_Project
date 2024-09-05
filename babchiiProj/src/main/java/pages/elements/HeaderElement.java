package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;

public class HeaderElement extends CommonActionsWithElements {
    // myProfileButton
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement myProfileButton;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = "//a[@data-original-title]")
    private WebElement searchButton;
    @FindBy(xpath = "//span[@data-original-title]")
    private WebElement chatButton;
    @FindBy(xpath = "//img")
    private WebElement userAvatar;
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
    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }
    public HomePage checkIsHeaderElementsAreVisible(String shouldBeVisible){  //possible values for shouldBeVisible is "yes" (should be display) or any other string (should not be displayed)
        if (shouldBeVisible.equals("yes")) {
            checkElementIsVisible(searchButton);
            checkElementIsVisible(chatButton);
            checkElementIsVisible(userAvatar);
            checkElementIsVisible(buttonCreatePost);
            checkElementIsVisible(buttonSignOut);
        } else {
            checkElementIsNotVisible(searchButton);
            checkElementIsNotVisible(chatButton);
            checkElementIsNotVisible(userAvatar);
            checkElementIsNotVisible(buttonCreatePost);
            checkElementIsNotVisible(buttonSignOut);
        }
        return new HomePage(webDriver);
    }
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
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
