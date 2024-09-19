package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTests.ValidationMessagesTest;
import registrationTests.ValidationMessagesWithOutSomeFieldsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ValidationMessagesTest.class,
        ValidationMessagesWithOutSomeFieldsTest.class
})
public class RegistrationSuite {

}
