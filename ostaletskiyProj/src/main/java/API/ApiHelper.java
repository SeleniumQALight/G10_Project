package API;

import API.DTO.responseDTO.PostsDTO;
import com.mysql.cj.log.Log;
import data.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static API.EndPoints.LOGIN;
import static API.EndPoints.POSTS_BY_USER;
import static data.TestData.VALID_LOGIN_API;
import static data.TestData.VALID_PASSWORD_API;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class ApiHelper {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(getClass());

    private static final Logger log = LoggerFactory.getLogger(ApiHelper.class);
    public static  RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

  public static  ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(SC_OK)
            .build();



    public ValidatableResponse getAllPostByUserRequest (String userName, int expectedStatusCode){
        return given()
         //       .contentType(ContentType.JSON)
        //     .log().all()
                .spec(requestSpecification)
                .when()
                .get(POSTS_BY_USER, userName)
                .then()
              //  .log().all()
              //  .statusCode(expectedStatusCode);
                .spec(responseSpecification.statusCode(expectedStatusCode));
    }

    public ValidatableResponse getAllPostByUserRequest (String userName){
        return getAllPostByUserRequest(userName, SC_OK);
    }

    public ValidatableResponse getAllPostByUserRequest (){
        return getAllPostByUserRequest(VALID_LOGIN_API, SC_OK);
    }


    public String getToken() {
        return getToken(VALID_LOGIN_API, VALID_PASSWORD_API);
    }


    public String getToken (String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().getBody().asString().replace("\"","");
    }

    public void deleteAllPostsTillPresent(String userName, String token) {
        PostsDTO[] listOfPosts = getAllPostByUserRequest(userName.toLowerCase())
                .extract().response().body().as(PostsDTO[].class);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(
                    String.format("Post with id %s and title '%s' was deleted"
                            , listOfPosts[i].getId(), listOfPosts[i].getTitle())
            );

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
