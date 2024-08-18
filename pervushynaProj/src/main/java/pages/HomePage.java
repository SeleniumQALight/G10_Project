package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{

    @FindBy(xpath = "//button[@class='btn btn-sm btn-secondary']")
    private WebElement buttonSignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible() {
      return  isElementVisible(buttonSignOut);

    }
}
