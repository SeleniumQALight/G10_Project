package postTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    //GUID = 1b1b1b1b-1b1b-1b1b-1b1b-1b1b1b1b1b1b - бібліотека в java для генерації унікальних ідентифікаторів

    private final String POST_TITLE = "TR003_pervushyna " + utils.Utils.getDateAndTimeFormatted();

    @Test
    public void TR003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillingFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post from Kait")
                //.selectTextInDropdownAccessByVisibleText("Приватне повідомлення")
                .selectValueInDropdownAccess ("One Person")
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
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)
        ;

    }
}