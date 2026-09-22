package tests.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "tests",
        tags = "@ui",
        plugin = {"pretty"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}