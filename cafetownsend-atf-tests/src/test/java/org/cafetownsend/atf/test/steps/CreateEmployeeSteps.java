package org.cafetownsend.atf.test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.cafetownsend.atf.models.Employee;
import org.cafetownsend.atf.ui.config.UIScenarioContext;
import org.cafetownsend.atf.ui.pages.EmployeesPage;
import org.openqa.selenium.Alert;
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
        employeesPage.createEmployeeForm().addEmployee();
    }

    @Then("the employee {string} is present in the employee list")
    public void newCreatedEmployeeIsPresentInTheList(String expected) {
        EmployeesPage employeesPage = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);

        List<String> employeeNames = employeesPage.getEmployeeNames();
        assertThat("Employee list is not empty", employeeNames, not(empty()));

        assertThat(String.format("employee '%s' is present in the list", expected), employeeNames, hasItem(expected));
    }

    @Then("the application displays the creation error alert window with message '(.*)'")
    public void theApplicationDisplaysTheAlertWindowWithMessage(String message) {
        EmployeesPage page = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);
        Alert creationErrorAlert = page.getCreationErrorAlert();
        assertThat(String.format("aler %s is displayed", message), creationErrorAlert.getText(), is(message));
    }

    @When("user closes the creation error alert")
    public void userClosesTheErrorAlert() {
        EmployeesPage page = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);
        page.closeCreationErrorAlert();
    }

    @When("user closes the Create User page")
    public void userClosesTheCreateUserPage() {
        EmployeesPage employeesPage = uiScenarioContext.getPageFactory().createPage(EmployeesPage.class);
        employeesPage.createEmployeeForm().cancel();
    }
}
