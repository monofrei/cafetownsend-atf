package org.cafetownsend.atf.test.steps;

import cucumber.api.java.en.Given;
import org.cafetownsend.atf.models.Employee;
import org.cafetownsend.atf.test.steps.actions.PageActions;
import org.cafetownsend.atf.test.utils.TestDataHandler;
import org.cafetownsend.atf.ui.pages.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginSteps {

    @Autowired
    private BasicSteps basicSteps;

    @Autowired
    private PageActions pageActions;

    @Autowired
    private TestDataHandler dataHandler;

    @Given("user is logged into the application")
    public void userIsLoggedIntoTheApplication() {
        Employee adminUser = dataHandler.getAdminUser();
        pageActions.openPage(LoginPage.class);
        basicSteps.logInWithCredentials(adminUser.getUsername(), adminUser.getPassword());
        pageActions.pageIsOpened(LoginPage.class);
    }
}
