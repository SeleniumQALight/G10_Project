package apiPrivat;

import apiPrivat.responseDtoPrivat.CurrencyRateDto;
import data.TestDataPrivat;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Getter;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;

public class ApiHelperPrivat {

    @Getter
    TestDataPrivat testDataPrivat;
    Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(SC_OK)
            .build();

    public void getISendRequestAndSaveTheApiBuyAndSaleRates(String currency, double uiBuyRate, double uiSaleRate) {
        CurrencyRateDto[] actualResponseAsDto =
                given()
                        .queryParam("coursid", "5")
                        .spec(requestSpecification)
                        .when()
                        .get(EndPointPrivat.PUBINFO)
                        .then()
                        .spec(responseSpecification)
                        .extract().body().as(CurrencyRateDto[].class);

//        [
//        {
//            "ccy": "EUR",
//                "base_ccy": "UAH",
//                "buy": "44.40000",
//                "sale": "45.40000"
//        },
//        {
//            "ccy": "USD",
//                "base_ccy": "UAH",
//                "buy": "41.05000",
//                "sale": "41.65000"
//        }
//]

        // знайди в масиві валюту currency
        // зберижи buyRate в тестДату
        // зберижи saleRate в тестДату

        for (CurrencyRateDto currencyRateDto : actualResponseAsDto) {
            if (currencyRateDto.getCcy().equalsIgnoreCase(currency)) {
                TestDataPrivat.apiBuyRate = Double.parseDouble(currencyRateDto.getBuy());
                TestDataPrivat.apiSaleRate = Double.parseDouble(currencyRateDto.getSale());
            }
        }
        logger.info("API buy rate: " + TestDataPrivat.apiBuyRate);
        logger.info("API sale rate: " + TestDataPrivat.apiSaleRate);
    }

}



