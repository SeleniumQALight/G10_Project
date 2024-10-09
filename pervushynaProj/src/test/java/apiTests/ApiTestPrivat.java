package apiTests;

import apiPrivat.EndPointPrivat;
import apiPrivat.responseDtoPrivat.ExchangeRateDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

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
          logger.info("Exchange Rate = " + actualResponseAsDto.getExchangeRate());


    }
}
