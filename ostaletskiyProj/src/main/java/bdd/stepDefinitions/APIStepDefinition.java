package bdd.stepDefinitions;

import API.ApiHelper;
import data.TestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class APIStepDefinition {
    ApiHelper apiHelper = new ApiHelper();

    @Given("I create {} new posts via API for {string} and {string} password")
    public void iCreateNewPostsViaAPIForDefaultAndDefaultPassword(
            Integer numberOfPosts, String userName, String password, DataTable dataTable) {
        if (MainSteps.DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN_API;
        }
        if (MainSteps.DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;
        }
        String token = apiHelper.getToken(userName, password);
        apiHelper.createPosts(numberOfPosts, token, dataTable.asMap());
    }
}
