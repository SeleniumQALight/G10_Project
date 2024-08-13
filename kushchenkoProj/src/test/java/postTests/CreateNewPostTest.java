package postTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndLoginWithValidCreds()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTitleInToInputTitle("Title of new Post")
                .enterTextInToInputBody("Body of new Post")
                .clickOnButtonSavePost()
                .checkIsRedirectToPostPage()
        ;
    }
}
