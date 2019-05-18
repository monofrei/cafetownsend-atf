package org.cafetownsend.atf.ui;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.List;

import static org.openqa.selenium.remote.BrowserType.*;

public class DriverFactory {

    public static WebDriver initDriver(String browser) {
        WebDriverManager.getInstance(DriverManagerType.valueOf(browser.toUpperCase())).forceCache().setup();
        DesiredCapabilities cap;
        switch (browser.toLowerCase()) {
            case CHROME:
                cap = DesiredCapabilities.chrome();
                return new ChromeDriver(cap);
            case FIREFOX:
                cap = DesiredCapabilities.firefox();
                return new FirefoxDriver(cap);
            default:
                throw new RuntimeException("Unsupported browser[" + browser + "]");
        }
    }

    public static WebDriver initDriver(String browser, String[] options) {
        WebDriverManager.getInstance(DriverManagerType.valueOf(browser.toUpperCase())).forceCache().setup();
        DesiredCapabilities capabilities;

        switch (browser.toLowerCase()) {
            case CHROME:
                capabilities = DesiredCapabilities.chrome();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(Arrays.asList(options));
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

                return new ChromeDriver(capabilities);

            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(Arrays.asList(options));

                return new FirefoxDriver(firefoxOptions);
            default:
                throw new RuntimeException("Unsupported browser[" + browser + "]");
        }
    }

}
