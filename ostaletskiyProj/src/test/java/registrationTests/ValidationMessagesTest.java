package registrationTests;

import baseBase.BaseTest;
import org.junit.Test;

public class ValidationMessagesTest extends BaseTest {
    final String SHORT_USER_NAME_MESSAGE = "Username must be at least 3 characters.";
    final String SHORT_PASSWORD_MESSAGE = "Password must be at least 12 characters.";
    final String SHORT_EMAIL_MESSAGE = "You must provide a valid email address.";
    final String SEMICOLON = ";";


    @Test
    public void TC023_ValidationMessagesTest() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextRegistrationNameField("tr")
                .enterTextRegistrationEmailField("tr")
                .enterTextRegistrationPasswordField("tr")
                .checkErrorMessageForRegistrationForm(SHORT_USER_NAME_MESSAGE + SEMICOLON + SHORT_EMAIL_MESSAGE + SEMICOLON + SHORT_PASSWORD_MESSAGE)

                ;


    }

}
