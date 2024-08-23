package postTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import utils.Utils;

public class CreateNewPostTest extends BaseTest {
    // GUID - бібліотека для генерації унікальних значень
    private final String POST_TITLE = "TR003_leonov" + Utils.getDateAndTimeFormatted();

    @Test
    public void TR_003_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post")
                // setCheckbox to true (create methods in CommonActionsWithElements)
//                .selectTextInDropDownAccessByVisibleText("Приватне повідомлення")
                .checkboxStateOnPostPage("uncheck")
                .selectInDropDownAccessByValue("One Person")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextThisPostWasWrittenIsVisible("One Person")
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectOnProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomepageAndLoginIfNeeded()
                .getHeader().clickOnButtonMyProfile()
                .checkIsRedirectOnProfilePage()
                .deletePostTillPresent(POST_TITLE)
        ;
    }


}
