package org.cafetownsend.atf.ui.pages;

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

    public void logout() {
        this.logout.click();
    }

    public void getGreetings() {
        this.greetings.getText();
    }

}
