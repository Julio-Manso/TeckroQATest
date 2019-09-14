package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:cucumber-features/TeckroCucumber_Web.feature",
        glue = {
                "ie.teckro.steps"
        },
        plugin = {
                "pretty",
                "json:target/cucumber_jsons/TeckroCucumber_Web.json"
        },
        monochrome = true)
public class TeckroCucumberWebTest {
}
