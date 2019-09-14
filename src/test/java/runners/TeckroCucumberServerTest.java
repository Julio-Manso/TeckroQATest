package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:cucumber-features/TeckroCucumber_Server.feature",
        glue = {
                "ie.teckro.steps"
        },
        plugin = {
                "pretty",
                "json:target/cucumber_jsons/TeckroCucumber_Server.json"
        },
        monochrome = true)
public class TeckroCucumberServerTest {
}
