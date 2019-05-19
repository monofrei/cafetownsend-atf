package org.cafetownsend.atf.test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.cafetownsend.atf.models.Employee;
import org.cafetownsend.atf.test.config.UIScenarioContext;
import org.cafetownsend.atf.ui.pages.EmployeesPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.cafetownsend.atf.assertion.AtfAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EmployeesSteps {

    @Autowired
    private UIScenarioContext uiScenarioContext;

    @Given("the employees are deleted from the list")
    public void theEmployeesAreDeletedFromTheList(List<String> employeeNames) {
        EmployeesPage employeesPage = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);
        employeesPage.deleteEmployee(employeeNames);
    }

    @When("user opens the employee {string} details")
    public void userOpensTheEmployeeFirstNameLastNameDetails(String name) {
        EmployeesPage employeesPage = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);
        employeesPage.openEmployeeDetails(name);
    }

    @Then("the employee details are:")
    public void theEmployeeDetailsAre(Employee expected) {
        EmployeesPage page = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);
        Employee employeeDetails = page.editEmployeeForm().getEmployeeDetails();

        assertThat("employee details are[" + expected + "]", expected, is(employeeDetails));
    }

    @Then("the user {string} is not present in the list")
    public void theUserFirstNameLastNameIsNotPresentInTheList(String name) {
        EmployeesPage employeesPage = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);

        List<String> employeeNames = employeesPage.getEmployeeNames();

        assertThat(String.format("employee '%s' is not present in the list", name), employeeNames, not(hasItem(name)));
    }
}
