package signOutTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SignOutTest extends BaseTest {

    @Before
    public void validLogin() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred();

        Assert.assertTrue("Search button is not visible",
                pageProvider.getHomePage().getHeaderElement().isSearchButtonVisible());

        Assert.assertTrue("Chat button is not visible",
                pageProvider.getHomePage().getHeaderElement().isChatButtonVisible());

        Assert.assertTrue("Button MyProfile is not visible",
                pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());

        Assert.assertTrue("Button Create Post is not visible",
                pageProvider.getHomePage().getHeaderElement().isButtonCreatePostVisible());

        Assert.assertTrue("Button Sign Out is not visible",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
    }

    @Test
    public void TR004_signout() {
        pageProvider.getHomePage()
                .getHeaderElement()
                .clickOnSignOutButton();

        Assert.assertFalse("Search button is visible",
                pageProvider.getHomePage().getHeaderElement().isSearchButtonVisible());

        Assert.assertFalse("Chat button is visible",
                pageProvider.getHomePage().getHeaderElement().isChatButtonVisible());

        Assert.assertFalse("Button MyProfile is visible",
                pageProvider.getHomePage().getHeaderElement().isButtonMyProfileVisible());

        Assert.assertFalse("Button Create Post is visible",
                pageProvider.getHomePage().getHeaderElement().isButtonCreatePostVisible());

        Assert.assertFalse("Button Sign Out is visible",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutVisible());
    }

    @Test
    public void HW6_verifyLogoutInTwoTabs() {
        pageProvider.getHomePage()
                .createNewBrowserTab()
                .gotoToNewBrowserTab()
                .openLoginPage()
                .getHeaderElement().checkIsButtonSighOutVisible()
                .getHomePage()
                .getBackToFirstBrowserTab()
                .getHeaderElement().clickOnSignOutButton()
                .getHeaderElement().checkIsButtonSighOutNotVisible()
                .getLoginPage()
                .openLoginPage()
                .gotoToNewBrowserTab()
                .reloadPageContent()
                .getHeaderElement().checkIsButtonSighOutNotVisible();
    }

}