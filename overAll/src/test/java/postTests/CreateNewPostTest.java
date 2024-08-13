package postTests;


import baseBase.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle("Title of the post from Taras")
                .enterTextIntoInputBody("Body of the post from Taras")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()

        ;

    }

}
