package registrationTests;

import baseBase.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTest extends BaseTest {
    final String SHORT_USER_NAME_MESSAGE = "Username must be at least 3 characters.";
    final String SHORT_PASSWORD_MESSAGE = "Password must be at least 12 characters.";
    final String SHORT_EMAIL_MESSAGE = "You must provide a valid email address.";
    final String SEMICOLON = ";";
    final String twoChars = "tr";

    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void TC023_ValidationMessagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextRegistrationNameField(userName)
                .enterTextRegistrationEmailField(email)
                .enterTextRegistrationPasswordField(password)
                .checkErrorMessageForRegistrationForm(expectedMessages);
    }

    public Object[][] parametersForValidationMessagesTest(){
        return new Object[][]{
                {twoChars, twoChars, twoChars, SHORT_USER_NAME_MESSAGE + SEMICOLON + SHORT_EMAIL_MESSAGE + SEMICOLON + SHORT_PASSWORD_MESSAGE},
                {"Andriy124", twoChars, twoChars, SHORT_EMAIL_MESSAGE + SEMICOLON + SHORT_PASSWORD_MESSAGE}
        };
    }
}
