package apiPrivat;


import java.net.URI;

public interface EndPointPrivat {

    String BASE_URL = "https://api.privatbank.ua";
    String EXCHANGE_RATE = BASE_URL + "/p24api/exchange_rates";

    String PUBINFO = BASE_URL + "/p24api/pubinfo?json&exchange";
}
