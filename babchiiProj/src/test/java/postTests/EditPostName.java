package postTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

public class EditPostName extends BaseTest {
    private final String POST_TITLE = "TR004_babchii_" + Utils.getDateAndTimeFormatted();
    private final String UPDATED_POST_TITLE = POST_TITLE + "_updated";
    @Before
    public void createPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextInToTextAreaBody("Text of the post from Vasyl")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.");
    }
    @Test
    public void TR004_editPostName() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .clickOnPostWithTitle(POST_TITLE)
                .clickOnEditButton()
                .checkIsRedirectToEditPostPage()
                .enterNewTitleInToInputTitle(UPDATED_POST_TITLE)
                .clickOnButtonSaveUpdates()
                .checkIsRedirectToEditPostPage()
                .checkIsSuccessUpdateMessageDisplayed()
                .checkTextInSuccessUpdateMessage("Post successfully updated.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkIsPostWithTitlePresent(UPDATED_POST_TITLE, 1);
    }
    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitlePresent(UPDATED_POST_TITLE)
                .deletePostsWithTitlePresent(POST_TITLE);
    }
}
