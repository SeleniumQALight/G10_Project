package postTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import utils.Utils;

public class CreateNewPostTest extends BaseTest {
    //GUID = 1b1b1b1b-1b1b-1b1b-1b1b-1b1b1b1b1b1b
    private final String POST_TITLE = "TR_003_mine_" + Utils.getDateAndTimeFormatted();

    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoTextAreaBody("Body of the post from me")
//                .selectTextInDropDownAccessByVisibleText("Приватне повідомлення")
                .selectValueInDropdownAccess("One Person")
                .setCheckBoxStatus("check")
                ////////////////////////////////
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                // isThisPost unique& : yes
                .checkIsUniqueTextInPostDisplayed()
                ////////////////////////////////
                .checkTextThisPostWasWrittenIsVisible("One Person")
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1);
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }

}
