package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import pages.PageProvider;

public class MainSteps {
    protected WebDriverHelper webDriverHelper;
    protected PageProvider pageProvider;

    public MainSteps(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
        pageProvider = new PageProvider(webDriverHelper.getWebDriver());
    }
}
