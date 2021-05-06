package com.demoproject.gui.cucumber.Deployment.Acceptance.webdriver;


import com.demoproject.gui.cucumber.Deployment.Acceptance.Pages.Base.PageInstances;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class GenericWait extends PageInstances {

    private static final int DRIVER_WAIT_TIME = 120;

    public List<WebElement> waitForElementsVisible(By by) {
        Wait<WebDriver> wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        return driver.findElements(by);
    }

    public static List<WebElement> waitForElementsClickable(By by) {
        Wait<WebDriver> wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        return driver.findElements(by);
    }

    public static void waitForElementsClickable(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForExpectedElement(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitForExpectedElements(By by) {
        Wait<WebDriver> wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }


    public WebElement waitForElementPresent(By by) {
        Wait<WebDriver> wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementToDissappear(By byLocator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
    }

    public static void waitForPageTitle(String title) {
        Wait<WebDriver> wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.titleIs(title));
    }


}
