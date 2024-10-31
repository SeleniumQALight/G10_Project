package bdd.stepDefinitions;


import api.dto.ApiHelper;
import io.cucumber.datatable.DataTable;
import data.TestData;
import io.cucumber.java.en.Given;

import static bdd.stepDefinitions.MainSteps.DEFAULT;


public class ApiStepDefinitions{

    ApiHelper apiHelper = new ApiHelper();

    @Given("I create {} new posts via API for {string} user and {string} password")
    public void iCreateNewPostsViaAPIForUserAndPassword(
            Integer numberOfPosts, String username, String password, DataTable dataTable) {
        if (DEFAULT.equalsIgnoreCase(username)) {
            username = TestData.VALID_LOGIN_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;
        }
        String token = apiHelper.getToken(username, password);
        apiHelper.createPosts(numberOfPosts, token, dataTable.asMap());
    }
}
