package apiTests;

import api.EndPoints;
import api.dto.ApiHelper;
import api.dto.requestDto.CreatePostDto;
import api.dto.responseDto.AuthorDto;
import api.dto.responseDto.PostsDto;
import data.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static api.dto.ApiHelper.requestSpecification;
import static api.dto.ApiHelper.responseSpecification;
import static io.restassured.RestAssured.given;

public class CreateNewPostByApi {
    ApiHelper apiHelper = new ApiHelper();
    String token;

    @Before
    public void getTokenAndDeletePosts() {
        token = apiHelper.getToken();
        System.out.println("TOKEN from api " + token);
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API, token);
    }

    @Test
    public void createNewPostByApi() {
        int numberOfPostsBefore = getNumberOfPostsByUser();

        CreatePostDto createPostDtoBody = CreatePostDto.builder()
                .title("Post from API")
                .body("Body of the post from API")
                .select1("One Person")
                .uniquePost("yes")
                .token(token)
                .build();
        String actualResponse =
                given()
                        .spec(requestSpecification)
                        .body(createPostDtoBody)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .spec(responseSpecification)
                        .extract().response().body().asString();
        Assert.assertEquals("Message in response", "\"Congrats.\"", actualResponse);
        int numberOfPostsAfterCreatingPost = getNumberOfPostsByUser();
        Assert.assertEquals("Number of posts Before+1 and After creating new post"
                , numberOfPostsBefore + 1
                , numberOfPostsAfterCreatingPost);
        PostsDto expectedPostDto =
                PostsDto.builder()
                        .title(createPostDtoBody.getTitle())
                        .body(createPostDtoBody.getBody())
                        .select(createPostDtoBody.getSelect1())
                        .uniquePost(createPostDtoBody.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDto.builder()
                                .username(TestData.VALID_LOGIN_API)
                                .build())
                        .build();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(apiHelper.getAllPostsByUserRequest()
                .extract().body().as(PostsDto[].class)[0])
                        .usingRecursiveComparison()
                                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedPostDto);
        softAssertions.assertAll();
    }

    private int getNumberOfPostsByUser() {
        return apiHelper.getAllPostsByUserRequest()
                .extract().body().as(PostsDto[].class).length;
    }
}
