package bdd.stepDefinitions;

import api.ApiHelper;
import data.TestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

import static bdd.stepDefinitions.MainSteps.DEFAULT;

public class ApiStepDefinitions {
    ApiHelper apiHelper = new ApiHelper();

    @Given("I create {} new posts via API for {string} user and {string} password")
    public void iCreateNewPostsViaAPIForDefaultUserAndDefaultPassword(
            Integer numberOfPosts, String userName, String password, DataTable dataTable) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;
        }
        String token = apiHelper.getToken(userName, password);
        apiHelper.createPosts(numberOfPosts, token, dataTable.asMap());

    }
}
