package apiTestPrivatBank;

import api.EndPoints;
import api.dtoPrivatBank.responseDto.CurrencyRateDto;
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
    String currencyExchangeRateOnDate = "22.03.2022";
    String jsonSample = "src/main/java/data/exchange_rates.json";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void hw1ApiCompareCurrencyExchangeRateToSample() throws IOException {
        CurrencyRateDto actualResponseAsCurrencyRateDto =
                given()
                        .queryParam("date", currencyExchangeRateOnDate)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(EndPoints.CURRENCY_EXCHANGE_RATE)
                        .then()
                        .statusCode(200)
                        .extract().body().as(CurrencyRateDto.class);
        logger.info("Number of currencies in response = " +
                actualResponseAsCurrencyRateDto.getExchangeRate().size());

        ObjectMapper objectMapper = new ObjectMapper();
        CurrencyRateDto expectedCurrencyRateDto = objectMapper.readValue
                (new File(jsonSample), CurrencyRateDto.class);
        logger.info("Number of currencies in sample = " +
                expectedCurrencyRateDto.getExchangeRate().size());

        Assert.assertEquals("number of currencies in 'exchangeRate' doesn't match",
                actualResponseAsCurrencyRateDto.getExchangeRate().size(),
                expectedCurrencyRateDto.getExchangeRate().size());
        logger.info("number of currencies in 'exchangeRate' match");

        Assert.assertEquals("values of 'date' don't match",
                actualResponseAsCurrencyRateDto.getDate(),
                expectedCurrencyRateDto.getDate());
        logger.info("values of 'date' match");

        Assert.assertEquals("values of 'bank' don't match",
                actualResponseAsCurrencyRateDto.getBank(),
                expectedCurrencyRateDto.getBank());
        logger.info("values of 'bank' match");

        Assert.assertEquals("values of 'baseCurrency' don't match",
                actualResponseAsCurrencyRateDto.getBaseCurrency(),
                expectedCurrencyRateDto.getBaseCurrency());
        logger.info("values of 'baseCurrency' match");

        Assert.assertEquals("values of 'baseCurrencyLit' don't match",
                actualResponseAsCurrencyRateDto.getBaseCurrencyLit(),
                expectedCurrencyRateDto.getBaseCurrencyLit());
        logger.info("values of 'baseCurrencyLit' match");

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < actualResponseAsCurrencyRateDto.getExchangeRate().size(); i++) {
            softAssertions
                    .assertThat(actualResponseAsCurrencyRateDto
                            .getExchangeRate().get(i)
                            .getBaseCurrency())
                    .isEqualTo(expectedCurrencyRateDto
                            .getExchangeRate().get(i)
                            .getBaseCurrency());
            softAssertions
                    .assertThat(actualResponseAsCurrencyRateDto
                            .getExchangeRate().get(i)
                            .getCurrency())
                    .isEqualTo(expectedCurrencyRateDto
                            .getExchangeRate().get(i)
                            .getCurrency());
        }
        softAssertions.assertAll();
        logger.info("values of 'BaseCurrency' match");
        logger.info("values of 'ExchangeRate' match");
    }

    @Test
    public void hw1ApiCurrencyExchangeRateIsMoreZero() {
        Float rateShouldBeMoreThan = 0.0f;
        List<Map<String, Float>> actualResponseAsCurrencyRate =
                given()
                        .queryParam("date", currencyExchangeRateOnDate)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(EndPoints.CURRENCY_EXCHANGE_RATE)
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getList("exchangeRate")
                ;

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
