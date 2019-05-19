package org.cafetownsend.atf.ui.config;

import lombok.Getter;
import org.cafetownsend.atf.config.ScenarioContext;
import org.cafetownsend.atf.ui.DriverFactory;
import org.cafetownsend.atf.ui.utils.ScreenshotUtils;
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

    @Value("${browser.options.splitter}")
    private String browserOptionsSplitter;

    @Value("${screenshot.extension}")
    private String IMAGE_FILE_EXTENSION = ".png";

    @Value("${screenshot.format}")
    private String IMAGE_FORMAT = "PNG";

    @Value("${logs.folder}")
    private String LOG_BASE_DIR = "target/logs/";

    @Getter
    private Browser currentBrowser;

    @Getter
    private PageFactory pageFactory;

    @Getter
    private PageScanner pageScanner;

    @Getter
    private ScreenshotUtils screenshotUtils;

    public PageFactory initContext() {
        currentBrowser = initBrowser();
        this.pageScanner = new PageScanner(pagesPackage);
        this.pageFactory = new PageFactory(currentBrowser, pageScanner, baseUrl);
        this.screenshotUtils = new ScreenshotUtils(IMAGE_FILE_EXTENSION, IMAGE_FORMAT, LOG_BASE_DIR, currentBrowser.getDriver());
        return pageFactory;
    }

    private Browser initBrowser() {
        WebDriver driver = DriverFactory.initDriver(browser, browserOptions.split(browserOptionsSplitter));
        return new Browser(driver, TimeUnit.SECONDS, timeout, pageTimeout);
    }
}
