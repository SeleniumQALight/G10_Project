package postTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

public class ChangeTitleInCreatedPostTest extends BaseTest {
    private final String POST_TITLE = "TR004_leonov" + Utils.getDateAndTimeFormatted();
    private final String NEW_POST_TITLE = POST_TITLE + "_changed";

    @Before
    public void createPost() {
        pageProvider.getHomePage().openHomepageAndLoginIfNeeded().clickOnButtonCreatePost()
                .createTestPost(POST_TITLE, "Body of the post").checkIsSuccessMessageDisplayed()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectOnProfilePage().checkPostWithTitleIsPresent(POST_TITLE, 1);

    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomepageAndLoginIfNeeded()
                .getHeader().clickOnButtonMyProfile()
                .checkIsRedirectOnProfilePage()
                .deletePostTillPresent(POST_TITLE)
                .deletePostTillPresent(NEW_POST_TITLE);
        ;
    }

    @Test
    public void TR_004_changeTitleInCreatedPost() {
        pageProvider.getHomePage().openHomepageAndLoginIfNeeded().getHeader().clickOnButtonMyProfile()
                .checkIsRedirectOnProfilePage()
                .clickOnPostWithTitle(POST_TITLE)
                .clickOnEditButton()
                .editPostTitle(NEW_POST_TITLE)
                .checkTextInSuccessMessage("Post successfully updated.")
                .getHeaderElement().clickOnButtonMyProfile()
                .checkPostWithTitleIsPresent(NEW_POST_TITLE, 1)
                .checkPostWithTitleIsPresent(POST_TITLE, 0)
                .deletePostTillPresent(POST_TITLE).deletePostTillPresent(NEW_POST_TITLE);


    }
}
