package org.cafetownsend.atf.test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.cafetownsend.atf.test.config.UIScenarioContext;
import org.cafetownsend.atf.test.steps.actions.PageActions;
import org.cafetownsend.atf.ui.pages.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.cafetownsend.atf.assertion.AtfAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@Slf4j
public class BasicSteps {
    //TODO to actions

    @Autowired
    private PageActions pageActions;

    @Autowired
    private UIScenarioContext context;

    @Given("^user opens the (.*) page$")
    public void openPageByName(String pageName) {
        pageActions.openPage(pageName);
    }

    @When("^user logs in using the credentials '(.*)':'(.*)'$")
    public void logInWithCredentials(String username, String password) {
        LoginPage loginPage = context.getPageFactory().createPage(LoginPage.class);
        loginPage.login(username, password);
    }

    @Then("^user is redirected to (.*) page$")
    public void pageIsOpened(String pageName) {
        assertThat("current page is " + pageName, pageActions.pageIsOpened(pageName), is(true));
    }


}
