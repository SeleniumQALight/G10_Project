package api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public ValidatableResponse getAllPostsByUserRequest(String userName, int expectedStatusCode) {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
                .log().all()
                .statusCode(expectedStatusCode);
    }

    public ValidatableResponse getAllPostsByUserRequest(String userName) {

        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK);
    }
}
