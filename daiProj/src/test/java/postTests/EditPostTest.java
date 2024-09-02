package postTests;

import baseBase.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

public class EditPostTest extends BaseTest {

    private final String POST_TITLE = "dai " + Utils.getDateAndTimeFormatted();
    private final String POST_TITLE_EDITED = POST_TITLE + " edited";


    @Test
    public void TR_005_editPost() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post from Masha for editing")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
                .clickOnEditPostButton()
                .enterTextIntoInputTitle(POST_TITLE_EDITED)
                .clickOnButtonSaveUpdates()
                .checkIsRemainOnEditPostPage()
                .checkIsEditSuccessMessageDisplayed("Post successfully updated.")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithEditedTitleIsPresent(POST_TITLE_EDITED)


        ;
    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)
                .deletePostsTillPresent(POST_TITLE_EDITED)

        ;
    }

}
