package com.elsevier.subsys.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(features = {"src/test/resources/features"},
                 glue = {"com/elsevier/subsys/steps"},
                 tags = {"~@ignore", "@engage_smoke"},
                 plugin = {"pretty",
                           "json:target/cucumber-json-report.json",
                           "html:target/cucumber-report-html"}
                 )
@RunWith(Cucumber.class)

public class RunCukeTest {

}
