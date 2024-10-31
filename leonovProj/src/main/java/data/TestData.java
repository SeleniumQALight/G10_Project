package data;


import api.dto.responseDto.CurrencyRateDto;
import api.dto.responseDto.ExchangeRateDto;

import java.util.List;

import static utils.ConfigProvider.*;

public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin", configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";
    public static final String INVALID_LOGIN_UI = "invalidLogin";
    public static final String INVALID_PASSWORD_UI = "invalidPassword";
    public static final String invalidCredentialsMessage = "Invalid username/password.";

    public static final String VALID_LOGIN_API = "denysleonov";
    public static final String VALID_PASSWORD_API = "123456qwerty";

    public static final String VALID_LOGIN_BOOK = "denystest1";
    public static final String VALID_PASSWORD_BOOK = "Qwerty123456!";

    public static final ExchangeRateDto exchangeRateDto = ExchangeRateDto.builder()
            .bank("PB")
            .date("22.03.2022")
            .baseCurrency(980)
            .baseCurrencyLit("UAH")
            .exchangeRate(List.of(
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("AUD")
                            .saleRateNB(21.261)
                            .purchaseRateNB(21.261)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("AZN")
                            .saleRateNB(16.777)
                            .purchaseRateNB(16.777)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("BYN")
                            .saleRateNB(11.0263)
                            .purchaseRateNB(11.0263)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("CAD")
                            .saleRateNB(23.0453)
                            .purchaseRateNB(23.0453)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("CHF")
                            .saleRateNB(31.8074)
                            .purchaseRateNB(31.8074)
                            .saleRate(32.83)
                            .purchaseRate(31.23)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("CNY")
                            .saleRateNB(4.6305)
                            .purchaseRateNB(4.6305)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("CZK")
                            .saleRateNB(1.3544)
                            .purchaseRateNB(1.3544)
                            .saleRate(1.4)
                            .purchaseRate(1.2)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("DKK")
                            .saleRateNB(4.4592)
                            .purchaseRateNB(4.4592)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("EUR")
                            .saleRateNB(33.1707)
                            .purchaseRateNB(33.1707)
                            .saleRate(32.75)
                            .purchaseRate(32.15)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("GBP")
                            .saleRateNB(39.7442)
                            .purchaseRateNB(39.7442)
                            .saleRate(40.4)
                            .purchaseRate(38.4)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("GEL")
                            .saleRateNB(9.3404)
                            .purchaseRateNB(9.3404)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("HUF")
                            .saleRateNB(0.092861)
                            .purchaseRateNB(0.092861)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("ILS")
                            .saleRateNB(9.0809)
                            .purchaseRateNB(9.0809)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("JPY")
                            .saleRateNB(0.25418)
                            .purchaseRateNB(0.25418)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("KZT")
                            .saleRateNB(0.066919)
                            .purchaseRateNB(0.066919)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("MDL")
                            .saleRateNB(1.6295)
                            .purchaseRateNB(1.6295)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("NOK")
                            .saleRateNB(3.3069)
                            .purchaseRateNB(3.3069)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("PLN")
                            .saleRateNB(7.296)
                            .purchaseRateNB(7.296)
                            .saleRate(7.12)
                            .purchaseRate(6.82)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("SEK")
                            .saleRateNB(3.1421)
                            .purchaseRateNB(3.1421)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("SGD")
                            .saleRateNB(21.7525)
                            .purchaseRateNB(21.7525)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("TMT")
                            .saleRateNB(8.1301)
                            .purchaseRateNB(8.1301)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("TRY")
                            .saleRateNB(2.1154)
                            .purchaseRateNB(2.1154)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("UAH")
                            .saleRateNB(29.2549)
                            .purchaseRateNB(29.2549)
                            .saleRate(29.5474)
                            .purchaseRate(29.2549)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("USD")
                            .saleRateNB(29.2549)
                            .purchaseRateNB(29.2549)
                            .saleRate(29.5474)
                            .purchaseRate(29.2549)
                            .build(),
                    CurrencyRateDto.builder()
                            .baseCurrency("UAH")
                            .currency("UZS")
                            .saleRateNB(0.0026336)
                            .purchaseRateNB(0.0026336)
                            .build()
            ))
            .build();

}
