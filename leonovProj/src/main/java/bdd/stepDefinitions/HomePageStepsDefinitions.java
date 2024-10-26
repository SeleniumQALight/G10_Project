package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class HomePageStepsDefinitions extends MainSteps {
    public HomePageStepsDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on Home page")
    public void iSeeAvatarOnHomePage() {
        pageProvider.getHomePage().getHeader().checkIsMyProfileVisible();
    }

    @And("I open home page as {string} user and {string} password")
    public void iOpenHomePageAsDefaultUserAndDefaultPassword(String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;
        }

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(userName);
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsRedirectOnHomePage();
    }
}
