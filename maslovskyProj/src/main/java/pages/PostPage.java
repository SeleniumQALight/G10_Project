package pages;

import org.openqa.selenium.WebDriver;

public class PostPage extends ParentPage {
    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO: check URL
        //TODO: check some element
        return this;
    }
}
