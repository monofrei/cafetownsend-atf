package org.cafetownsend.atf.ui.modules;

import org.cafetownsend.atf.models.Employee;
import org.cafetownsend.atf.ui.elements.ValidatedInput;
import org.cafetownsend.atf.ui.pages.Validatable;
import org.testmonkeys.maui.pageobjects.ElementAccessor;
import org.testmonkeys.maui.pageobjects.modules.AbstractModule;

public class EmployeeDetails extends AbstractModule implements Validatable {

    @ElementAccessor(elementName = "First Name", byXPath = ".//input[@type='text' and @ng-model='selectedEmployee.firstName']")
    protected ValidatedInput firstName;

    @ElementAccessor(elementName = "Last Name", byXPath = ".//input[@type='text' and @ng-model='selectedEmployee.lastName']")
    protected ValidatedInput lastName;

    @ElementAccessor(elementName = "Start Date", byXPath = ".//input[@type='text' and @ng-model='selectedEmployee.startDate']")
    protected ValidatedInput startDate;

    @ElementAccessor(elementName = "Email", byXPath = ".//input[@type='email' and @ng-model='selectedEmployee.email']")
    protected ValidatedInput email;

    public void fillInEmployeeDetail(Employee employee) {
        this.firstName.type(employee.getFirstName());
        this.lastName.type(employee.getLastName());
        this.startDate.type(employee.getStartDate().toString());
        this.email.type(employee.getEmail());
    }

    public Employee getEmployeeDetails() {
        return new Employee(firstName.getText(),
                lastName.getText(),
                startDate.getText(),
                email.getText());
    }

//    public boolean hasValidationErrors(String detail) {
//
//        ValidatedInput input = ReflectionUtils.getFieldInstanceByName(detail, this);
//
//        return !Objects.equals(input, null) && input.hasValidationErrors();
//    }
}
