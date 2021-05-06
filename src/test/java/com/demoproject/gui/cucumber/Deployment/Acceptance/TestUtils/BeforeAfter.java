package com.demoproject.gui.cucumber.Deployment.Acceptance.TestUtils;

import com.demoproject.gui.cucumber.Deployment.Acceptance.Helpers.Driver;
import com.demoproject.gui.cucumber.Deployment.Acceptance.Pages.Base.PageInstances;
import com.demoproject.gui.cucumber.Deployment.Acceptance.dataproviders.ConfigFileReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class BeforeAfter extends PageInstances {

    private ConfigFileReader configFileReader= new ConfigFileReader();
    @Before
    public void setUp() throws Exception {
        try {
            if(driver==null){
            Driver.setWebdriver(configFileReader.getBrowser(),true);
            driver = Driver.getDriver();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            goToStartPosition();
            }else{
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
                //Building up the destination path for the screenshot to save
                //Also make sure to create a folder 'screenshots' with in the cucumber-report folder
                File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");
                //Copy taken screenshot from source location to destination location
                FileUtils.copyFile(sourcePath,destinationPath);

                //This attach the specified screenshot to the test
//                Reporter.addScreenCaptureFromPath(destinationPath.toString());
            } catch (IOException e) {
            }
        }
    }


//    @After(order = 0)
//    public void AfterSteps() {
//        driver.quit();
//    }

    public void goToStartPosition() throws Exception {
        driver.manage().window().maximize();
        driver.get(configFileReader.getApplicationUrl());
    }
}
