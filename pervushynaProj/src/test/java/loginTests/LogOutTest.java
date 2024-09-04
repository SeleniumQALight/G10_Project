package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LogOutTest extends BaseTest {

    @Test
    public void TR004_logOut() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillingFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonSignOut()
                .checkIsRedirectToLoginPage()
        ;
    }


}
