
package com.portfolio.automation.locators;

import org.openqa.selenium.By;

public final class CartLocators {

    private CartLocators() {
    }

    public static final By SHOPPING_CART_LINK =
            By.xpath("//a[@data-test='shopping-cart-link']");

    public static final By SHOPPING_CART_BADGE =
            By.xpath("//span[@data-test='shopping-cart-badge']");

    public static final By CART_ITEMS =
            By.xpath("//div[@data-test='inventory-item']");

    public static final By CART_PRODUCT_NAMES =
            By.xpath("//div[@data-test='inventory-item-name']");

    public static final By CART_TITLE =
            By.xpath("//span[@data-test='title']");

    public static By addToCartButton(String productName) {

        String productId = productName
                .toLowerCase()
                .replace(" ", "-");

        return By.xpath(
                "//button[@data-test='add-to-cart-"
                        + productId + "']"
        );
    }

    public static By removeFromCartButton(String productName) {

        String productId = productName
                .toLowerCase()
                .replace(" ", "-");

        return By.xpath(
                "//button[@data-test='remove-"
                        + productId + "']"
        );
    }

    public static By cartProductName(String productName) {

        return By.xpath(
                "//div[@data-test='inventory-item-name' and text()='"
                        + productName + "']"
        );
    }
}
