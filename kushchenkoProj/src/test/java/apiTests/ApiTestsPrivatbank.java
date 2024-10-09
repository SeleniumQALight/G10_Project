package apiTests;

import apiPrivatbank.EndPointsPrivatbank;
import apiPrivatbank.dto.reqsponseDto.ExchangeRate;
import apiPrivatbank.dto.reqsponseDto.ExchangeRateResponse;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTestsPrivatbank {
    private final String EXCHANGE_RATE_DATE = "22.03.2022";
    private final static String BASE_CURRENCY = "UAH";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void checkExchangeRatesByDate() {
        ExchangeRateResponse actualResponseAsDto =
                given()
                        .queryParam("date", EXCHANGE_RATE_DATE)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPointsPrivatbank.EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().body().as(ExchangeRateResponse.class);
        logger.info(actualResponseAsDto);

        ExchangeRateResponse expectedResponseDto = new ExchangeRateResponse(
                "22.03.2022", "PB", 980, "UAH",
                List.of(new ExchangeRate(BASE_CURRENCY, "AUD"),
                        new ExchangeRate(BASE_CURRENCY, "AZN"),
                        new ExchangeRate(BASE_CURRENCY, "BYN"),
                        new ExchangeRate(BASE_CURRENCY, "CAD"),
                        new ExchangeRate(BASE_CURRENCY, "CHF"),
                        new ExchangeRate(BASE_CURRENCY, "CNY"),
                        new ExchangeRate(BASE_CURRENCY, "CZK"),
                        new ExchangeRate(BASE_CURRENCY, "DKK"),
                        new ExchangeRate(BASE_CURRENCY, "EUR"),
                        new ExchangeRate(BASE_CURRENCY, "GBP"),
                        new ExchangeRate(BASE_CURRENCY, "GEL"),
                        new ExchangeRate(BASE_CURRENCY, "HUF"),
                        new ExchangeRate(BASE_CURRENCY, "ILS"),
                        new ExchangeRate(BASE_CURRENCY, "JPY"),
                        new ExchangeRate(BASE_CURRENCY, "KZT"),
                        new ExchangeRate(BASE_CURRENCY, "MDL"),
                        new ExchangeRate(BASE_CURRENCY, "NOK"),
                        new ExchangeRate(BASE_CURRENCY, "PLN"),
                        new ExchangeRate(BASE_CURRENCY, "SEK"),
                        new ExchangeRate(BASE_CURRENCY, "SGD"),
                        new ExchangeRate(BASE_CURRENCY, "TMT"),
                        new ExchangeRate(BASE_CURRENCY, "TRY"),
                        new ExchangeRate(BASE_CURRENCY, "UAH"),
                        new ExchangeRate(BASE_CURRENCY, "USD"),
                        new ExchangeRate(BASE_CURRENCY, "UZS")));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResponseDto);
        softAssertions.assertAll();
    }

}
