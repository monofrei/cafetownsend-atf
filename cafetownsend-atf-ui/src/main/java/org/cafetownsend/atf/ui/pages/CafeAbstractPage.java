package org.cafetownsend.atf.ui.pages;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testmonkeys.maui.pageobjects.AbstractPage;
import org.testmonkeys.maui.pageobjects.ElementAccessor;
import org.testmonkeys.maui.pageobjects.elements.Button;
import org.testmonkeys.maui.pageobjects.elements.Label;

public class CafeAbstractPage extends AbstractPage {

    @ElementAccessor(elementName = "Greetings Label", byXPath = "//p[@id='greetings']")
    private Label greetings;

    @ElementAccessor(elementName = "Logout", byXPath = "//p[@ng-click='logout()']")
    private Button logout;

    @Override
    public boolean isCurrentPage() {
        return this.getBrowser().getDynamicWaiter()
                .until(ExpectedConditions.urlMatches(this.getUrl() + ".*"));
    }

    //TODO investigate how handle the overlay of the button
    public void logout() {
        this.getBrowser()
                .getDynamicWaiter()
                .ignoring(WebDriverException.class)
                .until((driver) -> {
                    driver.findElement(this.logout.getLocator().getSeleniumLocator()).click();
                    return true;
                });
//        this.logout.click();
    }

    public String getGreetings() {
        return this.greetings.getText();
    }

}
