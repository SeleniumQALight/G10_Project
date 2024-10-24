package apiTests;

import API.DTO.DemoQaDTO.BookStoreDto;
import API.DTO.DemoQaDTO.CollectionOfIsbnsDto;
import API.DTO.DemoQaDTO.UserDataDto;
import API.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static API.EndPoints.BOOKS_STORE;
import static io.restassured.RestAssured.given;

public class ApiTestDemoQa {
    Logger logger = Logger.getLogger(getClass());

    String userName = "AndriyTest";
    String password = "A1b@C3d!";

    UserDataDto requestBody = UserDataDto.builder()
            .password(password)
            .userName(userName)
            .build();

    String userId;
    String token;
    String isbnFirstBook;
    String nameFirstBook;
    JsonPath jsonPath;

    @Before
    public void createUserAndGenerateTokenAndLogin() {
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(EndPoints.REGISTER_USER)
                .then()
                .statusCode(201);
        logger.info(String.format("User '%s' profile created successfully", userName));

        token = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(EndPoints.GENERATE_TOKEN)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("token");
        logger.info(String.format("Token for User '%s' was generated", userName));

        userId = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(EndPoints.LOGIN_USER)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("userId");
        logger.info(String.format("User ID '%s' is received", userId));
    }

    @After
    public void deleteUserAccount() {
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .delete(EndPoints.DELETE_USER, userId)
                .then()
                .statusCode(204);
        logger.info(String.format("User account for '%s' has been deleted", userName));

    }

    @Test
    public void addBookToUserProfile() {
        jsonPath = given()
                .contentType(ContentType.JSON)
                .when()
                .get(BOOKS_STORE)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();
        isbnFirstBook = jsonPath.getJsonObject("books[0].isbn");
        nameFirstBook = jsonPath.getJsonObject("books[0].title");
        logger.info(String.format("ISBN '%s' of the first book is found", isbnFirstBook));

        BookStoreDto bookStoreDtoRequestBody = BookStoreDto.builder()
                .userId(userId)
                .collectionOfIsbns(
                        Collections.singletonList(
                                CollectionOfIsbnsDto.builder()
                                        .isbn(isbnFirstBook)
                                        .build()))
                .build();

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(bookStoreDtoRequestBody)
                .when()
                .post(BOOKS_STORE)
                .then()
                .statusCode(201)
                .extract().response();
        logger.info(String.format("Book '%s' is added to User profile", nameFirstBook));

        Response checkBook = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.BOOKS_OF_USER, userId)
                .then()
                .statusCode(200)
                .extract().response();

        int actualNumberBooks = checkBook.getBody().jsonPath().getList("books").size();
        int expectedNumberBooks = 1;
        Assert.assertEquals("Number of books in User profile is more than 1", expectedNumberBooks, actualNumberBooks);
        logger.info(String.format("Number of books in '%s' profile = 1", userName));

        String actualIsbn = checkBook
                .body()
                .jsonPath()
                .getJsonObject("books[0].isbn");

        Assert.assertEquals(
                String.format("Wrong book ISBN '%s' added to user '%s' profile", actualIsbn, userName),
                isbnFirstBook, actualIsbn);
        logger.info(String.format("Book with ISBN '%s' is in '%s' profile as expected", actualIsbn, userName));
    }

}
