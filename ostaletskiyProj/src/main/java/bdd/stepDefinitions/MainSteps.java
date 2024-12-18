package bdd.stepDefinitions;

import Pages.PageProvider;
import bdd.helpers.WebDriverHelper;

public class MainSteps {
    public static final String DEFAULT = "default";
    protected WebDriverHelper webDriverHelper;
    protected PageProvider pageProvider;

public MainSteps (WebDriverHelper webDriverHelper){
    this.webDriverHelper = webDriverHelper;
    pageProvider = new PageProvider(webDriverHelper.getWebDriver());
}

}
