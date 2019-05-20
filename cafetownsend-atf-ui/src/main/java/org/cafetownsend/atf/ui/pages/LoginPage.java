package org.cafetownsend.atf.ui.pages;

import org.cafetownsend.atf.ui.elements.ValidatedInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testmonkeys.maui.pageobjects.ElementAccessor;
import org.testmonkeys.maui.pageobjects.PageAccessor;
import org.testmonkeys.maui.pageobjects.elements.Button;
import org.testmonkeys.maui.pageobjects.elements.Label;

import java.util.concurrent.TimeUnit;

@PageAccessor(name = "Login", url = "/login")
public class LoginPage extends CafeAbstractPage implements Validatable {

    @ElementAccessor(elementName = "Username", byXPath = "//input[@ng-model='user.name']")
    private ValidatedInput username;

    @ElementAccessor(elementName = "Password", byXPath = "//input[@ng-model='user.password']")
    private ValidatedInput password;

    @ElementAccessor(elementName = "Login", byXPath = "//button[text()='Login']")
    private Button login;

    @ElementAccessor(elementName = "Error Message", byXPath = "//p[contains(@class,'error-message')]")
    private Label errorMessage;

    public void login(String username, String password) {
        this.username.type(username);
        this.password.type(password);
        this.login.click();
    }

    public String getErrorMessage() {
        return this.errorMessage.getText();
    }

    public boolean errorMessageIsDisplayed() {

        /*
         * The standart browser waiter is around find element.
         * This element is on the page but not yet visible at the moment of the call
         * */
        this.getBrowser().getDynamicWaiter().withTimeout(5, TimeUnit.SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(this.errorMessage.getLocator().getSeleniumLocator()));

        return this.errorMessage.find().isDisplayed();
    }
}
