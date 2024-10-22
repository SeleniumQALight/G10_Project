package apiDemoQa;

public interface EndPointsDemoQa {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";
    String DELETE_BOOKS_BY_USER = BASE_URL + "/BookStore/v1/Books?UserId={0}";
    String BOOK_STORE = BASE_URL + "/BookStore/v1/Books";
}

// https://demoqa.com/BookStore/v1/Books?UserId={userId}
