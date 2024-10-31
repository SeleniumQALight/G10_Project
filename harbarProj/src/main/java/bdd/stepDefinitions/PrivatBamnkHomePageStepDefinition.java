package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import static org.junit.Assert.assertEquals;

public class PrivatBamnkHomePageStepDefinition extends MainSteps {
    public PrivatBamnkHomePageStepDefinition(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }
    private Logger logger = Logger.getLogger(getClass());

    @When("I get the exchange rate for {} from the PrivatBank homepage")
    public void iGetTheExchangeRateForFromThePrivatBankHomepage(String currency) {
        pageProvider.getPrivatBankHomePage().openPrivatBankHomePage();
        pageProvider.getPrivatBankHomePage().clickOnCurrencyRateButton();
        String buyCurrencyRate = pageProvider.getPrivatBankHomePage().getBuyCurrencyRate(currency);
        String sellCurrencyRate = pageProvider.getPrivatBankHomePage().getSellCurrencyRate(currency);

        TestData.uiRates.put(Double.valueOf(sellCurrencyRate), Double.valueOf(buyCurrencyRate));

    }

    @Then("The exchange rate from the API match the rate from the UI")
    public void theExchangeRateFromTheAPIMatchTheRateFromTheUI() {
        logger.info("Verify exchange rate from the API " + TestData.apiRates +" match the rate from the UI "+ TestData.uiRates);
        assertEquals("Rates for currency", TestData.apiRates, TestData.uiRates);
    }
}
