package apiTests;

import apiDemoQA.ApiHelperDemo;
import apiDemoQA.EndPointsDemo;
import apiDemoQA.TestDataDemo;
import apiDemoQA.dto.requestDtoDemo.AddBookDto;
import apiDemoQA.dto.responseDtoDemo.IsbnDto;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.BDDAssumptions.given;

public class AddBookApi {
    ApiHelperDemo apiHelperDemo = new ApiHelperDemo();
    String token;
    String userId;

    @Before
    public void getTokenAndId() {
        token = apiHelperDemo.getToken();
        System.out.println("Token from api = " + token);
        userId = apiHelperDemo.getUserId();
        System.out.println("User id from api = " + userId);

}

    @Test
    public void addBookToUserApi() {
        apiHelperDemo.deleteAllBooksFromUser(userId, token);





}

}
