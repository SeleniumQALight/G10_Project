package postTests;


import baseBase.BaseTest;
import org.junit.After;
import org.junit.Test;
import utils.Utils;

public class CreateNewPostTest extends BaseTest {
    // GUID = 1b1b1b1b-1b1b-1b1b-1b1b-1b1b1b1b1b1b
    private final String POST_TITLE = "TR003_radulenko_" + Utils.getDateAndTimeFormatted();

    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post from Taras")
//                .selectTextInDropdownAccessByVisibleText("Приватне повідомлення")
                .selectValueInDropdownAccess("One Person")
                //                setcheckbox to true
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextThisPostWasWrittenIsVisible("One Person")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;

    }

    @After
    public void deletePosts() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)

        ;
    }



}
