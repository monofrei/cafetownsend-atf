package org.cafetownsend.atf.ui.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testmonkeys.maui.pageobjects.AbstractPage;

public class CafeAbstractPage extends AbstractPage {

    @Override
    public boolean isCurrentPage() {
        return this.getBrowser().getDynamicWaiter()
                .until(ExpectedConditions.urlMatches(this.getUrl() + ".*"));
    }

}
