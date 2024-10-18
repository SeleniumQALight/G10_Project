package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Then;

public class HomePageStepsDefinitions extends MainSteps {
    public HomePageStepsDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on Home page")
    public void iSeeAvatarOnHomePage() {
        pageProvider.getHomePage().getHeaderElement().checkIsMyProfileButtonVisible();
    }
}
