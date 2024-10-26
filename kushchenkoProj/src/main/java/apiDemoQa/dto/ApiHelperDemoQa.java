package apiDemoQa.dto;

import apiDemoQa.EndPointsDemoQa;
import data.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static data.TestData.VALID_PASSWORD_DEMO_QA;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class ApiHelperDemoQa {
    Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static RequestSpecification requestSpecificationWithAuth(String token) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(SC_OK)
            .build();

    public String getTokenForDemoQa(String username, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", username);
        requestBody.put("password", password);
        return given()
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .post(EndPointsDemoQa.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().getBody().jsonPath().getString("token").replace("\"", "");

    }

    public String getUserIdForDemoQa(String username, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", username);
        requestBody.put("password", password);
        return given()
                .spec(requestSpecification)
                .body(requestBody.toString())
                .when()
                .post(EndPointsDemoQa.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().getBody().jsonPath().getString("userId").replace("\"", "");
    }

    public void deleteAllBooksForUser() {
        String token = getTokenForDemoQa(TestData.VALID_LOGIN_DEMO_QA, VALID_PASSWORD_DEMO_QA);
        String userId = getUserIdForDemoQa(TestData.VALID_LOGIN_DEMO_QA, VALID_PASSWORD_DEMO_QA);
        given()
                .queryParam("UserId", userId)
                .spec(requestSpecificationWithAuth(token))
                .delete(EndPointsDemoQa.DELETE_BOOKS_BY_USER)
                .then()
                .statusCode(204);
        logger.info("All books for user " + userId + " were deleted");

    }



}
