
package com.portfolio.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    private static final String CHROMEDRIVER_PATH =
            "C:\\WebDriver\\chromedriver.exe";

    private DriverFactory() {
    }

    public static void initializeDriver() {

        if (DRIVER.get() != null) {
            throw new IllegalStateException(
                    "WebDriver is already initialized.");
        }

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");

        // Disable Chrome Password Manager features
        Map<String, Object> preferences = new HashMap<>();

        preferences.put(
                "credentials_enable_service",
                false
        );

        preferences.put(
                "profile.password_manager_enabled",
                false
        );

        preferences.put(
                "profile.password_manager_leak_detection",
                false
        );

        options.setExperimentalOption(
                "prefs",
                preferences
        );

        File driverFile = new File(CHROMEDRIVER_PATH);

        WebDriver driver;

        if (driverFile.isFile()) {

            System.out.println(
                    "Using locally installed ChromeDriver: "
                            + driverFile.getAbsolutePath()
            );

            ChromeDriverService service =
                    new ChromeDriverService.Builder()
                            .usingDriverExecutable(driverFile)
                            .build();

            driver = new ChromeDriver(service, options);

        } else {

            System.out.println(
                    "Local ChromeDriver not found. "
                            + "Using Selenium Manager."
            );

            driver = new ChromeDriver(options);
        }

        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {

        WebDriver driver = DRIVER.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized.");
        }

        return driver;
    }

    public static void quitDriver() {

        WebDriver driver = DRIVER.get();

        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DRIVER.remove();
        }
    }
}
