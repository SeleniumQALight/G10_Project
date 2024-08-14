package postTests;


import baseBase.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCredentials()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle("Title of the post")
                .enterTextIntoInputBody("Body of the post")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()

        ;


    }

}
