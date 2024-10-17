package apiDemoQA;

import apiDemoQA.dto.responseDtoDemo.LoginDtoDemo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;

public class ApiHelperDemo {

    private Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(SC_OK)
            .build();


    public String getToken() {
        return getToken(TestDataDemo.VALID_LOGIN_API, TestDataDemo.VALID_PASSWORD_API);
    }

    public String getToken(String login, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", login);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .post(EndPointsDemo.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().jsonPath().getString("token").replace("\"", "");
    }

    public String getUserId() {
        return getUserId(TestDataDemo.VALID_LOGIN_API, TestDataDemo.VALID_PASSWORD_API);
    }

    public static String getUserId(String login, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", login);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .post(EndPointsDemo.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().jsonPath().getString("userId").replace("\"", "");
    }

    public void deleteAllBooksTillPresent(String id, String token) {
        LoginDtoDemo loginDto = new LoginDtoDemo();

        }

    public void deleteAllBooksFromUser(String userId, String token) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);

        given()
                .headers(headers)
                .delete(EndPointsDemo.DELETE_BOOK + userId)
                .then()
                .statusCode(200);
    }
}



