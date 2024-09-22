package registrationTests;

import baseTest.BaseTest;
import data.UserForRegistration;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;

import static data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesWithoutSomeFieldsTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void TC021_ValidationMessagesTest(String tcName,
                                             UserForRegistration user,
                                             String expectedMessages) {
        logger.info(tcName);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterRegistrationIfNotNull(user)
                .clickOnSignUpButton();
        pageProvider.getLoginPage().checkErrorsMessages(expectedMessages);

    }

    public Object[][] parametersForValidationMessagesTest() {
        return new Object[][]{
                {"Fill only Email and Password" ,new UserForRegistration("TC021").updateUserName(null)
                        , ERROR_USERNAME},
                {"Fill only UserName and Password", new UserForRegistration("TC021").updateEmail(null)
                        , ERROR_EMAIL},
                {"Fill only UserName and Email ", new UserForRegistration("TC021").updatePassword(null)
                        , ERROR_PASSWORD},
        };
    }

}
