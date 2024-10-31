import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "bdd/stepDefinitions",
        //add R001 and R009 tags to run only these scenarios
        tags = "@R001",
        plugin = {"pretty", "html:target/cucumber.html"}
)
public class RunCucumberTest {
}
