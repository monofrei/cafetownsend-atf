package org.cafetownsend.atf.test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.cafetownsend.atf.models.Employee;
import org.cafetownsend.atf.test.config.UIScenarioContext;
import org.cafetownsend.atf.test.steps.actions.PageActions;
import org.cafetownsend.atf.test.utils.TestDataHandler;
import org.cafetownsend.atf.ui.pages.EmployeesPage;
import org.cafetownsend.atf.ui.pages.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.cafetownsend.atf.assertion.AtfAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthenticationSteps {

    @Autowired
    private BasicSteps basicSteps;

    @Autowired
    private PageActions pageActions;

    @Autowired
    private TestDataHandler dataHandler;

    @Autowired
    private UIScenarioContext scenarioContext;

    @Given("user is logged into the application")
    public void userIsLoggedIntoTheApplication() {
        Employee adminUser = dataHandler.getAdminUser();
        pageActions.openPage(LoginPage.class);
        basicSteps.logInWithCredentials(adminUser.getUsername(), adminUser.getPassword());
        pageActions.pageIsOpened(LoginPage.class);
    }

    @Then("the greetings displays {string}")
    public void theGreetingsDisplayTheText(String expected) {
        EmployeesPage page = scenarioContext.getPageFactory().createPage(EmployeesPage.class);
        assertThat(" greetings text is " + expected, page.getGreetings(), is(expected));
    }

    @When("user logs out from the application")
    public void userLogsOutFromTheApplication() {
        EmployeesPage page = scenarioContext.getPageFactory().createPage(EmployeesPage.class);
        page.logout();
    }

    @Then("the application highlights the Login (.*) as invalid")
    public void theApplicationHighlightsTheElementAsInvalid(String field) {
        LoginPage page = scenarioContext.getPageFactory().createPage(LoginPage.class);
        boolean actual = page.hasValidationErrors(field);
        assertThat("field [" + field + "] is highlighted as invalid", actual, is(true));
        scenarioContext.makeScreenshot();
    }

    @Then("the application displays message {string}")
    public void theApplicationDisplaysMessageInvalidUsernameOrPassword(String message) {
        LoginPage page = scenarioContext.getPageFactory().createPage(LoginPage.class);
        assertThat("error message is displayed on login form", page.errorMessageIsDisplayed(), is(true));
        assertThat("error message is [" + message + "]", page.getErrorMessage(), is(message));
        scenarioContext.makeScreenshot();
    }
}
