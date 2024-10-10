package api.dto;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static api.EndPoints.POSTS_BY_USER;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    public ValidatableResponse getAllPostsByUser(String username, int expectedStatusCode) {
        return
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(POSTS_BY_USER, username)
                        .then()
                        .log().all()
                        .statusCode(expectedStatusCode);
    }
}
