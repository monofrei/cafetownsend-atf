package org.cafetownsend.atf.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = {"@run"},
        features = {"src/test/resources/features"},
        plugin = {
                "json:target/cucumber.json",
                "org.cafetownsend.atf.formatter.CucumberLogsFormatter"
        },
        glue = {
                "org.cafetownsend.atf.test.steps",
                "org.cafetownsend.atf.test.hooks"
        }
)
public class RunCukeTest {}
