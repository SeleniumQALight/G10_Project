package bdd.stepDefinitions;

import API.PrivatBantDTO.PrivatApiHelper;
import io.cucumber.java.en.Given;
import org.apache.log4j.Logger;

public class PrivatApiStepDefinitions {
    Logger logger = Logger.getLogger(getClass());
    PrivatApiHelper privatApiHelper = new PrivatApiHelper();
    @Given("I send a GET request to PrivatApi and I save rates for {string} from response")
    public void iSendAGETRequestToPrivatApiAndISaveRatesForFromResponse(String selectedCurrency) {
        privatApiHelper.getCurrencyRate(selectedCurrency);
    }
}
