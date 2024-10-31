package bdd.stepDefinitions;

import api.PrivatApiHelper;
import data.TestData;
import io.cucumber.java.en.Given;
import org.apache.log4j.Logger;

import java.util.HashMap;

// не розумію чому при роботі з цим класом у мене викликається запускається Hook і спрацьовують степи Before та After
public class PrivatApiStepDefinitions {
    Logger logger = Logger.getLogger(getClass());

    PrivatApiHelper privatApiHelper = new PrivatApiHelper();


    @Given("I send a GET request to PrivatApi and I save rates for {string} from response")
    public void iSendAGETRequestToPrivatApiAndISaveRatesForFromResponse(String selectedCurrency) {
        privatApiHelper.getCurrencyRate(selectedCurrency);
    }
}
