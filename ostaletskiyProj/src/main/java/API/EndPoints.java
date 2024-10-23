package API;

import java.net.URI;

public interface EndPoints {
    String BASE_URL = "https://aqa-complexapp.onrender.com";
    String POSTS_BY_USER = BASE_URL + "/api/postsByAuthor/{0}";
    String PB_URL = "https://api.privatbank.ua";
    String CURRENCY_EXCHANGE_RATE = PB_URL + "/p24api/exchange_rates";


    String LOGIN = BASE_URL + "/api/login";
    String CREATE_POST = BASE_URL + "/api/create-post" ;
    String DELETE_POST = BASE_URL + "/api/post/{0}";
}
