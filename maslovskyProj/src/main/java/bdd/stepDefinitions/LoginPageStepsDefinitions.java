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
        pageProvider.getLoginPage().clickOnButtonSighIn();
    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterIntoInputLoginInLoginPage(String login) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
    }

    @When("I enter {string} into input PassWord in Login page")
    public void i_enter_into_input_pass_word_in_login_page(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }
    @When("I click on button SighIn in Login page")
    public void i_click_on_button_sigh_in_in_login_page() {
        pageProvider.getLoginPage().clickOnButtonSighIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String expectedMessage) {
        pageProvider.getLoginPage().checkTextInAlertMessageInCenter(expectedMessage);
    }
}
