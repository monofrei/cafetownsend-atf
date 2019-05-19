package org.cafetownsend.atf.ui.pages;

import org.cafetownsend.atf.models.Employee;
import org.cafetownsend.atf.ui.modules.CreateEmployeeForm;
import org.cafetownsend.atf.ui.modules.EditEmployeeDetails;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testmonkeys.maui.pageobjects.ElementAccessor;
import org.testmonkeys.maui.pageobjects.PageAccessor;
import org.testmonkeys.maui.pageobjects.elements.Button;
import org.testmonkeys.maui.pageobjects.elements.GroupComponent;

import java.util.List;
import java.util.stream.Collectors;


@PageAccessor(name = "Employees", url = "/employees")
public class EmployeesPage extends CafeAbstractPage {

    @ElementAccessor(elementName = "Create", byXPath = "//a[@id='bAdd']")
    private Button create;

    @ElementAccessor(elementName = "Edit", byXPath = "//a[@id='bEdit']")
    private Button edit;

    @ElementAccessor(elementName = "Delete", byXPath = "//a[@id='bDelete']")
    private Button delete;

    @ElementAccessor(elementName = "Employees List", byXPath = "//ul[@id='employee-list']/li")
    private GroupComponent<Button> employees;

    @ElementAccessor(elementName = "Create Employee Form", byXPath = "//form[@name='employeeForm']")
    private CreateEmployeeForm createEmployeeForm;

    @ElementAccessor(elementName = "Edit Employee Form", byXPath = "//form[@name='employeeForm']")
    private EditEmployeeDetails editEmployeeForm;

    public CreateEmployeeForm openCreateForm() {
        this.create.click();
        return this.createEmployeeForm;
    }

    public void fillInEmployeeDetails(Employee employee) {
        this.createEmployeeForm.fillInEmployeeDetail(employee);
    }

    private List<Button> getAllEmployees() {
        this.getBrowser().getDynamicWaiter()
                .until(ExpectedConditions
                        .numberOfElementsToBeMoreThan(this.employees
                                .getLocator().getSeleniumLocator(), 1));
        return this.employees.getAll();
    }

    public List<String> getEmployeeNames() {
        List<Button> all = getAllEmployees();

        return all.stream().map(Button::getText).collect(Collectors.toList());
    }

    public void deleteEmployee(List<String> names) {
        List<Button> all = getAllEmployees();
        for (Button employee : all) {
            String text = employee.getText();
            if (!names.contains(text)) continue;

            employee.click();
            this.delete.click();
            approveEmployeeDeletion(text);
        }
    }

    public void approveEmployeeDeletion(String employeeName) {
        getEmployeeDeletionAlert().accept();
    }

    public void cancelEmployeeDeletion(String employeeName) {
        getEmployeeDeletionAlert().dismiss();
    }

    private Alert getEmployeeDeletionAlert() {
        NoAlertPresentException noAlertPresentException = new NoAlertPresentException("The Delete Employee alert is not present");

        try {
            this.getBrowser().getDynamicWaiter().until(ExpectedConditions.alertIsPresent());
            Alert alert = this.getBrowser().getDriver().switchTo().alert();

            if (!alert.getText().startsWith("Are you sure you want to delete"))
                throw noAlertPresentException;

            return alert;
        } catch (NoAlertPresentException e) {
            throw noAlertPresentException;
        }
    }

    public EditEmployeeDetails openEmployeeDetails(String name) {
        List<Button> all = getAllEmployees();
        for (Button employee : all) {
            if (!employee.getText().equals(name)) continue;
            Actions actions = new Actions(this.getBrowser().getDriver());
            actions.doubleClick(employee.find()).perform();
            return this.editEmployeeForm;
        }
        return null;
    }

    public CreateEmployeeForm createEmployeeForm() {
        return this.createEmployeeForm;
    }

    public EditEmployeeDetails editEmployeeDetails() {
        return this.editEmployeeForm;
    }
}
