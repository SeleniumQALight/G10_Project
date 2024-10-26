package apiDemoQa;

public interface EndPointsDemoQa {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";
    String BOOK_STORE_ACTIONS = BASE_URL + "/BookStore/v1/Books";
    String GET_USER_ACCOUNT = BASE_URL + "/Account/v1/User/{0}";
}

