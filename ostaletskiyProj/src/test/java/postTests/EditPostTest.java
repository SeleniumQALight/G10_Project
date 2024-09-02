package postTests;

import baseBase.BaseTest;
import org.junit.After;
import org.junit.Test;
import utils.Utils;

public class EditPostTest extends BaseTest {
        private final String POST_TITLE = "TR_005_andriy_" + Utils.getDateAndTimeFormatted();
        private final String NEW_POST_TITLE = "NEW_TR_005_andriy_" + Utils.getDateAndTimeFormatted();
        private final String POST_BODY = "Body of the post";
        private final String NEW_POST_BODY = "New body of the post";

    @Test
    public void TR005_editAndSavePost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCredentials()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnEditButton()
                .checkIsRedirectToEditPostPage()
                .enterTextIntoInputTitle(NEW_POST_TITLE)
                .enterTextIntoTextAreaBody(NEW_POST_BODY)
                .clickOnButtonSaveUpdates()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("Post successfully updated.")
                .checkTextInPostTitle(NEW_POST_TITLE)
                .checkTextInPostBody(NEW_POST_BODY)
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(NEW_POST_TITLE, 1);

    }
        @After
        public void deletePost() {
            pageProvider.getHomePage()
                    .openHomePageAndLoginIfNeeded()
                    .getHeaderElement().clickOnButtonMyProfile()
                    .checkIsRedirectToMyProfilePage()
                    .deletePostsTillPresent(NEW_POST_TITLE)
                    .deletePostsTillPresent(POST_TITLE);
        }

    }

