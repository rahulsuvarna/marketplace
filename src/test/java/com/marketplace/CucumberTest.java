package com.marketplace;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by vsrah on 23/04/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:Feature"})
public class CucumberTest {
}
