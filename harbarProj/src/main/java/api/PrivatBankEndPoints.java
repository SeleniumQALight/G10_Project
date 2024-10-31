package api;

public interface PrivatBankEndPoints {
    String BASE_URL = "https://api.privatbank.ua/p24";
    String EXCHANGE_RATES = BASE_URL + "api/exchange_rates";
    String PUBLIC_EXCHANGE_RATES = BASE_URL + "api/pubinfo?json&exchange&coursid=5";

}
