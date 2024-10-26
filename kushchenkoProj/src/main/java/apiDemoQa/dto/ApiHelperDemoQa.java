package apiDemoQa.dto;

import api.dto.ApiHelper;
import apiDemoQa.EndPointsDemoQa;
import apiDemoQa.dto.requestDtoDemoQa.AddBookRequestDto;
import apiDemoQa.dto.requestDtoDemoQa.IsbnRequestDto;
import apiDemoQa.dto.responseDtoDemoQa.BooksResponseDto;
import apiDemoQa.dto.responseDtoDemoQa.ListBooksResponseDto;
import apiDemoQa.dto.responseDtoDemoQa.LoginDemoQaDto;
import apiDemoQa.dto.responseDtoDemoQa.UserAccountResponseDto;
import data.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.devtools.v85.dom.model.ShadowRootType;

import java.util.Arrays;

import java.util.Collections;
import java.util.HashMap;

import static data.TestData.VALID_LOGIN_DEMO_QA;
import static data.TestData.VALID_PASSWORD_DEMO_QA;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiHelperDemoQa {
    Logger logger = Logger.getLogger(getClass());
    private static LoginDemoQaDto loginResponse;
    private BooksResponseDto booksResponseDto;
    private String firstBookIsbn;

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public RequestSpecification requestSpecificationWithAuth() {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + loginResponse.getToken());
    }

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(SC_OK)
            .build();

    public void getTokenAndUserId() {
        HashMap<String, String> loginBodyRequest = new HashMap<>();
        loginBodyRequest.put("userName", VALID_LOGIN_DEMO_QA);
        loginBodyRequest.put("password", VALID_PASSWORD_DEMO_QA);

        loginResponse = given()
                .spec(requestSpecification)
                .body(loginBodyRequest)
                .when()
                .post(EndPointsDemoQa.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().body().as(LoginDemoQaDto.class);
    }

    public void deleteAllBooksForUser() {
        logger.info("Delete all books for user");
        requestSpecificationWithAuth()
                .queryParam("UserId", loginResponse.getUserId())
                .delete(EndPointsDemoQa.BOOK_STORE_ACTIONS)
                .then()
                .spec(responseSpecification.statusCode(SC_NO_CONTENT));
        logger.info("All books for user were deleted");

    }

    public void getAllBooks() {
        logger.info("Get list of all books");
        booksResponseDto =
                given()
                        .spec(requestSpecification)
                        .when()
                        .get(EndPointsDemoQa.BOOK_STORE_ACTIONS)
                        .then()
                        .spec(responseSpecification.statusCode(SC_OK))
                        .extract().response().body().as(BooksResponseDto.class);
    }

    public void addBookByUserId() {
        logger.info("Get ISBN of the first book from list");
        firstBookIsbn = booksResponseDto.getBooks().get(0).getIsbn();
        logger.info("ISBN of the first book is " + firstBookIsbn);
        logger.info("Create request dto for add book");

        AddBookRequestDto addBookRequestBody = AddBookRequestDto.builder()
                .userId(loginResponse.getUserId())
                .collectionOfIsbns(Arrays.asList(IsbnRequestDto.builder()
                        .isbn(firstBookIsbn).build()))
                        .build();

        logger.info("Add book with ISBN " + firstBookIsbn + " and verify ISBN in response");
        requestSpecificationWithAuth()
                .body(addBookRequestBody)
                .when()
                .post(EndPointsDemoQa.BOOK_STORE_ACTIONS)
                .then()
                .spec(responseSpecification.statusCode(SC_CREATED))
                .body("books[0].isbn", equalTo(firstBookIsbn));
        logger.info("Book with ISBN " + firstBookIsbn + " was added to profile");
    }

    public void checkIsBookAddedToProfile() {
        logger.info("Check that profile contains book with ISBN " + firstBookIsbn);
        UserAccountResponseDto userAccountInfo =
                requestSpecificationWithAuth()
                        .when()
                        .get(EndPointsDemoQa.GET_USER_ACCOUNT, loginResponse.getUserId())
                        .then()
                        .spec(responseSpecification.statusCode(SC_OK))
                        .extract().response().body().as(UserAccountResponseDto.class);
        Assert.assertEquals("", firstBookIsbn, userAccountInfo.getBooks().get(0).getIsbn());
        logger.info("Profile contains book with ISBN " + firstBookIsbn);
    }
}
