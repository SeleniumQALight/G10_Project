package api.dto;

import api.EndPoints;
import api.dto.responseDto.PostsDto;
import data.TestData;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;

import static api.EndPoints.POSTS_BY_USER;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());


    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(SC_OK)
            .build();


    public ValidatableResponse getAllPostsByUser(String username, int expectedStatusCode) {
        return
                given()
//                        .contentType(ContentType.JSON)
//                        .log().all()
                        .spec(requestSpecification)
                        .when()
                        .get(POSTS_BY_USER, username)
                        .then()
//                        .log().all()
//                        .statusCode(expectedStatusCode);
                        .spec(responseSpecification.statusCode(expectedStatusCode));
    }

    public ValidatableResponse getAllPostsByUserRequest(String username) {
        return getAllPostsByUser(username, SC_OK);
    }

    public ValidatableResponse getAllPostsByUserRequest() {
        return getAllPostsByUser(TestData.VALID_LOGIN_API, SC_OK);
    }

    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String username, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
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

    public void deleteAllPostsTillPresent(String username, String token) {
        PostsDto[] listOfPosts = getAllPostsByUserRequest(username)
                .extract().response().body().as(PostsDto[].class);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(String.format("Post with id %s and title '%s' was deleted"
                    , listOfPosts[i].getId()
                    , listOfPosts[i].getTitle()));

        }
    }

    private void deletePostById(String token, String id) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);
        given()
                .spec(requestSpecification)
                .body(bodyRequest)
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .spec(responseSpecification);
    }
}
