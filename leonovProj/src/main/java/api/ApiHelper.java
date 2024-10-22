package api;

import api.dto.responseDto.PostDto;
import data.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;

import static data.TestData.VALID_LOGIN_API;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    private Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .log(LogDetail.ALL)
            .build();


    public ValidatableResponse getAllPostsByUserRequest(String userName, int expectedStatusCode) {
        return given()
                .spec(requestSpecification)
//               .contentType(ContentType.JSON)
//               .log().all()
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
                .spec(responseSpecification.statusCode(expectedStatusCode));
//                .log().all()
//                .statusCode(expectedStatusCode);
    }

    public ValidatableResponse getAllPostsByUserRequest(String userName) {

        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK);
    }

    public ValidatableResponse getAllPostsByUserRequest() {
        return getAllPostsByUserRequest(VALID_LOGIN_API);
    }

    public String getToken() {
        return getToken(VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().getBody().asString().replace("\"", "");

    }

    public void deleteAllPostsTillPresent(String userName, String token) {
        PostDto[] listOfPosts = getAllPostsByUserRequest(userName.toLowerCase()).extract().body().as(PostDto[].class);
        for (PostDto listOfPost : listOfPosts) {
            deletePostById(token, listOfPost.getId());
            logger.info(String.format("Post with id %s and title '%s' was deleted"
                    , listOfPost.getId(), listOfPost.getTitle()));

        }
    }

    private void deletePostById(String token, String id) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);

        given().spec(requestSpecification)
                .body(bodyRequest)
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .spec(responseSpecification);
    }
}
