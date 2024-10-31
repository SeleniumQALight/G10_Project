package api.HWEndpointsAndDTO;

public interface BooksEndPoints {
    String BASE_URL = "https://demoqa.com";
    String LOGIN =  BASE_URL + "/Account/v1/Login";
    String ALL_BOOKS = BASE_URL + "/BookStore/v1/Books";
    String DELETE_BOOK = BASE_URL + "/BookStore/v1/Books?UserId={userId}";

}
