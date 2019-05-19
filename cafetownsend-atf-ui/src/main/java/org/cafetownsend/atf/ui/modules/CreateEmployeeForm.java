package org.cafetownsend.atf.ui.modules;

import org.cafetownsend.atf.models.Employee;
import org.testmonkeys.maui.pageobjects.ElementAccessor;
import org.testmonkeys.maui.pageobjects.elements.Button;

public class CreateEmployeeForm extends EmployeeDetails {

    @ElementAccessor(elementName = "Add", byXPath = ".//button[text()='Add']")
    private Button add;

    @ElementAccessor(elementName = "Cancel", byXPath = "./preceding-sibling::ul[@id='sub-nav']/li/a[@class='subButton bCancel']")
    private Button cancel;

    public void cancel() {
        this.cancel.click();
    }

    public void addEmployee() {
        this.add.click();
    }

    public void addEmployee(Employee employee) {
        this.fillInEmployeeDetail(employee);
        this.add.click();
    }

}
