package com.demoproject.gui.cucumber.Deployment.Acceptance.Helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;

import java.util.HashMap;

public class Driver {
    private static File file;
    private static DesiredCapabilities caps;
    private static WebDriver driver;

    public Driver() {
    }

    public static WebDriver getDriver() {
        return driver;
    }
    public static void setWebdriver(String BROWSER, Boolean withSaveDialog) {
        String var2 = BROWSER.toUpperCase().trim();
        byte var3 = -1;
        switch(var2.hashCode()) {
            case 2149:
                if(var2.equals("CH")) {
                    var3 = 2;
                }
                break;
            case 2240:
                if(var2.equals("FF")) {
                    var3 = 0;
                }
                break;
            case 2332:
                if(var2.equals("IE")) {
                    var3 = 1;
                }
                break;
            case 2643:
                if(var2.equals("SF")) {
                    var3 = 3;
                }
        }

        switch(var3) {
            case 0:
                System.setProperty("webdriver.gecko.driver", "tools/geckodriver.exe");
                File pathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
                DesiredCapabilities desired = DesiredCapabilities.firefox();
                FirefoxOptions foptions = new FirefoxOptions();
                desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, foptions.setBinary(firefoxBinary));
                desired.setCapability("marionette", true);
                FirefoxProfile FFP = new FirefoxProfile();
                FFP.setPreference("security.enable_java", true);
                FFP.setPreference("plugin.state.java", 2);
                FFP.setPreference("extensions.blocklist.enabled", false);
                if(withSaveDialog.booleanValue()) {
                    FFP.setPreference("browser.download.useDownloadDir", false);
                    FFP.setPreference("browser.helperApps.alwaysAsk.force", true);


                }
                desired.setCapability(FirefoxDriver.PROFILE, FFP);
                driver = new FirefoxDriver(desired);
                break;
            case 1:
                file = new File("tools/IEDriverServer.exe");
                caps = DesiredCapabilities.internetExplorer();
                System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                caps.setCapability("ignoreZoomSetting", true);
                caps.setCapability("nativeEvents", false);
               // caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                        true);
                caps.setJavascriptEnabled(true);
                driver = new InternetExplorerDriver(caps);
                break;
            case 2:
                caps = DesiredCapabilities.chrome();
                if(withSaveDialog.booleanValue()) {
                    HashMap prefs = new HashMap();
                    prefs.put("download.prompt_for_download", Boolean.valueOf(true));
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", prefs);
                    caps.setCapability("chromeOptions", options);
                }

                file = new File("tool/chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                caps.setCapability("ignoreZoomSetting", true);
                caps.setCapability("nativeEvents", false);
                driver = new ChromeDriver(caps);
                break;
            case 3:
                driver = new SafariDriver();
                break;
            default:
                driver = new FirefoxDriver();
        }

    }
}
