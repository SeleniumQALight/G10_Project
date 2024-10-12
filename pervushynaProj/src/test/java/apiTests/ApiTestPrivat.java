package apiTests;

import apiPrivat.EndPointPrivat;
import apiPrivat.responseDtoPrivat.ExchangeRate;
import apiPrivat.responseDtoPrivat.ExchangeRateDto;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ApiTestPrivat {

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getPrivatBankExchangeRate() {
        ExchangeRateDto actualResponseAsDto =
                given()
                .queryParam("date", "22.03.2022")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointPrivat.EXCHANGE_RATE)
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().as(ExchangeRateDto.class);

          logger.info("Date = " + actualResponseAsDto.getDate());
          logger.info("Bank = " + actualResponseAsDto.getBank());
          logger.info("Base Currency = " + actualResponseAsDto.getBaseCurrency());
          logger.info("Base Currency Lit = " + actualResponseAsDto.getBaseCurrencyLit());



//        List<String> actualCurrencies = Arrays.stream(actualResponseAsDto.getExchangeRate())
//                .map(ExchangeRate::getCurrency)
//                .collect(Collectors.toList());
//
//        List<String> expectedCurrencies = Arrays.asList("USD", "EUR", "CHF", "GBP", "SEK", "CAD");
//
//        SoftAssertions softAssertions = new SoftAssertions();
//
//        for (String expectedCurrency : expectedCurrencies) {
//            softAssertions
//                    .assertThat(actualCurrencies)
//                    .as("Currency " + expectedCurrency + " should be present in the exchange rates")
//                    .contains(expectedCurrency);
//
//
//            softAssertions.assertAll();
//
//        }

//        // Expected result
        ExchangeRateDto expectedResponseAsDto = new ExchangeRateDto(
                "22.03.2022",
                "PB",
                980,
                "UAH",
                new ExchangeRate[]{
                        new ExchangeRate("UAH", "AUD"),
                        new ExchangeRate("UAH", "AZN"),
                        new ExchangeRate("UAH", "BYN"),
                        new ExchangeRate("UAH", "CAD"),
                        new ExchangeRate("UAH", "CHF"),
                        new ExchangeRate("UAH", "CNY"),
                        new ExchangeRate("UAH", "CZK"),
                        new ExchangeRate("UAH", "DKK"),
                        new ExchangeRate("UAH", "EUR"),
                        new ExchangeRate("UAH", "GBP"),
                        new ExchangeRate("UAH", "GEL"),
                        new ExchangeRate("UAH", "HUF"),
                        new ExchangeRate("UAH", "ILS"),
                        new ExchangeRate("UAH", "JPY"),
                        new ExchangeRate("UAH", "KZT"),
                        new ExchangeRate("UAH", "MDL"),
                        new ExchangeRate("UAH", "NOK"),
                        new ExchangeRate("UAH", "PLN"),
                        new ExchangeRate("UAH", "SEK"),
                        new ExchangeRate("UAH", "SGD"),
                        new ExchangeRate("UAH", "TMT"),
                        new ExchangeRate("UAH", "TRY"),
                        new ExchangeRate("UAH", "UAH"),
                        new ExchangeRate("UAH", "USD"),
                        new ExchangeRate("UAH", "UZS")
                }
        );


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualResponseAsDto.getExchangeRate())
                .usingRecursiveComparison()
                .ignoringFields("saleRateNB", "purchaseRateNB", "saleRate", "purchaseRate")
                   .isEqualTo(expectedResponseAsDto.getExchangeRate());

        softAssertions.assertAll();

  }
}


