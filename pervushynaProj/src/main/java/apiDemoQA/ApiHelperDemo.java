package apiDemoQA;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;


import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;

public class ApiHelperDemo {

    private Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static RequestSpecification requestSpecificationWithToken(String token) {
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

public void DeleteAllBooks() {
    String token = getToken();
    String userId = getUserId();
    given()
            .queryParam("UserId", userId)
            .spec(requestSpecificationWithToken(token))
            .delete(EndPointsDemo.ACTIVITIES_WITH_BOOKS)
            .then()
            .statusCode(204);

    logger.info("All books are deleted");

}

//    public String getBookIsbnByUserId(String userId) {
//        return given()
//                .queryParam("UserId", userId)
//                .spec(requestSpecification)
//                .get(EndPointsDemo.ACTIVITIES_WITH_BOOKS)
//                .then()
//                .statusCode(200)
//                .extract().jsonPath().getString("books[0].isbn");
//    }

}



