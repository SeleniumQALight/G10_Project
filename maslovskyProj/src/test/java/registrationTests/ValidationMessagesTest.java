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
    public void TC023_validationMessagesTest(
            String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
//                .checkErrorsMessages(SHORT_USER_NAME_MESSAGE + SEMICOLON + SHORT_EMAIL_MESSAGE + SEMICOLON + SHORT_PASSWORD_MESSAGE)
                .checkErrorsMessages(expectedMessages)
        ;
    }

    public Object[][] parametersForValidationMessagesTest() {
        return new Object[][] {
                {twoChars, twoChars, twoChars, SHORT_USER_NAME_MESSAGE + SEMICOLON + SHORT_EMAIL_MESSAGE + SEMICOLON + SHORT_PASSWORD_MESSAGE},
                {"twoChars", twoChars, twoChars, SHORT_EMAIL_MESSAGE + SEMICOLON + SHORT_PASSWORD_MESSAGE}
        };
    }

    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void HW6_invalidRegistrationUsingKeyboardKeys
            (String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage()
                .tabPressing(5)
                .enterText(userName)
                .tabPressing(1)
                .enterText(email)
                .tabPressing(1)
                .enterText(password)
                .tabPressing(1)
                .checkErrorsMessages(expectedMessages);
    }

}
