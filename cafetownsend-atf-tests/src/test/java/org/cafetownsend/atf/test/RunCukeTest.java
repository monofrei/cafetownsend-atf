package org.cafetownsend.atf.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        junit = "--step-notifications",
        features = {"src/test/resources/features"},
        plugin = {
                "json:target/jsonReports/cucumber.json",
                "org.cafetownsend.atf.formatter.CucumberLogsFormatter"
        },
        glue = {
                "org.cafetownsend.atf.test.steps",
                "org.cafetownsend.atf.test.hooks",
                "org.cafetownsend.atf.test.config"
        }
)
public class RunCukeTest {
}

