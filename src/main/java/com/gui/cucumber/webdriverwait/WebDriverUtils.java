package com.gui.cucumber.webdriverwait;

import com.gui.cucumber.Pages.Base.PageInstances;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class WebDriverUtils extends PageInstances {

    /**
     * Performs delay until all background processes are finished, page is rendered.
     * Please use waitForPageUpdate(int timeout) instead of this method
     *
     */

    public static void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", element);
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", element);
    }

     public static void waitForElementLoading(String xpath) {
        (new WebDriverWait(driver, 30, 500))
                .until(ExpectedConditions.presenceOfElementLocated((By.xpath(xpath))));
    }

    public static void waitForElementLoading(int sleepInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(sleepInSeconds);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

}
