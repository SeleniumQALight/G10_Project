package apiTests;

import api.PbApiHelper;
import api.dto.responseDto.CurrencyRateDto;
import api.dto.responseDto.ExchangeRateDto;
import data.TestData;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.List;

public class CurrencyValidationTest {
    Logger logger = Logger.getLogger(getClass());
    PbApiHelper pbApiHelper = new PbApiHelper();

    @Test
    public void currencyValidationTest() {
        logger.info("Currency validation test started");

        ExchangeRateDto actualCurrency = pbApiHelper.getAllCurrencyRatesRequest("22.03.2022")
                .extract().body().as(ExchangeRateDto.class);

        SoftAssertions softAssertions = new SoftAssertions();

        // use GPT to get it faster than write it manually
        ExchangeRateDto expectedCurrency = TestData.exchangeRateDto;

        softAssertions
                .assertThat(actualCurrency)
                .usingRecursiveComparison()
                .ignoringFields(
                        "exchangeRate.saleRate", "exchangeRate.purchaseRate",
                        "exchangeRate.purchaseRateNB", "exchangeRate.saleRateNB")
                .isEqualTo(expectedCurrency);


        //the second (additional) part of HW1

        for (int i = 0; i < actualCurrency.getExchangeRate().size(); i++) {
            CurrencyRateDto o = actualCurrency.getExchangeRate().get(i);
            double saleRateNB = o.getSaleRateNB();
            Double saleRate = o.getSaleRate();
            double purchaseRateNB = o.getPurchaseRateNB();
            Double purchaseRate = o.getPurchaseRate();

            if (saleRate != null && purchaseRate != null) {
                softAssertions.assertThat(saleRate).isGreaterThan(0);
                softAssertions.assertThat(purchaseRate).isGreaterThan(0);
            }
            softAssertions.assertThat(saleRateNB).isGreaterThan(0);
            softAssertions.assertThat(purchaseRateNB).isGreaterThan(0);
        }

        softAssertions.assertAll();

        logger.info("Currency validation test finished");
    }

    @Test
    public void checkCurrencyValues() {

    }
}
