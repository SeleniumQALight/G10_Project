package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDto;
import api.dto.responseDto.AuthorDto;
import api.dto.responseDto.PostDto;
import data.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static api.ApiHelper.*;
import static io.restassured.RestAssured.given;

public class CreateNewPostViaAPI {
    ApiHelper apiHelper = new ApiHelper();
    String token;

    @Before
    public void getTokenAndDeleteAllPosts() {
        token = apiHelper.getToken();
        System.out.println("Token: " + token);
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API, token);
    }

    @Test
    public void createNewPostByApi() {
        int numberOfPostsBefore = getNumberOfPosts();

        CreatePostDto createPostDtoBody =
                CreatePostDto.builder()
                        .title("New Post Denys")
                        .body("New Post Body Denys")
                        .uniquePost("yes ")
                        .select1("One Person")
                        .token(token)
                        .build();

        String actualResponse =
                given().spec(requestSpecification)
                        .body(createPostDtoBody)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then().spec(responseSpecification)
                        .extract().response().asString();

        Assert.assertEquals("Message in response", "\"Congrats.\"", actualResponse);
        Assert.assertEquals("Message in response", "Congrats.", actualResponse.replace("\"", ""));

        int numberOfPostsAfter = getNumberOfPosts();

        Assert.assertEquals("Number of posts before+1 and after"
                , numberOfPostsBefore + 1, numberOfPostsAfter);

        PostDto expectedPost = PostDto.builder()
                .title(createPostDtoBody.getTitle())
                .body(createPostDtoBody.getBody())
                .select(createPostDtoBody.getSelect1())
                .isVisitorOwner(false)
                .uniquePost(createPostDtoBody.getUniquePost())
                .author(AuthorDto.builder()
                        .username(TestData.VALID_LOGIN_API)
                        .build())
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(apiHelper.getAllPostsByUserRequest().extract().body().as(PostDto[].class)[0])
                        .usingRecursiveComparison().ignoringFields("id", "createdDate", "author.avatar")
                        .isEqualTo(expectedPost);

        softAssertions.assertAll();
    }



    private int getNumberOfPosts() {
        return apiHelper.getAllPostsByUserRequest().extract().body().as(PostDto[].class).length;
    }
}
