package apiTests;

import api.PrivatBankEndPoints;
import api.exchangeRateDto.responseDto.ExchangeRateResponse;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiHomeWorkFirstAdditionalTest {
    private final String EXCHANGE_RATE_DATE = "22.03.2022";

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

        SoftAssertions softAssertions = new SoftAssertions();

        actualResponse.getExchangeRate()
                .forEach(rate -> {
                    softAssertions.assertThat(rate.getSaleRateNB())
                            .as("Check saleRateNB for currency: " + rate.getCurrency())
                            .isGreaterThan(0);
                    softAssertions.assertThat(rate.getPurchaseRateNB())
                            .as("Check purchaseRateNB for currency: " + rate.getCurrency())
                            .isGreaterThan(0);
                    if (rate.getSaleRate() != null) {
                        softAssertions.assertThat(rate.getSaleRate())
                                .as("Check saleRate for currency: " + rate.getCurrency())
                                .isGreaterThan(0);
                    }
                    if (rate.getPurchaseRate() != null) {
                        softAssertions.assertThat(rate.getPurchaseRate())
                                .as("Check purchaseRate 0 for currency: " + rate.getCurrency())
                                .isGreaterThan(0);
                    }
                });

        softAssertions.assertAll();
    }
}
