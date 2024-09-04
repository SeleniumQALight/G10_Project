package postTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UpdatePostTest extends BaseTest {

    private final String POST_TITLE = "TR003_pervushyna " + utils.Utils.getDateAndTimeFormatted();

    private final String NEW_POST_TITLE = "TR003_pervushynaNEW " + utils.Utils.getDateAndTimeFormatted();

    @Before
    public void createPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillingFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post from Kait")
                .selectValueInDropdownAccess("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextThisPostWasWrittenIsVisible("One Person")
        ;
    }

    @Test
    public void TR005_updatePost() {
        pageProvider.getPostPage()
                .clickOnButtonEditPost()
                .checkIsRedirectToEditPostPage()
                .enterNewTextIntoInputTitle(NEW_POST_TITLE)
                .enterNewTextIntoInputBody("New body of the post from KaitNew")
                .clickOnButtonSaveUpdatePost()
                .checkIsSuccessUpdateMessageDisplayed()
                .checkTextInUpdateSuccessMessage("Post successfully updated.")
                .clickOnButtonBackToPost()
                .checkIsRedirectToPostPage()
                .checkNewPostTitleIsPresent(NEW_POST_TITLE)
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(NEW_POST_TITLE, 1)

        ;
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(NEW_POST_TITLE)
                .deletePostsTillPresent(POST_TITLE)
        ;
    }
}
