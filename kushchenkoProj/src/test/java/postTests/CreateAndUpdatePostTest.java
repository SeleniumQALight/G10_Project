package postTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import utils.Utils;

public class CreateAndUpdatePostTest extends BaseTest {
    private final String POST_TITLE = "TR003_Kushchenko " + Utils.getDateAndTimeFormatted();
    private final String UPDATED_POST_TITLE = POST_TITLE + " updated";

    @Test
    public void TR005_createAndUpdatePost() {
        pageProvider.getLoginPage()
                .openLoginPageAndLoginWithValidCreds()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextInToInputBody("Body of new Post")
                .setCheckBoxPostUniqueTrue("check")
                .clickOnButtonSavePost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique()
                .clickOnEditButton()
                .checkIsRedirectToEditPostPage()
                .enterNewTitleInToInputTitle(UPDATED_POST_TITLE)
                .clickOnButtonSaveUpdates()
                .checkIsRedirectToEditPostPage()
                .checkIsSuccessUpdateMessageDisplayed()
                .checkTextInSuccessUpdateMessage("Post successfully updated.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .checkPostWithTitleIsPresent(UPDATED_POST_TITLE, 1);
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToProfilePage()
                .deletePostsTillPresent(UPDATED_POST_TITLE)
        ;
    }
}
