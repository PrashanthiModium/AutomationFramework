package com.demoproject.gui.cucumber.Deployment.Acceptance.Pages.Base;
import com.demoproject.gui.cucumber.Deployment.Acceptance.webdriver.WebDriverUtils;


import com.demoproject.gui.cucumber.Deployment.Acceptance.Helpers.Driver;


import org.openqa.selenium.NoSuchElementException;

public abstract class BasePage extends PageInstances {

    protected int _timeOut = 100;

    public BasePage() throws Exception {
        currentWindowHandler = Driver.getDriver().getWindowHandle();
        try {
            WebDriverUtils.waitForElementLoading(2);
//            WebDriverUtils.waitForElementIsPresent(elementForLoading(),2);
        } catch (NoSuchElementException e) {
            WebDriverUtils.waitForElementLoading(1);
        } catch (Exception e) {
            WebDriverUtils.waitForElementLoading(2);
        }
//        pageName();

    }
//    protected abstract WebElement elementForLoading() throws Exception;
//
//    public abstract boolean isPageDisplayed() throws Exception;
//
//    protected abstract void pageName() throws Exception;



}
