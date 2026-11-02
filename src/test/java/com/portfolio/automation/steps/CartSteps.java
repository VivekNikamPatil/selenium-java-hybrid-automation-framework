
package com.portfolio.automation.steps;

import com.portfolio.automation.pages.CartPage;
import com.portfolio.automation.utils.ScreenshotUtils;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.testng.Assert;

public class CartSteps {

    private CartPage cartPage;
    private Scenario scenario;

    @Before
    public void initializeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    private CartPage getCartPage() {

        if (cartPage == null) {
            cartPage = new CartPage();
        }

        return cartPage;
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

    @When("user adds {string} to cart")
    public void userAddsProductToCart(String productName) {

        getCartPage().addProductToCart(productName);

        attachScreenshot("Product Added - " + productName);
    }

    @Then("cart badge count should be {int}")
    public void cartBadgeCountShouldBe(int expectedCount) {

        try {

            int actualCount = getCartPage().getCartBadgeCount();

            Assert.assertEquals(
                    actualCount,
                    expectedCount,
                    "Cart badge count mismatch."
            );

        } finally {

            attachScreenshot("Cart Badge Count Verification");
        }
    }

    @When("user opens shopping cart")
    public void userOpensShoppingCart() {

        getCartPage().openShoppingCart();

        attachScreenshot("Shopping Cart Opened");
    }

    @Then("cart should contain {string}")
    public void cartShouldContainProduct(String productName) {

        try {

            Assert.assertTrue(
                    getCartPage().isProductPresentInCart(productName),
                    "Product not found in cart: " + productName
            );

        } finally {

            attachScreenshot("Cart Product Verification - " + productName);
        }
    }

    @When("user removes {string} from cart")
    public void userRemovesProductFromCart(String productName) {

        getCartPage().removeProductFromCart(productName);

        attachScreenshot("Product Removed - " + productName);
    }

    @Then("cart badge should not be displayed")
    public void cartBadgeShouldNotBeDisplayed() {

        try {

            Assert.assertFalse(
                    getCartPage().isCartBadgeDisplayed(),
                    "Cart badge is still displayed."
            );

        } finally {

            attachScreenshot("Cart Badge Absence Verification");
        }
    }

    @Then("shopping cart should be empty")
    public void shoppingCartShouldBeEmpty() {

        try {

            Assert.assertTrue(
                    getCartPage().isShoppingCartEmpty(),
                    "Shopping cart is not empty."
            );

        } finally {

            attachScreenshot("Empty Cart Verification");
        }
    }
}
