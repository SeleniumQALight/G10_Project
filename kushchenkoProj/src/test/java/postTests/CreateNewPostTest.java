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
                .setCheckBoxPostUniqueTrue("check")
                .selectVaueInDropDownAccess("One Person")
                .clickOnButtonSavePost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique()
                .checkTextThisPostWasWrittenIsVisible("One Person")
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

