package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyProfileStepDefinitions extends MainSteps{
    public MyProfileStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then(  "I was redirected to MyProfile page")
    public void i_was_redirected_to_my_profile_page() {
        pageProvider.getMyProfilePage().checkIsRedirectOnProfilePage();
    }

    @And("I see {} posts in Posts List on MyProfile page")
    public void iSeePostsInPostsListOnMyProfilePage(Integer numberOfPosts) {
        pageProvider.getMyProfilePage().checkNumberOfPosts(numberOfPosts);
    }
}
