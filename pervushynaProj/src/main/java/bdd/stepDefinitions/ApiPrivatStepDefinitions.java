package bdd.stepDefinitions;

import apiPrivat.ApiHelperPrivat;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;


public class ApiPrivatStepDefinitions {

    ApiHelperPrivat apiHelperPrivat = new ApiHelperPrivat();
    String currency;

    @Given("I send request to Privat Bank API to receive exchange rates")
    public void iSendRequestToPrivatBankAPIToReceiveExchangeRates() {

            apiHelperPrivat.getPrivatBankCurrencyRate(currency);

}

    @Given("I save the API buy and sale rates for {string}")
    public void i_save_the_api_buy_and_sale_rates_for(String string) {

        apiHelperPrivat.getI_save_the_api_buy_and_sale_rates_for(currency, 0.0, 0.0);
    }

}
