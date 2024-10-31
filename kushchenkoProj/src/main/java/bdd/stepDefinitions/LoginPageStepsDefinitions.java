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
    public void iOpenLoginPage() {
        pageProvider.getLoginPage().openLoginPage();
    }

    @When("I login with valid creds")
    public void iLoginWithValidCreds() {
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @When("I enter {string} into input login in Login page")
    public void iEnterIntoInputLoginInLoginPage(String login) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
    }

    @And("I enter {string} into input password in Login page")
    public void iEnterIntoInputPasswordInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }

    @And("I click on button SignIn in Login page")
    public void iClickOnButtonSignInInLoginPage() {
         pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String alertMessage) {
         pageProvider.getLoginPage().checkIsAlertMessageDisplayed(alertMessage);
    }

    @When("I enter {string} into input username in Registration form")
    public void iEnterIntoInputUsernameInRegistrationForm(String username) {
            pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(username);
    }


    @And("I enter {string} into input email in Registration form")
    public void iEnterIntoInputEmailInRegistrationForm(String email) {
        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
    }


    @And("I enter {string} into input password in Registration form")
    public void iEnterIntoInputPasswordInRegistrationForm(String password) {
        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
    }


    @Then("I see alert messages {string}")
    public void iSeeAlertMessages(String expectedMessages) {
        pageProvider.getLoginPage().checkErrorsMessages(expectedMessages);

    }
}
