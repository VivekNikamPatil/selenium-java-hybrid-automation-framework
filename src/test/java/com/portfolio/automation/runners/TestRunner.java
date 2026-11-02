
package com.portfolio.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.portfolio.automation.steps",
        "com.portfolio.automation.hooks"
    },
    plugin = {
        "pretty",
        "html:reports/cucumber.html",
        "json:reports/cucumber.json",
        "junit:reports/cucumber.xml"
    },
    monochrome = true,
    publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
