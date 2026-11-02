
package com.portfolio.automation.steps;

import com.portfolio.automation.pages.CheckoutPage;
import com.portfolio.automation.utils.ScreenshotUtils;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.testng.Assert;

public class CheckoutSteps {

    private CheckoutPage checkoutPage;
    private Scenario scenario;

    @Before
    public void initializeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    private CheckoutPage getCheckoutPage() {

        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage();
        }

        return checkoutPage;
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

    @When("user clicks checkout button")
    public void userClicksCheckoutButton() {

        getCheckoutPage().clickCheckoutButton();

        attachScreenshot("Checkout Information Page Opened");
    }

    @When("user enters checkout first name {string}")
    public void userEntersCheckoutFirstName(String firstName) {

        getCheckoutPage().enterFirstName(firstName);

        attachScreenshot("Checkout First Name Entered");
    }

    @When("user enters checkout last name {string}")
    public void userEntersCheckoutLastName(String lastName) {

        getCheckoutPage().enterLastName(lastName);

        attachScreenshot("Checkout Last Name Entered");
    }

    @When("user enters checkout postal code {string}")
    public void userEntersCheckoutPostalCode(String postalCode) {

        getCheckoutPage().enterPostalCode(postalCode);

        attachScreenshot("Checkout Postal Code Entered");
    }

    @When("user clicks continue button")
    public void userClicksContinueButton() {

        getCheckoutPage().clickContinueButton();

        attachScreenshot("Checkout Continue Button Clicked");
    }

    @Then("checkout overview page should be displayed")
    public void checkoutOverviewPageShouldBeDisplayed() {

        try {

            Assert.assertTrue(
                    getCheckoutPage().isCheckoutOverviewDisplayed(),
                    "Checkout overview page is not displayed."
            );

        } finally {

            attachScreenshot("Checkout Overview Verification");
        }
    }

    @Then("checkout overview should contain {string}")
    public void checkoutOverviewShouldContainProduct(String productName) {

        try {

            Assert.assertTrue(
                    getCheckoutPage().isProductPresentInOverview(productName),
                    "Product not found in checkout overview: " + productName
            );

        } finally {

            attachScreenshot(
                    "Checkout Overview Product Verification - " + productName
            );
        }
    }

    @When("user clicks finish button")
    public void userClicksFinishButton() {

        getCheckoutPage().clickFinishButton();

        attachScreenshot("Checkout Finish Button Clicked");
    }

    @Then("checkout success message should be displayed")
    public void checkoutSuccessMessageShouldBeDisplayed() {

        try {

            Assert.assertTrue(
                    getCheckoutPage().isCheckoutSuccessful(),
                    "Checkout success message is not displayed."
            );

        } finally {

            attachScreenshot("Checkout Success Verification");
        }
    }

    @Then("checkout error message should contain {string}")
    public void checkoutErrorMessageShouldContain(String expectedMessage) {

        try {

            String actualMessage =
                    getCheckoutPage().getCheckoutErrorMessage();

            Assert.assertTrue(
                    actualMessage.contains(expectedMessage),
                    "Checkout error mismatch. Expected: "
                            + expectedMessage
                            + ", Actual: "
                            + actualMessage
            );

        } finally {

            attachScreenshot("Checkout Error Message Verification");
        }
    }

    @When("user clicks cancel checkout button")
    public void userClicksCancelCheckoutButton() {

        getCheckoutPage().clickCancelButton();

        attachScreenshot("Checkout Cancel Button Clicked");
    }

    @Then("shopping cart page should be displayed")
    public void shoppingCartPageShouldBeDisplayed() {

        try {

            Assert.assertTrue(
                    getCheckoutPage().isShoppingCartPageDisplayed(),
                    "Shopping cart page is not displayed after cancellation."
            );

        } finally {

            attachScreenshot("Shopping Cart Page Verification");
        }
    }
}
