package pages;

import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

public class ParentPage extends CommonActionsWithElements {
    protected Logger logger = Logger.getLogger(getClass());
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
