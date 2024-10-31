package api.HWEndpointsAndDTO;

import api.dto.responseDto.BooksListDto;
import data.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;


public class ApiHelperBooks {

    Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();


    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API_BOOK, TestData.VALID_PASSWORD_API_BOOK);
    }

    public String getToken(String username, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", username);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .post(BooksEndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().jsonPath().getString("token").replace("\"", "");
    }


    public static String getUserId(String login, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", login);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .post(BooksEndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().jsonPath().getString("userId").replace("\"", "");
    }


    public void deleteAllBooksTillPresent(String userId, String token) {
        BooksListDto listOfBooks = given()
                .queryParam("userId", userId)
                .spec(requestSpecificationWithToken(token))
                .get(BooksEndPoints.ALL_BOOKS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().as(BooksListDto.class);

        for (int i = 0; i < listOfBooks.getBooks().size(); i++) {
            deleteBookById(userId, token, listOfBooks.getBooks().get(i).getIsbn());
            logger.info(String.format("Book with ISBN %s was deleted", listOfBooks.getBooks().get(i).getIsbn()));
        }
    }

    private void deleteBookById(String userId, String token, String isbn) {
        given()
//                .queryParam("userId", userId)
                .spec(requestSpecificationWithToken(token))
                .delete(BooksEndPoints.DELETE_BOOK.replace("{isbn}", isbn).replace("{userId}", userId))
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    public static RequestSpecification requestSpecificationWithToken(String token) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .log(LogDetail.ALL)
                .build();
    }

}

