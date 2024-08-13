package postTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle("Title of the post from me")
                .enterTextIntoTextAreaBody("Body of the post from me")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
        ;
    }

}
