package org.example.CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"org.example.StepDefinitions", "org.example.GlobalHooks"}, monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber_html_report.html",
                "json:target/cucumber-reports/cucumber_json_report.json",
        }, tags = "@regression")

public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {


    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}


