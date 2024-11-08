package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
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


    @When("I login with valid cred")
    public void i_login_with_valid_cred() {
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterIntoInputLoginInLoginPage(String login) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
    }

    @When("I enter {string} into input PassWord in Login page")
    public void i_enter_into_input_pass_word_in_login_page(String password){
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }
    @When("I click on button SignIn in Login page")
    public void i_click_on_button_sign_in_in_login_page() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }


    @When("I enter {string} into input Username in Login page")
    public void iEnterIntoInputUsernameInLoginPage(String username) {
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(username);
    }

    @When("I enter {string} into input Email in Login page")
    public void iEnterIntoInputEmailInLoginPage(String email) {
        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
    }

    @When("I enter {string} into input Password in Login page")
    public void iEnterIntoInputPasswordInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
    }

    @When("I click on button SignUp in Login page")
    public void iClickOnButtonSignUpInLoginPage() {
        pageProvider.getLoginPage().clickOnSignUpButton();
    }


    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String expectedMessage) {
        pageProvider.getLoginPage().checkTextInAlertMessageInCenter(expectedMessage);
    }


    @Then("I see validation message with text {string}")
    public void iSeeValidationMessageWithText(String expectedMessage) {
        pageProvider.getLoginPage().checkErrorsMessages(expectedMessage);
    }
}

