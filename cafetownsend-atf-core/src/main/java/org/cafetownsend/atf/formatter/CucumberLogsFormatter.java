package org.cafetownsend.atf.formatter;


import cucumber.api.PickleStepTestStep;
import cucumber.api.Result;
import cucumber.api.TestCase;
import cucumber.api.event.*;
import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ParserException;
import gherkin.TokenMatcher;
import gherkin.ast.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

import static cucumber.api.Result.Type.FAILED;

public class CucumberLogsFormatter implements ConcurrentEventListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RunContext context = RunContext.getInstance();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestSourceRead.class, this::handleTestSourceRead);
        publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
        publisher.registerHandlerFor(TestStepStarted.class, this::handleTestStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
        publisher.registerHandlerFor(TestRunFinished.class, this::finishReport);
    }

    private void handleTestSourceRead(TestSourceRead event) {
        Feature feature = getFeature(event.source);
        context.addFeature(event.uri, feature);
    }


    private Feature getFeature(String source) {
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        TokenMatcher matcher = new TokenMatcher();
        GherkinDocument gherkinDocument;
        try {
            gherkinDocument = parser.parse(source, matcher);
        } catch (ParserException e) {
            return null;
        }
        return gherkinDocument.getFeature();
    }

    private void finishReport(TestRunFinished event) {
        //todo log execution summary
    }

    private void handleTestStepFinished(TestStepFinished event) {
        if (event.testStep instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.testStep;

            Optional<Step> first = findStepInContext(testStep);

            if (!first.isPresent())
                throw new RuntimeException("Failed to found step:[" + testStep.getStepText() + "]");

            Result.Type status = event.result.getStatus();
            //todo log duration
            if (status.equals(FAILED))
                logger.info("[STEP FAILED]: {} {} {}", first.get().getKeyword(), testStep.getStepText(), event.result.getError());
            else
                logger.info("[STEP {} ]: {}", status.name(), first.get().getKeyword() + testStep.getStepText() + System.lineSeparator());
        }
    }


    private void handleTestStepStarted(TestStepStarted event) {
        if (event.testStep instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.testStep;

            Optional<Step> first = findStepInContext(testStep);

            if (!first.isPresent())
                throw new RuntimeException("Failed to found step:[" + testStep.getStepText() + "]");

            context.setCurrentStepLine(testStep.getStepLine());
            logger.info("[STEP STARTED]: {} {}", first.get().getKeyword(), testStep.getStepText());

        }
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        TestCase testCase = event.getTestCase();
        String uri = testCase.getUri();

        if (!Objects.equals(uri, context.getCurrentFeatureUri())) {
            context.setCurrentFeatureUri(uri);
            logger.info("[FEATURE STARTED]: {} [{}]", context.getFeature(uri).getName(), uri);
        }

        ScenarioDefinition scenario = context.getScenario(event.testCase);
        int line = scenario.getLocation().getLine();
        if (scenario.getKeyword().equals("Scenario Outline")) {
            line = event.testCase.getLine();
        }
        context.setCurrentScenario(scenario);
        TestLogHelper.stopTestLogging();

        TestLogHelper.startTestLogging(TestLogHelper.createTimeStamp() + "_" + scenario.getName() + "_" + line);
        logger.info("[TEST STARTED]: {}", event.testCase.getName());

    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        if (event.result.getStatus().equals(FAILED))
            logger.info("[TEST FAILED] {} {}", event.testCase.getName(), event.result.getError());
        else
            logger.info("[TEST {} ] {}", event.result.getStatus().name(), event.testCase.getName() + System.lineSeparator());
    }

    private Optional<Step> findStepInContext(PickleStepTestStep step) {

        Optional<Step> first = context.getCurrentScenario().getSteps().stream()
                .filter(s -> s.getLocation().getLine() == step.getStepLine()).findFirst();

        if (!first.isPresent()) {
            Optional<ScenarioDefinition> background = context.getFeature(context.getCurrentFeatureUri())
                    .getChildren().stream()
                    .filter(c -> c.getClass().equals(Background.class))
                    .findFirst();
            if (background.isPresent())
                first = background.get().getSteps().stream()
                        .filter(s -> s.getLocation().getLine() == step.getStepLine()).findFirst();
        }

        return first;
    }
}
