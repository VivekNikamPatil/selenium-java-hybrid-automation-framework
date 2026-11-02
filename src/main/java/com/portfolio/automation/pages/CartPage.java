
package com.portfolio.automation.pages;

import com.portfolio.automation.locators.CartLocators;
import com.portfolio.automation.utils.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CartPage() {

        this.driver = DriverFactory.getDriver();

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    public void addProductToCart(String productName) {

        WebElement addButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        CartLocators.addToCartButton(productName)
                )
        );

        addButton.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CartLocators.removeFromCartButton(productName)
                )
        );
    }

    public void removeProductFromCart(String productName) {

        WebElement removeButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        CartLocators.removeFromCartButton(productName)
                )
        );

        removeButton.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CartLocators.addToCartButton(productName)
                )
        );
    }

    public void openShoppingCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        CartLocators.SHOPPING_CART_LINK
                )
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CartLocators.CART_TITLE
                )
        );
    }

    public int getCartBadgeCount() {

        try {

            WebElement badge = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            CartLocators.SHOPPING_CART_BADGE
                    )
            );

            return Integer.parseInt(badge.getText().trim());

        } catch (NoSuchElementException exception) {

            return 0;
        }
    }

    public boolean isCartBadgeDisplayed() {

        List<WebElement> badges = driver.findElements(
                CartLocators.SHOPPING_CART_BADGE
        );

        return !badges.isEmpty()
                && badges.get(0).isDisplayed();
    }

    public boolean isProductPresentInCart(String productName) {

        List<WebElement> products = driver.findElements(
                CartLocators.cartProductName(productName)
        );

        return !products.isEmpty()
                && products.get(0).isDisplayed();
    }

    public boolean isShoppingCartEmpty() {

        List<WebElement> cartItems = driver.findElements(
                CartLocators.CART_ITEMS
        );

        return cartItems.isEmpty();
    }
}
