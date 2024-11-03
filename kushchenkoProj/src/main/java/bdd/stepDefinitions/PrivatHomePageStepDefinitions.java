package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;


public class PrivatHomePageStepDefinitions extends MainSteps {
    protected WebDriverHelper webDriverHelper;
    private Logger logger = Logger.getLogger(getClass());

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
}
