package apiDemoQa;

import api.EndPoints;
import api.dtoDemoQa.BookStoreDto;
import api.dtoDemoQa.CollectionOfIsbnsDto;
import api.dtoDemoQa.UserDataDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import static api.EndPoints.BOOKS_STORE;
import static io.restassured.RestAssured.given;

public class ApiTestDemoQa {
    String userName = "alex";
    String password = "Alex_123456!";

    UserDataDto requestBody = UserDataDto.builder()
            .password(password)
            .userName(userName)
            .build();

    String userId;
    String token;
    String isbnFirstBook;

    @Before
    public void createUserAndGenerateTokenAndLogin() {
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(EndPoints.REGISTER_USER)
                .then()
//                .statusCode(201)
                ;

        token = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(EndPoints.GENERATE_TOKEN)
                .then()
//                .statusCode(200)
//                .log().all()
                .extract()
                .response()
                .jsonPath()
                .getString("token");

        userId = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(EndPoints.LOGIN_USER)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("userId");
    }

    @After
    public void deleteUserAccount() {
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .delete(EndPoints.DELETE_USER, userId)
                .then()
                .statusCode(204)
                ;
    }

    @Test
    public void addBookToUserProfile() {
        isbnFirstBook = given()
                .contentType(ContentType.JSON)
                .when()
                .get(BOOKS_STORE)
                .then()
//                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getJsonObject("books[0].isbn");
        System.out.println(isbnFirstBook);

//        List<CollectionOfIsbnsDto> isbnList = new ArrayList<>();
//        isbnList.add(CollectionOfIsbnsDto.builder()
//                .isbn(isbnFirstBook)
//                .build());

        BookStoreDto bookStoreDtoRequestBody = BookStoreDto.builder()
                .userId(userId)
                .collectionOfIsbnsDto(
                        Collections.singletonList(
                                CollectionOfIsbnsDto.builder()
                                        .isbn(isbnFirstBook)
                                        .build()
                        )
                )
                .build();

        System.out.println(bookStoreDtoRequestBody);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(bookStoreDtoRequestBody)
                .log().all()
                .when()
                .post(EndPoints.BOOKS_STORE)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        System.out.println(response.prettyPrint());


    }

}
