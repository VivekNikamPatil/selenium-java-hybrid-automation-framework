
package com.portfolio.automation.locators;

import org.openqa.selenium.By;

public final class CheckoutLocators {

    private CheckoutLocators() {
    }

    // Cart page
    public static final By CHECKOUT_BUTTON =
            By.xpath("//button[@data-test='checkout']");

    public static final By CART_TITLE =
            By.xpath("//span[@data-test='title']");

    // Checkout information page
    public static final By FIRST_NAME =
            By.xpath("//input[@data-test='firstName']");

    public static final By LAST_NAME =
            By.xpath("//input[@data-test='lastName']");

    public static final By POSTAL_CODE =
            By.xpath("//input[@data-test='postalCode']");

    public static final By CONTINUE_BUTTON =
            By.xpath("//input[@data-test='continue']");

    public static final By CHECKOUT_ERROR =
            By.xpath("//h3[@data-test='error']");

    public static final By CANCEL_BUTTON =
            By.xpath("//button[@data-test='cancel']");

    // Checkout overview page
    public static final By OVERVIEW_TITLE =
            By.xpath("//span[@data-test='title']");

    public static final By FINISH_BUTTON =
            By.xpath("//button[@data-test='finish']");

    public static By overviewProductName(String productName) {
        return By.xpath(
                "//div[@data-test='inventory-item-name' and text()='"
                        + productName + "']"
        );
    }

    // Checkout completion page
    public static final By COMPLETE_TITLE =
            By.xpath("//span[@data-test='title']");

    public static final By SUCCESS_MESSAGE =
            By.xpath("//h2[@data-test='complete-header']");
}
