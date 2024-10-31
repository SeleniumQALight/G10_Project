package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;

public class CurrencyPrivatBankUI extends MainSteps {

    public CurrencyPrivatBankUI(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @And("I retrieve {string} exchange rates from UI")
    public void iRetrieveCurrencyExchangeRatesFromUI(String currency) {
        pageProvider
                .getPrivatBankPage()
                .openPrivatBankPage()
                .openCurrencyPopUp()
                .selectRateType()
                .readCurrencyRates(currency);
    }

}
