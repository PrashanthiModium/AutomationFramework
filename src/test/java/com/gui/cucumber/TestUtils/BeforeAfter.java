package com.gui.cucumber.TestUtils;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.gui.cucumber.Helpers.Driver;
import com.gui.cucumber.Pages.Base.BasePage;
import com.gui.cucumber.Pages.Base.PageInstances;
import com.gui.cucumber.dataproviders.ConfigFileReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class BeforeAfter extends PageInstances {

    private ConfigFileReader configFileReader = new ConfigFileReader();

    @Before
    public void setUp() throws Exception {
        try {
            if (driver == null) {
                Driver.setup(configFileReader.getBrowser());
                driver = Driver.getDriver();
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                goToStartPosition();
            } else {
                driver.manage().deleteAllCookies();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

   @After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                //This takes a screenshot from the driver at save it to the specified location
                File sourcePath = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
                File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");
                FileUtils.copyFile(sourcePath, destinationPath);
                Reporter.addScreenCaptureFromPath(destinationPath.toString());
            } catch (IOException e) {
            }
        }
    }

    @After(order = 0)
    public void AfterSteps() {
        driver.quit();
    }

    public void goToStartPosition() throws Exception {
        driver.manage().window().maximize();
        driver.get(configFileReader.getApplicationUrl());
    }
}
