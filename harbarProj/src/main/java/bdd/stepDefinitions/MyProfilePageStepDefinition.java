package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MyProfilePageStepDefinition extends MainSteps {
    public MyProfilePageStepDefinition(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I was redirected to MyProfile page")
    public void iWasRedirectedToMyProfilePage() {
        pageProvider.getMyProfilePage().checkIsRedirectToMyProfilePage();
    }

    @And("I see {} posts in Posts list on MyProfile Page")
    public void iSeePostsInPostsListOnMyProfilePage(Integer numberOfPosts) {
        pageProvider.getMyProfilePage().checkNumbersOfPosts(numberOfPosts);
    }
}
