package smoke;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/resources/features",
        glue = "smoke",
        plugin = "pretty"
)

public class SmokeTestRunner extends AbstractTestNGCucumberTests {
}