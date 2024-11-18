package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PrivatBankPageStepDefinitions extends MainSteps {

    public PrivatBankPageStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @And("I open Privat Bank page")
    public void iOpenPrivatBankPage() {
        pageProvider.getPrivatBankPage().openPrivatBankPage();
    }

    @When("I click on button {string} on Header")
    public void iClickOnButtonOnHeader(String buttonName) {
        pageProvider.getPrivatBankPage().clickOnButtonOnHeader(buttonName);
    }

    @And("I save exchange rates {string} from UI")
    public void i_save_exchange_rates_from_ui(String currency) {
        pageProvider.getPrivatBankPage().getSaveExchangeRates(currency);
    }

    @Then("I compare exchange rates from API and UI")
    public void iCompareExchangeRatesFromAPIAndUI() {
        pageProvider.getPrivatBankPage().compareExchangeRates();
    }
}
