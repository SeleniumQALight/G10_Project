package apiTests.HW;

import api.HWEndpointsAndDTO.ApiHelperBooks;
import api.HWEndpointsAndDTO.BooksEndPoints;
import api.dto.requestDto.AddBookDto;
import api.dto.requestDto.BooksIsbnDto;
import api.dto.responseDto.BooksListDto;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;


public class AddBookByApiTest {
    ApiHelperBooks apiHelperBooks = new ApiHelperBooks();
    String token;
    String userId;
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void getTokenByLogin(){
        token = apiHelperBooks.getToken();
        userId = apiHelperBooks.getUserId("mdai", "123456Qwerty!");

    }

    @Test
    public void addBookToUser(){
        apiHelperBooks.deleteAllBooksTillPresent(userId, token);
        BooksListDto actualResponseAsDto =
                given()
                        .spec(ApiHelperBooks.requestSpecificationWithToken(token))
                        .get(BooksEndPoints.ALL_BOOKS)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().body().as(BooksListDto.class);

        logger.info("Book isbn is " + actualResponseAsDto.getBooks().get(0).getIsbn());
        String bookIsbn = actualResponseAsDto.getBooks().get(0).getIsbn();

        AddBookDto addBookDtoBody = AddBookDto.builder()
                .userId(userId)
                .collectionOfIsbns(Arrays.asList(BooksIsbnDto.builder().isbn(bookIsbn).build()))
                .build();

        given()
                .spec(ApiHelperBooks.requestSpecificationWithToken(token))
                .body(addBookDtoBody)
                .post(BooksEndPoints.ALL_BOOKS)
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Response bookIsbnResponse=
                given()
                        .queryParam("userId", userId)
                        .spec(ApiHelperBooks.requestSpecificationWithToken(token))
                        .get(BooksEndPoints.ALL_BOOKS)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().response();

        boolean isBookIsbnPresent = bookIsbnResponse.jsonPath().getString("books.isbn").contains(bookIsbn);
        Assert.assertTrue(("Book with isbn " + bookIsbn + " was not added to user with user Id " + userId), isBookIsbnPresent);

        logger.info("Book with isbn " + bookIsbn + " was added to user with user Id " + userId);


    }
}
