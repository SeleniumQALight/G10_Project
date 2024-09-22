package suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTest.ValidationMessagesTest;
import registrationTest.ValidationMessagesWithoutSomeFieldsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ValidationMessagesTest.class,
        ValidationMessagesWithoutSomeFieldsTest.class
})
public class RegistrationSuite {

}
