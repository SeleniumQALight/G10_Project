package apiTests.HW;

import api.HWEndpointsAndDTO.ExchangeRateDto;
import api.HWEndpointsAndDTO.PrivatBankEndpoints;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.apache.log4j.Logger;


import java.util.List;

import static io.restassured.RestAssured.given;

public class PrivatBankApiTests {

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getListOfCurrencies() {

        String date = "22.02.2022";

        ExchangeRateDto actualResponseAsDto =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("date", date)
                        .when()
                        .get(PrivatBankEndpoints.EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().body().as(ExchangeRateDto.class);

        logger.info("Date = " + actualResponseAsDto.getDate());
        logger.info("Bank = " + actualResponseAsDto.getBank());
        logger.info("Base Currency = " + actualResponseAsDto.getBaseCurrency());
        logger.info("Base Currency Lit = " + actualResponseAsDto.getBaseCurrencyLit());


        ExchangeRateDto expectedResponseDto = ExchangeRateDto.builder()
                .date(date)
                .bank("PB")
                .baseCurrency("980")
                .baseCurrencyLit("UAH")
                .exchangeRate(
                        List.of(

                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("AUD").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("AZN").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("BYN").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("CAD").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("CHF").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("CNY").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("CZK").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("DKK").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("EUR").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("GBP").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("GEL").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("HUF").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("ILS").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("JPY").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("KZT").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("MDL").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("NOK").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("PLN").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("SEK").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("SGD").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("TMT").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("TRY").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("UAH").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("USD").build(),
                                ExchangeRateDto.ExchangeRate.builder().baseCurrency("UAH").currency("UZS").build()

                        )
                )
                .build();


        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponseAsDto.getExchangeRate())
                .usingRecursiveComparison()
                .ignoringFields("saleRateNB", "purchaseRateNB", "saleRate", "purchaseRate")
                .isEqualTo(expectedResponseDto.getExchangeRate());


        softAssertions.assertAll();
    }
}



