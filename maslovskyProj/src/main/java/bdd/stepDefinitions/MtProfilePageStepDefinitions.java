package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MtProfilePageStepDefinitions extends MainSteps{

    public MtProfilePageStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I was redirected to MyProfile page")
    public void i_was_redirected_to_my_profile_page() {
        pageProvider.getMyProfilePage().checkIsRedirectToMyProfilePage();
    }

    @And("I see {} in Posts list on MyProfile Page")
    public void iSeeInPostsListOnMyProfilePage(Integer numberOfPosts) {
        pageProvider.getMyProfilePage().checkNumberOfPosts(numberOfPosts);
    }
}
