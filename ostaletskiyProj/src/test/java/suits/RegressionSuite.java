package suits;


import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.validator.ValidateWith;
import postTests.CreateNewPostTest;
import registrationTests.ValidationMessagesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostTest.class,
        ValidationMessagesTest.class
        //TODO Add all classes with tests
})
public class RegressionSuite {




}
