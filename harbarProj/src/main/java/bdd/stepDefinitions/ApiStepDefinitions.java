package bdd.stepDefinitions;

import api.ApiHelper;
import api.PrivatBankEndPoints;
import api.exchangeRateDto.responseDto.CurrencyExchangeRateResponse;
import data.TestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static api.ApiHelper.requestSpecification;
import static api.ApiHelper.responseSpecification;
import static bdd.stepDefinitions.MainSteps.DEFAULT;
import static io.restassured.RestAssured.given;

public class ApiStepDefinitions {
    ApiHelper apiHelper = new ApiHelper();

    @Given("I create {} new posts via API for {string} user and {string} password")
    public void iCreateNewPostsViaAPIForDefaultUserAndDefaultPassword(Integer numberOfPosts, String userName, String password, DataTable dataTable) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;
        }
        String token = apiHelper.getToken(userName, password);
        apiHelper.createPosts(numberOfPosts, token, dataTable.asMap());

    }

    @Given("Given I request the exchange rate for {} from the PrivatBank API")
    public void givenIRequestTheExchangeRateForFromThePrivatBankAPI(String currency) {
        CurrencyExchangeRateResponse[] actualResponse =
                given()
                        .spec(requestSpecification)
                        .when()
                        .get(PrivatBankEndPoints.PUBLIC_EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .spec(responseSpecification)
                        .extract().response().body().as(CurrencyExchangeRateResponse[].class);

        CurrencyExchangeRateResponse currencyResponse = Arrays.stream(actualResponse).filter(rate -> rate.getCcy().equalsIgnoreCase(currency))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Currency %s not found".formatted(currency)));

        BigDecimal currencyBuyRate = BigDecimal.valueOf(currencyResponse.getBuy()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currencySellRate = BigDecimal.valueOf(currencyResponse.getSale()).setScale(2, RoundingMode.HALF_UP);

        TestData.apiRates.put(currencySellRate.doubleValue(), currencyBuyRate.doubleValue());

    }
}
