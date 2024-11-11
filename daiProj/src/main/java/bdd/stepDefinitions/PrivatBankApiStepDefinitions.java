package bdd.stepDefinitions;

import api.HWEndpointsAndDTO.PrivatApiHelper;
import io.cucumber.java.en.Given;

public class PrivatBankApiStepDefinitions {
    PrivatApiHelper privatApiHelper = new PrivatApiHelper();

    @Given("I send GET request to PrivatBankApi and I save rates for {string} from response")
            public void iSendGetRequestToPrivatBankApiAndISaveRatesForFromResponse(String selectedCurrency) {
        privatApiHelper.getCurrencyRate(selectedCurrency);
    }
}
