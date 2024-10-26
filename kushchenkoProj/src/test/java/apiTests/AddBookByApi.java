package apiTests;

import api.dto.ApiHelper;
import apiDemoQa.dto.ApiHelperDemoQa;
import org.junit.Before;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_DEMO_QA;
import static data.TestData.VALID_PASSWORD_DEMO_QA;

public class AddBookByApi {

    ApiHelperDemoQa apiHelper = new ApiHelperDemoQa();
    String token;
    String userId;


    @Before
public void getTokenAndDeleteBooks() {
        token = apiHelper.getTokenForDemoQa(VALID_LOGIN_DEMO_QA, VALID_PASSWORD_DEMO_QA);
        userId = apiHelper.getUserIdForDemoQa(VALID_LOGIN_DEMO_QA, VALID_PASSWORD_DEMO_QA);
        System.out.println("TOKEN from api " + token);
        System.out.println("USER ID from api " + userId);
        apiHelper.deleteAllBooksForUser(VALID_LOGIN_DEMO_QA, token);
    }

    @Test
    public void addBookTest() {
    }
}
