package api;

public interface BookEndPoints {
    String BASE_URL = "https://demoqa.com";
    String LOGIN_URL = BASE_URL + "/Account/v1/Login";
    String ALL_BOOKS_URL = BASE_URL + "/BookStore/v1/Books";
    String USER_BOOKS_URL = BASE_URL + "/Account/v1/User/{0}";

}
