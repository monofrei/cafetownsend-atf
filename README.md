# Cafetownsend ATF

This is a test automation framework for the application [Cafe Town Send](http://cafetownsend-angular-rails.herokuapp.com/employees).

## Prerequisites
In order to be able to use this framework please make sure the following is tools are installed:
1. Execute the test:
    * JDK 8;
    * Maven;
    * At least one of the browsers: Coogle Chrome or Mozilla Firefox.
2. Import project in IDE and start automating test scenarios:
    * All of the above;
    * Any IDE that supports Java;
    * Lombok plugin for the IDE of choice. See [Lombok Setup](https://projectlombok.org/setup/overview) page for list of supported IDEs.

## Technology stack

1. **[JAVA 8](https://en.wikipedia.org/wiki/Java_programming_language)** - general purpose OOP programming language. 
2. **[MAUI](https://github.com/TestMonkeys/maui)** - is a wrapper framework for selenium with the aim of providing an easier way of automating and executing tests.
This framework enhances the selenium capabilities with:
    * `PageFactory` that builds all the pages and subsequent modules and elements using annotation based configuration.
    * It offers a wrapper for the WebDriver class that has a configurable smart wait functionality around the browser interactions such as finding elements, page loading.
    It also exposes a waiter capabilty that can be used outside of the Browser class but maintain the user defined configurations.
    * It offers a `PageScanner` utility that allow to search for pages and elements using their defined business specific names defined by the tester. This allows to automated 
    generic primitive actions such as click, type, select, check, etc. that can be used for any element in the framework.
    * Using lambda expressions it allows to defined and inject custom behaviour before and after execution of and action. Example: make a screenshot before/after.
    This can be done by using the class `org.testmonkeys.maui.core.elements.actions.ActionHooksContext`. Before/After hooks can be registered and removed at any time during the execution of the test.
3. **[WebDriver Manager](https://github.com/bonigarcia/webdrivermanager)** - automate the management of the binary drivers required for selenium.
   You don't have to worry about downloading the corresponding driver version for you browser, it will identify your browser version and download the correct driver.
4. **[Cucumber 4](https://github.com/cucumber/cucumber)** - Cucumber is a tool that supports [Behaviour-Driven Development (BDD)](https://cucumber.io/blog/intro-to-bdd-and-tdd/) - a software development process that aims to enhance software quality and reduce maintenance costs.
This is the main test management framework that allows to define test definition using [Gherkin](https://cucumber.io/docs/gherkin/) DSL.
5. **[Yandex Ashot](https://mvnrepository.com/artifact/ru.yandex.qatools.ashot/ashot)** - WebDriver screenshot utility.
This library facilitates the process of taking a screenshot and allows to make screenshot of the entire page by handling the scrolling. It allows to take screenshot of a segment of the page.
6. **[Hamcrest](https://github.com/hamcrest/JavaHamcrest)** - library of matchers that can be combined into creating flexible assert expressions.
7. **[Logback](https://github.com/qos-ch/logback)** - fast and flexible logging framework.
8. **[Spring](https://github.com/spring-projects/spring-framework)** - Spring is an [Inversion of Control(IoC)](https://en.wikipedia.org/wiki/Inversion_of_control) container via dependency injection.
In this framework it is used to initialize JAVA classes, load configuration and dependency injection.
9. **[Maven](https://maven.apache.org)** - project management tool that allows to manage the build and reporting of the project, dependency management, trigger test execution, generate reports.
10. **[Lombok](https://projectlombok.org)** - java library that allows to using annotation based configuration automatically generate getters, setters, constructors, initialize loggers, equals methods. 

## Get Started
Clone the project using git.

### Execute all test using maven
To execute the test from using CLI open a command prompt in the cloned repository location and execute the following command:

`mvn clean verify`

This command will start to build the project and will run all the tests that are configured by `class org.cafetownsend.atf.test.RunCukeTest`:
```java
@RunWith(Cucumber.class)
@CucumberOptions(
        junit = "--step-notifications",
        features = {"src/test/resources/features"}, //path to feature files 
        plugin = {
                "json:target/jsonReports/cucumber.json", // generate a json based execution report
                "org.cafetownsend.atf.formatter.CucumberLogsFormatter" // Logging formatter that will add extended logging capabilites
        },
        glue = {//packages that contain Cucumber related code
                "org.cafetownsend.atf.test.steps",
                "org.cafetownsend.atf.test.hooks",
                "org.cafetownsend.atf.test.config"
        }
)
public class RunCukeTest {
}
```

After the build is complete two sets of evidence will be generated:
1. [Masterthought HTML Report](https://github.com/damianszczepanik/cucumber-reporting) - this is a pretty HTML based Cucumber report that organizes the results per Feature, Scenario, Steps and Tags.
It also has support to show the loggs generated by each step and support for screenshots, that allows easy debugging of the test results. The html report will be generated at `cafetownsend-atf-tests/target/cucumber-html-reports`
No dedicated web server is required to view this report just open one of the overview files in any browser.

2. The framework will create a folder for each Scenario instance executed and will store in it all the generated evidence. For now the evidence are the logs and screenshots generated by the framework.
The evidence folders are located under `cafetownsend-atf-tests/target/logs`. This location can be changed using the property `logs.folder` located in the file `cafetownsend-atf-tests/src/test/resources/application.properties`

### Execute using an IDE
Import the project as a maven projct into any IDE of choice. Run the class `class org.cafetownsend.atf.test.RunCukeTest`. No additional configuration is needed the default settings will execute all the tests.

## Project Structure
```
    cafetownsend-atf
    ├── cafetownsend-atf-core
    │   │── src
    │   └── pom.xml 
    ├── cafetownsend-atf-tests
    │   │── src
    │   └── pom.xml 
    │──cafetownsend-atf-ui
    │   │── src
    │   └── pom.xml 
    └── pom.xml    
```

This framework consists of three main modules:
1. **cafetownsend-atf-core** - this module contains the core generic functionality that will be used by other modules and can be also used as a standalone library for other projects
   Its main functionalities are: 
   * AtfAssert - a class with a custom `assertThat` wrapper that will log bot successful and failed results of the assertion.
   
   Package: `org.cafetownsend.atf.assertion`
   * Cucumber Appender - a custom cucumber log appender that will redirect all the logs from the framework to the cucumber context thus allowing to visualize them in the reports.
   
   Package: `org.cafetownsend.atf.appender`
   * Log Formatter - cucumber listener that log cucumber pickle events and allows to separate and redirect the logs to different files for each Scenario.
   
   Package: `org.cafetownsend.atf.formatter`
   
   Examples of logs
   ``` 
   INFO  o.c.a.f.CucumberLogsFormatter - [TEST STARTED]: Verify user can login into the application with valid credentials 
   INFO  o.c.a.f.CucumberLogsFormatter - [STEP STARTED]: Given  user opens the Login page 
   INFO  o.t.m.c.b.Browser - Accessing URL:http://cafetownsend-angular-rails.herokuapp.com/ 
   INFO  o.c.a.f.CucumberLogsFormatter - [STEP PASSED ]: Given user opens the Login page
   INFO  o.c.a.f.CucumberLogsFormatter - [STEP STARTED]: When  user logs in using the credentials Luke:Skywalker 
   INFO  o.t.m.c.e.a.SendKeysAction - Sending text 'Luke' to Input(name=Username, locator[XPath, "//input[@ng-model='user.name']"]) 
   INFO  o.t.m.c.e.a.SendKeysAction - Sending text 'Skywalker' to Input(name=Password, locator[XPath, "//input[@ng-model='user.password']"]) 
   INFO  o.t.m.c.e.a.ClickAction - Performing Click on Button(name=Login, locator[XPath, "//button[text()='Login']"]) 
   INFO  o.c.a.f.CucumberLogsFormatter - [STEP PASSED ]: When user logs in using the credentials Luke:Skywalker
   INFO  o.c.a.f.CucumberLogsFormatter - [STEP STARTED]: Then  user is redirected to Employees page 
   INFO  o.c.a.a.AtfAssert - Assert that current page is Employees
   INFO  o.c.a.f.CucumberLogsFormatter - [STEP PASSED ]: Then user is redirected to Employees page
   INFO  o.c.a.f.CucumberLogsFormatter - [STEP STARTED]: And  the greetings displays 'Hello Luke' 
   INFO  o.t.m.c.e.a.GetTextAction - Getting text from Label(name=Greetings Label, locator[XPath, "//p[@id='greetings']"]) 
   INFO  o.t.m.c.e.a.GetTextAction - Action result is 'Hello Luke' 
   INFO  o.c.a.a.AtfAssert - Assert that  greetings text is Hello Luke
   INFO  o.c.a.f.CucumberLogsFormatter - [STEP PASSED ]: And the greetings displays 'Hello Luke'
   INFO  o.c.a.f.CucumberLogsFormatter - [TEST PASSED ] Verify user can login into the application with valid credentials
   ```
   This formatter enhances the static debugging possibilities of the tests results.

2. **cafetownsend-atf-test** - the module that contains all the tests, step definitions and test configuration classes and files.
    *   `org.cafetownsend.atf.test.steps` - Cucumber step definitions
    *   `org.cafetownsend.atf.test.hooks` - Cucumber Before and After hooks. Use this classes to defined the pre and post actions
    that need to be executed for each scenario.
    *   `org.cafetownsend.atf.test.config` - The configuration classes. 
    `UIScenarioContext` is the class that is meant to handle the context between steps. It is responsible to load the browser
    related properties and initialize all the required component to interact with the browser, pages and elements.
    *   `cafetownsend-atf-tests/src/test/resources/features` - the location where all the test features are stored.
    *   `application.properties`, `ui-application.properties` - use this files to change the atf configuration:
        
        `logs.folder` - folder were all the evidence is stored.
        
        `browser` - set the browser for execution, supported values are: chrome, firefox. The browser must be installed on the machine.
        
        `browser.options` - options that need to be applied on browser's startup. Examples:start-maximized, headless, etc. More details on [Chrome Options](https://www.guru99.com/chrome-options-desiredcapabilities.html) and [Firefox Options](https://www.guru99.com/gecko-marionette-driver-selenium.html).
        
        `browser.options.splitter` - specify which character is used as splitter for the options(comma, dot, slash, etc.).
        
        `screenshot.extension` - extension to be used when saving the screenshot(ex.: .png, .jpg, etc.). Leave empty if not needed.
        
        `screenshot.format` - the format of the screenshot(PNG,JPEG, etc.). Default set to PNG.
        
        `ui.page.package` - the package where all page object classes are located. Required for the MAUI Page Factory to initialize the pages.
        
        `ui.base.url` - the base url of the application. Default set to [http://cafetownsend-angular-rails.herokuapp.com](http://cafetownsend-angular-rails.herokuapp.com).
        
        `ui.element.find.timeout` - timeout for finding the elements on the pages.
        
        `ui.element.page.timeout` - timeout for loading the pages.
        
        `ui.element.timeout.unit` - [Time unit](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/concurrent/TimeUnit.html) used for the timeout. Default set to SECONDS.
        
3. **cafetownsend-atf-ui** - this module contains all UI related code: Page Object, UI Modules, DriverFactory, Screenshot Utils.
      * `org.cafetownsend.atf.ui.DriverFactory` - initialize the web driver based on the specified configuration. It is used by `org.cafetownsend.atf.test.config.UIScenarioContext`.
      * `org.cafetownsend.atf.ui.utils.ScreenshotUtils` - this class allows to make a screenshot and get the byte array of the image. It also can save the image on the file system.
      Also this class can highlight an element on the page before making the screenshot. The screenshot will include the entire page not only the visible part.

