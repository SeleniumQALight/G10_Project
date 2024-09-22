package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTests.ValidationMessagesTest;
import registrationTests.ValidationMessagesWithoutSomeFieldsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ValidationMessagesTest.class,
        ValidationMessagesWithoutSomeFieldsTest.class
})
public class RegistrationSuite {

}
