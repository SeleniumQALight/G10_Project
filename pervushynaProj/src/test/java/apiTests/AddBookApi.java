package apiTests;

import apiDemoQA.ApiHelperDemo;
import apiDemoQA.EndPointsDemo;
import apiDemoQA.dto.requestDtoDemo.AddBookDto;
import apiDemoQA.dto.requestDtoDemo.BookIsbnDto;
import apiDemoQA.dto.responseDtoDemo.BookListDto;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

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

       apiHelperDemo.DeleteAllBooks();

        BookListDto actualResponseAsDto =
                given()
                        .spec(ApiHelperDemo.requestSpecificationWithToken(token))
                        .get(EndPointsDemo.ACTIVITIES_WITH_BOOKS)
                        .then()
                        .statusCode(200)
                        .extract().body().as(BookListDto.class);

        logger.info("Book isbn = " + actualResponseAsDto.getBooks().get(0).getIsbn());
        String bookIsbn = actualResponseAsDto.getBooks().get(0).getIsbn();


        AddBookDto addBookDtoBody = AddBookDto.builder()
                .userId(userId)
                .collectionOfIsbns(Arrays.asList(BookIsbnDto.builder().isbn(bookIsbn).build()))
                .build();

        given()
                .spec(ApiHelperDemo.requestSpecificationWithToken(token))
                .body(addBookDtoBody)
                .post(EndPointsDemo.ACTIVITIES_WITH_BOOKS)
                .then()
                .statusCode(201);

        Response bookIsbnResponse =
                given()
                        .queryParam("UserId", userId)
                        .spec(ApiHelperDemo.requestSpecificationWithToken(token))
                        .get(EndPointsDemo.ACTIVITIES_WITH_BOOKS)
                        .then()
                        .statusCode(200)
                        .extract().response();

        boolean isBookIsbnPresent = bookIsbnResponse.jsonPath().getString("books.isbn").contains(bookIsbn);
        Assert.assertTrue("Book with isbn = " + bookIsbn + " was added to user with id = " + userId, isBookIsbnPresent);

        logger.info("Book with isbn = " + bookIsbn + " was added to user with id = " + userId);

}

}
