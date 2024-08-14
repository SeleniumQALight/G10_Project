package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class PostPage extends ParentPage{

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }


    public PostPage checkIsRedirectToPostPage() {
        // TODO checkUrl
        // TODO check some element
        return this;
    }
}
