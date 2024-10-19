package api;

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
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;

import static api.EndPoints.POSTS_BY_USER;
import static io.restassured.RestAssured.given;

public class ApiHelper {

    Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();


    public ValidatableResponse getAllPostByUserRequest(String userName, int expectedStatusCode) {
        return given()
//
                .spec(requestSpecification)
                .when()
                .get(POSTS_BY_USER, userName)
                .then()
//                .log().all()
//                .statusCode(expectedStatusCode);
                .spec(responseSpecification.statusCode(expectedStatusCode));

    }

    public ValidatableResponse getAllPostByUserRequest(String userName) {
        return getAllPostByUserRequest(userName, HttpStatus.SC_OK);
    }

    public ValidatableResponse getAllPostByUserRequest() {
        return getAllPostByUserRequest(TestData.VALID_LOGIN_API, HttpStatus.SC_OK);
    }


    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
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
        PostsDto[] listOfPosts = getAllPostByUserRequest(userName.toLowerCase())
                .extract().response().body().as(PostsDto[].class);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(String.format("Post with id %s and title '%s' was deleted"
                    , listOfPosts[i].getId(), listOfPosts[i].getTitle()));

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

