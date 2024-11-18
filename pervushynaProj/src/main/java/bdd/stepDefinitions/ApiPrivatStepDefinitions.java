package bdd.stepDefinitions;

import apiPrivat.ApiHelperPrivat;
import io.cucumber.java.en.Given;


public class ApiPrivatStepDefinitions {

    ApiHelperPrivat apiHelperPrivat = new ApiHelperPrivat();


    @Given("I send request and save the API buy and sale rates for {string}")
    public void iSendRequestAndSaveTheApiBuyAndSaleRates(String currency) {

        apiHelperPrivat.getISendRequestAndSaveTheApiBuyAndSaleRates(currency, 0.0, 0.0);
    }

}
