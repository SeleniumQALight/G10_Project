package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDto;
import api.dto.responseDto.AuthorDto;
import api.dto.responseDto.PostsDto;
import data.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static api.ApiHelper.requestSpecification;
import static api.ApiHelper.responseSpecification;
import static data.TestData.VALID_LOGIN_API;
import static io.restassured.RestAssured.given;

public class CreateNewPostByApi {
    ApiHelper apiHelper = new ApiHelper();
    String token;

    @Before
    public void getTokenAndDeletePosts() {
        token = apiHelper.getToken();
        System.out.println("token from API = " + token);
        apiHelper.deleteAllPostsTillPresent(VALID_LOGIN_API, token);
    }

    @Test
    public void createNewPostByApi() {
        int numberOfPosts = getNumberOfPostsByUser();

        CreatePostDto createPostDto =
                CreatePostDto.builder()
                        .title("Post title")
                        .body("Post body")
                        .select1("One Person")
                        .uniquePost("yes")
                        .token(token)
                        .build();

        String actualResponse =
                given()
                        .spec(requestSpecification)
                        .body(createPostDto)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .spec(responseSpecification)
                        .extract().response().body().asString();

        Assert.assertEquals("Message in response ", "\"Congrats.\"", actualResponse);
        Assert.assertEquals("Message in response ", "Congrats.", actualResponse.replace("\"", ""));

        int numberOfPostsAfterCreatingPost = getNumberOfPostsByUser();

        Assert.assertEquals("Number of posts before+1 and after creating new post ",
                numberOfPosts + 1,
                numberOfPostsAfterCreatingPost);

    PostsDto expectedPostDto =
            PostsDto.builder()
                    .title(createPostDto.getTitle())
                    .body(createPostDto.getBody())
                    .select(createPostDto.getSelect1())
                    .uniquePost(createPostDto.getUniquePost())
                    .isVisitorOwner(false)
                    .author(AuthorDto.builder()
                            .username(VALID_LOGIN_API)
                            .build())
                    .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(apiHelper.getAllPostByUserRequest().extract().body().as(PostsDto[].class)[0])
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedPostDto);
        softAssertions.assertAll();

    }

    private int getNumberOfPostsByUser() {
        return apiHelper.getAllPostByUserRequest()
                .extract().body().as(PostsDto[].class).length;
    }

}
