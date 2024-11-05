package apiPrivatbank;

public interface EndPointsPrivatbank {
    String BASE_URL = "https://api.privatbank.ua/p24api";
    String EXCHANGE_RATES = BASE_URL + "/exchange_rates";
    String CURRENCY_EXCHANGE_RATES = BASE_URL + "/pubinfo?json&exchange&coursid=5";
}
