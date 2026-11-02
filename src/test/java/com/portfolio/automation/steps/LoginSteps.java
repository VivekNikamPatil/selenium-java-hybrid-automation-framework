
package com.portfolio.automation.steps;

import com.portfolio.automation.pages.LoginPage;
import com.portfolio.automation.utils.ScreenshotUtils;

import io.cucumber.java.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage;
    private Scenario scenario;

    @Before
    public void initializeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    private void attachScreenshot(String screenshotName) {

        byte[] screenshot = ScreenshotUtils.captureScreenshot();

        if (screenshot.length > 0) {
            scenario.attach(
                    screenshot,
                    "image/png",
                    screenshotName
            );
        }
    }

    @Given("user launches the SauceDemo application")
    public void userLaunchesSauceDemoApplication() {

        loginPage = new LoginPage();

        loginPage.launchApplication();

        attachScreenshot("Application Launched");
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {

        loginPage.enterUsername(username);

        attachScreenshot("Username Entered");
    }

    @When("user enters password {string}")
    public void userEntersPassword(String password) {

        loginPage.enterPassword(password);

        attachScreenshot("Password Entered");
    }

    @When("user clicks on login button")
    public void userClicksLoginButton() {

        loginPage.clickLoginButton();

        attachScreenshot("Login Button Clicked");
    }

    @Then("user should be redirected to products page")
    public void userShouldBeRedirectedToProductsPage() {

        try {

            Assert.assertTrue(
                    loginPage.isProductsPageDisplayed(),
                    "Login failed: Products page is not displayed."
            );

        } finally {

            attachScreenshot("Products Page Verification");
        }
    }

    @Then("user should see login error message")
    public void userShouldSeeLoginErrorMessage() {

        try {

            Assert.assertTrue(
                    loginPage.isLoginErrorDisplayed(),
                    "Expected login error message is not displayed."
            );

        } finally {

            attachScreenshot("Login Error Verification");
        }
    }
}
