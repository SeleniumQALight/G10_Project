package apiTests;

import apiDemoQA.ApiHelperDemo;
import apiDemoQA.EndPointsDemo;
import apiDemoQA.dto.requestDtoDemo.AddBookDto;
import apiDemoQA.dto.responseDtoDemo.BookListDto;
import apiDemoQA.dto.responseDtoDemo.BookParamsDto;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;


public class AddBookApi {
    ApiHelperDemo apiHelperDemo = new ApiHelperDemo();
    String token;
    String userId;
    Logger logger = Logger.getLogger(getClass());


    @Before
    public void getTokenAndId() {
        token = apiHelperDemo.getToken();
        System.out.println("Token from api = " + token);
        userId = apiHelperDemo.getUserId();
        System.out.println("User id from api = " + userId);

}

    @Test
    public void addBookToUserApi() {

                given()
                        .queryParam("UserId", userId)
                        .spec(ApiHelperDemo.requestSpecification)
                        .header("Authorization", "Bearer " + token)
                        .delete(EndPointsDemo.ACTIVITIES_WITH_BOOKS)
                        .then()
                        .statusCode(204);

        logger.info("All books are deleted");


        BookListDto actualResponseAsDto =
                given()
                        .spec(ApiHelperDemo.requestSpecification)
                        .when()
                        .header("Authorization", "Bearer" + token)
                        .get(EndPointsDemo.ACTIVITIES_WITH_BOOKS)
                        .then()
                        .statusCode(200)
                        .extract().body().as(BookListDto.class);


        logger.info("Book isbn = " + actualResponseAsDto.getBooks().get(0).getIsbn());
        String bookIsbn = actualResponseAsDto.getBooks().get(0).getIsbn();


//        AddBookDto addBookDtoBody = AddBookDto.builder()
//                .userId(userId)
//                .collectionOfIsbns(BookListDto.BookParamsDto.builder()
//                        .isbn(isbn)
//                        .build())
//                .build();

        given()
                .spec(ApiHelperDemo.requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body("{ \"userId\": \"" + userId + "\", \"collectionOfIsbns\": [{ \"isbn\": \"" + bookIsbn + "\" }] }")
                .post(EndPointsDemo.ACTIVITIES_WITH_BOOKS)
                .then()
                .statusCode(201);

        logger.info("Book is added to user");



}


}
