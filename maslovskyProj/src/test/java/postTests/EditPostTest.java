package postTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

public class EditPostTest extends BaseTest {
    private final String POST_TITLE = "TR_005_mine_" + Utils.getDateAndTimeFormatted();
    private final String POST_BODY = "Old text body";
    private final String NEW_POST_TITLE = "NEW_TR_005_mine_" + Utils.getDateAndTimeFormatted();
    private final String NEW_POST_BODY = "New text body";


    @Before
    public void CreateNewPostTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderElement()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoTextAreaBody(POST_BODY)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed();
    }


    @Test
    public void TR005_editAndSavePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement()
                .clickOnMyProfileButton()
                .clickOnPostWithTitle(POST_TITLE)
                .clickOnEditButton()
                .enterTextIntoInputTitle(NEW_POST_TITLE)
                .enterTextIntoTextAreaBody(NEW_POST_BODY)
                .clickOnButtonSaveUpdates()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("Post successfully updated.")
                .checkTextInPostTitle(NEW_POST_TITLE)
                .checkTextInPostBody(NEW_POST_BODY)
                .getBackToCurrentPostPage()
                .checkTextInPostTitleOfPostPage(NEW_POST_TITLE)
                .checkTextInPostBodyOfPostPage(NEW_POST_BODY)
                .getHeaderElement()
                .clickOnMyProfileButton()
                .checkPostWithTitleIsPresent(NEW_POST_TITLE, 1);
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(NEW_POST_TITLE)
                .deletePostsTillPresent(POST_TITLE);
    }

}
