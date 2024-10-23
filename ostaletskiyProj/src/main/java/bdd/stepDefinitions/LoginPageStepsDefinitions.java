package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepsDefinitions extends MainSteps {

    public LoginPageStepsDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Login page")
    public void iOpenLoginPage () {
        pageProvider.getLoginPage().openLoginPage();

    }

    @When("I login with valid cred")
    public void i_login_with_valid_cred() {
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }


    @When("I enter {string} into input Login in Login Page")
    public void iEnterIntoInputLoginInLoginPage(String login) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
    }

    @And("I enter {string} into input Password in Login Page")
    public void iEnterIntoInputPasswordInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }

    @And("I click on button SignIn in Login Page")
    public void iClickOnButtonSignInInLoginPage() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String expectedMessage) {
        pageProvider.getLoginPage().checkIsAlertMessageDisplayed(expectedMessage);
    }
}
