package com.epam.webdriver.aleksandrgostev.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class JSExecutorUtils {

    private JSExecutorUtils() {
    }

    public static void clickElement(WebDriver driver, WebElement webElement) {
        executeScript(driver, webElement, "arguments[0].click();");
    }

    public static void scrollIntoView(WebDriver driver, WebElement webElement) {
        executeScript(driver, webElement, "arguments[0].scrollIntoView();");
    }

    private static void executeScript(WebDriver driver, WebElement webElement, String action) {
        ((JavascriptExecutor) driver).executeScript(action, webElement);
    }
}