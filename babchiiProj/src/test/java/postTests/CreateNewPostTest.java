package postTests;

import baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR001_createNewPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTitleInToInputTitle("Title of the post from Vasyl")
                .enterTextInToTextAreaBody("Text of the post from Vasyl")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                ;
    }

}
