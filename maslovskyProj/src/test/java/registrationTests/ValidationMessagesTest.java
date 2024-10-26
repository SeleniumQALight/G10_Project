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

    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void TC023_validationMessagesTest(
            String tcName, UserForRegistration userForRegistration, String expectedMessages) {
        logger.info(tcName);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userForRegistration.getUserName())
                .enterTextIntoRegistrationEmailField(userForRegistration.getEmail())
                .enterTextIntoRegistrationPasswordField(userForRegistration.getPassword())
//                .checkErrorsMessages(SHORT_USER_NAME_MESSAGE + SEMICOLON + SHORT_EMAIL_MESSAGE + SEMICOLON + SHORT_PASSWORD_MESSAGE)
                .checkErrorsMessages(expectedMessages)
        ;
    }

    public Object[][] parametersForValidationMessagesTest() {
        return new Object[][] {
//                {new UserForRegistration("TC023").updateUserName(), twoChars, twoChars, ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"Login - valid, Email - not valid, Password - not valid",
                        new UserForRegistration("TC023").updateEmail(SHORT_EMAIL_NOT_VALID).updatePassword(SHORT_PASSWORD_NOT_VALID),
                        ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"Login - valid, Email - valid, password - NOT valid"
                        , new UserForRegistration("TC023").updatePassword(SHORT_PASSWORD_NOT_VALID)
                        , ERROR_PASSWORD},
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid"
                        , new UserForRegistration(deleteSomeSymbols(USER_NAME_MIN_LENGTH, 1), SHORT_EMAIL_NOT_VALID, SHORT_PASSWORD_NOT_VALID)
                        , ERROR_USERNAME +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD },
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid"
                        , new UserForRegistration(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, SHORT_PASSWORD_NOT_VALID)
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"Login - valid, Email -  NOT valid, password - valid"
                        , new UserForRegistration(USER_NAME_MAX_LENGTH, SHORT_EMAIL_NOT_VALID, PASSWORD_MIN_LENGTH)
                        , ERROR_EMAIL},
                {"Login -  NOT valid, Email -  NOT valid, password - valid"
                        , new UserForRegistration(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, PASSWORD_MAX_LENGTH)
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL},
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid"
                        , new UserForRegistration(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, PASSWORD_MAX_LENGTH + "2".repeat(2))
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_LONG_PASSWORD}
        };
    }

//    @Test
//    @Parameters(method = "parametersForValidationMessagesTest")
//    public void HW6_invalidRegistrationUsingKeyboardKeys
//            (String userName, String email, String password, String expectedMessages) {
//        pageProvider.getLoginPage().openLoginPage()
//                .tabPressing(5)
//                .enterText(userName)
//                .tabPressing(1)
//                .enterText(email)
//                .tabPressing(1)
//                .enterText(password)
//                .tabPressing(1)
//                .checkErrorsMessages(expectedMessages);
//    }

}
