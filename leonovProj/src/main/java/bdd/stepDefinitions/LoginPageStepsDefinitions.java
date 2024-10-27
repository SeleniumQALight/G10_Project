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
    public void iOpenLoginPage(){
        pageProvider.getLoginPage().openLoginPage();
    }

    @When("I login with valid cred")
    public void i_login_with_valid_cred() {
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @When("I enter {string} into input Login on Login page")
    public void iEnterIntoInputLoginOnLoginPage(String login) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
    }

    @And("I enter {string} into input Password on Login page")
    public void iEnterIntoInputPasswordOnLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }

    @And("I click on button SignIn on Login page")
    public void iClickOnButtonSignInOnLoginPage() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @Then("I see error message {string}")
    public void iSeeErrorMessageInvalidUsernamePassword(String  expectedErrorMessage){
        pageProvider.getLoginPage().checkErrorMessage(expectedErrorMessage);
    }

    @When("I enter {string} into Username field")
    public void iEnterIntoUsernameField(String userName) {
        pageProvider.getLoginPage().enterTextIntoRegistrationNameField(userName);
    }

    @And("I enter {string} into Email field")
    public void iEnterIntoEmailField(String email) {
        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
    }

    @And("I enter {string} into Password field")
    public void iEnterIntoPasswordField(String password) {
        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
    }


    @Then("I see validation message {string} for fields")
    public void iSeeValidationMessageForFields(String expectedMessages) {
        pageProvider.getLoginPage().checkErrorsMessages(expectedMessages);
    }
}
