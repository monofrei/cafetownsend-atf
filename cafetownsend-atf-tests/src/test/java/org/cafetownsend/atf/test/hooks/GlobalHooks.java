package org.cafetownsend.atf.test.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.cafetownsend.atf.appender.CucumberScenarioContext;
import org.cafetownsend.atf.test.config.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfiguration.class)
@Slf4j
public class GlobalHooks {

    @Before
    public void before(Scenario scenario) {
        /*Initialize the cucumber context with the current scenario
         * This is required for the Cucumber Logs appender to work.
         * */
        CucumberScenarioContext.getInstance().setScenario(scenario);
    }
}
