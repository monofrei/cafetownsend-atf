package org.cafetownsend.atf.test.hooks;

import cucumber.api.Result;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.cafetownsend.atf.ui.config.UIScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UIHooks {

    @Autowired
    private UIScenarioContext context;

    @Before
    public void before() {
        context.initPageFactory();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.getStatus().equals(Result.Type.FAILED)) {
            //TODO add screenshot on fail
        }
        context.getCurrentBrowser().quit();
    }
}
