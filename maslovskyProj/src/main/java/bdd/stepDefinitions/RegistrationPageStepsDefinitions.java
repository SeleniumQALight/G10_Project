package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static data.RegistrationValidationMessages.*;
import static data.UserForRegistration.*;
import static org.apache.commons.lang3.SystemUtils.getUserName;
import static utils.StringUtils.deleteSomeSymbols;

public class RegistrationPageStepsDefinitions extends MainSteps {
    public RegistrationPageStepsDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Registration page")
    public void iOpenRegistrationPage() {
        pageProvider.getLoginPage().openLoginPage();
    }

    @When("I enter {string} into input field Username in Registration page")
    public void i_enter_into_input_field_in_registration_page(String username) {
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(username);
    }

    @When("I enter {string} into input field Pick a username in Registration page")
    public void iEnterIntoInputFieldPickAUsernameInRegistrationPage(String username) {
        switch (username) {
            case "shortName":
                username = deleteSomeSymbols(USER_NAME_MIN_LENGTH, 1);
                break;
            case "overLengthName":
                username = USER_NAME_MAX_LENGTH + "1";
                break;
            case "maxLengthName":
            username = USER_NAME_MAX_LENGTH;
            break;
        default:
            username = getUserName(username);
        }
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(username);
    }

    @And("I enter {string} into input field Email in Registration page")
    public void iEnterIntoInputFieldEmailInRegistrationPage(String email) {
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationEmailField(email);
    }

    @And("I enter {string} into input field Password in Registration page")
    public void iEnterIntoInputFieldPasswordInRegistrationPage(String password) {
        switch (password) {
            case "shortPassword":
                password = SHORT_PASSWORD_NOT_VALID;
                break;
            case "minLengthPassword":
                password = PASSWORD_MIN_LENGTH;
                break;
            case "maxLengthPassword":
                password = PASSWORD_MAX_LENGTH;
                break;
            case "overLengthPassword":
            password = PASSWORD_MAX_LENGTH + "2".repeat(2);
            break;
        }
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationPasswordField(password);
    }

    @Then("I see registration error messages with text {string}")
    public void iSeeRegistrationErrorMessagesWithTextForUsername(String listOfErrors) {
        String newListOfErrors = listOfErrors
                .replace("errorEmail", ERROR_EMAIL)
                .replace("errorPassword", ERROR_PASSWORD)
                .replace("errorUsername", ERROR_USERNAME)
                .replace("errorMaxSizeUsername", ERROR_LONG_USER)
                .replace("errorMaxSizePassword", ERROR_LONG_PASSWORD);
        pageProvider.getLoginPage()
                .checkErrorsMessages(newListOfErrors);
    }

    @And("I see registration error messages with text {string} for Email")
    public void iSeeRegistrationErrorMessagesWithTextForEmail(String arg0) {

    }

    @And("I see registration error messages with text {string} for Password")
    public void iSeeRegistrationErrorMessagesWithTextForPassword(String arg0) {
    }
}
