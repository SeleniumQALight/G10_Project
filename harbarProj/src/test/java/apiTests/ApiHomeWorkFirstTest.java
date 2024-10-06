package apiTests;

import api.PrivatBankEndPoints;
import api.exchangeRateDto.responseDto.ExchangeRate;
import api.exchangeRateDto.responseDto.ExchangeRateResponse;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class ApiHomeWorkFirstTest {
    private final String EXCHANGE_RATE_DATE = "22.03.2022";
    private final static String BASE_CURRENCY = "UAH";

    @Test
    public void verifyExchangeRatesFields() {
        ExchangeRateResponse actualResponse =
                given()
                        .queryParam("date", EXCHANGE_RATE_DATE)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(PrivatBankEndPoints.EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().body().as(ExchangeRateResponse.class);

        ExchangeRateResponse expectedResponseDto = new ExchangeRateResponse(
                EXCHANGE_RATE_DATE, "PB", 980, "UAH",
                Arrays.asList(
                        new ExchangeRate(BASE_CURRENCY, "AUD", 21.2610000, 21.2610000),
                        new ExchangeRate(BASE_CURRENCY, "AZN", 16.7770000, 16.7770000),
                        new ExchangeRate(BASE_CURRENCY, "BYN", 11.0263000, 11.0263000),
                        new ExchangeRate(BASE_CURRENCY, "CAD", 23.0453000, 23.0453000),
                        new ExchangeRate(BASE_CURRENCY, "CHF", 31.8074000, 31.8074000, 32.8300000, 31.2300000),
                        new ExchangeRate(BASE_CURRENCY, "CNY", 4.6305000, 4.6305000),
                        new ExchangeRate(BASE_CURRENCY, "CZK", 1.3544000, 1.3544000, 1.4000000, 1.2000000),
                        new ExchangeRate(BASE_CURRENCY, "DKK", 4.4592000, 4.4592000),
                        new ExchangeRate(BASE_CURRENCY, "EUR", 33.1707000, 33.1707000, 32.7500000, 32.1500000),
                        new ExchangeRate(BASE_CURRENCY, "GBP", 39.7442000, 39.7442000, 40.4000000, 38.4000000),
                        new ExchangeRate(BASE_CURRENCY, "GEL", 9.3404000, 9.3404000),
                        new ExchangeRate(BASE_CURRENCY, "HUF", 0.0928610, 0.0928610),
                        new ExchangeRate(BASE_CURRENCY, "ILS", 9.0809000, 9.0809000),
                        new ExchangeRate(BASE_CURRENCY, "JPY", 0.2541800, 0.2541800),
                        new ExchangeRate(BASE_CURRENCY, "KZT", 0.0669190, 0.0669190),
                        new ExchangeRate(BASE_CURRENCY, "MDL", 1.6295000, 1.6295000),
                        new ExchangeRate(BASE_CURRENCY, "NOK", 3.3069000, 3.3069000),
                        new ExchangeRate(BASE_CURRENCY, "PLN", 7.2960000, 7.2960000, 7.1200000, 6.8200000),
                        new ExchangeRate(BASE_CURRENCY, "SEK", 3.1421000, 3.1421000),
                        new ExchangeRate(BASE_CURRENCY, "SGD", 21.7525000, 21.7525000),
                        new ExchangeRate(BASE_CURRENCY, "TMT", 8.1301000, 8.1301000),
                        new ExchangeRate(BASE_CURRENCY, "TRY", 2.1154000, 2.1154000),
                        new ExchangeRate(BASE_CURRENCY, "UAH", 1.0000000, 1.0000000),
                        new ExchangeRate(BASE_CURRENCY, "USD", 29.2549000, 29.2549000, 29.5474000, 29.2549000),
                        new ExchangeRate(BASE_CURRENCY, "UZS", 0.0026336, 0.0026336)
                )
        );

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualResponse)
                .as("Verifying exchange rates for date: " + EXCHANGE_RATE_DATE)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResponseDto);

        softAssertions.assertAll();
    }
}
