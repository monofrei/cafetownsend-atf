package org.cafetownsend.atf.test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.cafetownsend.atf.models.Employee;
import org.cafetownsend.atf.ui.config.UIScenarioContext;
import org.cafetownsend.atf.ui.pages.EmployeesPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.cafetownsend.atf.assertion.AtfAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateEmployeeSteps {

    @Autowired
    private UIScenarioContext uiScenarioContext;

    @Given("user opens the Create Employee form")
    public void userOpensCreateForm() {
        EmployeesPage employeesPage = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);
        employeesPage.openCreateForm();
    }

    @When("user fills in all the employee details")
    public void userFillsInAllTheEmployeeDetails(Employee employee) {
        EmployeesPage employeesPage = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);
        employeesPage.fillInEmployeeDetails(employee);
    }

    @When("user approves the creation of the new user")
    public void userApprovesTheCreationOfTheNewUser() {
        EmployeesPage employeesPage = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);
        employeesPage.getCreateEmployeeForm().addEmployee();
    }

    @Then("the employee (.*) (.*) is present in the employee list")
    public void newCreatedEmployeeIsPresentInTheList(String firstName, String lastName) {
        EmployeesPage employeesPage = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);

        List<String> employeeNames = employeesPage.getEmployeeNames();
        assertThat("Employee list is not empty", employeeNames, not(empty()));

        String expected = String.format("%s %s", firstName, lastName);
        assertThat(String.format("employee '%s' is present in the list", expected), employeeNames, hasItem(expected));
    }

    @Given("the employees are deleted from the list")
    public void theEmployeesAreDeletedFromTheList(List<String> employeeNames) {
        EmployeesPage employeesPage = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);
        employeesPage.deleteEmployee(employeeNames);
    }
}
