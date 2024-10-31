package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.When;

public class HeaderElementsStepDefinitions extends MainSteps {

    public HeaderElementsStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I click on button MyProfile on Header Element")
    public void iClickOnButtonMyProfileOnHeaderElement() {
        pageProvider.getHeaderElement().clickOnMyProfileButton();
    }

}
