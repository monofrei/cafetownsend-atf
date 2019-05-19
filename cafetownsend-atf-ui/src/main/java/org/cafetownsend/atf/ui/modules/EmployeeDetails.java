package org.cafetownsend.atf.ui.modules;

import org.cafetownsend.atf.models.Employee;
import org.testmonkeys.maui.pageobjects.ElementAccessor;
import org.testmonkeys.maui.pageobjects.elements.Input;
import org.testmonkeys.maui.pageobjects.elements.html.HtmlAttribute;
import org.testmonkeys.maui.pageobjects.modules.AbstractModule;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

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

    public Employee getEmployeeDetails() {
        return new Employee(firstName.getText(),
                lastName.getText(),
                startDate.getText(),
                email.getText());
    }

    public boolean isInvalid(String detail) {
        try {
            Field declaredField = this.getClass().getSuperclass().getDeclaredField(detail);
            Input input = (Input) declaredField.get(this);
            List<HtmlAttribute> attributes = input.getHtmlElement().getAttributes();
            Optional<HtmlAttribute> clazz = attributes.stream().filter(a -> a.getName().equals("class")).findFirst();

            return clazz.map(htmlAttribute -> htmlAttribute.getValue().contains("ng-invalid")).orElse(false);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }
}
