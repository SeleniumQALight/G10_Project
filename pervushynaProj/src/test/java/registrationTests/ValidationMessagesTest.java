package registrationTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)

public class ValidationMessagesTest extends BaseTest {

    final String SHORT_USER_NAME_MESSAGE = "Username must be at least 3 characters.";
    final String SHORT_EMAIL_MESSAGE = "You must provide a valid email address.";
    final String SHORT_PASSWORD_MESSAGE = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";
    final String twoChars = "tr";


    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void TR005_validationMessagesTest(String userName, String email, String password, String expectedMessage) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessage(expectedMessage)
        ;
    }

    public Object[][] parametersForValidationMessagesTest() {
        return new Object[][]{
                {twoChars, twoChars, twoChars,
                          SHORT_USER_NAME_MESSAGE + SEMICOLON
                        + SHORT_EMAIL_MESSAGE + SEMICOLON
                        + SHORT_PASSWORD_MESSAGE},
                {"kait123", twoChars, twoChars,
                          SHORT_EMAIL_MESSAGE + SEMICOLON
                        + SHORT_PASSWORD_MESSAGE},

        };
    }

}
