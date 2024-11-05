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

import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;

public class ApiHelperPrivat {

    @Getter
    TestDataPrivat testDataPrivat;


    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(SC_OK)
            .build();

    public void getPrivatBankCurrencyRate(String currency) {
        CurrencyRateDto[] actualResponseAsDto =
                given()
                        .queryParam("coursid", "5")
                        .spec(requestSpecification)
                        .when()
                        .get(EndPointPrivat.PUBINFO)
                        .then()
                        .spec(responseSpecification)
                        .extract().body().as(CurrencyRateDto[].class);

    }

    public void getI_save_the_api_buy_and_sale_rates_for(String currency, double uiBuyRate, double uiSaleRate) {
        CurrencyRateDto[] actualResponseAsDto =
                given()
                        .queryParam("coursid", "5")
                        .spec(requestSpecification)
                        .when()
                        .get(EndPointPrivat.PUBINFO)
                        .then()
                        .spec(responseSpecification)
                        .extract().body().as(CurrencyRateDto[].class);

        double apiBuyRate = Double.parseDouble(actualResponseAsDto[0].getBuy());
        double apiSaleRate = Double.parseDouble(actualResponseAsDto[0].getSale());
        testDataPrivat = new TestDataPrivat(currency, apiBuyRate, apiSaleRate, uiBuyRate, uiSaleRate);
    }

}



