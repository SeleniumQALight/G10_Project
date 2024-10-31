package bdd.stepDefinitions;

import api.EndPoints;
import api.dtoPrivatBank.responseDto.ExchangePublicRateDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Assert;

import static data.PrivatBankCurrencyExchangeData.*;
import static io.restassured.RestAssured.given;
import static pages.CommonActionsWithElements.roundToTwoDigits;

public class CurrencyPrivatBankAPI {
    Logger logger = Logger.getLogger(getClass());

    @Given("I retrieve {string} exchange rates via API")
    public void iRetrieveCurrencyExchangeRatesViaAPI(String currency) {
        ExchangePublicRateDto[] exchangePublicRateDtos = given()
                .queryParam("json", "", "exchange", "", "coursid", "5")
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.CURRENCY_EXCHANGE_RATE_PUBLIC)
                .then()
                .statusCode(200)
                .extract().body()
                .as(ExchangePublicRateDto[].class);

        for (ExchangePublicRateDto exchangePublicRateDto : exchangePublicRateDtos) {
            if (exchangePublicRateDto.getCcy().equals(currency)) {
                currencyBuyRateAPI = exchangePublicRateDto.getBuy();
                currencySellRateAPI = exchangePublicRateDto.getSale();
            }
        }
        logger.info("Currency exchange rates were retrieved via API");

    }

    @Then("I compare currency exchange rates from API with rate displayed on UI")
    public void iCompareCurrencyExchangeRatesFromAPIWithRateDisplayedOnUI() {
        Assert.assertEquals("Currency buying rates don't match",
                roundToTwoDigits(currencyBuyRateAPI),
                roundToTwoDigits(currencyBuyRateUI));
        logger.info("Currency buying rates match");

        Assert.assertEquals("Currency selling rates don't match",
                roundToTwoDigits(currencySellRateAPI),
                roundToTwoDigits(currencySellRateUI));
        logger.info("Currency selling rates match");
    }

}
