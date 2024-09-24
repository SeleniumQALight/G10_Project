package registrationTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import data.UserForRegistration;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import static data.RegistrationValidationMessages.*;
import static data.UserForRegistration.*;
import static utils.StringUtils.deleteSomeSymbols;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class ValidationMessagesTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());
    final String SHORT_USER_NAME_MESSAGE = "Username must be at least 3 characters.";
    final String SHORT_EMAIL_MESSAGE = "You must provide a valid email address.";
    final String SHORT_PASSWORD_MESSAGE = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void TC023_ValidationMessages(String tcName, UserForRegistration userForRegistration, String expectedMessages) {
        logger.info(tcName);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userForRegistration.getUserName())
                .enterTextIntoRegistrationEmailField(userForRegistration.getEmail())
                .enterTextIntoRegistrationPasswordField(userForRegistration.getPassword())
                .checkErrorsMessages(expectedMessages);
    }

    public Object[][] parametersForValidationMessagesTest() {
        return new Object[][]{
                {"Login - valid, email - not valid, password - not valid",
                        new UserForRegistration("TC023").updateEmail(SHORT_EMAIL_NOT_VALID).updatePassword(SHORT_PASSWORD_NOT_VALID)
                        ,  ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}
                ,
                {"Login - valid, Email - valid, password - NOT valid", new UserForRegistration("TC023").updatePassword(SHORT_PASSWORD_NOT_VALID)
                        , ERROR_PASSWORD},
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid", new UserForRegistration(deleteSomeSymbols(USER_NAME_MIN_LENGTH, 1), SHORT_EMAIL_NOT_VALID, SHORT_PASSWORD_NOT_VALID)
                        , ERROR_USERNAME +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD },
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid", new UserForRegistration(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, SHORT_PASSWORD_NOT_VALID)
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"Login - valid, Email -  NOT valid, password - valid", new UserForRegistration(USER_NAME_MAX_LENGTH, SHORT_EMAIL_NOT_VALID, PASSWORD_MIN_LENGTH)
                        , ERROR_EMAIL},
                {"Login -  NOT valid, Email -  NOT valid, password - valid", new UserForRegistration(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, PASSWORD_MAX_LENGTH)
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL},
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid", new UserForRegistration(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, PASSWORD_MAX_LENGTH + "2".repeat(2))
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_LONG_PASSWORD}
        };
    }
}
