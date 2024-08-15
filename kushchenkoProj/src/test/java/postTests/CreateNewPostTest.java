package postTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import utils.Utils;

public class CreateNewPostTest extends BaseTest {
    // GUID = 3b3b3b3b-3b3b-3b3b-3b3b-3b3b3b3b3b3b
    private final String POST_TITLE = "TR003_Kushchenko " + Utils.getDateAndTimeFormatted();

    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndLoginWithValidCreds()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextInToInputBody("Body of new Post")
                // set check-box to true
                .clickOnButtonSavePost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                // isPostUnique? - yes
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(POST_TITLE)


        ;

    }
}
