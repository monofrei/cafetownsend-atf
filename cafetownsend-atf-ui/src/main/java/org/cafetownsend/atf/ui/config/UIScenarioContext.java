package org.cafetownsend.atf.ui.config;

import lombok.Getter;
import org.cafetownsend.atf.config.ScenarioContext;
import org.cafetownsend.atf.ui.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.testmonkeys.maui.core.browser.Browser;
import org.testmonkeys.maui.core.factory.PageFactory;
import org.testmonkeys.maui.core.factory.PageScanner;

import java.util.concurrent.TimeUnit;

@Component

public class UIScenarioContext extends ScenarioContext {

    @Value("${browser}")
    private String browser;

    @Value("${ui.element.find.timeout}")
    private int timeout;

    @Value("${ui.element.page.timeout}")
    private int pageTimeout;

    @Value("${ui.base.url}")
    private String baseUrl;

    @Value("${ui.page.package}")
    private String pagesPackage;

    @Value("${ui.element.timeout.unit}")
    private TimeUnit unit;

    @Value("${browser.options}")
    private String browserOptions;

    @Getter
    private Browser currentBrowser;

    @Getter
    private PageFactory pageFactory;

    @Getter
    private PageScanner pageScanner;

    public PageFactory initPageFactory() {
        currentBrowser = initBrowser();
        this.pageScanner = new PageScanner(pagesPackage);
        this.pageFactory = new PageFactory(currentBrowser, pageScanner, baseUrl);
        return pageFactory;
    }

    private Browser initBrowser(){
        WebDriver driver = DriverFactory.initDriver(browser, browserOptions.split(","));
        return new Browser(driver, TimeUnit.SECONDS, timeout, pageTimeout);
    }
}
