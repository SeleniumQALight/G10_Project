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
                .queryParam("date", "01.12.2014")
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



        List<String> actualCurrencies = Arrays.stream(actualResponseAsDto.getExchangeRate())
                .map(ExchangeRate::getCurrency)
                .collect(Collectors.toList());

        List<String> expectedCurrencies = Arrays.asList("USD", "EUR", "CHF", "GBP", "PLZ", "SEK", "CAD");

        SoftAssertions softAssertions = new SoftAssertions();

        for (String expectedCurrency : expectedCurrencies) {
            softAssertions
                    .assertThat(actualCurrencies)
                    .as("Currency " + expectedCurrency + " should be present in the exchange rates")
                    .contains(expectedCurrency);


            softAssertions.assertAll();

        }


//        SoftAssertions softAssertionsTwo = new SoftAssertions();
//        softAssertions
//                .assertThat(actualResponseAsDto.getExchangeRate())
//                .usingRecursiveComparison()
//                .ignoringFields("saleRateNB", "purchaseRateNB", "saleRate", "purchaseRate")
//                   .isEqualTo(());
//
//        softAssertions.assertAll();
//
  }
}


