package apiPrivatbank;

import apiPrivatbank.dto.reqsponseDto.CurrencyExchangeRateResponse;
import data.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatbank {
    Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .log(LogDetail.ALL)
            .build();

    public void getCurrencyExchangeRates(String currency) {
        CurrencyExchangeRateResponse[] response = given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsPrivatbank.CURRENCY_EXCHANGE_RATES)
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .as(CurrencyExchangeRateResponse[].class);
        for (CurrencyExchangeRateResponse currencyExchangeRateResponse : response) {
            if (currencyExchangeRateResponse.getCcy().equals(currency)) {
                TestData.CURRENCY_RATE_API.put("buy", currencyExchangeRateResponse.getBuy());
                TestData.CURRENCY_RATE_API.put("sale", currencyExchangeRateResponse.getSale());
                logger.info("Currency exchange rate for " + currency + " are: buy - " + currencyExchangeRateResponse.getBuy() +
                        ", sale - " + currencyExchangeRateResponse.getSale());
            }
        }
    }
}
