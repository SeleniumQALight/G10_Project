package postTests;

import baseBase.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void TR_003_createNewPost(){
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle("Title of the post from Masha")
                .enterTextIntoInputBody("Body of the post from Masha")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage();
    }
}
