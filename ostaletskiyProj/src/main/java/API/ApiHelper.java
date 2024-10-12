package API;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static API.EndPoints.POSTS_BY_USER;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    public ValidatableResponse getAllPostByUserRequest (String userName, int expectedStatusCode){
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(POSTS_BY_USER, userName)
                .then()
                .log().all()
                .statusCode(expectedStatusCode);
    }
}
