package api;

public interface DemoQaEndPoints {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";
    String BOOKS = BASE_URL + "/BookStore/v1/Books";
    String GET_ACCOUNT_USER = BASE_URL + "/Account/v1/User/{0}";
}
