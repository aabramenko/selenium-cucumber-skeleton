package project.steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import project.core.ConfigManager;
import project.core.CredsManager;
import project.core.RunTimeDataStorage;
import project.core.Utils;
import project.driverFactory.DriverFactory;
import project.models.Driver;
import ru.yandex.qatools.ashot.Screenshot;

import static project.core.TestRunParams.getPathToAllArtifactsFolders;
import static project.core.TestRunParams.getPathToCurrentArtifactsFolder;
import static project.core.Utils.createFolder;
import static project.core.Utils.deleteTempFiles;

public abstract class AbstractSteps extends AbstractTestNGCucumberTests {

    WebDriver getDriver() {
        return getD().getDriver();
    }

    Driver getD() {
        return RunTimeDataStorage.DriverStorage.getD(Utils.getCurrentThreadId());
    }

    public void beforeAllActions() {
        Logger log = Logger.getLogger("");
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        RunTimeDataStorage.DriverStorage.initializeDMap();
        ConfigManager.uploadRunConfigParameters();
        ConfigManager.uploadEnvConfigParameters();
        ConfigManager.uploadDbConfigParameters();
        CredsManager.uploadCredsValues();

        deleteTempFiles();
        createFolder(getPathToAllArtifactsFolders());
        createFolder(getPathToCurrentArtifactsFolder());

        log.info("=== OS: " + Utils.getCurrentOS());
        log.info("=== Grid?: " + ConfigManager.isGrid());
        log.info("=== Selenoid?: " + ConfigManager.isSelenoid());
        log.info("=== Headless?: " + ConfigManager.isHeadless());
    }

    void openBrowser() {
        Logger log = Logger.getLogger("");
        Driver d = DriverFactory.createNewDriverInstance(ConfigManager.getBrowserName());
        RunTimeDataStorage.DriverStorage.setD(Utils.getCurrentThreadId(), d);
        Utils.sleepMsec(2000);
        Utils.printDashedLine();
        log.info("Browser: " + d.getBrowserName() + " | Driver: " + d.getDriver());
        Utils.printDashedLine();
        d.getDriver().manage().window().setSize(new Dimension(1366, 768));
    }

    void closeBrowser() {
        Logger log = Logger.getLogger("");
        if (getD() != null) {
            log.info("closing browser");
            Utils.printDashedLine();
            log.info("Browser: " + getD().getBrowserName() + " | Driver: " + getD().getDriver());
            Utils.printDashedLine();
            try {
                getD().getDriver().quit();
                Utils.sleepMsec(500);
            }
            catch (WebDriverException e) {
                //
            }
        }
        else {
            log.info("Driver is null!");
        }
    }

    void embedScreenshotToReport(Scenario scenario) {
        Screenshot screenshot = Utils.getScreenshot(getDriver());
        byte[] imageInByte = Utils.convertScreenshotToByte(screenshot);
        scenario.embed(imageInByte, "image/png");
    }

    void embedTextToReport(Scenario scenario, String text) {
        scenario.write(text);
    }
}