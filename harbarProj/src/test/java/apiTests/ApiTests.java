package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.responseDto.AuthorDto;
import api.dto.responseDto.PostsDto;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(ApiTests.class);
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getAllPostsForUser() {
        PostsDto[] actualResponseAsDto =
        given()
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .log().all()
           .when()
                .get(EndPoints.POSTS_BY_USER, USER_NAME)
           .then()
                .log().all()
                .statusCode(200)
        // method #1 REST-assured assert
                .body("[0].title", equalTo("The second Default post"))
                .body("author.username", everyItem(equalTo(USER_NAME)))
        // method #2 DTO
                .extract().body().as(PostsDto[].class)
        ;
        logger.info("Number of posts = " + actualResponseAsDto.length);
        logger.info("Title = " + actualResponseAsDto[0].getTitle());
        logger.info("User Name = " + actualResponseAsDto[0].getAuthor().getUsername());
        logger.info(actualResponseAsDto[1]);

        for (int i = 0; i < actualResponseAsDto.length; i++) {
            Assert.assertEquals("UserName is not expected in post " + i
                    , USER_NAME
                    , actualResponseAsDto[i].getAuthor().getUsername());

        }

        //Expected result
        PostsDto[] expectedResponseDto = {
                PostsDto.builder()
                        .title("The second Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDto.builder()
                                .username(USER_NAME)
                                .build())
                        .isVisitorOwner(false)
                        .build(),
                PostsDto.builder()
                        .title("The first Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDto.builder()
                                .username(USER_NAME)
                                .build())
                        .isVisitorOwner(false)
                        .build()


//                new PostsDto("The second Default post", "This post was created automatically after cleaning the database"
//                        , "All Users", "no", new AuthorDto(USER_NAME), false),
//                new PostsDto("The first Default post", "This post was created automatically after cleaning the database"
//                        , "All Users", "no", new AuthorDto(USER_NAME), false)
        };

        Assert.assertEquals("Number of posts ", expectedResponseDto.length, actualResponseAsDto.length);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedResponseDto);

        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserNegative() {
        final String NOT_VALID_USER_NAME = "NotValidUser";

        String actualResponse = apiHelper
                .getAllPostByUserRequest(NOT_VALID_USER_NAME, SC_BAD_REQUEST)
                // method #3 response as String
                .extract().response().body().asString();

        Assert.assertEquals(
                "Message in response",
                "\"Sorry, invalid user requested. Wrong username - " + NOT_VALID_USER_NAME
                        + " or there is no posts. Exception is undefined\""
                , actualResponse);
    }

    @Test
    public void getAllPostsByUserPath() {
        //method 4 - json path
        Response actualResponse = apiHelper.getAllPostByUserRequest(USER_NAME).extract().response();
        SoftAssertions softAssertions = new SoftAssertions();

        List<String> actualListOfPosts = actualResponse.jsonPath().getList("title", String.class);
        for (int i = 0; i < actualListOfPosts.size(); i++) {
            softAssertions.assertThat(actualListOfPosts.get(i))
                    .as("Item number " + i)
                    .contains("Default post");

            List<Map> actualAuthorList = actualResponse.jsonPath().getList("author", Map.class);

            for (Map actualAuthorObject: actualAuthorList) {
                softAssertions.assertThat(actualAuthorObject.get("username"))
                        .as("Field userName in Author")
                        .isEqualTo(USER_NAME);
            }
        }

        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserSchema(){
        apiHelper.getAllPostByUserRequest(USER_NAME)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("response.json"));
    }
}
