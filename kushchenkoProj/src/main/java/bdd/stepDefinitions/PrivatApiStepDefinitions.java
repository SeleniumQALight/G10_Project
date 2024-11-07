package bdd.stepDefinitions;

import apiPrivatbank.ApiHelperPrivatbank;
import io.cucumber.java.en.Given;
import org.apache.log4j.Logger;

public class PrivatApiStepDefinitions {

    ApiHelperPrivatbank apiHelperPrivatbank = new ApiHelperPrivatbank();
    Logger logger = Logger.getLogger(getClass());

    @Given("I get currency exchange rates {string} from Privatbank by API")
    public void iGetCurrencyExchangeRatesFromPrivatbankByAPI(String currency) {
        logger.info("Get currency exchange rates from Privatbank by API");
        apiHelperPrivatbank.getCurrencyExchangeRates(currency);
    }
}
