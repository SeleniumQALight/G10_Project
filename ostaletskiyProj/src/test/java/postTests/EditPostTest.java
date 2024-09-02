package postTests;

import baseBase.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

public class EditPostTest extends BaseTest {
        private final String POST_TITLE = "TR_005_andriy_" + Utils.getDateAndTimeFormatted();
        private final String NEW_POST_TITLE = "NEW_TR_005_andriy_" + Utils.getDateAndTimeFormatted();

    @Test
    public void TR005_editAndSavePost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCredentials()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkIsPostUniqueDisplayed()
                .clickOnEditButton()
                .checkIsRedirectToEditPostPage()
                .enterTextIntoInputTitle(NEW_POST_TITLE)
                .clickOnButtonSaveUpdates()
                .checkIsRedirectToEditPostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnButtonMyProfile()
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

