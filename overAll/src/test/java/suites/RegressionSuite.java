package suites;


import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTests.CreateNewPostTest;
import registrationTests.ValidationMessagesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostTest.class,
        ValidationMessagesTest.class
        //TODO add all classes with test
})
public class RegressionSuite {
}
