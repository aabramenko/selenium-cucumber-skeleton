package project.steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import project.core.ConfigManager;

public class Hooks extends AbstractSteps {

    private static Logger log = Logger.getLogger("");

    @Before
    public void beforeScenario() {
        openBrowser();
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed() && ConfigManager.isScreenOnFailure()) {
            log.info("Current url: " + getDriver().getCurrentUrl());
            embedScreenshotToReport(scenario);
            embedTextToReport(scenario, getDriver().getCurrentUrl());
        }
        if (!scenario.isFailed() && ConfigManager.isScreenOnSuccess()) {
            embedScreenshotToReport(scenario);
        }
        closeBrowser();
    }
}
