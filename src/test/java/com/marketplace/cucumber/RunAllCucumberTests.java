package com.marketplace.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "json:target/cucumber.json"},
        features = "src/test/Features")

public class RunAllCucumberTests {
}
