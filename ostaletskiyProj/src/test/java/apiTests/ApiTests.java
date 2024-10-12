package apiTests;

import API.ApiHelper;
import API.DTO.responseDTO.AuthorDTO;
import API.DTO.responseDTO.PostsDTO;
import API.EndPoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();


    @Test
    public void getAllPostsForUser() {
        PostsDTO[] actualResponseAsDto =
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POSTS_BY_USER, USER_NAME)
                .then()
                .log().all()
                .statusCode(200)
                // method #1 REST ASSURED ASSERT
                .body("[0].title", equalTo("The second Default post"))
                .body("author.username", everyItem(equalTo(USER_NAME)))
               // method #2 DTO
                .extract().body().as(PostsDTO[].class)
        ;
        logger.info("Number of posts = " + actualResponseAsDto.length);
        logger.info("Title = " + actualResponseAsDto[0].getTitle());
        logger.info("User Name = " + actualResponseAsDto[0].getAuthor().getUsername());
        logger.info(actualResponseAsDto[1]);

        for (int i = 0; i < actualResponseAsDto.length; i++) {
            Assert.assertEquals("User Name is not expected in post", USER_NAME,
                    actualResponseAsDto[i].getAuthor().getUsername());
            
        }
        // Expected Result
        PostsDTO[] expectedResponseDTO = {
                PostsDTO.builder()
                        .title("The second Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDTO.builder()
                                .username(USER_NAME)
                                .build())
                        .isVisitorOwner(false)
                        .build(),
                PostsDTO.builder()
                        .title("The first Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDTO.builder()
                                .username(USER_NAME)
                                .build())
                        .isVisitorOwner(false)
                        .build()
                //          new PostsDTO("The second Default post","This post was created automatically after cleaning the database",
       //                 "All Users","no", new AuthorDTO(USER_NAME),false),
      //          new PostsDTO("The first Default post","This post was created automatically after cleaning the database",
       //                 "All Users","no", new AuthorDTO(USER_NAME),false),
        };

        Assert.assertEquals("Number of posts ", expectedResponseDTO.length, actualResponseAsDto.length);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualResponseAsDto)
                        .usingRecursiveComparison()
                                .ignoringFields("id","createdDate","author.avatar")
                                        .isEqualTo(expectedResponseDTO);

        softAssertions.assertAll();

    }
    @Test
    public void getAllPostsByUserNegative(){
        final String NOT_VALID_USER_NAME = "NotValidUser";

       String actualResponse =
               apiHelper.getAllPostByUserRequest(NOT_VALID_USER_NAME, SC_BAD_REQUEST)
                .extract().response().body().asString();
       Assert.assertEquals(
               "Message in response "
               ,"\"Sorry, invalid user requested. Wrong username - " +
                       ""+NOT_VALID_USER_NAME +" or there is no posts. Exception is undefined\""
               ,actualResponse
       );



    }






}
