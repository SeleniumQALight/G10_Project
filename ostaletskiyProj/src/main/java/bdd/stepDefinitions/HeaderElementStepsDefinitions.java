package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HeaderElementStepsDefinitions extends MainSteps {

    public HeaderElementStepsDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I click on button MyProfile on Header Element")
    public void iClickOnButtonMyProfileOnHeaderElement() {
        pageProvider.getHomePage().getHeaderElement().clickOnButtonMyProfile();
    }

}
