package api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static api.EndPoints.POSTS_BY_USER;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    public ValidatableResponse getAllPostByUserRequest(String userName, int expectedStatusCode) {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(POSTS_BY_USER, userName)
                .then()
                .log().all()
                .statusCode(expectedStatusCode);
    }

    public ValidatableResponse getAllPostByUserRequest(String userName) {
        return getAllPostByUserRequest(userName, HttpStatus.SC_OK);
    }
}
