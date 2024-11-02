package API.PrivatBantDTO;

import API.PrivatBantDTO.responseDTO.PrivatCurrencyRateDto;
import data.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class PrivatApiHelper {
    Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .log(LogDetail.ALL)
            .build();


    public void getCurrencyRate( String selectedCurrency) {
        PrivatCurrencyRateDto[] response = given()
                .spec(requestSpecification)
                .when()
                .get(PrivatEndPoints.CURRENCY_EXCHANGE)
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .as(PrivatCurrencyRateDto[].class);
        for (PrivatCurrencyRateDto currency : response) {
            if (selectedCurrency.equalsIgnoreCase(currency.getCcy())) {
                TestData.CURRENCY_RATE_API.put("buy", currency.getBuy());
                TestData.CURRENCY_RATE_API.put("sell", currency.getSale());
                logger.info("CURRENCY RATE API: " + TestData.CURRENCY_RATE_API);
            }
        }
    }


}
