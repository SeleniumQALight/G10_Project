package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class PrivatHomePageStepDefinitions extends MainSteps {

    public PrivatHomePageStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I open Privatbank Home page")
    public void iOpenPrivatbankHomePage() {
        pageProvider.getPrivatHomePage().openPrivatbankHomePage();
    }

    @And("I get currency exchange rates {string} from Privatbank Exchange Rate popup")
    public void iGetCurrencyExchangeRatesFromPrivatbankExchangeRatePopup(String currency) {
        pageProvider.getPrivatHomePage().openExchangeRatePopup();
        pageProvider.getPrivatHomePage().getCurrencyExchangeRates(currency);
    }

    @Then("I compare currency exchange rates from API and from UI to be equal")
    public void iCompareCurrencyExchangeRatesFromAPIAndFromUIToBeEqual() {
        Assert.assertEquals("Currency exchange rates from API and from UI are not equal",
                TestData.CURRENCY_RATE_API, TestData.CURRENCY_RATE_UI);
    }
}
