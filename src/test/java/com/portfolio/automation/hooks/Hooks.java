
package com.portfolio.automation.hooks;

import com.portfolio.automation.utils.DriverFactory;
import com.portfolio.automation.utils.ScreenshotUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp() {

        DriverFactory.initializeDriver();

        System.out.println("Browser launched successfully.");
    }

    @After
    public void tearDown(Scenario scenario) {

        try {

            if (scenario.isFailed()) {

                byte[] screenshot =
                        ScreenshotUtils.captureScreenshot();

                if (screenshot.length > 0) {

                    scenario.attach(
                            screenshot,
                            "image/png",
                            "Failure Screenshot"
                    );
                }
            }

        } finally {

            DriverFactory.quitDriver();

            System.out.println("Browser closed successfully.");
        }
    }
}
