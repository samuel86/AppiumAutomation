package com.vytrack.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/vytrack/step_definitions",
        features = "src/test/resources/features/activities",
        dryRun = false,
        strict = false,
//        to exclude smoke test scenarios
        tags = "@smoke_test",
        plugin = {
                "html:target/default-report-for-regression",
                "json:target/cucumber_regression.json",
                "rerun:target/rerun_regression.txt"
        }

)
public class RegressionRunner {
}
