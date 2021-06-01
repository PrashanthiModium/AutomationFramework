package com.gui.cucumber.Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;

public class Driver {
    private static File file;
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setup(String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
            file = new File("tool/geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            file = new File("tool/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("IE")) {
            file = new File("tool/IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        } else {
            System.out.println("browser is not specified");
        }
    }
}
