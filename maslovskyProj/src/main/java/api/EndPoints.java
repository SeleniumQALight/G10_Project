package api;

public interface EndPoints {
    String BASE_URL = "https://aqa-complexapp.onrender.com";
    String POSTS_BY_USER = BASE_URL + "/api/postsByAuthor/{0}";

    String PB_URL = "https://api.privatbank.ua";
    String CURRENCY_EXCHANGE_RATE = PB_URL + "/p24api/exchange_rates";
    String CURRENCY_EXCHANGE_RATE_PUBLIC = PB_URL + "/p24api/pubinfo";

    String LOGIN = BASE_URL + "/api/login";
    String CREATE_POST = BASE_URL + "/api/create-post";
    String DELETE_POST = BASE_URL + "/api/post/{0}";

    String DEMO_QA_URL = "https://demoqa.com";
    String REGISTER_USER = DEMO_QA_URL + "/Account/v1/User";
    String GENERATE_TOKEN = DEMO_QA_URL + "/Account/v1/GenerateToken";
    String LOGIN_USER = DEMO_QA_URL + "/Account/v1/Login";
    String DELETE_USER = REGISTER_USER + "/{0}";
    String BOOKS_STORE = DEMO_QA_URL + "/BookStore/v1/Books";
    String BOOKS_OF_USER = DEMO_QA_URL + "/Account/v1/User/{0}";

}
