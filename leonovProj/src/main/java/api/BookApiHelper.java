package api;

import api.dto.responseDto.BookCatalogDto;
import api.dto.responseDto.BookDTO;
import api.dto.responseDto.UserDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookApiHelper {
    Logger logger = Logger.getLogger(getClass());
    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .log(LogDetail.ALL)
            .build();


    public ResponseBody login(String validLoginBook, String validPasswordBook) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", validLoginBook);
        requestBody.put("password", validPasswordBook);
        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(BookEndPoints.LOGIN_URL)
                .then()
                .spec(responseSpecification)
                .extract().response().getBody();

    }

    public ResponseBody deleteUserBooks(String userId, String token) {
        JSONObject requestBody = new JSONObject();

        return given()
                .spec(requestSpecification)
                .queryParam("UserId", userId)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(BookEndPoints.ALL_BOOKS_URL)
                .then()
                //changed status code from 200 to 204 according to the documentation
                .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT))
                .extract().response().getBody();
    }

    public ResponseBody addBookToCollection(String userId, String token, String isbn) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", userId);
        List<Map<String, String>> listOfBook;
        listOfBook = List.of(Map.of("isbn", isbn));
        requestBody.put("collectionOfIsbns", listOfBook);

        logger.info("Book with isbn: " + isbn + " added to user collection");
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toMap())
                .when()
                .post(BookEndPoints.ALL_BOOKS_URL)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_CREATED))
                .extract().response().getBody();

    }

    public BookDTO[] getAllBooksCatalog() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(BookEndPoints.ALL_BOOKS_URL)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK))
                .extract().response().getBody().as(BookCatalogDto.class).getBooks();
    }

    public UserDto getAllBooksByUser(String userId, String token) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(BookEndPoints.USER_BOOKS_URL, userId)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK))
                .extract().response().getBody().as(UserDto.class);

    }
}
