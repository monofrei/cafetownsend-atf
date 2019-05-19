package org.cafetownsend.atf.ui.modules;

import org.cafetownsend.atf.models.Employee;
import org.testmonkeys.maui.pageobjects.ElementAccessor;
import org.testmonkeys.maui.pageobjects.elements.Input;
import org.testmonkeys.maui.pageobjects.modules.AbstractModule;

public class EmployeeDetails extends AbstractModule {

    @ElementAccessor(elementName = "First Name", byXPath = ".//input[@type='text' and @ng-model='selectedEmployee.firstName']")
    protected Input firstName;

    @ElementAccessor(elementName = "Last Name", byXPath = ".//input[@type='text' and @ng-model='selectedEmployee.lastName']")
    protected Input lastName;

    @ElementAccessor(elementName = "Start Date", byXPath = ".//input[@type='text' and @ng-model='selectedEmployee.startDate']")
    protected Input startDate;

    @ElementAccessor(elementName = "Email", byXPath = ".//input[@type='email' and @ng-model='selectedEmployee.email']")
    protected Input email;

    public void fillInEmployeeDetail(Employee employee) {
        this.firstName.type(employee.getFirstName());
        this.lastName.type(employee.getLastName());
        this.startDate.type(employee.getStartDate().toString());
        this.email.type(employee.getEmail());
    }

}
