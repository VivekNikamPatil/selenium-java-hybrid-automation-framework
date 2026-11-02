
package com.portfolio.automation.pages;

import com.portfolio.automation.locators.CheckoutLocators;
import com.portfolio.automation.utils.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutPage() {

        this.driver = DriverFactory.getDriver();

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    public void clickCheckoutButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        CheckoutLocators.CHECKOUT_BUTTON
                )
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CheckoutLocators.FIRST_NAME
                )
        );
    }

    public void enterFirstName(String firstName) {

        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CheckoutLocators.FIRST_NAME
                )
        );

        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        wait.until(
                ExpectedConditions.attributeToBe(
                        CheckoutLocators.FIRST_NAME,
                        "value",
                        firstName
                )
        );
    }

    public void enterLastName(String lastName) {

        WebElement lastNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CheckoutLocators.LAST_NAME
                )
        );

        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        wait.until(
                ExpectedConditions.attributeToBe(
                        CheckoutLocators.LAST_NAME,
                        "value",
                        lastName
                )
        );
    }

    public void enterPostalCode(String postalCode) {

        WebElement postalCodeField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CheckoutLocators.POSTAL_CODE
                )
        );

        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);

        wait.until(
                ExpectedConditions.attributeToBe(
                        CheckoutLocators.POSTAL_CODE,
                        "value",
                        postalCode
                )
        );
    }

    public void clickContinueButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        CheckoutLocators.CONTINUE_BUTTON
                )
        ).click();
    }

    public boolean isCheckoutOverviewDisplayed() {

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CheckoutLocators.OVERVIEW_TITLE
                )
        );

        return title.getText()
                .equalsIgnoreCase("Checkout: Overview");
    }

    public boolean isProductPresentInOverview(String productName) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CheckoutLocators.overviewProductName(productName)
                )
        ).isDisplayed();
    }

    public void clickFinishButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        CheckoutLocators.FINISH_BUTTON
                )
        ).click();
    }

    public boolean isCheckoutSuccessful() {

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CheckoutLocators.COMPLETE_TITLE
                )
        );

        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CheckoutLocators.SUCCESS_MESSAGE
                )
        );

        return title.getText()
                .equalsIgnoreCase("Checkout: Complete!")
                && successMessage.getText()
                .equalsIgnoreCase("Thank you for your order!");
    }

    public String getCheckoutErrorMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CheckoutLocators.CHECKOUT_ERROR
                )
        ).getText().trim();
    }

    public void clickCancelButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        CheckoutLocators.CANCEL_BUTTON
                )
        ).click();
    }

    public boolean isShoppingCartPageDisplayed() {

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        CheckoutLocators.CART_TITLE
                )
        );

        return title.getText()
                .equalsIgnoreCase("Your Cart");
    }
}
