package com.gui.cucumber.TestUtils;

import com.gui.cucumber.Pages.Base.PageInstances;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "json:target/cucumber-report.json", "junit:target/cucumber-junit-report/cuc.xml"}
        , glue = {"com.gui.cucumber"}
        , plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/report.html"}
        , features = {"src/test/resources"}
        , monochrome = true
        , tags = {"@EstimateBorrow"}
)

public class DemoRunner extends PageInstances {




}

