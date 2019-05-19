package org.cafetownsend.atf.ui.pages;

import org.testmonkeys.maui.pageobjects.ElementAccessor;
import org.testmonkeys.maui.pageobjects.PageAccessor;
import org.testmonkeys.maui.pageobjects.elements.Button;
import org.testmonkeys.maui.pageobjects.elements.Input;

@PageAccessor(name = "Login", url = "/")
public class LoginPage extends CafeAbstractPage {

    @ElementAccessor(elementName = "Username", byXPath = "//input[@ng-model='user.name']")
    private Input username;

    @ElementAccessor(elementName = "Password", byXPath = "//input[@ng-model='user.password']")
    private Input password;

    @ElementAccessor(elementName = "Login", byXPath = "//button[text()='Login']")
    private Button login;

    public void login(String username, String password){
        this.username.type(username);
        this.password.type(password);
        this.login.click();
    }
}
