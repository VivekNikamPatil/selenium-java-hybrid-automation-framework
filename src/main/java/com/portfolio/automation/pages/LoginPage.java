
package com.portfolio.automation.pages;

import com.portfolio.automation.locators.LoginLocators;
import com.portfolio.automation.utils.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String BASE_URL =
            "https://www.saucedemo.com/";

    public LoginPage() {

        this.driver = DriverFactory.getDriver();

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    public void launchApplication() {

        driver.get(BASE_URL);
    }

    public void enterUsername(String username) {

        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LoginLocators.USERNAME
                )
        );

        usernameField.clear();

        usernameField.sendKeys(username);

        wait.until(
                ExpectedConditions.attributeToBe(
                        LoginLocators.USERNAME,
                        "value",
                        username
                )
        );
    }

    public void enterPassword(String password) {

        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LoginLocators.PASSWORD
                )
        );

        passwordField.clear();

        passwordField.sendKeys(password);

        wait.until(
                ExpectedConditions.attributeToBe(
                        LoginLocators.PASSWORD,
                        "value",
                        password
                )
        );
    }

    public void clickLoginButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        LoginLocators.LOGIN_BUTTON
                )
        ).click();
    }

    public boolean isProductsPageDisplayed() {

        WebElement productsTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LoginLocators.PRODUCTS_TITLE
                )
        );

        return productsTitle.getText()
                .equalsIgnoreCase("Products");
    }

    public boolean isLoginErrorDisplayed() {

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LoginLocators.ERROR_MESSAGE
                )
        );

        return errorMessage.isDisplayed();
    }
}
