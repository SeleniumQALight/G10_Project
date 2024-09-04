package postTests;


import baseBase.BaseTest;
import org.junit.After;
import org.junit.Test;
import utils.Utils;


public class CreateNewPostTest extends BaseTest {
    // GUID = 3b3b3b3b-3b3b-3b3b-3b3b-3b3b3b3b3b3b
    private final String POST_TITLE = "TR003_andriy_" + Utils.getDateAndTimeFormatted();

    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCredentials()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post")
                .setCheckBoxState("check")
       //         .selectTextInDropDownAccessByVisibleText("Приватне повідомлення")
                .selectValueInDropDownAccess("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUniqueDisplayed()
                .checkIsPostUniqueText("Is this post unique? : yes")
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextThisPostWasWrittenIsVisible("One Person")
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)


        ;
    }


}
