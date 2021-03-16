package project;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import project.steps.AbstractSteps;

@CucumberOptions(
    features = { "src/test/resources/features" },
    glue = {"project/steps"},
    //tags = {"@Rate or @Login"},
    tags = {"@Rate"},
    plugin = {
            "pretty",
            "html:target/cucumber-reports/cucumber-pretty",
            "json:target/cucumber-reports/json-reports/CucumberTestReport.json",
            "rerun:target/cucumber-reports/rerun-reports/rerun.txt"
    })

public class Runner extends AbstractSteps {

    @BeforeSuite
    public void beforeRun() {
        beforeAllActions();
    }

    @AfterSuite
    public void afterRun() {

    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}