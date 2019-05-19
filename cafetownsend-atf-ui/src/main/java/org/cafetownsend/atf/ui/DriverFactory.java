package org.cafetownsend.atf.ui;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class DriverFactory {

    public static WebDriver initDriver(String browser) {
        WebDriverManager.getInstance(DriverManagerType.valueOf(browser.toUpperCase())).forceCache().setup();
        switch (browser.toLowerCase()) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            default:
                throw new RuntimeException("Unsupported browser[" + browser + "]");
        }
    }

    public static WebDriver initDriver(String browser, String[] options) {
        WebDriverManager.getInstance(DriverManagerType.valueOf(browser.toUpperCase())).forceCache().setup();

        switch (browser.toLowerCase()) {
            case CHROME:

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(Arrays.asList(options));

                return new ChromeDriver(chromeOptions);

            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(Arrays.asList(options));
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new RuntimeException("Unsupported browser[" + browser + "]");
        }
    }

}
