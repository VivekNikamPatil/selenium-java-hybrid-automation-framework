
package com.portfolio.automation.locators;

import org.openqa.selenium.By;

public final class LoginLocators {

    private LoginLocators() {
    }

    public static final By USERNAME =
            By.xpath("//input[@id='user-name']");

    public static final By PASSWORD =
            By.xpath("//input[@id='password']");

    public static final By LOGIN_BUTTON =
            By.xpath("//input[@id='login-button']");

    public static final By ERROR_MESSAGE =
            By.xpath("//h3[@data-test='error']");

    public static final By PRODUCTS_TITLE =
            By.xpath("//span[@data-test='title']");
}
