
package com.portfolio.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static byte[] captureScreenshot() {

        try {

            WebDriver driver = DriverFactory.getDriver();

            return ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

        } catch (IllegalStateException
                 | WebDriverException exception) {

            System.out.println(
                    "Screenshot capture failed: "
                    + exception.getMessage()
            );

            return new byte[0];
        }
    }
}
