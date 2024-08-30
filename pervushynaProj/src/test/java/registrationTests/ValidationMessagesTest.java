package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationMessagesTest extends BaseTest {

    final String SHORT_USER_NAME_MESSAGE = "Username must be at least 3 characters.";
    final String SHORT_EMAIL_MESSAGE = "You must provide a valid email address.";
    final String SHORT_PASSWORD_MESSAGE = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";


    @Test
    public void TR005_validationMessagesTest() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField("TR")
                .enterTextIntoRegistrationEmailField("TR")
                .enterTextIntoRegistrationPasswordField("TR")
                .checkErrorsMessage(SHORT_USER_NAME_MESSAGE + SEMICOLON + SHORT_EMAIL_MESSAGE + SEMICOLON + SHORT_PASSWORD_MESSAGE)

        ;

    }
}
