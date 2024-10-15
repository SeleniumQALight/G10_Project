package apiTests;

import api.DemoQaEndPoints;
import api.demoQaDto.requestDto.AddBookRequestDto;
import api.demoQaDto.requestDto.IsbnRequestDto;
import api.demoQaDto.responseDto.BooksResponseDto;
import api.demoQaDto.responseDto.LoginDto;
import api.demoQaDto.responseDto.ProfileResponseDto;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;

import static api.ApiHelper.requestSpecification;
import static api.ApiHelper.responseSpecification;
import static data.TestData.DEMO_QA_VALID_PASSWORD_API;
import static data.TestData.DEMO_QA_VALID_USERNAME_API;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiAddBookHomeWorkTest {
    private final Logger logger = Logger.getLogger(getClass());
    private static LoginDto loginResponse;

    @Before
    public void getUserLoginData() {
        logger.info("Get login data for user");
        HashMap<String, String> loginBodyRequest = new HashMap<>();
        loginBodyRequest.put("userName", DEMO_QA_VALID_USERNAME_API);
        loginBodyRequest.put("password", DEMO_QA_VALID_PASSWORD_API);

        loginResponse = given()
                .spec(requestSpecification)
                .body(loginBodyRequest)
                .when()
                .post(DemoQaEndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().body().as(LoginDto.class);
    }

    @Test
    public void addBookByApi() {
        logger.info("Delete all user books");
        authenticatedRequest()
                .queryParam("UserId",loginResponse.getUserId() )
                .delete(DemoQaEndPoints.BOOKS)
                .then()
                .spec(responseSpecification.statusCode(SC_NO_CONTENT));

        logger.info("Get list of all books");
        BooksResponseDto booksResponseDto = given()
                .spec(requestSpecification)
                .when()
                .get(DemoQaEndPoints.BOOKS)
                .then()
                .spec(responseSpecification.statusCode(SC_OK))
                .extract().response().body().as(BooksResponseDto.class);

        logger.info("Get ISBN of first book from list");
        String firstBookIsbn = booksResponseDto.getBooks().get(0).getIsbn();

        logger.info("Create request dto for add book");
        AddBookRequestDto addBookRequestBody = AddBookRequestDto.builder()
                .userId(loginResponse.getUserId())
                .collectionOfIsbns(Collections.singletonList(IsbnRequestDto.builder()
                        .isbn(firstBookIsbn)
                        .build()))
                .build();

        logger.info("Add book with isnb " + firstBookIsbn + " and verify isnb in response");
        authenticatedRequest()
                .body(addBookRequestBody)
                .when()
                .post(DemoQaEndPoints.BOOKS)
                .then()
                .spec(responseSpecification.statusCode(SC_CREATED))
                .body("books[0].isbn", equalTo(firstBookIsbn))
                .extract().response();

        logger.info("Verify profile has added book with isbn " + firstBookIsbn);
        ProfileResponseDto profileResponse = authenticatedRequest()
                .when()
                .get(DemoQaEndPoints.GET_ACCOUNT_USER, loginResponse.getUserId())
                .then()
                .spec(responseSpecification.statusCode(SC_OK))
                .extract().response().body().as(ProfileResponseDto.class);

        Assert.assertEquals("Profile has added book isdn", firstBookIsbn, profileResponse.getBooks().get(0).getIsbn());
    }

    private RequestSpecification authenticatedRequest() {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + loginResponse.getToken());
    }
}
