package com.demoproject.gui.cucumber.Deployment.Acceptance.webdriver;

import com.demoproject.gui.cucumber.Deployment.Acceptance.Pages.Base.PageInstances;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class WebDriverUtils extends PageInstances {

    /**
     * JavaScript code to check if all the ajax requests completed
     */
    static private final String JS_AJAX_COMPLETION =
            "var docReady = window.document.readyState == 'complete';" +
                    "var isJqueryComplete = typeof(window.jQuery) != 'function' || window.jQuery.active == 0;" +
                    "var isPrototypeComplete = typeof(window.Ajax) != 'function' || window.Ajax.activeRequestCount == 0;" +
                    "var isDojoComplete = typeof(window.dojo) != 'function' || window.dojo.io.XMLHTTPTransport.inFlight.length == 0;" +
                    "var result = docReady && isJqueryComplete && isPrototypeComplete && isDojoComplete;" +
                    "return result;";

    static private final String JS_ANIMATION_COMPLETION = "var anim = 0; var anim = $(':animated'); return anim.length == 0";

    static private final String JS_ANGULAR_COMPLETION =
            "if (window.angular) { \n" +
                    "  var el = document.getElementsByClassName('ng-scope');\n" +
                    "  if (angular.element(el).injector()) {\n" +
                    "    return angular.element(el).injector().get('$http').pendingRequests.length;\n" +
                    "  }\n" +
                    "}\n" +
                    "return -1;";

    private static final String XPATH_ANGULAR_PLACEHOLDERS = ".//*[contains(text(), '{{')]";

    private static final String XPATH_CURRENT_PROCESS_GIF = ".//img[contains(@src, 'loading.gif')]";

    /**
     * Wait while element is present by locator
     * Please use waitForElementIsPresentByLocator(By, int) instead of this method
     *
     * @param driver  - object of Seelenium Webdriver
     * @param locator - locator with By object
     * @param timeout - time im ms for wait condition
     */
    @Deprecated
    public static void waitForElementIsPresentByLocator(WebDriver driver, By locator, final int timeout) {
        (new WebDriverWait(driver, timeout / 1000))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementIsPresentByLocator(By locator, final int timeoutSec) {
        (new WebDriverWait(driver, timeoutSec)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementIsPresent(WebElement element, final int timeoutSec) {
        (new WebDriverWait(driver, timeoutSec)).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementIsAbsentByLocator(By locator, final int timeoutSec) {
        (new WebDriverWait(driver, timeoutSec)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitForElementIsAbsent(WebElement element, final int timeoutSec) {
        (new WebDriverWait(driver, timeoutSec)).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }

    /**
     * Performs delay until all background processes are finished, page is rendered.
     * Please use waitForPageUpdate(int timeout) instead of this method
     *
     * @param timeout - waiting timeout in milliseconds
     */
    @Deprecated
    public static void waitForPageUpdate(WebDriver driver, int timeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, timeout, 10);
        wait.until(isPageUpdated);
        wait.until(isAngularFinished);
    }

    public static void waitForPageUpdate(int timeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, timeout, 10);
        wait.until(isPageUpdated);
        wait.until(isAngularFinished);
        waitForElementIsAbsentByLocator(By.xpath(XPATH_ANGULAR_PLACEHOLDERS), 10);
        waitForElementIsAbsentByLocator(By.xpath(XPATH_CURRENT_PROCESS_GIF), 10);
    }

    public static void waitForPageUpdate(int timeout, int sleepInMillis) {
        Wait<WebDriver> wait = new WebDriverWait(driver, timeout, sleepInMillis);
        wait.until(isPageUpdated);
        wait.until(isAngularFinished);
        waitForElementIsAbsentByLocator(By.xpath(XPATH_ANGULAR_PLACEHOLDERS), 10);
        waitForElementIsAbsentByLocator(By.xpath(XPATH_CURRENT_PROCESS_GIF), 10);
    }


    public static void waitForJSCompletionAndElementIsClickable(WebElement e, int timeoutJS, int timeoutClickable) {
        try {
            waitForPageUpdate(timeoutJS);
        } catch (TimeoutException ex) {
            waitForElementToBeClickable(e, timeoutClickable);
        }
        waitForElementToBeClickable(e, timeoutClickable);
    }

    public static void waitForJSCompletionAndElementIsPresent(By locator, int timeoutJS, int timeoutPresence) {
        try {
            waitForPageUpdate(timeoutJS);
        } catch (TimeoutException ex) {
            waitForElementIsPresentByLocator(locator, timeoutPresence);
        }
        waitForElementIsPresentByLocator(locator, timeoutPresence);
    }

    public static WebDriver getDriver() {

        return driver;
    }

    public static void waitForElementToBeClickable(final WebElement e, final int timeout) {
        new WebDriverWait(driver, timeout / 1000).until(ExpectedConditions.elementToBeClickable(e));
    }

    public static void waitForElementToBeClickable(final By locator, final int timeoutSec) {
        WebElement element = driver.findElement(locator);
        waitForElementToBeClickable(element, timeoutSec * 1000);
    }

    public static void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", element);
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", element);
    }

    private static final ExpectedCondition<Object> isPageUpdated = new ExpectedCondition<Object>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            boolean ready = Boolean.parseBoolean(js.executeScript(JS_AJAX_COMPLETION).toString());
            return ready;
        }
    };

    private static final ExpectedCondition<Object> isAnimated = new ExpectedCondition<Object>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            return Boolean.parseBoolean(js.executeScript(JS_ANIMATION_COMPLETION).toString());
        }
    };

        private static final ExpectedCondition<Object> isAngularFinished = new ExpectedCondition<Object>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            String result = js.executeScript(JS_ANGULAR_COMPLETION).toString();
            return "0".equals(result) || "-1".equals(result);
        }
    };

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
