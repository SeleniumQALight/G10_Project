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
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post")
       //         .selectTextInDropDownAccessByVisibleText("Приватне повідомлення")
                .selectValueInDropDownAccess("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccesMessage("New post successfully created.")
                .checkTextThisPostWasWrittenIsVissible("One Person")
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
