package org.cafetownsend.atf.formatter;

import cucumber.api.TestCase;
import gherkin.ast.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunContext {


    private static RunContext instance;
    private static int currentStepName;
    private String currentFeatureUri;
    private ScenarioDefinition currentScenario;

    private Map<String, Feature> featureMap = new HashMap<>();

    private RunContext() {

    }

    public static RunContext getInstance() {
        if (instance == null)
            instance = new RunContext();

        return instance;
    }

    <T extends ScenarioDefinition> T getScenario(TestCase testCase) {
        List<ScenarioDefinition> featureScenarios = getFeature(currentFeatureUri).getChildren();
        for (ScenarioDefinition scenario : featureScenarios) {
            if (scenario instanceof Background) {
                continue;
            }
            if (testCase.getLine() == scenario.getLocation().getLine() && testCase.getName().equals(scenario.getName())) {
                return (T) scenario;
            } else {
                if (scenario instanceof ScenarioOutline) {
                    for (Examples example : ((ScenarioOutline) scenario).getExamples()) {
                        for (TableRow tableRow : example.getTableBody()) {
                            if (tableRow.getLocation().getLine() == testCase.getLine()) {
                                return (T) scenario;
                            }
                        }
                    }
                }
            }
        }
        throw new IllegalStateException("Scenario can't be null!");
    }


    public static void setInstance(RunContext instance) {
        RunContext.instance = instance;
    }

    public String getCurrentFeatureUri() {
        return currentFeatureUri;
    }

    public void setCurrentFeatureUri(String currentFeatureUri) {
        this.currentFeatureUri = currentFeatureUri;
    }

    public void setCurrentScenario(ScenarioDefinition currentScenario) {
        this.currentScenario = currentScenario;
    }

    public ScenarioDefinition getCurrentScenario() {
        return currentScenario;
    }

    public void addFeature(String uri, Feature feature) {
        this.featureMap.put(uri, feature);
    }

    public Feature getFeature(String uri) {
        return this.featureMap.get(uri);
    }

    public static int getCurrentStepLine() {
        return currentStepName;
    }

    public void setCurrentStepLine(int currentStepName) {
        this.currentStepName = currentStepName;
    }
}
