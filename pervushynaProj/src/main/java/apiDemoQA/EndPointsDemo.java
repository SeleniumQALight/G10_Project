package apiDemoQA;

public interface EndPointsDemo {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";

    String DELETE_BOOK = BASE_URL + "/BookStore/v1/Book{0}";
}
