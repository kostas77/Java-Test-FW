package com.elsevier.subsys.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

// This is a template for "Cucable" generated parallel runners
@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com.elsevier.subsys.steps",
        features = {"target/parallel/features/[CUCABLE:FEATURE].feature"},
        plugin = {"pretty", "json:target/cucumber-report/[CUCABLE:RUNNER].json"}
)
public class CucableTemplate {

}