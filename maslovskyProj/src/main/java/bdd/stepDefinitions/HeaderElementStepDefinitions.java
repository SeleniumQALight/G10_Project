package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.When;

public class HeaderElementStepDefinitions extends MainSteps{

    public HeaderElementStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I click on button MyProfile on Header Element")
    public void i_click_on_button_my_profile_on_header_element() {
        pageProvider.getHomePage().getHeaderElement().clickOnMyProfileButton();
    }

}
