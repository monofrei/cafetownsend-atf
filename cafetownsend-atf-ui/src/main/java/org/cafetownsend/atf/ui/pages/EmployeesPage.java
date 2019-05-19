package org.cafetownsend.atf.ui.pages;

import lombok.Getter;
import org.cafetownsend.atf.models.Employee;
import org.cafetownsend.atf.ui.modules.CreateEmployeeForm;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
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
    @Getter
    private CreateEmployeeForm createEmployeeForm;

    public CreateEmployeeForm openCreateForm() {
        this.create.click();
        return this.createEmployeeForm;
    }

    public void fillInEmployeeDetails(Employee employee) {
        this.createEmployeeForm.fillInEmployeeDetail(employee);
    }

    public List<String> getEmployeeNames() {
        this.getBrowser().getDynamicWaiter()
                .until(ExpectedConditions
                        .numberOfElementsToBeMoreThan(this.employees
                                .getLocator().getSeleniumLocator(), 1));
        List<Button> all = this.employees.getAll();

        return all.stream().map(Button::getText).collect(Collectors.toList());
    }

    public void deleteEmployee(List<String> names) {
        List<Button> all = this.employees.getAll();
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
            Alert alert = this.getBrowser().getDriver().switchTo().alert();

            if (!alert.getText().startsWith("Are you sure you want to delete"))
                throw noAlertPresentException;

            return alert;
        } catch (NoAlertPresentException e) {
            throw noAlertPresentException;
        }
    }
}
