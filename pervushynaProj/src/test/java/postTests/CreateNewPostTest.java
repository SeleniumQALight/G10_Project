package postTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillingFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle("Title of the post from Kait")
                .enterTextIntoInputBody("Body of the post from Kait")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()

        ;

    }

}
