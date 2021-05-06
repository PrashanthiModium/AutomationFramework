package com.demoproject.gui.cucumber.Deployment.Acceptance.TestUtils;

import com.cucumber.listener.Reporter;
import com.demoproject.gui.cucumber.Deployment.Acceptance.Pages.Base.PageInstances;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "json:target/cucumber-report.json", "junit:target/cucumber-junit-report/cuc.xml"}
        , glue = {"com.demoproject.gui.cucumber"}
        , plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/report1.html"}
        , features = {"src/test/resources"}
        , monochrome = true
        , tags = {"@Test050521"}
)

public class DemoRunner extends PageInstances {

}

