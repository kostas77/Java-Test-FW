package com.elsevier.bts.regional.ecom.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.elsevier.bts.regional.ecom")
//@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.elsevier.bts.regional.ecom.steps")
//@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.elsevier.bts.regional.ecom.framework.hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:target/cucumber-json-report.json")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber-html-report")

public class CucumberRunner {

}
