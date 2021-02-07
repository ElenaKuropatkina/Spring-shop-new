package com.elenakuropatkina;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/cucumber-html-report"},
        features = {"classpath:features"},
        glue = {"com.elenakuropatkina.steps"},
        snippets = SnippetType.CAMELCASE)
public class LaunchTest {

}
