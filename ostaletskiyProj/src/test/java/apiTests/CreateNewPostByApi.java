package apiTests;

import API.ApiHelper;
import API.DTO.requestDTO.CreatePostDTO;
import API.DTO.responseDTO.AuthorDTO;
import API.DTO.responseDTO.PostsDTO;
import API.EndPoints;
import data.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static API.ApiHelper.*;
import static io.restassured.RestAssured.given;

public class CreateNewPostByApi {
    ApiHelper apiHelper = new ApiHelper();
    String token;

    @Before
    public void getTokenAndDeletePosts (){
        token = apiHelper.getToken();
        System.out.println("Token from api = " + token);
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API,token);

}

    @Test
    public void createNewPostByApi () {
        int numberOfPosts = getNumberOfCreatingPost();

        CreatePostDTO createPostDTOBody =
                CreatePostDTO.builder()
                        .title("Post From API Andriy")
                        .body("Post Body")
                        .select1("One Person")
                        .uniquePost("yes")
                        .token(token)
                        .build();

        String actualResponse =
                given()
                        .spec(requestSpecification)
                        .body(createPostDTOBody)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .spec(ApiHelper.responseSpecification)
                        .extract().response().body().asPrettyString();

        Assert.assertEquals("Message in response", "Congrats.", actualResponse.replace("\"",""));

        int numberOfPostsAfterCreatingPost = getNumberOfCreatingPost();

        Assert.assertEquals("Number of posts Before+1 and After creating new post "
                ,numberOfPosts + 1
                        ,numberOfPostsAfterCreatingPost);

        PostsDTO expectedPostDTO =
                PostsDTO.builder()
                        .title(createPostDTOBody.getTitle())
                        .body(createPostDTOBody.getBody())
                        .select(createPostDTOBody.getSelect1())
                        .uniquePost(createPostDTOBody.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder()
                                .username(TestData.VALID_LOGIN_API)
                                .build())
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(apiHelper.getAllPostByUserRequest()
                        .extract().body().as(PostsDTO[].class)[0])
                        .usingRecursiveComparison()
                                .ignoringFields("id","createdDate","author.avatar")
                                        .isEqualTo(expectedPostDTO);
        softAssertions.assertAll();
    }

    private int getNumberOfCreatingPost() {
        return apiHelper.getAllPostByUserRequest()
                .extract().body().as(PostsDTO[].class).length;
    }
}
