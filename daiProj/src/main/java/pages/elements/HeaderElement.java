package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.HomePage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[contains(@class, 'header-search-icon')]")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[contains(@class, 'header-chat-icon')]")
    private WebElement buttonChat;



    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
        public MyProfilePage clickOnMyProfileButton() {
            clickOnElement(buttonMyProfile);
            return new MyProfilePage(webDriver);
        }
    public boolean isButtonSignOutVisible() {
        return isElementVisible(buttonSignOut);
    }

    public boolean isButtonCreatePostVisible() {return isElementVisible(buttonCreatePost);
    }

    public boolean isButtonMyProfileVisible() {return isElementVisible(buttonMyProfile);
    }

    public boolean isButtonSearchVisible() {return isElementVisible(buttonSearch);
    }

    public boolean isButtonChatVisible() {return isElementVisible(buttonChat);
    }

    public HomePage clickOnButtonSignOut() {clickOnElement(buttonSignOut);
        return new HomePage(webDriver);
    }
}

