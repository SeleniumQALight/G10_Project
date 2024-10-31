package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MyProfilePageStepsDefinitions extends MainSteps{
    public MyProfilePageStepsDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }


    @Then("I was redirected to MyProfile page")
    public void iWasRedirectedToMyProfilePage() {
        pageProvider.getMyProfilePage().checkIsRedirectToMyProfilePage();
    }

    @And("I see {} in Posts list on MyProfile Page")
    public void iSeeInPostsListOnMyProfilePage(Integer numberOfPosts) {
        pageProvider.getMyProfilePage().checkNumberOfPosts(numberOfPosts);
    }
}
