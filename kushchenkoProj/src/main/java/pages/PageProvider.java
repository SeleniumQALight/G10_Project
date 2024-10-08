package pages;

import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver);
    }

    public HeaderElement getHeaderElement() { return new HeaderElement(webDriver);
    }

    public CommonActionsWithElements getCommonActionsWithElements() { return new CommonActionsWithElements(webDriver);
    }
}
