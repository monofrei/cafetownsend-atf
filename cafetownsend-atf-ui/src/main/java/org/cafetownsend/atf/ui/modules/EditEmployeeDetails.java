package org.cafetownsend.atf.ui.modules;

import org.testmonkeys.maui.pageobjects.ElementAccessor;
import org.testmonkeys.maui.pageobjects.elements.Button;

public class EditEmployeeDetails extends EmployeeDetails {

    @ElementAccessor(elementName = "Update", byXPath = ".//button[text()='Update']")
    private Button update;

    @ElementAccessor(elementName = "Delete", byXPath = ".//button[text()='Delete']")
    private Button delete;

    @ElementAccessor(elementName = "Back", byXPath = "./preceding-sibling::ul[@id='sub-nav']/li/a[@class='subButton bBack']")
    private Button back;

}
