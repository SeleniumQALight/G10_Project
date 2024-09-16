package suits;

import categories.SmokeTestFilter;
import loginTests.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTests.CreateNewPostTest;
import registrationTests.ValidationMessagesTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        ValidationMessagesTest.class,
        CreateNewPostTest.class
})
public class SmokeSuite {
}
