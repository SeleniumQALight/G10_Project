package apiTests;

import API.EndPoints;
import API.PrivatBantDTO.responseDTO.CurrencyRateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiTestPrivatBank {
    String CurrencyExchangeRateOnDate = "22.03.2022";
    String jsonSample = "src/main/java/data/exchange_rates.json";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void HW1_ApiCompareCurrencyExchangeRate() throws IOException {
        CurrencyRateDTO actualResponseAsCurrencyRateDTO =
                given()
                        .queryParam("date", CurrencyExchangeRateOnDate)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(EndPoints.CURRENCY_EXCHANGE_RATE)
                        .then()
                        .statusCode(200)
                        .extract().body().as(CurrencyRateDTO.class);
        logger.info("Number of currencies in response = " +
                actualResponseAsCurrencyRateDTO.getExchangeRate().size());

        ObjectMapper objectMapper = new ObjectMapper();
        CurrencyRateDTO expectedCurrencyRateDTO = objectMapper.readValue
                (new File(jsonSample), CurrencyRateDTO.class);
        logger.info("Number of currencies in sample = " +
                expectedCurrencyRateDTO.getExchangeRate().size());

        Assert.assertEquals("Number of currencies in 'ExchangeRate' does not match",
                actualResponseAsCurrencyRateDTO.getExchangeRate().size(),
                expectedCurrencyRateDTO.getExchangeRate().size());
        logger.info("Number of currencies matches");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualResponseAsCurrencyRateDTO)
                .usingRecursiveComparison()
                .isEqualTo(expectedCurrencyRateDTO);
        softAssertions.assertAll();
        logger.info("Actual Result Values match to expected results");
    }

    @Test
    public void HW2_ApiCurrencyExchangeIsMoreZero() {
        Float rateShouldBeMoreThan = 0.0f;
        List<Map<String, Float>> actualResponseAsCurrencyRate =
                given()
                        .queryParam("date", CurrencyExchangeRateOnDate)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(EndPoints.CURRENCY_EXCHANGE_RATE)
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getList("exchangeRate");

        SoftAssertions softAssertions = new SoftAssertions();
        for (Map<String, Float> part : actualResponseAsCurrencyRate) {
            for (String key : part.keySet()) {
                if (key.contains("Rate")) {
                    Float rateValue = part.get(key);
                    softAssertions
                            .assertThat(rateValue)
                            .as("Exchange rate %s (element %s) should be greater than %.1f",
                                    part.get("currency"), key, rateShouldBeMoreThan)
                            .isGreaterThan(rateShouldBeMoreThan);
                }
            }
            logger.info("Exchange rates of " + part.get("currency") + " have been checked");
        }
        softAssertions.assertAll();
    }

}
